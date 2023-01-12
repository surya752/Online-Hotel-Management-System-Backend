package com.example.MangerMicroservice.repository;



import com.example.MangerMicroservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository  extends MongoRepository<Employee,Long> {

}
