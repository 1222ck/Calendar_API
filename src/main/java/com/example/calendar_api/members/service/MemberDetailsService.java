package com.example.calendar_api.members.service;

import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository; // 의존성 주입(DI)

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Member member = memberRepository.findByEmail(email);
        //return new org.springframework.security.core.userdetails.User(member.getEmail(), member.getPassword(), new ArrayList<>());

        Optional<Member> findMember = memberRepository.findByEmail(email);
        return findMember.orElseThrow(() -> new UsernameNotFoundException(String.format("email=%d", email)));
    }
}
