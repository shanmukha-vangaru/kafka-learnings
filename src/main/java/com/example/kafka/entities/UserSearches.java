package com.example.kafka.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_searches")
@Data
public class UserSearches {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time_stamp")
    private LocalDate timestamp;

    @Column(name = "search_term")
    private String searchTerm;
}
