package com.example.calendar_api.members.service;

import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.dto.MemberDto;
import com.example.calendar_api.members.repository.MemberRepository;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // private final GuestbookRepository repository;

    public MemberDto save(MemberDto memberDto) {
        memberRepository.save(memberDto.build());
        return memberDto;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findById(Integer id) {
        return memberRepository.findById(id).get();
    }
}
