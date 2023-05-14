package com.example.calendar_api.members.domain;

import lombok.*;
import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Entity
@Table(name = "MEMBERS")
public class Member {
    /*
     * 복합키를 구성 하기 위한 필수 조건
     * 1. @EmbeddedId or @IdClass 의 annotation을 붙여야한다.
     * 2. public 의 no-args constructor 가 있어야 한다.
     * 3. Serializable 를 implement 받아야 한다.
     * 4. equals() 와 hashCode() method를 override해야 한다. (Id Class도)
        (복합키의 일부 컬럼에 @GeneratedValue는 사용하지 못한다. 고 나와있지만 실제로 TEST 시 사용 가능한 경우도 있었다. )
     */

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_IDX", unique = true, nullable = false)
    private Integer id;

    @Column(name="E_MAIL", length = 255, nullable = false)
    private String email;

    @Column(name="NAME", length = 100, nullable = false)
    private String name;

    @Column(name="PASSWORD", length = 255, nullable = false)
    private String password;

    @Column(name="REG_DATE")
    private LocalDateTime regDate = LocalDateTime.now(); // 생성일

    @Column(name="ALT_DATE")
    private LocalDateTime altDate; // 수정일

    /*@OneToMany(mappedBy = "member", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Board> board = new ArrayList<>();*/

    //빌더
    public Member() {

    }

    @Builder
    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
