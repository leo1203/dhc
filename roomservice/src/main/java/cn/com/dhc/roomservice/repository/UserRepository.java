package cn.com.dhc.roomservice.repository;

import cn.com.dhc.roomservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String>, JpaRepository<User, String> {

    @Query(value = "select * from `meeting_user` where `user_id`=? ", nativeQuery = true)
    User findByUserId(String userId);
    @Query(value = "select * from `meeting_user` where `user_name`=? ", nativeQuery = true)
    User findByUserName(String userName);

    @Query(value = "select * from meeting_user where mobile = ? ",nativeQuery = true)
    User findByMobile(String mobile);


    @Query(value = "select * from `meeting_user` where `del_flag`=?1", nativeQuery = true)
    List<User> findAllUsers(int delFlag);
}
