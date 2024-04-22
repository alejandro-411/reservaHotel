package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Department;

public interface IDepartmentRepository  extends JpaRepository<Department, Long>{

}
