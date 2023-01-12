package com.example.OwnerMicroservices.controller;


import com.example.OwnerMicroservices.entity.Payment;
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
public class PaymentRestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    static  final String RESERVATION_URL="http://localhost:8084/Receptionist/";

    @GetMapping("/restpayment/{id}")
    public String fetchPayment(@PathVariable(value = "id") int id){
        return restTemplate.exchange(RESERVATION_URL+"payment/"+id, HttpMethod.GET,null,String.class).getBody();

    }
    @GetMapping("/restlistpayment")
    public String fetchallpayment(){
        return restTemplate.exchange(RESERVATION_URL+"payment/list",HttpMethod.GET,null,String.class).getBody();
    }

    @PostMapping("/restpaymentave")
    public String addPayment( @Valid @RequestBody Payment payment){
        return restTemplate.postForObject(RESERVATION_URL+"payment/save",payment,String.class);
    }

    @PutMapping("/updatepayment/{id}")
    public String updatePayment(@PathVariable(value = "id") int id, @RequestBody Payment payment ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Payment> entity = new HttpEntity<Payment>(payment,headers);

        return restTemplate.exchange(
                RESERVATION_URL+"payment/edit/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping("/deletepayment/{id}")
    public String deleteRoom(@PathVariable(value = "id") int id, @RequestBody Payment Payment ) {

        return restTemplate.exchange(
                RESERVATION_URL+"payment/delete/"+id, HttpMethod.DELETE, null, String.class).getBody();
    }

}
