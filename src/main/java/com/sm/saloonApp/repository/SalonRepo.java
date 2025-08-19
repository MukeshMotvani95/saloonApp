package com.sm.saloonApp.repository;

import com.sm.saloonApp.entity.Saloon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalonRepo extends JpaRepository<Saloon,Long> {
}
