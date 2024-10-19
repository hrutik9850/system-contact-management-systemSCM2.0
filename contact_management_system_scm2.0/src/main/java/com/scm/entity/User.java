package com.scm.entity;

import com.scm.entity.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
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
    private boolean enable = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    // SELF , GOOGLE , FACEBOOK , TWITTER, LINKEDID , GITHUB
    private Providers provider = Providers.SELF;
    private String providerUserId;
    //add more filids if needed
      @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contact = new ArrayList<>();
  

}
