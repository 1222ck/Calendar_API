package com.example.calendar_api.members.service;

import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.dto.MemberDto;
import com.example.calendar_api.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // private final GuestbookRepository repository;

    public String save(MemberDto memberDto) {
        // member_seq 생성
        Date from = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String to = transFormat.format(from);

        Long memberSeq = Long.parseLong(to);
        memberDto.setId(memberSeq);

        // 비밀번호 복호화
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(encodedPassword);

        String email = memberRepository.save(memberDto.build()).getEmail();
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

    public void delete(Integer id) {
        memberRepository.deleteById(id);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findById(Integer id) {
        return memberRepository.findById(id).get();
    }
}
