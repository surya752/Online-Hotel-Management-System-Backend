package com.example.OwnerMicroservices.controller;

import com.example.OwnerMicroservices.entity.Guest;

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
public class GuestRestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    static final String RESERVATION_URL = "http://localhost:8084/Receptionist/";

    @GetMapping("restguest/{id}")
    public String fetchguest(@PathVariable(value = "id") int id) {
        return restTemplate.exchange(RESERVATION_URL + "guest/" + id, HttpMethod.GET, null, String.class).getBody();

    }

    @GetMapping("/restlistguest")
    public String fetchallguest(){
        return restTemplate.exchange(RESERVATION_URL+"guest/list",HttpMethod.GET,null,String.class).getBody();
    }
    @PostMapping("/restguestsave")
    public String addPayment(@Valid @RequestBody Guest guest){
        return restTemplate.postForObject(RESERVATION_URL+"guest/save",guest,String.class);
    }
    @PutMapping("/updateguest/{id}")
    public String updateguest(@PathVariable(value = "id") int id, @RequestBody Guest guest ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Guest> entity = new HttpEntity<Guest>(guest,headers);

        return restTemplate.exchange(
                RESERVATION_URL+"guest/edit/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }
    @DeleteMapping("/deleteguest/{id}")
    public String deleteRoom(@PathVariable(value = "id") int id, @RequestBody Guest Guest ) {

        return restTemplate.exchange(
                RESERVATION_URL+"guest/delete/"+id, HttpMethod.DELETE, null, String.class).getBody();
    }
}
