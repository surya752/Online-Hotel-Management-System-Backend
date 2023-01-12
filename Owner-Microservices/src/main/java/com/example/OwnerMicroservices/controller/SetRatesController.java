package com.example.OwnerMicroservices.controller;

import com.example.OwnerMicroservices.entity.SetRates;
import com.example.OwnerMicroservices.exception.ResourceNotFoundException;
import com.example.OwnerMicroservices.repository.SetRatesRepository;
import com.example.OwnerMicroservices.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/Owner")
public class SetRatesController {

    @Autowired
    private SetRatesRepository setRatesRepository;
    @Autowired
    private SequenceGeneratorService service;

    @PostMapping("/setratessave")
    public SetRates saverates(@Valid @RequestBody SetRates setRates){
        setRates.setId(service.getSequenceNumber(SetRates.SEQUENCE_NAME));
       return this.setRatesRepository.save(setRates);
    }
    @GetMapping("/setrateslist")

    public List<SetRates> getRatesList(){
       return this.setRatesRepository.findAll();
    }
    @GetMapping("/setrates/{id}")
    public ResponseEntity<SetRates> getrateById(@PathVariable(value="id") Long rateId)throws ResourceNotFoundException {
        SetRates setRates=setRatesRepository.findById(rateId)
                .orElseThrow(()->new ResourceNotFoundException("Rates Details  not found:: "+rateId));
        return ResponseEntity.ok().body(setRates);
    }
    @PutMapping("editsetrate/{id}")
    public ResponseEntity<SetRates> updaterate(@PathVariable(value = "id") Long  rateId,
                                              @Validated @RequestBody SetRates rateDetails  )
            throws ResourceNotFoundException {
        SetRates rate = setRatesRepository.findById(rateId)
                .orElseThrow(() -> new ResourceNotFoundException("rate not found for this id :: " +rateId ));
       rate.setGuest(rateDetails.getGuest());
       rate.setDays(rateDetails.getDays());
       rate.setNightPrice(rateDetails.getNightPrice());
       rate.setExtension(rateDetails.getExtension());
        return ResponseEntity.ok(this.setRatesRepository.save(rate));
    }

    @DeleteMapping("deletesetrate/{id}")
    public Map<String,Boolean> deleterate(@PathVariable(value = "id") Long  rateId)throws ResourceNotFoundException {
        SetRates rate=setRatesRepository.findById(rateId)
                .orElseThrow(() -> new ResourceNotFoundException("rate not found for this id :: " +rateId ));

        this.setRatesRepository.delete(rate);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }


}
