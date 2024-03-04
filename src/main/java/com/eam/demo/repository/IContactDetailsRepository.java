package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.ContactDetails;


public interface IContactDetailsRepository extends JpaRepository<ContactDetails, Long>{

}
