package com.example.ReceptionistMicroservice.repository;

import com.example.ReceptionistMicroservice.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,Long> {
    Boolean existsByroomNo(int roomNo);
}
