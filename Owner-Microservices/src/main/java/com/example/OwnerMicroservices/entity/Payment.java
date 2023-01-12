package com.example.OwnerMicroservices.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Payment")
public class Payment {
    @Transient
    public static final  String SEQUENCE_NAME="user_sequence";

    @Id

    private int id;
    @NotNull
    @Size(min = 1, message = " Occuption should have atleast 1 characters")
    private int roomNo;
    @NotNull
    @Size(min = 12, message = " Occuption should have atleast 12 characters")
    private Long creditcard;
    @NotNull
    @Size(min = 1, message = " Occuption should have atleast 1 characters")
    private int total;
    @NotNull

    private String paytime;


}
