package org.naamtamilar.magazine.enums;

public enum ProductImageEnum {

    SMALL(0, "small"),
    MEDIUM(1, "medium"),
    LARGE(2, "large"),
    THUMBNAIL(3, "thumbnail");
    private int imageSize;
    private String sizeName;

    ProductImageEnum(int imageSize, String sizeName) {
        this.imageSize = imageSize;
        this.sizeName = sizeName;
    }

    public int getImageSize() {
        return imageSize;
    }

    public String getSizeName() {
        return sizeName;
    }
}