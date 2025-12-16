package com.example.cinemate.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequest {
    private Long locationId;
    private List<PurchaseItem> items;
}

