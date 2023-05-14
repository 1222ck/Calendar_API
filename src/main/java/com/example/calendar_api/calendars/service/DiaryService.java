package com.example.calendar_api.calendars.service;

import com.example.calendar_api.calendars.domain.Diary;
import com.example.calendar_api.calendars.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }
}
