package com.interview.imageProcessing.Controllers;

import com.interview.imageProcessing.Models.ImageChanges;
import com.interview.imageProcessing.Models.ImageResponseModel;
import com.interview.imageProcessing.Models.ReceveImageModel;
import com.interview.imageProcessing.Services.IImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/image_controller")
public class ImagesController {

    @Autowired
    private IImageService iImageService;
    ReceveImageModel imageModel = new ReceveImageModel();

    @PostMapping("editImage")
    public void editImage(@RequestParam("Image") MultipartFile image, @RequestParam("grayScale") Boolean grayScale, @RequestParam("compress") Boolean compress, HttpServletResponse response) throws IOException {
        InputStream in = new FileInputStream(".\\images\\"+iImageService.editImage(image,grayScale,compress).getImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}
