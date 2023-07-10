package com.example.calendar_api.members.service;

import com.example.calendar_api.jwt.component.JwtUtilComponent;
import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.dto.MemberDto;
import com.example.calendar_api.members.repository.MemberRepository;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtilComponent jwtTokenProvider;

    public MemberService(MemberRepository memberRepository, JwtUtilComponent jwtTokenProvider) {
        this.memberRepository = memberRepository;
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

    public String login(MemberDto memberDto) {
        // 이메일 체크
        Member member = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        // 비밀번호 체크
        if (!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // 성공 시 토큰 발급
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

        /*try {
            Authentication resultAuth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(memberDto.getEmail(), memberDto.getPassword())
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Optional<Member> findMember = memberRepository.findByEmail(memberDto.getEmail());
        if (ObjectUtils.isEmpty(findMember)) {
            return false;
        }

        //String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.get().getPassword())) {
            return false;
        }

        return true;*/
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
