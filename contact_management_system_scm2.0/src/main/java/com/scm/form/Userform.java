package com.scm.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Userform {
    private String name;
    private String email;
    private String password;
    private String about ;
    private String phoneNumber;

}
