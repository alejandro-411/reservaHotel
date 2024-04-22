package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.City;
import com.eam.demo.models.Rol;

public interface ICityRepository  extends JpaRepository<City, Long>{

}
