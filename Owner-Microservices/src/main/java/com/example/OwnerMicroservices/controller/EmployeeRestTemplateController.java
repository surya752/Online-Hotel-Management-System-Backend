package com.example.OwnerMicroservices.controller;

import com.example.OwnerMicroservices.entity.Employee;
import com.example.OwnerMicroservices.entity.Room;
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
public class EmployeeRestTemplateController {


    @Autowired
    private RestTemplate restTemplate;

    static  final String RESERVATION_URL="http://localhost:8082/Manager/";

    @GetMapping("/restemployee/{id}")
    public String fetchroom(@PathVariable(value = "id") int id){
        return restTemplate.exchange(RESERVATION_URL+"employee/"+id, HttpMethod.GET,null,String.class).getBody();

    }
    @GetMapping("/rest/listemployees")
    public String fetchallEmployees(){
        return restTemplate.exchange(RESERVATION_URL+"employee/list",HttpMethod.GET,null,String.class).getBody();
    }
    @PostMapping("rest/employeesave")
    public String addEmployee(@Valid @RequestBody Employee employee){
        return restTemplate.postForObject(RESERVATION_URL+"employee/save",employee,String.class);
    }
    @PutMapping("/rest/updateemployee/{id}")
    public String updateEmpoyee(@PathVariable(value = "id") int id, @RequestBody Employee employee ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee,headers);

        return restTemplate.exchange(
                RESERVATION_URL+"employee/edit/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }
    @DeleteMapping("/rest/deleteemployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id, @RequestBody Employee employee ) {

        return restTemplate.exchange(
                RESERVATION_URL+"employee/delete/"+id, HttpMethod.DELETE, null, String.class).getBody();
    }
}
