package com.example.calendar_api.calendars.service;

import com.example.calendar_api.calendars.dto.DiaryGrpDto;
import com.example.calendar_api.calendars.repository.DiaryGrpRepository;
import org.springframework.stereotype.Service;

@Service
public class DiaryGrpService {
    private final DiaryGrpRepository groupRepository;

    public DiaryGrpService(DiaryGrpRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Integer save(DiaryGrpDto groupDto) {
        Integer grpId = groupRepository.save(groupDto.build()).getGrpId();
        return grpId;
    }
}
