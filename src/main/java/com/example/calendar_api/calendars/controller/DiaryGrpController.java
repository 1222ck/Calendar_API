package com.example.calendar_api.calendars.controller;

import com.example.calendar_api.calendars.domain.DiaryGrp;
import com.example.calendar_api.calendars.repository.DiaryGrpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diaryGrp/")
public class DiaryGrpController {

    @Autowired
    private DiaryGrpRepository diaryGrpRepository;

    @PostMapping("/groupCreate")
    public void groupCreate(@RequestBody DiaryGrp diaryGrp) {
        diaryGrpRepository.save(diaryGrp);
    }
}
