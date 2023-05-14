package com.example.calendar_api.calendars.domain;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRP_ID", unique = true, nullable = false)
    private Integer grpId;

    @Column(name="GRP_NM", length = 100, nullable = false)
    private String grpNm; // 제목

    @Column(name="LEADER_ID", length = 255, nullable = false)
    private String leaderId; // 내용

    @Column(name="REG_DATE")
    private LocalDateTime regDate = LocalDateTime.now(); // 다이어리 작성 일자



}