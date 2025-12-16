package com.example.cinemate.service;

import com.example.cinemate.dto.PurchaseItem;
import com.example.cinemate.dto.PurchaseRequest;
import com.example.cinemate.entities.FNBItem;
import com.example.cinemate.entities.Location;
import com.example.cinemate.repository.FNBItemRepository;
import com.example.cinemate.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FNBItemService {

    private final FNBItemRepository fnbRepo;
    private final LocationRepository locationRepo;

    private static final List<FNBItem> DEFAULT_ITEMS = List.of(
            new FNBItem("Burger", 0, null),
            new FNBItem("Hotdog", 0, null),
            new FNBItem("Fried Chicken", 0, null),
            new FNBItem("Rice Bowl", 0, null),

            new FNBItem("Cola", 0, null),
            new FNBItem("Mineral Water", 0, null),
            new FNBItem("Milk Tea", 0, null),
            new FNBItem("Orange Juice", 0, null),

            new FNBItem("Popcorn", 0, null),
            new FNBItem("Nachos", 0, null),
            new FNBItem("French Fries", 0, null),
            new FNBItem("Potato Wedges", 0, null)
    );

    /** Load all menu for a location; if not exist yet, initialize default */
    public List<FNBItem> getFnbByLocation(Long locationId) {
        List<FNBItem> items = fnbRepo.findByLocationId(locationId);

        if (items.isEmpty()) {
            Location loc = locationRepo.findById(locationId)
                    .orElseThrow(() -> new RuntimeException("Location not found"));

            List<FNBItem> created = DEFAULT_ITEMS.stream()
                    .map(i -> new FNBItem(i.getName(), 0, loc))
                    .toList();

            return fnbRepo.saveAll(created);
        }

        return items;
    }

    /** Update quantity only */
    public FNBItem updateQuantity(Long id, Integer quantity) {
        FNBItem item = fnbRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FNB item not found"));

        item.setQuantity(quantity);
        return fnbRepo.save(item);
    }

    public void purchase(PurchaseRequest req) {

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
        }
    }
}
