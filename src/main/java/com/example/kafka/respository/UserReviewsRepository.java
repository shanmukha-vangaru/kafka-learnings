package com.example.kafka.respository;

import com.example.kafka.entities.UserReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewsRepository extends JpaRepository<UserReviews, Integer> {
}
