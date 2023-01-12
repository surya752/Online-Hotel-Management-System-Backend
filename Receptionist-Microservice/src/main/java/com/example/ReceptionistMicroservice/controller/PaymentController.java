package com.example.ReceptionistMicroservice.controller;


import com.example.ReceptionistMicroservice.entity.Payment;
import com.example.ReceptionistMicroservice.exception.ResourceNotFoundException;
import com.example.ReceptionistMicroservice.repository.PaymentRepository;
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
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private SequenceGeneratorService service;


    //save payment
    @PostMapping("/payment/save")
    public Payment savePayment(@Valid @RequestBody Payment payment ){
        payment.setId(service.getSequenceNumber(Payment.SEQUENCE_NAME));
        return this.paymentRepository.save(payment);
    }

    //getting list of payment
    @GetMapping("/payment/list")
    public List<Payment> getReservation(){
        return this.paymentRepository.findAll();
    }
    //getting payment by id
    @GetMapping("payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable(value="id") Long paymentId)throws ResourceNotFoundException {
        Payment payment=paymentRepository.findById(paymentId)
                .orElseThrow(()->new ResourceNotFoundException("payment  not found:: "+paymentId));
        return ResponseEntity.ok().body(payment);
    }

    //edit Reservation
    @PutMapping("payment/edit/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable(value = "id") Long  paymentId,
                                                         @Validated @RequestBody Payment paymentDetails  )
            throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("payment not found for this id :: " +paymentId ));
        payment.setRoomNo(paymentDetails.getRoomNo());
        payment.setCreditcard(paymentDetails.getCreditcard());
        payment.setTotal(paymentDetails.getTotal());
        payment.setPaytime(paymentDetails.getPaytime());


        return ResponseEntity.ok(this.paymentRepository.save(payment));
    }

    //delete payment

    @DeleteMapping("payment/delete/{id}")
    public Map<String,Boolean> deletepayment(@PathVariable(value = "id") Long  paymentId)throws ResourceNotFoundException {
        Payment payment=paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("payment not found for this id :: " +paymentId ));

        this.paymentRepository.delete(payment);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }


}
