package com.example.ReceptionistMicroservice.controller;



import com.example.ReceptionistMicroservice.entity.Room;
import com.example.ReceptionistMicroservice.exception.ResourceNotFoundException;
import com.example.ReceptionistMicroservice.payload.response.MessageResponse;
import com.example.ReceptionistMicroservice.repository.RoomRepository;
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
public class RoomController {
    @Autowired
    private SequenceGeneratorService service;

    @Autowired
    private RoomRepository roomRepository;

//
//    @PostMapping("/room/save")
//    public Room createRoom(@RequestBody Room room){
//        room.setId(service.getSequenceNumber(Room.SEQUENCE_NAME));
//        return this.roomRepository.save(room);
//    }
@PostMapping("/room/save")
public ResponseEntity<?> createRoom(@Valid @RequestBody Room room){
    if (roomRepository.existsByroomNo(room.getRoomNo())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse(" room is already taken or booked!"));
    }
    room.setId(service.getSequenceNumber(Room.SEQUENCE_NAME));
    // create new Room
    Room rooms=new Room(room.getId(),room.getRoomNo(),room.getRoomType(),room.getIsOccupied(),room.getRoomCost(),room.getCheckIn(),room.getCheckOut());

    return ResponseEntity.ok().body(this.roomRepository.save(rooms));
}

    @GetMapping("/room/list")
    public List<Room> getRoomlist(){
         return  this.roomRepository.findAll();
    }

    @GetMapping("room/{id}")
    public ResponseEntity<Room> getroomById(@PathVariable(value="id") Long roomId)throws ResourceNotFoundException {
        Room room=roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("room  not found:: "+roomId));
        return ResponseEntity.ok().body(room);
    }
    @PutMapping("room/edit/{id}")
    public ResponseEntity<Room> updatePayment(@PathVariable(value = "id") Long  roomId,
                                                 @Validated @RequestBody Room roomDetails  )
            throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("room not found for this id :: " +roomId ));
       room.setRoomNo(roomDetails.getRoomNo());
       room.setRoomType(roomDetails.getRoomType());
       room.setIsOccupied(roomDetails.getIsOccupied());
       room.setRoomCost(roomDetails.getRoomCost());
       room.setCheckIn(roomDetails.getCheckIn());
       room.setCheckOut(roomDetails.getCheckOut());
        return ResponseEntity.ok(this.roomRepository.save(room));
    }

    @DeleteMapping("room/delete/{id}")
    public Map<String,Boolean> deleteroom(@PathVariable(value = "id") Long  roomId)throws ResourceNotFoundException {
        Room room=roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("room not found for this id :: " +roomId ));

        this.roomRepository.delete(room);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }




}
