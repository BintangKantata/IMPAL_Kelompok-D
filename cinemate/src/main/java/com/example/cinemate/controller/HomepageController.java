package com.example.cinemate.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/homepage")
public class HomepageController {

    // Endpoint untuk lihat list film + filter
    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    // Endpoint untuk lihat detail film
    @GetMapping("/films/{id}")
    public String filmDetail(@PathVariable Long id) {
        return "Detail Film dengan ID: " + id;
    }

    // Tambahkan endpoint lain seperti pencarian film dsb.
}