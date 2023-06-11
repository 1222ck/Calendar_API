package com.example.calendar_api.calendars.service;

import com.example.calendar_api.calendars.domain.Diary;
import com.example.calendar_api.calendars.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }
    public List<Diary> findBysDateYear(int sDateYear){
        List<Diary> dataYear = diaryRepository.findBysDateYear(sDateYear);
        return dataYear;
    }

//    public List<Diary> findBysDateYearAndsDateMonth(int sDateYear, int sDateMonth){
//        List<Diary> dataYearAndMonth = diaryRepository.findBysDateYearAndsDateMonth(sDateYear, sDateMonth);
//        return dataYearAndMonth;
//    }

//    public List<Diary> findBysDateMonth(int sDateMonth){
//        List<Diary> dataMonth = diaryRepository.findBysDateMonth(sDateMonth);
//        return dataMonth;
//    }
//
//    public List<Diary> findBysDateDay(int sDateDay){
//        List<Diary> dataDay = diaryRepository.findBysDateDay(sDateDay);
//        return dataDay;
//    }
}
