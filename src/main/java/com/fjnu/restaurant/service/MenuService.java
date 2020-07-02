package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    int addfood(Menu menu);

    List<Menu> getFoodList();

    int deleteByPrimaryKey(int foodID);

    Menu selectByPrimaryKey(Integer foodID);

    int updateByPrimaryKeySelective(Menu record);

    int updateStatusByPrimaryKey(Menu record);

}
