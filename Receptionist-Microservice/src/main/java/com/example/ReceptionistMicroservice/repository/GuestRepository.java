package com.example.ReceptionistMicroservice.repository;

import com.example.ReceptionistMicroservice.entity.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends MongoRepository<Guest,Long> {
}
