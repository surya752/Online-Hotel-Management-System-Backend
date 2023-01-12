package com.example.OwnerMicroservices.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "InventoryReports")
public class InventoryReports {

    @Transient
    public static final  String SEQUENCE_NAME="user_sequence";


    @Id

    private int id;
    @NotNull
    private Long totalincome;
    @NotNull
    private Long maintainanceCost;
    @NotNull
    private Long employeesalary;
    @NotNull
    private Long totalProfit;


}