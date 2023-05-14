package com.example.calendar_api.members.controller;

import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public void create(@RequestBody Member member) {
        memberRepository.save(member);
    }

    @GetMapping("/check-email")
    public Map<String, Object> checkEmail(@RequestParam String email) {
        Map<String, Object> data = new HashMap<String, Object>();

        /**
         * Use the correct HTTP status code.
         * 6.1. 성공 응답은 2XX로 응답한다.
         * 6.2. 실패 응답은 4XX로 응답한다.
         * 6.3. 5XX 에러는 절대 사용자에게 나타내지 마라!
         */

        String statusCode = "200";

        try {
            // 이메일을 공백으로 입력했을때
            if (ObjectUtils.isEmpty(email)) {
                throw new IllegalStateException("이메일을 입력해주세요.");
            }

            Member member = memberRepository.findByEmail(email);
            if (!ObjectUtils.isEmpty(member)) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }

            data.put("statusMessage", "사용 가능한 이메일입니다.");
        } catch (Exception e) {
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Map<String, Object> getMember(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<String, Object>();

        String statusCode = "200";
        try {
            data.put("member", memberRepository.findById(id));
            data.put("statusMessage", "사용 가능한 이메일입니다.");
        } catch (Exception e) {
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }
}
