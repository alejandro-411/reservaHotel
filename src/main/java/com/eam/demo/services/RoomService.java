package com.eam.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eam.demo.models.Room;
import com.eam.demo.repository.IRoomRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private IRoomRepository roomRepository;

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> findAllRoomsForBooking(Date endDate) {
        return roomRepository.findAvailableRoomsForDate(endDate);
    }

    public Optional<Room> findRoomById(long id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(long id, Room roomDetails) {
        return roomRepository.findById(id)
                .map(room -> {
                    room.setRoomNumber(roomDetails.getRoomNumber());
                    room.setPricePerNight(roomDetails.getPricePerNight());
                    room.setAvalability(roomDetails.isAvalability());
                    room.setAmenitiesDetails(roomDetails.getAmenitiesDetails());
                    room.setRoomType(roomDetails.getRoomType());
                    room.setBooking(roomDetails.getBooking());
                    room.setHotel(roomDetails.getHotel());
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new RuntimeException("Room not found with id " + id));
    }

    public boolean deleteRoom(long id) {
        return roomRepository.findById(id)
                .map(room -> {
                    roomRepository.delete(room);
                    return true;
                })
                .orElse(false);
    }
    
    public Double totalPaymentForRooms(List<Long> roomList){
        List <Room> rooms = this.roomRepository.findAllById(roomList);

        double total = rooms.stream()
                            .mapToDouble(Room::getPricePerNight)
                            .sum();

        return total;
        }
}
