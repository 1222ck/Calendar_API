package com.example.calendar_api.calendars.controller;

import com.example.calendar_api.calendars.domain.Diary;
import com.example.calendar_api.calendars.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

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
    @PostMapping("/getDiary")
    public Map<String, Object> DiaryContent(@RequestBody HashMap<String, String> paramDate){
        Map<String, Object> data = new HashMap<>();

        /*
         * Use the correct HTTP status code.
         * 6.1. 성공 응답은 2XX로 응답한다.
         * 6.2. 실패 응답은 4XX로 응답한다.
         * 6.3. 5XX 에러는 절대 사용자에게 나타내지 마라!
         */

        String statusCode = "200";

        try{
            List<Diary> diaryData = new ArrayList<>();

            if(ObjectUtils.isEmpty(paramDate.get("sDateYear"))) {
                throw new IllegalStateException("연도 값은 필수값 입니다.");
            }else{
                //연도만 있을 경우
                if(ObjectUtils.isEmpty(paramDate.get("sDateMonth"))){
                    int year = Integer.parseInt(String.valueOf(paramDate.get("sDateYear")));

                    diaryData = diaryRepository.findBysDateYear(year);
                //연도, 월이 있을 경우
                }else if(!ObjectUtils.isEmpty(paramDate.get("sDateYear")) && !ObjectUtils.isEmpty(paramDate.get("sDateMonth")) && ObjectUtils.isEmpty(paramDate.get("sDateDay"))) {
                    int year = Integer.parseInt(String.valueOf(paramDate.get("sDateYear")));
                    int month = Integer.parseInt(String.valueOf(paramDate.get("sDateMonth")));

                    diaryData = diaryRepository.findByYearAndMonth(year, month);
                }
                //연도, 월, 일이 모두 있을경우
                else if(!ObjectUtils.isEmpty(paramDate.get("sDateYear")) && !ObjectUtils.isEmpty(paramDate.get("sDateMonth")) && !ObjectUtils.isEmpty(paramDate.get("sDateDay"))){
                    int year = Integer.parseInt(String.valueOf(paramDate.get("sDateYear")));
                    int month = Integer.parseInt(String.valueOf(paramDate.get("sDateMonth")));
                    int day = Integer.parseInt(String.valueOf(paramDate.get("sDateDay")));

                    diaryData = diaryRepository.findByYearAndMonthAndDay(year, month, day);
                }
            }
            data.put("diaryData", diaryData);
        }catch (Exception e){
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }

    //월별 일정 삭제
    @GetMapping("/delDiary")
    public Map<String, Object> deleteDiary(@RequestParam int diaryId){
        Map<String, Object> data = new HashMap<>();

        /*
         * Use the correct HTTP status code.
         * 6.1. 성공 응답은 2XX로 응답한다.
         * 6.2. 실패 응답은 4XX로 응답한다.
         * 6.3. 5XX 에러는 절대 사용자에게 나타내지 마라!
         */
        String statusCode = "200";

        try{
            diaryRepository.deleteByDiaryId(diaryId);

            data.put("statusMessage", "삭제가 완료 되었습니다");
        }catch (Exception e){
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);
        return data;
    }

}
