package com.example.cinemate.dto;

import com.example.cinemate.entities.FnbType;
import lombok.Data;

@Data
public class FNBItemRequest {
    private String name;
    private String description;
    private Long price;
    private FnbType type;
    private Integer quantity;
    private String imageUrl;
    private Long locationId;
}
