package com.example.calendar_api.members.domain;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEMBERS")
public class Member {
    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_IDX", unique = true, nullable = false)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String NAME;

    @Column(length = 255, nullable = false)
    private String E_MAIL;

    @Column(length = 255, nullable = false)
    private String PASSWORD;

    private LocalDateTime REG_DATE = LocalDateTime.now(); // 생성일

    private LocalDateTime ALT_DATE; // 수정일

    /*@OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();*/

    //빌더
    @Builder
    public Member(String name, String password, String email) {
        this.NAME = name;
        this.PASSWORD = password;
        this.E_MAIL = email;
    }
}
