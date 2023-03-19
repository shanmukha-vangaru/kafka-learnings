package com.example.kafka.respository;

import com.example.kafka.entities.UserSearches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSearchesRepository extends JpaRepository<UserSearches, Integer> {
}
