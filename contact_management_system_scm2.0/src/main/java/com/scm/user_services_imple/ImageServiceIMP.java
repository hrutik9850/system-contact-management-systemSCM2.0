package com.scm.user_services_imple;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.services.ImageService;
@Service
public class ImageServiceIMP implements ImageService{

    private Cloudinary cloudinary ;

    private String fillname = UUID.randomUUID().toString();

    public ImageServiceIMP(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    @Override
    public String uplodeImage(MultipartFile contactImage, String filllname) {
        try {
            byte[] data = new byte[contactImage.getInputStream().available()] ;
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data ,ObjectUtils.asMap(
                "public_id", fillname
            ));
            return getURLpublicId(fillname);
        } catch (Exception e) {
            
           
            e.getMessage();
            return null;
        }
       }
    // @Override
    // public String uplodeImage(MultipartFile contactImage) {
    //     try {
    //         byte[] data = contactImage.getBytes();
    //         String publicId = contactImage.getOriginalFilename();
    //         cloudinary.uploader().upload(data, ObjectUtils.asMap(
    //             "public_id", publicId
    //         ));
    //         return getURLpublicId(publicId);
    //     } catch (Exception e) {
    //         e.printStackTrace(); // Log the exception
    //         return null;
    //     }
    // }

       
          
    
    @Override
    public String getURLpublicId(String publicId) {
        return cloudinary
            .url()
            .transformation(
                new Transformation()
                .width(AppConstants.IMAGE_CONTATC_WIGH_Int)
                .height(AppConstants.IMAGE_CONTATC_HEIGHT_INT)
                .crop(AppConstants.IMAGE_CONTATC_CROP_STRING) // Example transformation
            )
            .generate(publicId);
    }
    
}
