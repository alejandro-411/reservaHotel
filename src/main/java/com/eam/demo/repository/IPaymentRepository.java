package com.eam.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Payments;


public interface IPaymentRepository  extends JpaRepository<Payments, Long>{
    
}
