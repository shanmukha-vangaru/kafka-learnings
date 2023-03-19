package com.example.kafka.mappers;

import com.example.kafka.entities.UserClicks;

public class UserClicksMapper {
    public static UserClicks getEntity(com.example.kafka.models.UserClicks userClicks) {
        UserClicks entity = new UserClicks();
        entity.setClickType(userClicks.getClickType());
        entity.setPageUrl(userClicks.getPageUrl());
        entity.setTimestamp(userClicks.getTimestamp());
        entity.setUserId(userClicks.getUserId());
        return entity;
    }
}
