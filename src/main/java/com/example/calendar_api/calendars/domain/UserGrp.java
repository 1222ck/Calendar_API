package com.example.calendar_api.calendars.domain;

import javax.persistence.Column;

public class UserGrp {
    @Column(name = "GRP_ID", nullable = false)
    private Integer grpId;

    @Column(name="MEMBERS_SEQ", length = 100, nullable = false)
    private String membersId; // 제목

    @Column(name="GRADE", length = 255, nullable = false)
    private String grade; // 내용
}
