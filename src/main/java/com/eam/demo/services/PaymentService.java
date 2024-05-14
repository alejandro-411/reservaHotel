package com.eam.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.Payments;
import com.eam.demo.repository.IPaymentRepository;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    
    public Payments savePayment(Payments payment) {
        return paymentRepository.save(payment);
    }

    
    public List<Payments> findAllPayments() {
        return paymentRepository.findAll();
    }

    
    public Payments findPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payments updatePayment(Payments payment, Long id) {
        return paymentRepository.findById(id)
            .map(existingPayment -> {
                existingPayment.setBooking(payment.getBooking());
                existingPayment.setUser(payment.getUser());
                existingPayment.setTotalPrice(payment.getTotalPrice());
                existingPayment.setPaymentStatus(payment.getPaymentStatus());
                return paymentRepository.save(existingPayment);
            }).orElseGet(() -> {
                payment.setPaymentId(id); // Assure the ID is set for a new entry if not found
                return paymentRepository.save(payment);
            });
    }
}

