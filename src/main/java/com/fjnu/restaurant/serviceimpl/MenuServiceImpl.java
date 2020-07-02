package com.fjnu.restaurant.serviceimpl;

import com.fjnu.restaurant.bean.Menu;
import com.fjnu.restaurant.dao.MenuMapper;
import com.fjnu.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public int addfood(Menu menu) {
        return menuMapper.insertSelective(menu);
    }

    @Override
    public List<Menu> getFoodList(){
        List<Menu> menuList = new ArrayList<Menu>();
        menuList = menuMapper.getFoodList();
        return menuList;
    }

    @Override
    public int deleteByPrimaryKey(int foodID) {
        return menuMapper.deleteByPrimaryKey(foodID);
    }

    @Override
    public Menu selectByPrimaryKey(Integer foodID){
        return menuMapper.selectByPrimaryKey(foodID);
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record){
        return 	menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateStatusByPrimaryKey(Menu record) {	//修改菜品状态
        return menuMapper.updateByPrimaryKeySelective(record);
    }

}
