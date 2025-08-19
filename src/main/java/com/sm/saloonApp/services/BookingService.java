package com.sm.saloonApp.services;

import com.sm.saloonApp.dto.BookingRequest;
import com.sm.saloonApp.entity.Booking;
import com.sm.saloonApp.entity.Saloon;
import com.sm.saloonApp.entity.Users;
import com.sm.saloonApp.repository.BookingRepository;
import com.sm.saloonApp.repository.SalonRepo;
import com.sm.saloonApp.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final SalonRepo salonRepo;
    private final UserRepo userRepo;
    private final BookingRepository bookingRepository;

    public Booking createBooking(BookingRequest bookingRequest, String email) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Saloon saloon = salonRepo.findById(bookingRequest.getSalonId())
                .orElseThrow(() -> new RuntimeException("Salon not found"));

        if(bookingRepository.existsBySalonAndAppointmentTime(saloon,bookingRequest.getAppointmentTime())){
            throw new RuntimeException("Time Slot is already booked");
        }

        Booking booking = new Booking();

        booking.setSalon(saloon);
        booking.setUser(user);
        booking.setAppointmentTime(bookingRequest.getAppointmentTime());

        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(String userEmail){
        Users user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return bookingRepository.findByUser(user);
    }

    public List<Booking> getBookingsForSalon(Long salonId){

        Saloon saloon = salonRepo.findById(salonId)
                .orElseThrow(() -> new RuntimeException("Salon not found"));

        return bookingRepository.findBySalon(saloon);
    }

}
