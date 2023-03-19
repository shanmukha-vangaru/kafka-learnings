package com.example.kafka.respository;

import com.example.kafka.entities.UserPurchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPurchasesRepository extends JpaRepository<UserPurchases, Integer> {
}
