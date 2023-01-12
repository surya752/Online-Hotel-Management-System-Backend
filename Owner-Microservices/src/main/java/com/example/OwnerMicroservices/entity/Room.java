package com.example.OwnerMicroservices.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Room")
public class Room {
    @Transient
    public static final  String SEQUENCE_NAME="user_sequence";
    @Id
    private int id;
    @NotNull

    private int roomNo;
    @NotNull
    @Size(min = 1, message = " Room should have atleast 1 Number")
    private String roomType;
    @NotNull
    private String isOccupied;
    @NotNull
    @Min(1)
    private int roomCost;
    private String checkIn;
    private String checkOut;

}