package com.example.ReceptionistMicroservice;

import com.example.ReceptionistMicroservice.entity.Room;
import com.example.ReceptionistMicroservice.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

@SpringBootTest
class ReceptionistMicroserviceApplicationTests {


			@Autowired

			RoomRepository roomRepository;

	@Test
	public  void saveRoom(){
		Room room=Room.builder()
				.id(20)
				.roomNo(7)
				.roomType("family")
				.roomCost(5000)
				.isOccupied("avalibale")
				.checkIn("25/08/2022")
				.checkOut("26/08/2022")
				.build();
		roomRepository.save(room);
		Assertions.assertThat(room.getId()).isGreaterThan(0);

	}
	@Test
	public  void updateRoom(){
		Room room=roomRepository.findById(8L).get();
		room.setRoomType("single");
		room.setIsOccupied("Booked");
		Room roomupdated=roomRepository.save(room);
		Assertions.assertThat(roomupdated.getRoomType()).isEqualTo("single");
		Assertions.assertThat(roomupdated.getIsOccupied()).isEqualTo("Booked");
	}
	@Test
	public  void getListRooms(){
		List<Room> rooms=roomRepository.findAll();
		Assertions.assertThat(rooms.size()).isGreaterThan(0);

	}
	@Test
	public  void getRoomById(){
		Room room=roomRepository.findById(1L).get();
		Assertions.assertThat(room.getId()).isEqualTo(1);
	}
	@Test
	public void deleteEmployee(){
		Room room=roomRepository.findById(20l).get();
		roomRepository.delete(room);

		Room room1=null;

		Optional<Room> optionalRoom=roomRepository.findById(15L);

		if(optionalRoom.isPresent()){
			room1=optionalRoom.get();
		}
		Assertions.assertThat(room1).isNull();

	}




}


