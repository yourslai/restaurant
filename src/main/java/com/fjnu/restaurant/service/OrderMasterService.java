package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.OrderMaster;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderMasterService {

    List<OrderMaster> getOrderList();

    int deleteByPrimaryKey(Integer orderno);

    OrderMaster selectByPrimaryKey(Integer orderno);

}
