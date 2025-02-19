package com.scm.services;


import java.util.Optional;
import java.util.*;
import com.scm.entity.User;

public interface UserServices {
    public User savaUser(User user);
    public Optional<User> getUserById(String Id);
    public Optional<User> updateUser(User user);
    public void deleteUser(String Id);
    public boolean isUserExist(String  userId);
    public boolean isUserExistByEmail(String email);
   public  List<User> getAllUser();
   public User getUserByEmail(String Username);
}
