package com.example.YachtAndPrivateJetRental.ServiceImpl;

import com.example.YachtAndPrivateJetRental.DTO.JetDetailsDTO;
import com.example.YachtAndPrivateJetRental.DTO.JetDetailsDTOList;
import com.example.YachtAndPrivateJetRental.DTO.JetPriceDTO;
import com.example.YachtAndPrivateJetRental.Enums.FleetStatus;
import com.example.YachtAndPrivateJetRental.Model.*;
import com.example.YachtAndPrivateJetRental.Repository.*;
import com.example.YachtAndPrivateJetRental.Request.JetPriceDurationRequest;
import com.example.YachtAndPrivateJetRental.Request.JetRequest;
import com.example.YachtAndPrivateJetRental.Service.JetService;
import com.example.YachtAndPrivateJetRental.Util.CustomException;
import com.example.YachtAndPrivateJetRental.Util.Formatter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JetServiceImpl implements JetService {
    public static final String MESSAGE = "Successful";

    @Autowired
    EntityManager entityManager;
    @Autowired
    ManufacturerRepo manufacturerRepo;

    @Autowired
    TransportationRepo transportationRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    JetRepo jetRepo;

    @Autowired
    OwnerRepo ownerRepo;

    @Autowired
    ServiceJetRepo serviceJetRepo;

    @Autowired
    AmenityJetRepo amenityJetRepo;

    @Autowired
    ServiceRepo serviceRepo;

    @Autowired
    AmenityRepo amenityRepo;

    @Autowired
    ImageJetRepo imageJetRepo;

    @Autowired
    JetPriceRepo jetPriceRepo;

    @Autowired
    DurationRepo durationRepo;

    @Autowired
    JetLocationRepo jetLocationRepo;
    int count;

    @Override
    public String save(JetRequest request, List<MultipartFile> multipartFiles) {
        requestValidation(request,multipartFiles);
        Jet jet= jetRepo.save(toJet(request));
        saveJetAmenities(request,jet);
        saveJetServices(request, jet);
        saveJetImage(multipartFiles,jet);
        saveJetPrice(request,jet);
        saveJetLocation(request,jet);
        return MESSAGE;
    }
    @Override
    public String update(JetRequest request,int id) {
        return MESSAGE;
    }

    @Override
    public String delete(int id) {
        return MESSAGE;
    }

    @Override
    public JetDetailsDTOList findAll(String category, String ownerName, String jetName, String manufacturer, String sortByManufacturedYear, int page, int size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Jet> query = cb.createQuery(Jet.class);
        Root<Jet> jetRoot = query.from(Jet.class);
        Join<Jet, Manufacturer> manufacturerJoin = jetRoot.join("manufacturer", JoinType.LEFT);
        Join<Jet, Owner> ownerJoin = jetRoot.join("owner", JoinType.LEFT);
        Join<Jet, Category> categoryJoin = jetRoot.join("category", JoinType.LEFT);
        query.select(jetRoot);
        List<Predicate> predicates = new ArrayList<>();
        if (ownerName != null && !ownerName.isEmpty()) {
            predicates.add(cb.like(cb.lower(ownerJoin.get("name")), "%" + ownerName.toLowerCase() + "%"));
        }
        if (category != null && !category.isEmpty()) {
            predicates.add(cb.equal(cb.lower(categoryJoin.get("category")), category.toLowerCase()));
        }
        if (jetName != null && !jetName.isEmpty()) {
            predicates.add(cb.like(cb.lower(jetRoot.get("name")), "%" + ownerName.toLowerCase() + "%"));
        }
        if (manufacturer != null && !manufacturer.isEmpty()) {
            predicates.add(cb.like(cb.lower(manufacturerJoin.get("name")), "%" + manufacturer.toLowerCase() + "%"));
        }
        if (sortByManufacturedYear != null && !sortByManufacturedYear.isEmpty()) {
            if (sortByManufacturedYear.equals("year")) {
                query.orderBy(cb.asc(jetRoot.get("manufacturedDate")));
            } else if (sortByManufacturedYear.equals("-year")) {
                query.orderBy(cb.desc(jetRoot.get("manufacturedDate")));
            }
        }
        List<Order> orderList = new ArrayList<>();
        orderList.add(cb.asc(jetRoot.get("name")));
        query.orderBy(orderList);
        Predicate[] predicateArr = new Predicate[predicates.size()];
        Predicate predicate = cb.and(predicates.toArray(predicateArr));
        query = query.where(predicate);
        List<Jet> jets = entityManager.createQuery(query).getResultList();
        TypedQuery<Jet> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((page - 1) * size);
        typedQuery.setMaxResults(size);
        int currentPage = page - 1;
        List<Jet> paginatedJets = typedQuery.getResultList();
        int totalElements = jets.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        List<JetDetailsDTO> jetDetailsDTOS= getJetDetailDTOs(paginatedJets);
        return toJetDetailsDTOList(jetDetailsDTOS,currentPage,totalPages,totalElements);
    }

    private JetDetailsDTOList toJetDetailsDTOList(List<JetDetailsDTO> jetDetailsDTOS, int currentPage, int totalPages, int totalElements) {
        return JetDetailsDTOList.builder()
                .jetDetailsDTOS(jetDetailsDTOS)
                .currentPage(currentPage)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }

    private List<JetDetailsDTO> getJetDetailDTOs(List<Jet> paginatedJets) {
        List<JetDetailsDTO> jetDetailsDTOS= new ArrayList<>();
        for (Jet jet:paginatedJets
             ) {
            jetDetailsDTOS.add(toJetDetailDTO(jet));
        }
        return  jetDetailsDTOS;
    }

    private JetDetailsDTO toJetDetailDTO(Jet jet) {
        return JetDetailsDTO.builder()
                .jetDescription(jet.getJetDescription())
                .altitude(jet.getAltitude())
                .fleetStatus(jet.getFleetStatus())
                .fuelConsumption(jet.getFuelConsumption())
                .capacity(jet.getCapacity())
                .manufacturedDate(Formatter.convertDateToStr(jet.getManufacturedDate(),"yy-mm-dd"))
                .manufacturer(jet.getManufacturer().getName())
                .category(jet.getCategory().getCategory())
                .maximumRange(jet.getMaximumRange())
                .maximumSpeed(jet.getMaximumSpeed())
                .name(jet.getName())
                .owner(jet.getOwner().getName())
                .amenities(getJetAmenities(jet))
                .services(getJetServices(jet))
                .pricing(getJetPricing(jet))
                .imagePaths(getJetImages(jet)).build();
    }

    private List<String> getJetImages(Jet jet) {
        List<String> jetImages= new ArrayList<>();
        List<ImageJet> imageJets= imageJetRepo.findByJet(jet);
        if (!imageJets.isEmpty()){
            for (ImageJet imageJet: imageJets
                 ) {
                jetImages.add(imageJet.getImagePath());
            }
        }
        else
            throw new NullPointerException("Jet Images Not Found");
        return  jetImages;
    }

    private List<JetPriceDTO> getJetPricing(Jet jet) {
        List<JetPriceDTO> jetPrices= new ArrayList<>();
        List<JetPrice> jetPriceList= jetPriceRepo.findByJet(jet);
        if (!jetPriceList.isEmpty()){
            for (JetPrice jetPrice: jetPriceList
            ) {
                jetPrices.add(toJetPriceDTO(jetPrice));
            }
        }
        else
            throw new NullPointerException("JetPricing Not Found");
        return  jetPrices;
    }

    private JetPriceDTO toJetPriceDTO(JetPrice jetPrice) {
        return JetPriceDTO.builder()
                .price(jetPrice.getPrice())
                .duration(jetPrice.getDuration().getDuration())
                .build();
    }

    private List<String> getJetServices(Jet jet) {
        List<String> jetServices= new ArrayList<>();
        List<ServiceJet> serviceJets= serviceJetRepo.findByJet(jet);
        if (!serviceJets.isEmpty()){
            for (ServiceJet serviceJet: serviceJets
            ) {
                jetServices.add(serviceJet.getServices().getService());
            }
        }
        else
            throw new NullPointerException("Jet Services Not Found");
        return  jetServices;
    }

    private List<String> getJetAmenities(Jet jet) {
        List<String> jetAmenities= new ArrayList<>();
        List<AmenityJet> amenityJets= amenityJetRepo.findByJet(jet);
        if (!amenityJets.isEmpty()){
            for (AmenityJet amenityJet: amenityJets
            ) {
                jetAmenities.add(amenityJet.getAmenity().getAmenity());
            }
        }
        else
            throw new NullPointerException("Jet Amenities Not Found");
        return  jetAmenities;
    }

    private void saveJetImage(List<MultipartFile> multipartFiles, Jet jet) {
        count=0;
        for (MultipartFile multipartFile: multipartFiles
        ) {
            validate(multipartFile);
            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (fileName.contains(".php%00.")){
                throw new CustomException(CustomException.Type.INVALID_FILE_EXTENSION);
            }
            imageJetRepo.save(toImageJet(getImagePath(multipartFile,jet,fileName),jet,fileName));
            count++;
        }
    }

    private ImageJet toImageJet(String imagePath, Jet jet,String filename) {
        ImageJet imageJet= new ImageJet();
        imageJet.setImagePath(imagePath);
        imageJet.setJet(jet);
        imageJet.setFileName(filename);
        return imageJet;
    }
    private void saveJetServices(JetRequest request,Jet jet) {
        for (String service: request.getServices()
        ) {
            serviceJetRepo.save(toServiceJet(service,jet));
        }
    }

    private ServiceJet toServiceJet(String service, Jet jet) {
        ServiceJet serviceJet= new ServiceJet();
        serviceJet.setJet(jet);
        serviceJet.setServices(findService(service));
        return serviceJet;
    }

    private Services findService(String service) {
        Services services= new Services();
        Optional<Services> findServices= serviceRepo.findByService(service);
        if (findServices.isPresent()){
            services= findServices.get();
        }
        else
            throw new NullPointerException("Service is not present");
        return services;
    }

    private void saveJetAmenities(JetRequest request,Jet jet) {
        for (String amenity: request.getAmenities()
        ) {
            amenityJetRepo.save(toAmenityJet(amenity,jet));
        }
    }

    private AmenityJet toAmenityJet(String amenity, Jet jet) {
        AmenityJet amenityJet= new AmenityJet();
        amenityJet.setJet(jet);
        amenityJet.setAmenity(findAmenity(amenity));
        return amenityJet;
    }

    private Amenity findAmenity(String findAmenity) {
        Amenity amenity= new Amenity();
        Optional<Amenity> searchAmenity= amenityRepo.findByAmenity(findAmenity);
        if (searchAmenity.isPresent()){
            amenity= searchAmenity.get();
        }
        else
            throw new NullPointerException("Amenity is Not Present");
        return amenity;
    }

    private Jet toJet(JetRequest request) {
        Jet jet = new Jet();
        jet.setCategory(findCategory(request));
        jet.setName(request.getName());
        jet.setManufacturer(findManufacturer(request));
        jet.setOwner(findOwner(request));
        jet.setAltitude(request.getAltitude());
        jet.setCapacity(request.getCapacity());
        jet.setFuelConsumption(request.getFuelConsumption());
        jet.setManufacturedDate(Formatter.convertStrToDate(request.getManufacturedDate(),"yy-mm-dd"));
        jet.setMaximumRange(request.getMaximumRange());
        jet.setMaximumSpeed(request.getMaximumSpeed());
        jet.setFleetStatus(FleetStatus.AVAILABLE);
        jet.setJetDescription(request.getJetDescription());
        return jet;
    }

    private Owner findOwner(JetRequest request) {
        Owner owner= new Owner();
        Optional<Owner> findOwner = ownerRepo.findById(request.getOwnerId());
        if (findOwner.isPresent()){
            owner=findOwner.get();}
        else {
            throw new NullPointerException("Owner Not Found");
        }
        return  owner;
    }

    private Manufacturer findManufacturer(JetRequest request) {
        Manufacturer manufacturer= new Manufacturer();
        Optional<Manufacturer> findManufacturer= manufacturerRepo.findByName(request.getManufacturer());
        if (!findManufacturer.isPresent()){
            manufacturer=manufacturerRepo.save(toManufacturer(request));
        }else {
            manufacturer= findManufacturer.get();
        }
        return  manufacturer;
    }

    private Category findCategory(JetRequest request) {
        Category category = new Category();
        Optional<Category> findCategory= categoryRepo.findByCategory(request.getCategory());
        if (!findCategory.isPresent()){
            category=categoryRepo.save(toCategory(request));
        }else {
            category= findCategory.get();
        }
        return category;
    }

    private Category toCategory(JetRequest request) {
        Category category = new Category();
        category.setCategory(request.getCategory());
        category.setType(toTransportationType(request.getTransportationType()));
        return category;
    }

    private Manufacturer toManufacturer(JetRequest request) {
        Manufacturer manufacturer= new Manufacturer();
        manufacturer.setName(request.getManufacturer());
        manufacturer.setType(toTransportationType(request.getTransportationType()));
        return  manufacturer;
    }

    private Transportation toTransportationType(String transportationType) {
        Transportation transportation= new Transportation();
        Optional<Transportation> findTransportation= transportationRepo.findByType(transportationType);
        if (findTransportation.isPresent()){
            transportation= findTransportation.get();
        }
        else {
            throw new NullPointerException("Transportation Type Not Found");
        }
        return  transportation;
    }
    private void validate(MultipartFile multipartFile) {
        if (multipartFile.getSize()> 10000000)
            throw new CustomException(CustomException.Type.INVALID_FILE_SIZE);
        String extension= FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if(!checkFileExtension(extension))
            throw new CustomException(CustomException.Type.INVALID_FILE_EXTENSION);
        checkMimeType(multipartFile);
        if (!checkMimeType(multipartFile))
            throw new CustomException(CustomException.Type.INVALID_MIME_TYPE);
    }

    private boolean checkFileExtension(String extension) {
        return (extension != null && (extension.equals("png") || extension.equals("jpeg")||extension.equals("jpg")));
    }

    private boolean checkMimeType(MultipartFile multipartFile){
        Tika tika = new Tika();
        String mimeType;
        try {
            mimeType= tika.detect(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (mimeType.equals("image/png") || mimeType.equals("image/jpg") || mimeType.equals("image/jpeg"));

    }
    private String getImagePath(MultipartFile multipartFile,Jet jet,String fileName) {
        String uploadDirectory;
        if (count==0){
            uploadDirectory = "./images/"+ jet.getOwner().getName().replaceAll("\\s","")+"/jets/"+jet.getName()+"/root";
        }
        else {
            uploadDirectory = "./images/"+ jet.getOwner().getName().replaceAll("\\s","")+"/jets/"+jet.getName();
        }
        Path path = Paths.get(uploadDirectory);
        Path filePath= path.resolve(fileName);
        if(!Files.exists(path)){
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (InputStream inputStream= multipartFile.getInputStream()){
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath.toString().replace("./","/").trim();
    }
    private JetPrice toJetPrice(JetPriceDurationRequest priceDurationRequest, Jet jet) {
        JetPrice jetPrice= new JetPrice();
        jetPrice.setPrice(priceDurationRequest.getPrice());
        jetPrice.setJet(jet);
        jetPrice.setDuration(findDuration(priceDurationRequest.getDuration()));
        return  jetPrice;
    }

    private Duration findDuration(String durationRequest) {
        Duration duration= new Duration();
        Optional<Duration> findDuration= durationRepo.findByDuration(durationRequest);
        if (findDuration.isPresent()){
            duration= findDuration.get();
        }
        else {
            throw  new NullPointerException("Duration is Not present");
        }
        return duration;
    }
    private void requestValidation(JetRequest request, List<MultipartFile> multipartFiles) {
        if (request.getJetPricing().isEmpty())
            throw new CustomException(CustomException.Type.INVALID_JET_PRICE_REQUEST);
        if (multipartFiles.isEmpty())
            throw new CustomException(CustomException.Type.INVALID_IMAGE_REQUEST);
    }
    private void saveJetLocation(JetRequest request, Jet jet) {
        JetLocation jetLocation = toJetLocation(request, jet);
        jetLocationRepo.save(jetLocation);
    }

    private JetLocation toJetLocation(JetRequest request, Jet jet) {
        JetLocation jetLocation= new JetLocation();
        jetLocation.setJet(jet);
        jetLocation.setAddress(request.getAddress());
        jetLocation.setLat(request.getLat());
        jetLocation.setLon(request.getLon());
        return jetLocation;
    }

    private void saveJetPrice(JetRequest request, Jet jet) {
        for (JetPriceDurationRequest priceDurationRequest:request.getJetPricing()
        ) {
            jetPriceRepo.save(toJetPrice(priceDurationRequest,jet));
        }
    }
}
