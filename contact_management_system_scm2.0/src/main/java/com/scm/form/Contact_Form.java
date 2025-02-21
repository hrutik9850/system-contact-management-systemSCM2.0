package com.scm.form;

import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contact_Form {
    

    private String id;

    private String name;

    private String email;

    private String phomeNumber;

    private String address;

    private MultipartFile profileImage;

    private String description;

    private boolean favarite = false;

    private String websitelink ;

    private String linkedIdlink;
   
  
   
}
