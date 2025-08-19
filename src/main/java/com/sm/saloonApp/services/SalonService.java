package com.sm.saloonApp.services;

import com.sm.saloonApp.dto.SaloonDTO;
import com.sm.saloonApp.entity.Saloon;
import com.sm.saloonApp.repository.SalonRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class SalonService {

    @Autowired
    SalonRepo salonRepo;

    public List<Saloon> getAllSalon(){

        return salonRepo.findAll();
    }

    public Saloon getSalonByID(Long id){
        return salonRepo
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Saloon not found"));
    }

    public Saloon saveSaloon(SaloonDTO salonDTO){

        Saloon saloon = new Saloon();
        saloon.setName(salonDTO.getName());
        saloon.setDescription(salonDTO.getDescription());
        saloon.setLocation(salonDTO.getLocation());
        saloon.setImageUrl(salonDTO.getImageUrl());
        return  salonRepo.save(saloon);
    }

    public Saloon updateSaloon(Long id, SaloonDTO salonDTO){
        Saloon saloon = getSalonByID(id);
        saloon.setName(salonDTO.getName());
        saloon.setDescription(salonDTO.getDescription());
        saloon.setLocation(salonDTO.getLocation());
        saloon.setImageUrl(salonDTO.getImageUrl());
        return  salonRepo.save(saloon);
    }

    public void deleteSaloon(Long id){
         salonRepo.deleteById(id);
    }



}
