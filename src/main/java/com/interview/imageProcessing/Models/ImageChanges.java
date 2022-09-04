package com.interview.imageProcessing.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageChanges {
    private String type;
    private String value;

    public ImageChanges() {
    }

    public ImageChanges(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
