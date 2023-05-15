package com.example.calendar_api.calendars.service;

import com.example.calendar_api.calendars.dto.GroupDto;
import com.example.calendar_api.calendars.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Integer save(GroupDto groupDto) {
        Integer grpId = groupRepository.save(groupDto.build()).getGrpId();
        return grpId;
    }
}
