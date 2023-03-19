package com.example.kafka.entities;

import com.example.kafka.models.Click;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_clicks")
@Data
public class UserClicks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "time_stamp")
    private LocalDate timestamp;

    @Column(name = "page_url")
    private String pageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "click_type")
    private Click clickType;

}
