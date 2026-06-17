package com.beeswithmotion.thehive.image;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private Long postId;
    private String originalFileName;
    private String storedFileName;
    private String contentType;
    private Long fileSize;
    private LocalDateTime uploadDateTime;

    public Image() {}

    public Long getImageId() {
        return imageId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }
}
