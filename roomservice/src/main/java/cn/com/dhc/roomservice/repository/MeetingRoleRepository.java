package cn.com.dhc.roomservice.repository;

import cn.com.dhc.roomservice.bean.MeetingRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huleikai
 * @create 2019-04-29 15:54
 */
@Repository
public interface MeetingRoleRepository extends CrudRepository<MeetingRole, Integer>, JpaRepository<MeetingRole, Integer> {

    @Query(value = "select * from `meeting_role` where `del_flag`=?1", nativeQuery = true)
    List<MeetingRole> findAllRooms(int delFlag);
}
