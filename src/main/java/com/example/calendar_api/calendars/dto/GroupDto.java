package com.example.calendar_api.calendars.dto;

import com.example.calendar_api.calendars.domain.Group;
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

    public Group build() {
        return Group.builder()
                .grpNm(grpNm)
                .leaderId(leaderId)
                .build();
    }
}
