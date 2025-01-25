package com.scm.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Userform {
    @NotBlank(message ="Username is required")
    @Size(min = 3,message = "Min 3 Character")
    private String name;
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank(message = "Password is reuired ")
    @Size(min = 6 ,message = "Min 6 Character")
    private String password;
    @NotBlank(message = "About is required")
    private String about ;
    /**
     * this is used to data valadation  
     */
    @Size(min = 8,max = 12,message = "Invalid Phone number")
    private String phoneNumber;

}
