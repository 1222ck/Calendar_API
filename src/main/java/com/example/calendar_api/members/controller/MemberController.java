package com.example.calendar_api.members.controller;

import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    /*@Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto, HttpSession session) {
        SessionUtil.setUser(session, userService.login(loginDto));
        return new ResponseEntity(HttpStatus.OK);
    }*/

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/join")
    public void joinMember(Member member) {
        memberRepository.save(member);
    }
}
