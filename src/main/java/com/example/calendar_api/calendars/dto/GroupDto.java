package com.example.calendar_api.calendars.dto;

import com.example.calendar_api.calendars.domain.DiaryGrp;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GroupDto {

    String grpNm;
    String leaderId;

    @Builder
    public GroupDto(String grpNm, String leaderId) {
        this.grpNm = grpNm;
        this.leaderId = leaderId;
    }

    public DiaryGrp build() {
        return DiaryGrp.builder()
                .grpNm(grpNm)
                .leaderId(leaderId)
                .build();
    }
}
