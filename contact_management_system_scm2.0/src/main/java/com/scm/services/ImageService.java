package com.scm.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uplodeImage(MultipartFile contactImage ,String fillname);
    String getURLpublicId(String publicId);
}
