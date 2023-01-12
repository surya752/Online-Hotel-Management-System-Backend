package com.example.ReceptionistMicroservice.controller;

import com.example.ReceptionistMicroservice.entity.Guest;
import com.example.ReceptionistMicroservice.exception.ResourceNotFoundException;
import com.example.ReceptionistMicroservice.repository.GuestRepository;
import com.example.ReceptionistMicroservice.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/Receptionist")
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private SequenceGeneratorService service;

    @PostMapping("/guest/save")
    public Guest createGuest( @Valid @RequestBody Guest guest){
        guest.setId(service.getSequenceNumber(Guest.SEQUENCE_NAME));
        return this.guestRepository.save(guest);
    }

    @GetMapping("/guest/list")

    public List<Guest> getguestlist(){
        return this.guestRepository.findAll();
    }
    @GetMapping("guest/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable(value="id") Long guestId)throws ResourceNotFoundException {
        Guest guest=guestRepository.findById(guestId)
                .orElseThrow(()->new ResourceNotFoundException("guest  not found:: "+guestId));
        return ResponseEntity.ok().body(guest);
    }
    @PutMapping("guest/edit/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable(value = "id") Long  guestId,
                                                 @Validated @RequestBody Guest guestDetails  )
            throws ResourceNotFoundException {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("guest not found for this id :: " +guestId ));
       guest.setRoomNo(guestDetails.getRoomNo());
       guest.setName(guestDetails.getName());
       guest.setPhoneNo(guestDetails.getPhoneNo());
       guest.setEmail(guestDetails.getEmail());
       guest.setGender(guestDetails.getGender());
       guest.setAddress(guestDetails.getAddress());

        return ResponseEntity.ok(this.guestRepository.save(guest));
    }

    @DeleteMapping("guest/delete/{id}")
    public Map<String,Boolean> deleteGuest(@PathVariable(value = "id") Long  guestId)throws ResourceNotFoundException {
        Guest guest=guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("guest not found for this id :: " +guestId ));

        this.guestRepository.delete(guest);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }
}
