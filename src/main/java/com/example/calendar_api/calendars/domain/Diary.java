package com.example.calendar_api.calendars.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
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

    @Column(name="S_DATE_YEAR", length = 4)
    private Integer sDateYear; // 일정 시작 연도

    @Column(name="S_DATE_MONTH", length = 2)
    private Integer sDateMonth; // 일정 시작 월

    @Column(name="S_DATE_DAY", length = 2)
    private Integer sDateDay; // 일정 시작 일

    @Column(name="S_DATE_TIME", length = 4)
    private Integer sDateTime; // 일정 시작 시간

    @Column(name="E_DATE_YEAR", length = 4)
    private Integer eDateYear; // 일정 종료 연도

    @Column(name="E_DATE_MONTH", length = 2)
    private Integer eDateMonth; // 일정 종료 월

    @Column(name="E_DATE_DAY", length = 2)
    private Integer eDateDay; // 일정 종료 일

    @Column(name="E_DATE_TIME", length = 4)
    private Integer eDateTime; // 일정 종료 시간

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
    public Diary(Integer diaryId, String title, String contents, Integer sDateYear, Integer sDateMonth, Integer sDateDay, Integer sDateTime, Integer eDateYear, Integer eDateMonth, Integer eDateDay, Integer eDateTime, String allDayYn, String color, String place, Integer grpId, String regUserId, LocalDateTime regDate) {
        this.diaryId = diaryId;
        this.title = title;
        this.contents = contents;
        this.sDateYear = sDateYear;
        this.sDateMonth = sDateMonth;
        this.sDateDay = sDateDay;
        this.sDateTime = sDateTime;
        this.eDateYear = eDateYear;
        this.eDateMonth = eDateMonth;
        this.eDateDay = eDateDay;
        this.eDateTime = eDateTime;
        this.allDayYn = allDayYn;
        this.color = color;
        this.place = place;
        this.grpId = grpId;
        this.regUserId = regUserId;
        this.regDate = regDate;
    }
}
