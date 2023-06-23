package com.example.calendar_api.members.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@Table(name = "MEMBERS")
@Entity
@IdClass(MemberId.class)
public class Member implements UserDetails {
    //필드
    @Column(name = "MEMBERS_SEQ", unique = true, nullable = false)
    private Long id;

    @Id
    @Column(name="E_MAIL", unique = true, length = 255, nullable = false)
    private String email;

    @Column(name="NAME", length = 100, nullable = false)
    private String name;

    @Column(name="PASSWORD", length = 255, nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name="REG_DATE")
    private LocalDateTime regDate = LocalDateTime.now(); // 생성일

    @UpdateTimestamp
    @Column(name="ALT_DATE")
    private LocalDateTime altDate = LocalDateTime.now();; // 수정일

    /*@OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();*/

    //생성자
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(); // 사용자별 권한 설정은 사용하지 않을 예정
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 유효한 계정
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 사용불가(잠금)하지 않은 계정
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호가 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    //빌더
    public Member() {

    }

    @Builder
    public Member(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
