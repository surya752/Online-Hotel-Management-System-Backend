package com.example.OwnerMicroservices.controller;


import com.example.OwnerMicroservices.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;

@CrossOrigin("*")
@RestController
@RequestMapping("/Owner")
public class ReservationRestTemplateController {



    @Autowired
    private RestTemplate restTemplate;

 static  final String RESERVATION_URL="http://localhost:8084/Receptionist/";

 @GetMapping("/find/{id}")
 public String fetchReservation(@PathVariable(value = "id") int id){
      return restTemplate.exchange(RESERVATION_URL+"reservation/"+id, HttpMethod.GET,null,String.class).getBody();

 }
 @GetMapping("/find")
 public String fetchReservation(){
     return restTemplate.exchange(RESERVATION_URL+"reservation/list",HttpMethod.GET,null,String.class).getBody();
 }
 @PostMapping("/restreservation")
 public String addReservation( @Valid @RequestBody Reservation reservation){
      return restTemplate.postForObject(RESERVATION_URL+"reservation/save",reservation,String.class);
 }

@PutMapping("/updatereservation/{id}")
public String updatereservation(@PathVariable(value = "id") int id, @RequestBody Reservation reservation ) {
 HttpHeaders headers = new HttpHeaders();
   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
   HttpEntity<Reservation> entity = new HttpEntity<Reservation>(reservation,headers);

    return restTemplate.exchange(
            RESERVATION_URL+"reservation/edit/"+id, HttpMethod.PUT, entity, String.class).getBody();
}

@DeleteMapping("/deletereservation/{id}")
public String deletereservation(@PathVariable(value = "id") int id, @RequestBody Reservation reservation ) {

    return restTemplate.exchange(
            RESERVATION_URL+"reservation/delete/"+id, HttpMethod.DELETE, null, String.class).getBody();
}

}
