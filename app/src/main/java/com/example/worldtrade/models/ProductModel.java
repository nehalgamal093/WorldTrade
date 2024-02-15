package com.example.worldtrade.models;

import java.util.List;

public class ProductModel {
    private String title;

    private List<ImageModel> images;
    String image;

    public ProductModel(String title, List<ImageModel> images, String image) {
        this.title = title;
        this.images = images;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }

    public String getImage() {
        return String.valueOf(images.get(0).getAttachment_file());
    }

    public void setImage(String image) {
        this.image = image;
    }
}
