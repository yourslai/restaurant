package com.fjnu.restaurant.dao;

import com.fjnu.restaurant.bean.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMasterMapper {

    int deleteByPrimaryKey(Integer orderno);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(Integer orderno);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);

    List<OrderMaster> getOrderList();

}