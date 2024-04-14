package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Rol;
import com.eam.demo.models.RoomType;

public interface IRoomTypeRepository  extends JpaRepository<RoomType, Long>{

}
