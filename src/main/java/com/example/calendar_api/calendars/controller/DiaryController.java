package com.example.calendar_api.calendars.controller;

import com.example.calendar_api.calendars.domain.Diary;
import com.example.calendar_api.calendars.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/diaryGrp")
public class DiaryController {


    @Autowired
    private DiaryRepository diaryRepository;

    @PostMapping("/diaryRegister")
    public void create(@RequestBody Diary diary) {
        diaryRepository.save(diary);
    }

    //월별 일정 조회
    @GetMapping("/getDiary")
    public Map<String, Object> DiaryContent(@RequestParam int sDate){
        Map<String, Object> data = new HashMap<>();

        /**
         * Use the correct HTTP status code.
         * 6.1. 성공 응답은 2XX로 응답한다.
         * 6.2. 실패 응답은 4XX로 응답한다.
         * 6.3. 5XX 에러는 절대 사용자에게 나타내지 마라!
         */

        String statusCode = "200";

        try{
            Diary diaryData = diaryRepository.findBysDate(sDate);

            data.put("diaryData", diaryData);
        }catch (Exception e){
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }

}
