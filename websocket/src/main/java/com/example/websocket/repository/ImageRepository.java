package com.example.websocket.repository;

import com.example.websocket.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void deleteAllByName(String name);
}