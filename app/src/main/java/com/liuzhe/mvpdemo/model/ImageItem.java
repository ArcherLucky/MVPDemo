package com.liuzhe.mvpdemo.model;

/**
 * Created by liuzhe on 2015/10/30.
 */
public class ImageItem {
    private int imageId;
    private String name;
    private String intro;

    public ImageItem() {
    }

    public ImageItem(int imageId, String intro, String name) {
        this.imageId = imageId;
        this.intro = intro;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
