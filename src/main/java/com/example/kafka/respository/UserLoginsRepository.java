package com.example.kafka.respository;

import com.example.kafka.entities.UserLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginsRepository extends JpaRepository<UserLogins, Integer> {
}
