package com.example.calendar_api.members.dto;

import com.example.calendar_api.members.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class MemberDto extends User {
    private Long id;
    private String email;
    private String name;
    private String password;
    private LocalDateTime regDate;
    private LocalDateTime altDate;

    // @Builder
    public MemberDto(String email,
                     String name,
                     String password,
                     LocalDateTime regDate,
                     LocalDateTime altDate,
                     Collection<?extends GrantedAuthority> authorities) {
        super(name, password, authorities);
        this.email = email;
        this.name = name;
        this.password = password;
        this.regDate = regDate;
        this.altDate = altDate;
    }

    public Member build(){
        return Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .password(password)
                .build();
    }
}
