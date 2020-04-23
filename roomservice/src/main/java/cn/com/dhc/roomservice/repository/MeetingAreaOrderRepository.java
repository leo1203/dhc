package cn.com.dhc.roomservice.repository;

import cn.com.dhc.roomservice.bean.MeetingAreaOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author huleikai
 * @create 2019-04-29 14:00
 */
@Repository
public interface MeetingAreaOrderRepository extends CrudRepository<MeetingAreaOrder, Integer>, JpaRepository<MeetingAreaOrder, Integer> {

    @Query(value = "select * from `meeting_area_order` where `area_order_id`=?1 ", nativeQuery = true)
    MeetingAreaOrder findMeetingAreaOrderByAreaOrderId(Integer areaOrderId);

    @Query(value = "SELECT * FROM `meeting_area_order` WHERE `contact_phone` like %?%1 ORDER BY `area_order_status`,`reserve_date` DESC,`reserve_start_time`", nativeQuery = true)
    Page<MeetingAreaOrder> findMeetingAreaOrdersByPhone(String phone, Pageable pageable);

    @Query(value = "SELECT * FROM `meeting_area_order`  WHERE `contact_name` like %?%1 ORDER BY `area_order_status`,`reserve_date` DESC,`reserve_start_time`", nativeQuery = true)
    Page<MeetingAreaOrder> findMeetingAreaOrdersByName(String contactName, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete from `meeting_area_order` where `area_order_id`=?1", nativeQuery = true)
    int deleteMeetingAreaOrderByAreaOrderId(Integer areaOrderId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE meeting_area_order s INNER JOIN (SELECT area_order_id FROM meeting_area_order WHERE STR_TO_DATE(CONCAT_WS(\" \",reserve_date,reserve_end_time),'%Y-%m-%d %H:%i:%s') <= NOW()) a ON s.area_order_id=a.area_order_id SET s.area_order_status = 20", nativeQuery = true)
    int updateMeetingAreaStatus();

    @Query(value = "SELECT * FROM meeting_area_order WHERE area_id = ?3 AND area_order_status = 10 AND STR_TO_DATE(CONCAT_WS(\" \",reserve_date,reserve_end_time),'%Y-%m-%d %H:%i:%s') > ?1 AND STR_TO_DATE(CONCAT_WS(\" \",reserve_date,reserve_start_time),'%Y-%m-%d %H:%i:%s') < ?2", nativeQuery = true)
    List<MeetingAreaOrder> findMeetingAreaOrderByDate(String startTime, String endTime, String areaId);
}
