package com.fjnu.restaurant.dao;

import com.fjnu.restaurant.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByTelephone(String telephone);

    User selectByEmail(String email);

    User getuserbyid(int userid);

    List<User> queryuser();

    List<User> getuserquery(String username);

}