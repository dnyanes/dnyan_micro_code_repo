package com.ds.hotel_service.controller;

import com.ds.hotel_service.entities.Hotel;
import com.ds.hotel_service.services.HotelService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create

    //@PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<@NonNull Hotel> createHotel(@RequestBody Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }


    //get single
    //@PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<@NonNull Hotel> getHotelById(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }


    //get all
   // @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<@NonNull List<Hotel>> getAllHotel(){
        return ResponseEntity.ok(hotelService.getAll());
    }


}
