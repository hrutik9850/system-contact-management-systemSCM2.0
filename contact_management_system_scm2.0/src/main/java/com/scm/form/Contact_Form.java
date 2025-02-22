package com.scm.form;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}",message = "Invalide Phone Number")
    private String phomeNumber;
    @NotBlank(message = "Addres is required")
    private String address;

    private MultipartFile profileImage;

    private String description;

    private boolean favarite = false;

    private String websitelink ;

    private String linkedIdlink;
   
  
   
}
