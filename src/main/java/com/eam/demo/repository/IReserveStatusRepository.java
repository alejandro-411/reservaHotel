package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.ReserveStatus;


public interface IReserveStatusRepository  extends JpaRepository<ReserveStatus, Long>{

}
