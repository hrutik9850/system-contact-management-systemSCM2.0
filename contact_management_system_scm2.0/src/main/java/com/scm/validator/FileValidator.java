package com.scm.validator;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValiFile, MultipartFile> {
    //file size
    private final long maxfilesize = 1024 * 1024 * 2; //2MB
    //file type
    private final String[] allowedMimeTypes = {"image/jpeg", "image/png", "image/jpg", "image/gif", "image/bmp", "image/webp"};
   //file hight and width
    private final int maxWidth = 1920;
    private final int maxHeight = 1080;
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
       //file is empty
        if (file == null || file.isEmpty()) {
            
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File is not be  empty").addConstraintViolation();
            return false;
            
        }
        System.out.println(file.getSize());
        //file size
        if (file.getSize() > maxfilesize) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size less then 2MB").addConstraintViolation();
            return false;
        }
        //file type
        if (!isAllowedMimeType(file.getContentType())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File type is not allowed").addConstraintViolation();
            return false;
        }
        //file hight and width
        // if (file.getSize() > maxWidth || file.getSize() > maxHeight) {
        //     context.disableDefaultConstraintViolation();
        //     context.buildConstraintViolationWithTemplate("Image height and width size too large").addConstraintViolation();
        //     return false;
        // }
        try{  
             BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("File is not a valid image").addConstraintViolation();
                return false;
            }
            int width = image.getWidth();
            int height = image.getHeight();
            if (width > maxWidth || height > maxHeight) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Image height and width size too large").addConstraintViolation();
                return false;
            }
        } catch (IOException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Error reading the image file").addConstraintViolation();
            return false;
        }

        return true;
    }
    private boolean isAllowedMimeType(String mimeType) {
        for (String allowedMimeType : allowedMimeTypes) {
            if (allowedMimeType.equals(mimeType)) {
                return true;
            }
        }
        return false;
    
   
    }
    
    
}
