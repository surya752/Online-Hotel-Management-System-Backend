package com.example.ReceptionistMicroservice.repository;

import com.example.ReceptionistMicroservice.entity.IssueBills;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueBillRepository extends MongoRepository<IssueBills,Long> {
}
