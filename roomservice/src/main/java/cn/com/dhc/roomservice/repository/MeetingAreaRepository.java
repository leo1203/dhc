package cn.com.dhc.roomservice.repository;

import cn.com.dhc.roomservice.bean.MeetingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author huleikai
 * @create 2019-04-28 15:55
 */
@Repository
public interface MeetingAreaRepository extends CrudRepository<MeetingArea, String>, JpaRepository<MeetingArea, String> {

    @Query(value = "select * from `meeting_area` where `area_id`=?1 ", nativeQuery = true)
    MeetingArea findMeetingAreaByAreaId(String areaId);

    @Modifying
    @Transactional
    @Query(value = "delete from `meeting_area` where `area_id`=?1", nativeQuery = true)
    int deleteMeetingAreaByAreaId(String areaId);

    @Query(value = "select * from `meeting_area` where `del_flag`=?1", nativeQuery = true)
    List<MeetingArea> findAllRooms(boolean delFlag);
}
