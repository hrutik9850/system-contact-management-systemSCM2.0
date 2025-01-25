package com.scm.repository;
import com.scm.entity.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository< User ,String>{
    // some extro method tho fetach a data In to data base 
    //custome finder method 
    //custome query method 

   public Optional<User> findByEmail(String email);
   public Optional<User> findByEmailAndPassword(String email, String password);
}
