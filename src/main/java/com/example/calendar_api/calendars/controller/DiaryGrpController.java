package com.example.calendar_api.calendars.controller;

import com.example.calendar_api.calendars.domain.DiaryGrp;
import com.example.calendar_api.calendars.repository.DiaryGrpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calendar")
public class DiaryGrpController {

    @Autowired
    private DiaryGrpRepository diaryGrpRepository;

    @GetMapping("/getMenus")
    public Map<String, Object> getMenus(@RequestParam String eMail){
        Map<String, Object> data = new HashMap<>();

        String statusCode = "200";

        try{
            diaryGrpRepository.getMenusByEMail(eMail);

            data.put("statusMessage", "삭제가 완료 되었습니다");
        }catch (Exception e){
            statusCode = "400";
            data.put("statusMessage", e.getMessage());
        }

        data.put("statusCode", statusCode);

        return data;
    }

    @PostMapping("/groupCreate")
    public void groupCreate(@RequestBody DiaryGrp diaryGrp) {
        diaryGrpRepository.save(diaryGrp);
    }
}
