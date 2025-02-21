package com.scm.services;

import java.util.List;

import com.scm.entity.Contact;

public interface ContactService {
 //save contact
    Contact saveContact(Contact contact);
// update contact
    Contact updateContact(Contact contact);
//get all contact
    List<Contact> getAll();
// find by id contact
    Contact getContactById(String ContactId);
// deleite contact by id 
    void deleteContact(String id);
// find by name or email or id 
   List<Contact> geContacts(String name , String email ,String id );
// get by all user contact
   List<Contact> getbyUserId(String UserId);





   

}
