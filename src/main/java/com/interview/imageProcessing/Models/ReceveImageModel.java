package com.interview.imageProcessing.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ReceveImageModel {

    private MultipartFile image;
    private ImageChanges changesList;

    public ReceveImageModel() {
    }

    public ReceveImageModel(MultipartFile image, ImageChanges changesList) {
        this.image = image;
        this.changesList = changesList;
    }
}
