package com.fjnu.restaurant.serviceimpl;

import com.fjnu.restaurant.bean.OrderMaster;
import com.fjnu.restaurant.dao.OrderMasterMapper;
import com.fjnu.restaurant.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Override
    public List<OrderMaster> getOrderList() {
        return orderMasterMapper.getOrderList();
    }

    @Override
    public int deleteByPrimaryKey(Integer orderno) {
        return orderMasterMapper.deleteByPrimaryKey(orderno);
    }

    @Override
    public OrderMaster selectByPrimaryKey(Integer orderno){
        return orderMasterMapper.selectByPrimaryKey(orderno);
    }

}
