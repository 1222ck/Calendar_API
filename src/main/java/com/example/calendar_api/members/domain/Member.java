package com.example.calendar_api.members.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Column(name = "E_MAIL", unique = true, length = 255, nullable = false)
    private String email;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "REG_DATE")
    private LocalDateTime regDate = LocalDateTime.now(); // 생성일

    @UpdateTimestamp
    @Column(name = "ALT_DATE")
    private LocalDateTime altDate = LocalDateTime.now();
    ; // 수정일

    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override   //사용자의 권한 목록 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //빌더
    public Member() {

    }

    @Builder
    public Member(Long id, String name, String password, String email, List<String> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
