package com.beeswithmotion.thehive.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public Image findByImageId(Long imageId);

    public Optional<Image> findByPostId(Long postId);

    public Optional<Image> findByStoredFileName(String storedFileName);
}
