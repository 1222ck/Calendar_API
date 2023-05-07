package com.example.calendar_api.members.repository;

import com.example.calendar_api.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
