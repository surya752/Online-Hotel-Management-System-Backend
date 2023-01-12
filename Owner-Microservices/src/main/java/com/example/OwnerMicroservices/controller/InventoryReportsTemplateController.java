package com.example.OwnerMicroservices.controller;


import com.example.OwnerMicroservices.entity.InventoryReports;
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
public class InventoryReportsTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    static  final String RESERVATION_URL="http://localhost:8082/Manager/";

    @GetMapping("rest/inreports/{id}")
    public String fetchinventory(@PathVariable(value = "id") int id){
        return restTemplate.exchange(RESERVATION_URL+"inreports/"+id, HttpMethod.GET,null,String.class).getBody();

    }
    @GetMapping("rest/listinreports")
    public String fetchallinventory(){
        return restTemplate.exchange(RESERVATION_URL+"inreports/list",HttpMethod.GET,null,String.class).getBody();
    }
    @PostMapping("rest/inreportssave")
    public String addInventory(@Valid  @RequestBody InventoryReports inventoryReports){
        return restTemplate.postForObject(RESERVATION_URL+"inreports/save",inventoryReports,String.class);
    }
    @PutMapping("rest/updateinventory/{id}")
    public String updateinventory(@PathVariable(value = "id") int id, @RequestBody InventoryReports inventoryReports ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<InventoryReports> entity = new HttpEntity<InventoryReports>(inventoryReports,headers);

        return restTemplate.exchange(
                RESERVATION_URL+"inreports/edit/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }
    @DeleteMapping("/rest/deleteinventory/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id, @RequestBody InventoryReports inventoryReports ) {

        return restTemplate.exchange(
                RESERVATION_URL+"inreports/delete/"+id, HttpMethod.DELETE, null, String.class).getBody();
    }

}
