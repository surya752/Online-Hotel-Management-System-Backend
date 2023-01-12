package com.example.ReceptionistMicroservice.repository;

import com.example.ReceptionistMicroservice.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment,Long> {
}
