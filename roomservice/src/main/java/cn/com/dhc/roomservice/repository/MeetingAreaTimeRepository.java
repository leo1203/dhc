package cn.com.dhc.roomservice.repository;

import cn.com.dhc.roomservice.bean.MeetingAreaTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingAreaTimeRepository extends CrudRepository<MeetingAreaTime, String>, JpaRepository<MeetingAreaTime, String> {

    @Query(value = "select * from `meeting_area_time` where `area_time_id`=? ", nativeQuery = true)
    MeetingAreaTime findByAreaTimeId(String areaTimeId);
    @Query(value = "select * from `meeting_area_time` where `create_user`=? and del_flag=0 ", nativeQuery = true)
    MeetingAreaTime findByCreateUser(String createUser);

}
