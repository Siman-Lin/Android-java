package com.lyk.apiweather20200417;

public class dataTextImag {
    private String name;
    private int imageId;

    public dataTextImag (String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
