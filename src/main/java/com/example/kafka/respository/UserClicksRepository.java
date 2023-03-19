package com.example.kafka.respository;

import com.example.kafka.entities.UserClicks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClicksRepository extends JpaRepository<UserClicks, Integer> {
}
