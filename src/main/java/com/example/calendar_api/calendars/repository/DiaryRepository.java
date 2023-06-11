package com.example.calendar_api.calendars.repository;

import com.example.calendar_api.calendars.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    @Transactional
    public void deleteByDiaryId(int diaryId);

    List<Diary> findBysDateYear(int sDateYear);

//    List<Diary> findBysDateYearAndsDateMonth(int sDateYear, int sDateMonth);

}
