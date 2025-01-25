package com.scm.user_services_imple;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.entity.User;
import com.scm.repository.UserRepo;
import com.scm.services.UserServices;
@Service
public class UserServiceImple implements UserServices {
   @Autowired
    private UserRepo userRepo;
   @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger  logger = LoggerFactory.getLogger(this.getClass());
   
    
    @Override
    public User savaUser(User user) {
        //user id generate in randome UUID class 
        System.out.println("------------sava user----------");
        String userID = UUID.randomUUID().toString();
        user.setId(userID);
        //password encoder 
        //user.setPassword(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set user role
         user.setRolList(List.of(AppConstants.ROLE_USER));
        
        System.out.println("----------------generate a id ----------------");
        return userRepo.save(user);
       
        //passwoed encodede 
    }

    @Override
    public Optional<User> getUserById(String Id) {
        return userRepo.findById(Id);
    }

    @Override
    public Optional<User> updateUser(User user) {
     User user2 = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        user2.setId(user.getId());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setName(user.getName());
        user2.setEnable(user.isEnable());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProfilePic(user.getProfilePic());
        user2.setProviderUserId(user.getProviderUserId());


        //save the user in database 
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String Id) {
        User user2 = userRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("user not found"));
            userRepo.delete(user2);
    }
    @Override
    public boolean isUserExist(String userId) {
       User user = userRepo.findById(userId).orElse(null);
       return user!=null ? true : false ;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
      User user = userRepo.findByEmail(email).orElse(null);
      return user!=null ? true : false ;
    }

    @Override
    public List<User> getAllUser() {
       return userRepo.findAll();
    }

}
