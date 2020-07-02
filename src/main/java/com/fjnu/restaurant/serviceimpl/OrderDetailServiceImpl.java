package com.fjnu.restaurant.serviceimpl;

import com.fjnu.restaurant.bean.OrderDetail;
import com.fjnu.restaurant.dao.OrderDetailMapper;
import com.fjnu.restaurant.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public List<OrderDetail> selectByOrderno(Integer orderno) {
        return orderDetailMapper.selectByOrderno(orderno);
    }

}
