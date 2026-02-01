package com.ds.hotel_service.controller;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffControler {

    @GetMapping
    public ResponseEntity<@NonNull List<String>> getStaffs(){

        List<String> staffList = Arrays.asList("Ram", "Shyam", "Sita", "Krishna");

        return ResponseEntity.ok(staffList);
    }
}
