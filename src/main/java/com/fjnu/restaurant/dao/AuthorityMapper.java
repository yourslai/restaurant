package com.fjnu.restaurant.dao;

import com.fjnu.restaurant.bean.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthorityMapper {

    int deleteByPrimaryKey(Integer authorityid);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer authorityid);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    List<Authority> queryAuthorities();

    List<String> getByRole(String role);
}