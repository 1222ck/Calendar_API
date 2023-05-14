package com.example.calendar_api.calendars.controller;

import com.example.calendar_api.calendars.domain.Diary;
import com.example.calendar_api.calendars.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {


    @Autowired
    private DiaryRepository diaryRepository;

    @PostMapping("/diaryRegister")
    public void create(@RequestBody Diary diary) {
        diaryRepository.save(diary);
    }

}
