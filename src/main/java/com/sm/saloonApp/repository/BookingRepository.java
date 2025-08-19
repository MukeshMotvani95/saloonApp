package com.sm.saloonApp.repository;

import com.sm.saloonApp.entity.Booking;
import com.sm.saloonApp.entity.Saloon;
import com.sm.saloonApp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUser(Users user);
    List<Booking> findBySalon(Saloon saloon);

    boolean existsBySalonAndAppointmentTime(Saloon salon, LocalDateTime time);

}
