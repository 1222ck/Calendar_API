package com.example.calendar_api.calendars.repository;

import com.example.calendar_api.calendars.domain.DiaryGrp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryGrpRepository extends JpaRepository<DiaryGrp, Integer> {
}
