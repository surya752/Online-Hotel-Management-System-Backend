package com.example.MangerMicroservice.repository;



import com.example.MangerMicroservice.entity.InventoryReports;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryReportsRepository extends MongoRepository<InventoryReports,Long> {

}
