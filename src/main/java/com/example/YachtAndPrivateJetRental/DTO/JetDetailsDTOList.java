package com.example.YachtAndPrivateJetRental.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JetDetailsDTOList {
    List<JetDetailsDTO> jetDetailsDTOS;
    int currentPage;
    int totalElements;
    int totalPages;
}
