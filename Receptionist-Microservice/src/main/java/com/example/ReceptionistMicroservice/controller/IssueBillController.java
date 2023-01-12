package com.example.ReceptionistMicroservice.controller;



import com.example.ReceptionistMicroservice.entity.IssueBills;


import com.example.ReceptionistMicroservice.exception.ResourceNotFoundException;
import com.example.ReceptionistMicroservice.repository.IssueBillRepository;
import com.example.ReceptionistMicroservice.service.SequenceGeneratorService;
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
@RequestMapping("/Receptionist")
public class IssueBillController {
    @Autowired
    private IssueBillRepository issueBillRepository;
    @Autowired
    private SequenceGeneratorService service;

    @PostMapping("/billsave")
    public IssueBills saveBill(@Valid @RequestBody IssueBills issueBills){
        issueBills.setBillNo(service.getSequenceNumber(IssueBills.SEQUENCE_NAME));
         return this.issueBillRepository.save(issueBills);
    }

    @GetMapping("/billlist")
    public List<IssueBills> gettingBills(){
         return this.issueBillRepository.findAll();
    }
    @GetMapping("bill/{id}")
    public ResponseEntity<IssueBills> getBillById(@PathVariable(value="id") Long billId)throws ResourceNotFoundException {
        IssueBills issueBills=issueBillRepository.findById(billId)
                .orElseThrow(()->new ResourceNotFoundException("bill  not found:: "+billId));
        return ResponseEntity.ok().body(issueBills);
    }

    @PutMapping("editbill/{id}")
    public ResponseEntity<IssueBills> updateBill(@PathVariable(value = "id") Long  billId,
                                                 @Validated @RequestBody IssueBills issuebillDetails  )
            throws ResourceNotFoundException {
        IssueBills issueBills = issueBillRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("bill not found for this id :: " +billId ));
        issueBills.setRoomNo(issuebillDetails.getRoomNo());
        issueBills.setPrice(issuebillDetails.getPrice());
        issueBills.setTaxes(issuebillDetails.getTaxes());
        issueBills.setDate(issuebillDetails.getDate());
        issueBills.setService(issuebillDetails.getService());
        issueBills.setTotal(issuebillDetails.getTotal());

        return ResponseEntity.ok(this.issueBillRepository.save(issueBills));
    }

    @DeleteMapping("deletebill/{id}")
    public Map<String,Boolean> deleteBill(@PathVariable(value = "id") Long  billId)throws ResourceNotFoundException {
        IssueBills issueBills=issueBillRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("bill not found for this id :: " +billId ));

        this.issueBillRepository.delete(issueBills);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }


}
