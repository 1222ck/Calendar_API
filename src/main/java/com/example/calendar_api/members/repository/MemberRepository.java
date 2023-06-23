package com.example.calendar_api.members.repository;

import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository의 역할
// 영속성 관리를 위한 인터페이스를 제공하는 역할
// 개발자가 쉽게 db 조작을 할 수 있도록 도와주는 역할
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    //Member findByEmail(String email);

    public Optional<Member> findByEmail(String email);
}
