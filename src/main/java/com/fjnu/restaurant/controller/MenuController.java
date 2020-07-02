package com.fjnu.restaurant.controller;

import com.fjnu.restaurant.bean.Menu;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiresPermissions("菜单管理")
public class MenuController {

    @Autowired
    MenuService menuService;

    //显示所有菜品
    @RequestMapping("/food-list")
    public String food_list(Model model){
        List<Menu> menuList = new ArrayList<Menu>();
        menuList = menuService.getFoodList();
        int size = menuList.size();
        model.addAttribute("menusize",size);
        model.addAttribute("menuList",menuList);
        return "food-list";
    }

    //编辑菜品页面
    @RequestMapping("/food-edit")
    public String food_edit(Integer foodid, Model model) {
        Menu menu = new Menu();
        menu = menuService.selectByPrimaryKey(foodid);
        model.addAttribute("foodid", foodid);
        model.addAttribute("menu", menu);
        return "food-edit";
    }

    //添加菜品页面
    @RequestMapping("/food-add.html")
    public String food_add() {
        return "food-add";
    }

    //添加菜品
    @RequestMapping("/food_add")
    @ResponseBody
    public CommonReturnType food_add(Menu menu) throws BusinessException {
        if(menuService.addfood(menu)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    //删除菜品&批量删除
    @RequestMapping("/deleteByfoodID")
    @ResponseBody
    public CommonReturnType deleteByPrimaryKey(Integer foodID) throws BusinessException {
        if(menuService.deleteByPrimaryKey(foodID)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    //修改菜品信息
    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public String updateByPrimaryKeySelective(Integer foodid,String foodname,BigDecimal price) throws BusinessException {
        Menu menu = new Menu();
        menu.setFoodid(foodid);
        menu.setFoodname(foodname);
        menu.setPrice(price);
        menuService.updateByPrimaryKeySelective(menu);
        return "success";
    }
    //修改菜品状态
    @RequestMapping("/updafjnuatusByPrimaryKey")
    @ResponseBody
    public String updafjnuatusByPrimaryKey(Integer foodid,Byte status) {
        Menu menu  = new Menu();
        menu.setFoodid(foodid);
        menu.setStatus(status);
        menuService.updateStatusByPrimaryKey(menu);
        return "success";
    }
}
