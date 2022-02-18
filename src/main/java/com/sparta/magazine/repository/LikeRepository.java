package com.sparta.magazine.repository;

import com.sparta.magazine.model.LikeNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeNumber, Long> {
}
