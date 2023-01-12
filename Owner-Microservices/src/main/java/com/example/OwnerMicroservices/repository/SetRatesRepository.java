package com.example.OwnerMicroservices.repository;

import com.example.OwnerMicroservices.entity.SetRates;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SetRatesRepository  extends MongoRepository<SetRates,Long> {
}
