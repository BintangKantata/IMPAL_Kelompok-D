package com.example.cinemate.controller;

import com.example.cinemate.dto.PurchaseRequest;
import com.example.cinemate.entities.FNBItem;
import com.example.cinemate.service.FNBItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fnb")
@RequiredArgsConstructor
public class FNBItemController {

    private final FNBItemService fnbService;

    @GetMapping("/{locationId}")
    public List<FNBItem> getFnb(@PathVariable Long locationId) {
        return fnbService.getFnbByLocation(locationId);
    }

    @PutMapping("/{itemId}")
    public FNBItem updateQuantity(
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {

        return fnbService.updateQuantity(itemId, quantity);
    }

    @PutMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody PurchaseRequest req) {
        fnbService.purchase(req);
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}