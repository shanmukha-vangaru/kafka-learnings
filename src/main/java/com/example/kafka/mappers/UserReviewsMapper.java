package com.example.kafka.mappers;

import com.example.kafka.entities.UserReviews;

public class UserReviewsMapper {
    public static UserReviews getEntity(com.example.kafka.models.UserReviews userReviews) {
        UserReviews entity = new UserReviews();
        entity.setProductId(userReviews.getProductId());
        entity.setRating(userReviews.getRating());
        entity.setReviewText(userReviews.getReviewText());
        entity.setTimestamp(userReviews.getTimestamp());
        return entity;
    }
}
