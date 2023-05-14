package com.example.calendar_api.calendars.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "DIARY")
public class Diary {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIARY_ID", unique = true, nullable = false)
    private Integer diaryId;

    @Column(name="TITLE", length = 100, nullable = false)
    private String title; // 제목

    @Lob
    @Column(name="CONTENTS")
    private String contents; // 내용

    @Column(name="S_DATE", length = 12)
    private Integer sDate; // 일정 시작 시간

    @Column(name="E_DATE", length = 12)
    private Integer eDate; // 일정 종료 시간

    @Column(name="ALL_DAY_YN", length = 2)
    private String allDayYn; // 종일 유무

    @Column(name="COLOR", length = 10)
    private String color; // 다이어리 표시 색

    @Column(name="PLACE", length = 100)
    private String place; // 장소

    @Column(name="GRP_ID", length = 11, nullable = false)
    private Integer grpId; // 속한 그룹 ID

    @Column(name="REG_USER_ID", length = 255, nullable = false)
    private String regUserId; // 다이어리 등록 ID

    @Column(name="REG_DATE")
    private LocalDateTime regDate = LocalDateTime.now(); // 다이어리 작성 일자


    @Builder
    public Diary(String title, Integer sDate, Integer eDate, String allDayYn, String color, String place) {
        this.title = title;
        this.sDate = sDate;
        this.eDate = eDate;
        this.allDayYn = allDayYn;
        this.color = color;
        this.place = place;
    }
}
