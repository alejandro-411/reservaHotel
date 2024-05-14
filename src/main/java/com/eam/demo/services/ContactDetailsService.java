package com.eam.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.ContactDetails;
import com.eam.demo.repository.IContactDetailsRepository;



@Service
public class ContactDetailsService {
    
    @Autowired
    private IContactDetailsRepository contactDetailsRepository;

    
    public List<ContactDetails> getAllContactDetails() {
        return contactDetailsRepository.findAll();
    }

    
    public ContactDetails getContactDetailsById(Long contactDetailsId) {
        return contactDetailsRepository.findById(contactDetailsId).orElse(null);
    }

    
    public ContactDetails saveContactDetails(ContactDetails contactDetails) {
        return contactDetailsRepository.save(contactDetails);
    }

    
    public void deleteContactDetails(Long contactDetailsId) {
        contactDetailsRepository.deleteById(contactDetailsId);
    }
}
