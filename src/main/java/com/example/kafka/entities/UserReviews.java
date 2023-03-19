package com.example.kafka.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_reviews")
@Data
public class UserReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time_stamp")
    private LocalDate timestamp;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "review_text")
    private String reviewText;
}
