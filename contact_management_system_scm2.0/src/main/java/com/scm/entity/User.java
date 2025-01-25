package com.scm.entity;

import com.scm.entity.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{
    @Id
    private String Id ;
    @Column(name ="user_name",nullable = false)
    private String name ;
    @Column(unique = true,nullable = false)
    private String email ;
    
    private String password ;
    @Column(length = 1000)
    private String about ;
    @Column(length = 10000)
    private String profilePic ;
    private String phoneNumber ;

    // infarmation 
   
    private boolean enable = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    @Enumerated(value = EnumType.STRING)
    // SELF , GOOGLE , FACEBOOK , TWITTER, LINKEDID , GITHUB
    private Providers provider = Providers.SELF;
    private String providerUserId;
    //add more filids if needed
      @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();
    
    
  //store the role list 
  @ElementCollection(fetch =FetchType.EAGER)
  private List<String>rolList = new ArrayList();

    @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
       // list of roles [user ,admin]
       // collection of simpleGrantedAuthority {roles[user,admin]}
        Collection<SimpleGrantedAuthority> roles = rolList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
      }
      
      // for this project 
      //email  id is the username 
      
      @Override
      public String getUsername() {
       return this.getEmail();
      }

      // @Override
      // public String getPassword() {
      //   return this.getPassword();
      // }
    

}
