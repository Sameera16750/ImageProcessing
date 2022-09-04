package com.interview.imageProcessing.Models;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.Message;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageResponseModel {
    private String image;
    private String Message;
    private boolean status;

    public ImageResponseModel() {
    }

    public ImageResponseModel(String image, String message, boolean status) {
        this.image = image;
        Message = message;
        this.status = status;
    }
}
