package com.fjnu.restaurant.dao;

import com.fjnu.restaurant.bean.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDetailMapper {

    int deleteByPrimaryKey(Integer detailid);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByOrderno(Integer orderno);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

}