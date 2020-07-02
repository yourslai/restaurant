package com.fjnu.restaurant.dao;

import com.fjnu.restaurant.bean.Managerpassword;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ManagerpasswordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Managerpassword record);

    int insertSelective(Managerpassword record);

    Managerpassword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Managerpassword record);

    int updateByPrimaryKey(Managerpassword record);

    int updateByManagerIDSelective(Managerpassword record);

    Managerpassword selectByManagerID(Integer managerID);

    Managerpassword getManagerpassword(String email);

}