package com.sm.saloonApp.controller;

import com.sm.saloonApp.dto.SaloonDTO;
import com.sm.saloonApp.entity.Saloon;
import com.sm.saloonApp.repository.SalonRepo;
import com.sm.saloonApp.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salons")
public class SaloonController {

    @Autowired
    SalonService salonService;

    @GetMapping("AllSalon")
    public List<Saloon> getAllSalon(){
        return salonService.getAllSalon();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saloon> getSalon(@PathVariable Long id){

        return ResponseEntity.ok(salonService.getSalonByID(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Saloon> createSalon(@RequestBody SaloonDTO salon) {
        return ResponseEntity.ok(salonService.saveSaloon(salon));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Saloon> updateSalon(@PathVariable Long id, @RequestBody SaloonDTO dto) {
        return ResponseEntity.ok(salonService.updateSaloon(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSalon(@PathVariable Long id) {
        salonService.deleteSaloon(id);
        return ResponseEntity.noContent().build();
    }


}
