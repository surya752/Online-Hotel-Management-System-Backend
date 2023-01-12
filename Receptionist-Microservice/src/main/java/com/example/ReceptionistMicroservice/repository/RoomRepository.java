package com.example.ReceptionistMicroservice.repository;

import com.example.ReceptionistMicroservice.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface RoomRepository extends MongoRepository<Room,Long> {
    Boolean existsByroomNo(int roomNo);



}
