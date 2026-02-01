package com.ds.hotel_service.repositories;

import com.ds.hotel_service.entities.Hotel;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<@NonNull Hotel, @NonNull String> {
}
