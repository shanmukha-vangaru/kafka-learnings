package com.example.kafka.mappers;

import com.example.kafka.entities.UserSearches;

public class UserSearchesMapper {
    public static UserSearches getEntity(com.example.kafka.models.UserSearches userSearches) {
        UserSearches entity = new UserSearches();
        entity.setSearchTerm(userSearches.getSearchTerm());
        entity.setTimestamp(userSearches.getTimestamp());
        return entity;
    }
}
