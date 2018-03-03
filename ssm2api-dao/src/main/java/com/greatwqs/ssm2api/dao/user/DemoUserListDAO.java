package com.greatwqs.ssm2api.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.greatwqs.ssm2api.domain.pojo.user.UserPojo;

/**
 * 
 * ssm2user.demo_userlist
 * @author greatwqs
 * 
 */
public interface DemoUserListDAO {
	
//	CREATE TABLE `demo_userlist` (
//	  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
//	  `UserName` varchar(200) NOT NULL,
//	  `CreateTime` datetime NOT NULL DEFAULT '2013-04-11 00:00:00',
//	  PRIMARY KEY (`ID`)
//	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

    @Insert("insert into demo_userlist(name,age) values(#{name},#{age})")
    public void insertT(UserPojo user);

    @Delete("delete from demo_userlist where id=#{id}")
    public void deleteById(int id);

    @Update("update demo_userlist set name=#{name},age=#{age} where id=#{id}")
    public void updateT(UserPojo user);

    @Select("select id,userName,createTime from demo_userlist where id=#{id}")
    public UserPojo getUserPojo(@Param("id") int id);

    @Select("select * from demo_userlist")
    public List<UserPojo> getAllUsers();
}
