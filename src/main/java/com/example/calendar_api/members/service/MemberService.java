package com.example.calendar_api.members.service;

import com.example.calendar_api.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    /*@Resource(name = "bCryptPasswordEncoder")
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MessageSourceAccessor msa;

    public User save(UserDto userDto) {
        if (isExistUser(userDto.getEmail())) {
            throw new UserDuplicatedException(msa.getMessage("email.duplicate.message"));
        }
        return userRepository.save(userDto.toEntityWithPasswordEncode(bCryptPasswordEncoder);
    }*/
}
