package com.eam.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eam.demo.models.Room;

public interface IRoomRepository  extends JpaRepository<Room, Long>{

    @Query("SELECT r FROM Room r LEFT JOIN r.booking b WHERE b IS NULL OR b.reservationDepartureDate < :date")
    List<Room> findAvailableRoomsForDate(@Param("date") Date date);
    
}
