package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eam.demo.models.Location;

@Repository
public interface IlocationRepository extends JpaRepository<Location, Long>{

}
