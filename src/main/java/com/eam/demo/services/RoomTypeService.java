package com.eam.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eam.demo.models.RoomType;
import com.eam.demo.repository.IRoomTypeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

    public List<RoomType> findAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public Optional<RoomType> findRoomTypeById(long id) {
        return roomTypeRepository.findById(id);
    }

    public RoomType createRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public RoomType updateRoomType(long id, RoomType roomTypeDetails) {
        return roomTypeRepository.findById(id)
                .map(roomType -> {
                    roomType.setRoomTypeName(roomTypeDetails.getRoomTypeName());
                    return roomTypeRepository.save(roomType);
                })
                .orElseThrow(() -> new RuntimeException("RoomType not found with id " + id));
    }

    public boolean deleteRoomType(long id) {
        return roomTypeRepository.findById(id)
                .map(roomType -> {
                    roomTypeRepository.delete(roomType);
                    return true;
                })
                .orElse(false);
    }
}
