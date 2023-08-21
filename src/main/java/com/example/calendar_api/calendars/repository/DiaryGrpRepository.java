package com.example.calendar_api.calendars.repository;

import com.example.calendar_api.calendars.domain.Diary;
import com.example.calendar_api.calendars.domain.DiaryGrp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryGrpRepository extends JpaRepository<DiaryGrp, Integer> {

    @Query("SELECT ug.GRP_ID as grpId , ug.MEMBERS_SEQ as membersSeq  , dg.GRP_NM as grpNm" +
            "FROM UserGrp ug " +
            "left join DIARY_GRP dg on ug.GRP_ID  = dg.GRP_ID" +
            "left join MEMBERS m on ug.MEMBERS_SEQ = m.MEMBERS_SEQ" +
            "where 1=1" +
            "and m.E_MAIL = 'bbb@bbb.com'")
    List<Diary> getMenusByEMail(String eMail);
}
