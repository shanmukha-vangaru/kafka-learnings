package com.example.kafka.mappers;

import com.example.kafka.entities.UserLogins;

public class UserLoginsMapper {
    public static UserLogins getEntity(com.example.kafka.models.UserLogins userLogins) {
        UserLogins entity = new UserLogins();
        entity.setLoginType(userLogins.getLoginType());
        entity.setTimestamp(userLogins.getTimestamp());
        return entity;
    }
}
