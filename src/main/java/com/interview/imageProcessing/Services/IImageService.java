package com.interview.imageProcessing.Services;

import com.interview.imageProcessing.Models.ImageResponseModel;
import com.interview.imageProcessing.Models.ReceveImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
    public ImageResponseModel editImage( MultipartFile image, Boolean grayScale,Boolean compress) throws IOException;
}
