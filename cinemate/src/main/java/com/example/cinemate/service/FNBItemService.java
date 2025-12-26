package com.example.cinemate.service;

import com.example.cinemate.dto.FNBItemRequest;
import com.example.cinemate.dto.PurchaseItem;
import com.example.cinemate.dto.PurchaseRequest;
import com.example.cinemate.entities.Booking;
import com.example.cinemate.entities.FNBItem;
import com.example.cinemate.entities.FnbOrder;
import com.example.cinemate.entities.Location;
import com.example.cinemate.repository.BookingRepository;
import com.example.cinemate.repository.FNBItemRepository;
import com.example.cinemate.repository.FnbOrderRepository;
import com.example.cinemate.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FNBItemService {

    private final FNBItemRepository fnbRepo;
    private final LocationRepository locationRepo;
    private final BookingRepository bookingRepo;
    private final FnbOrderRepository fnbOrderRepo;

    public List<FNBItem> getByLocation(Long locationId) {
        return fnbRepo.findByLocationId(locationId);
    }

    public FNBItem create(FNBItemRequest req) {
        Location loc = locationRepo.findById(req.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        FNBItem item = new FNBItem();
        item.setName(req.getName());
        item.setDescription(req.getDescription());
        item.setPrice(req.getPrice());
        item.setType(req.getType());
        item.setQuantity(req.getQuantity());
        item.setImageUrl(req.getImageUrl());
        item.setLocation(loc);

        return fnbRepo.save(item);
    }

    public FNBItem update(Long id, FNBItemRequest req) {
        FNBItem item = fnbRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FNB item not found"));

        if (!item.getLocation().getId().equals(req.getLocationId())) {
            throw new RuntimeException("Item does not belong to this location");
        }

        item.setName(req.getName());
        item.setDescription(req.getDescription());
        item.setPrice(req.getPrice());
        item.setType(req.getType());
        item.setQuantity(req.getQuantity());
        item.setImageUrl(req.getImageUrl());

        return fnbRepo.save(item);
    }

    public FNBItem updateQuantity(Long id, Integer quantity) {
        FNBItem item = fnbRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FNB item not found"));

        item.setQuantity(quantity);
        return fnbRepo.save(item);
    }

    public void delete(Long id) {
        if (!fnbRepo.existsById(id)) {
            throw new RuntimeException("FNB item not found");
        }
        fnbRepo.deleteById(id);
    }

    @Transactional
    public void purchase(PurchaseRequest req) {

        Booking booking = bookingRepo.findById(req.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        for (PurchaseItem p : req.getItems()) {

            FNBItem item = fnbRepo.findById(p.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            if (!item.getLocation().getId().equals(req.getLocationId())) {
                throw new RuntimeException("Item does not belong to this location");
            }

            if (item.getQuantity() < p.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + item.getName());
            }

            item.setQuantity(item.getQuantity() - p.getQuantity());
            fnbRepo.save(item);

            FnbOrder order = new FnbOrder();
            order.setBooking(booking);
            order.setItem(item);
            order.setQuantity(p.getQuantity());

            fnbOrderRepo.save(order);
        }
    }
}
