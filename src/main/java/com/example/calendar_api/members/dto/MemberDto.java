package com.example.calendar_api.members.dto;

import com.example.calendar_api.members.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDto {
    private String email;
    private String name;
    private String password;
    private LocalDateTime regDate;
    private LocalDateTime altDate;

    @Builder
    public MemberDto(String email, String name, String password, LocalDateTime regDate, LocalDateTime altDate) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.regDate = regDate;
        this.altDate = altDate;
    }

    public Member build(){
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
