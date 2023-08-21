package com.example.calendar_api.members.service;

import com.example.calendar_api.jwt.component.JwtUtilComponent;
import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.domain.RefreshToken;
import com.example.calendar_api.members.dto.MemberDto;
import com.example.calendar_api.members.dto.TokenDto;
import com.example.calendar_api.members.repository.MemberRepository;
import com.example.calendar_api.members.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;


@Service
public class MemberService {
    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtilComponent jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, RefreshTokenRepository refreshTokenRepository, JwtUtilComponent jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    // private final GuestbookRepository repository;

    public String save(MemberDto memberDto) {
        // member_seq 생성
        Date from = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String to = transFormat.format(from);

        Long memberSeq = Long.parseLong(to);

        Member member = Member.builder()
                .id(memberSeq)
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(passwordEncoder.encode(memberDto.getPassword()))  //비밀번호 인코딩
                .roles(Collections.singletonList("ROLE_USER"))         //roles는 최초 USER로 설정
                .build();


        String email = memberRepository.save(member).getEmail();
        return email;
    }

    public MemberDto update(Integer id, MemberDto memberDto) {
        Optional<Member> updateMember = memberRepository.findById(id);
        if(updateMember.isPresent() == false)
            return null;

        Member member = updateMember.get();
        member.setName(memberDto.getName());
        if (!ObjectUtils.isEmpty(memberDto.getPassword())) {
            member.setPassword(memberDto.getPassword());
        }

        memberRepository.save(member);
        return memberDto;
    }

    public TokenDto login(MemberDto memberDto) {
        // 이메일 체크
        Member member = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        // 비밀번호 체크
        if (!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // 이메일 정보로 accessToken 발급
        TokenDto tokenDto = JwtUtilComponent.createAllToken(memberDto.getEmail());
        // jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

        // Refresh토큰 있는지 확인
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountEmail(memberDto.getEmail());

        // 있다면 새토큰 발급후 업데이트
        // 없다면 새로 만들고 디비 저장
        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), memberDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        return tokenDto;
    }

    public void delete(Integer id) {
        memberRepository.deleteById(id);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findById(Integer id) {
        return memberRepository.findById(id).get();
    }
}
