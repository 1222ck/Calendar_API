package com.example.calendar_api.calendars.repository;

import com.example.calendar_api.calendars.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    @Transactional
    public void deleteByDiaryId(int diaryId);

    List<Diary> findBysDateYear(int sDateYear);

//    List<Diary> findBysDateYearAndsDateMonth(int sDateYear, int sDateMonth);

    @Query("SELECT d FROM Diary d WHERE d.sDateYear = ?1 and d.sDateMonth = ?2")
    List<Diary> findByYearAndMonth(int sDateYear, int sDateMonth);

}
