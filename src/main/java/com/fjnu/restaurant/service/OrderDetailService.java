package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {

    List<OrderDetail> selectByOrderno(Integer orderno);

}
