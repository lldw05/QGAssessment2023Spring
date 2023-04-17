package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class Picture {
    private Integer pictureId;
    private String  pictureName;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", pictureName='" + pictureName + '\'' +
                '}';
    }
}
