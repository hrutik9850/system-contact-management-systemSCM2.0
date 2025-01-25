package com.scm.helper;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String massage){
        super(massage);
    }
    public ResourceNotFoundException(){
        super("Resorces not found ");
    }
}
