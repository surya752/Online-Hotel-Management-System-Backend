package com.example.MangerMicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private int id;
    @NotNull
    @Min(0)
    private int roomNo;
    @NotNull
    @Min(0)
    private int children;
    @NotNull
    @Min(1)
    private int adults;
    @NotNull
    @Size(min = 1, message = " Room should have atleast 1 Number")
    private String checkindate;
    @NotNull
    private String checkoutdate;
}