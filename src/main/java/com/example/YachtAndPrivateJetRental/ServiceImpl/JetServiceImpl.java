package com.example.YachtAndPrivateJetRental.ServiceImpl;

import com.example.YachtAndPrivateJetRental.DTO.JetDetailsDTO;
import com.example.YachtAndPrivateJetRental.Enums.FleetStatus;
import com.example.YachtAndPrivateJetRental.Model.*;
import com.example.YachtAndPrivateJetRental.Repository.*;
import com.example.YachtAndPrivateJetRental.Request.JetRequest;
import com.example.YachtAndPrivateJetRental.Service.JetService;
import com.example.YachtAndPrivateJetRental.Util.CustomException;
import com.example.YachtAndPrivateJetRental.Util.Formatter;
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
import java.util.List;
import java.util.Optional;

@Service
public class JetServiceImpl implements JetService {
    public static final String MESSAGE = "Successful";
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

    @Override
    public String save(JetRequest request, List<MultipartFile> multipartFiles) {
        Jet jet= jetRepo.save(toJet(request));
        saveJetAmenities(request,jet);
        saveJetServices(request, jet);
        saveJetImage(multipartFiles,jet);
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
    public JetDetailsDTO findAll() {
        return null;
    }
    private void saveJetImage(List<MultipartFile> multipartFiles, Jet jet) {
        for (MultipartFile multipartFile: multipartFiles
        ) {
            validate(multipartFile);
            String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (fileName.contains(".php%00.")){
                throw new CustomException(CustomException.Type.INVALID_FILE_EXTENSION);
            }
            imageJetRepo.save(toImageJet(getImagePath(multipartFile,jet,fileName),jet,fileName));
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
        String uploadDirectory= "./images/"+ jet.getOwner().getName().replaceAll("\\s","")+"/jets/"+jet.getName();
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
}
