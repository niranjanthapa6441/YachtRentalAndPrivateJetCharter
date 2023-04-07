package com.example.YachtAndPrivateJetRental.Service;

import com.example.YachtAndPrivateJetRental.DTO.JetDetailsDTOList;
import com.example.YachtAndPrivateJetRental.Request.JetRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface JetService {

    public String save(JetRequest request, List<MultipartFile> multipartFiles);

    public String update(JetRequest request, int id);

    public String delete(int id);

    public JetDetailsDTOList findAll(String category, String ownerName, String jetName, String manufacturer, String sortByManufacturedYear, int page, int size);
}
