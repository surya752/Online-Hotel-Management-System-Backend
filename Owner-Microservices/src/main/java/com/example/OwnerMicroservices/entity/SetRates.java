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
@Document(collection = "SetRates")
public class SetRates {

    @Transient
    public static final  String SEQUENCE_NAME="user_sequence";

    @Id
    private int id;

    @NotNull
    @Min(1)
    private  int guest;
    @NotNull
    @Min(1)
    private int days;
    @NotNull
    @Min(1)
    private int nightPrice;
    @NotNull
    private int extension;

}
