package com.example.kafka.mappers;

import com.example.kafka.entities.UserPurchases;

public class UserPurchasesMapper {
    public static UserPurchases getEntity(com.example.kafka.models.UserPurchases userPurchases) {
        UserPurchases entity = new UserPurchases();
        entity.setOrderId(userPurchases.getOrderId());
        entity.setPrice(userPurchases.getPrice());
        entity.setProductId(userPurchases.getProductId());
        entity.setQuantity(userPurchases.getQuantity());
        entity.setTimestamp(userPurchases.getTimestamp());
        return entity;
    }
}
