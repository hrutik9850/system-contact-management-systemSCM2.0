package com.scm.user_services_imple;


import java.util.List;
import java.util.UUID;
import com.scm.helper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entity.Contact;
import com.scm.entity.User;
import com.scm.repository.ContactFormRepo;
import com.scm.services.ContactService;
@Service
public class ContactServiceImple implements ContactService{

    @Autowired
    private ContactFormRepo contactFormRepo ;


    @Override
    public Contact saveContact(Contact contact) {
        // save the contact on data base
          String contactId = UUID.randomUUID().toString();
          contact.setId(contactId);
         return  contactFormRepo.save(contact);

        
    }

    @Override
    public Contact updateContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public List<Contact> getAll() {
        //find all the data 
        return contactFormRepo.findAll();
    }

    @Override
    public Contact getContactById(String ContactId) {
        return contactFormRepo.findById(ContactId).orElseThrow(()-> new ResourceNotFoundException("Contact not found with giving id "+ContactId));
    }

    @Override
    public void deleteContact(String id) {
        var contact =contactFormRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with giving id "+id));

        contactFormRepo.delete(contact);
    }

    @Override
    public List<Contact> geContacts(String name, String email, String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geContacts'");
    }

    @Override
    public List<Contact> getbyUserId(String UserId) {
        return contactFormRepo.findByUserId(UserId);
    }

}
