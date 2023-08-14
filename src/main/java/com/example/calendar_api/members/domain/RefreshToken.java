package com.example.calendar_api.members.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {
    @Id
    @Column(name = "REFRESH_TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REFRESH_TOKEN", length = 255, nullable = false)
    private String refreshToken;

    @Column(name = "E_MAIL", nullable = false)
    private String accountEmail;

    public RefreshToken(String token, String email) {
        this.refreshToken = token;
        this.accountEmail = email;
    }

    public RefreshToken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }
}
