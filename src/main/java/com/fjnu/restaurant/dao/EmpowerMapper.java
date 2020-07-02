package com.fjnu.restaurant.dao;

import com.fjnu.restaurant.bean.Empower;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmpowerMapper {

    int deleteByPrimaryKey(Integer authorizeid);

    int insert(Empower record);

    int insertSelective(Empower record);

    Empower selectByPrimaryKey(Integer authorizeid);

    List<Empower> selectByRoleID(Integer roleid);

    int updateByPrimaryKeySelective(Empower record);

    int updateByPrimaryKey(Empower record);

}