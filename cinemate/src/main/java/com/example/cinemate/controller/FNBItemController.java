package com.example.cinemate.controller;

import com.example.cinemate.dto.FNBItemRequest;
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

    @GetMapping("/location/{locationId}")
    public List<FNBItem> getByLocation(@PathVariable Long locationId) {
        return fnbService.getByLocation(locationId);
    }

    @PostMapping
    public FNBItem create(@RequestBody FNBItemRequest req) {
        return fnbService.create(req);
    }

    @PutMapping("/{id}")
    public FNBItem update(@PathVariable Long id, @RequestBody FNBItemRequest req) {
        return fnbService.update(id, req);
    }

    @PutMapping("/{id}/quantity")
    public FNBItem updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return fnbService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fnbService.delete(id);
    }

    @PutMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody PurchaseRequest req) {
        fnbService.purchase(req);
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}
