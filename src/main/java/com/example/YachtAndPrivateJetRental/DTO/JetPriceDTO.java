package com.example.YachtAndPrivateJetRental.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JetPriceDTO {
    private String duration;

    private double price;
}
