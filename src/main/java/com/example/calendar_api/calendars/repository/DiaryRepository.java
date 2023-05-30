package com.example.calendar_api.calendars.repository;

import com.example.calendar_api.calendars.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    Diary findBysDate(int sDate);

    @Transactional
    public void deleteByDiaryId(int diaryId);
}
