package com.example.calendar_api.members.controller;

import com.example.calendar_api.calendars.dto.DiaryGrpDto;
import com.example.calendar_api.calendars.service.DiaryGrpService;
import com.example.calendar_api.members.domain.Member;
import com.example.calendar_api.members.dto.MemberDto;
import com.example.calendar_api.members.service.MemberService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    @Resource(name = "diaryGrpService")
    private DiaryGrpService groupService;

    /**
     * 회원 가입
     * @param member
     */
    @PostMapping("/join")
    public Map<String, Object> create(@RequestBody MemberDto member) {
        Map<String, Object> data = new HashMap<String, Object>();
        String statusCode = "200";

        try {
            // 1. member 생성
            String email = memberService.save(member);

            // 2. group 생성
            String grpNm =  email + " 의 첫번째 그룹";
            DiaryGrpDto groupDto = new DiaryGrpDto(grpNm, email);
            Integer grpId = groupService.save(groupDto);

            data.put("email", email);
            data.put("grpId", grpId);
        } catch (Exception e) {
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }

    /**
     * 사용자 로그인을 위한 Entry point. jwt 기반의 auth token과 refresh token을 반환
     */
    /*@PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(
            @Param(value = "로그인 요청 파라미터") @Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authService.authenticateUser(loginRequest)
                .orElseThrow(() -> new UserLoginException("사용자 로그인 실패: [" + loginRequest + "]"));

        User user = (User) authentication.getPrincipal();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        LOGGER.info("로그인한 사용자 [Username]: " + customUserDetails.getUsername());

        // Spring Security의 Authentication 객체 생성
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authService.createAndPersistRefreshTokenForDevice(authentication, loginRequest)
                .map(RefreshToken::getToken)
                .map(refreshToken -> {
                    String jwtToken = authService.generateToken(customUserDetails);
                    return ResponseEntity.ok(new JwtResponse(
                                    jwtToken,
                                    refreshToken,
                                    tokenProvider.getUserAuthorities(customUserDetails),
                                    tokenProvider.getExpiryDuration(),
                                    customUserDetails.getId(),
                                    customUserDetails.getUsername(),
                                    customUserDetails.getMembername(),
                                    customUserDetails.getEmail()
                            )
                    );
                })
                .orElseThrow(() -> new UserLoginException("Refresh token 생성 실패: [" + loginRequest + "]"));
    }*/

    /**
     * 로그인
     * @param member
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody MemberDto member) {
        Map<String, Object> data = new HashMap<String, Object>();
        String statusCode = "200";

        try {
            if(!memberService.login(member)){
                statusCode = "400";
                data.put("statusMessage", "이메일, 비밀번호를 다시 한번 확인해주세요.");
            }
        } catch (Exception e) {
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }

    /**
     * 이메일 중복 체크
     * @param email
     * @return
     */
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

            Member member = memberService.findByEmail(email);
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

    /**
     * 회원 정보 조회
     * @param id
     * @return Map<String, Object>
     */
    @GetMapping("/get/{id}")
    public Map<String, Object> getMember(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<String, Object>();

        String statusCode = "200";
        String statusMessage = "";
        try {
            Member member = memberService.findById(id);
            System.out.println(member);

            data.put("member", member);
        } catch (Exception e) {
            statusCode = "400";
            statusMessage = e.getMessage();
        }

        data.put("statusCode", statusCode);
        data.put("statusMessage", statusMessage);
        System.out.println(data);

        return data;
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param member
     * @return
     */
    @PostMapping("/edit/{id}")
    public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody MemberDto member) {
        Map<String, Object> data = new HashMap<String, Object>();

        String statusCode = "200";
        String statusMessage = "";
        try {
            memberService.update(id, member);
        } catch (Exception e) {
            statusCode = "400";
            statusMessage = e.getMessage();
        }

        data.put("statusCode", statusCode);
        data.put("statusMessage", statusMessage);

        return data;
    }

    /**
     * 회원 삭제
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> data = new HashMap<String, Object>();

        String statusCode = "200";
        String statusMessage = "";
        try {
            memberService.delete(id);
        } catch (Exception e) {
            statusCode = "400";
            statusMessage = e.getMessage();
        }

        data.put("statusCode", statusCode);
        data.put("statusMessage", statusMessage);

        return data;
    }
}
