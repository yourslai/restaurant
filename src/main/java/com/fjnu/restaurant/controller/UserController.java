package com.fjnu.restaurant.controller;


import com.fjnu.restaurant.bean.User;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.UserService;
import com.fjnu.restaurant.service.UserpasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequiresPermissions("用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserpasswordService userpasswordService;

    @RequestMapping("/adduser")
    @ResponseBody
    public CommonReturnType adduser(User user,@RequestParam("pass") String password) throws BusinessException {
        if(userService.addUser(user, password)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/member-list")
    public String getalluser(Model model) throws BusinessException {
        List<User> user=userService.queryUser();
        if(user.size()>=1) {
            model.addAttribute("userlist", user);
            model.addAttribute("usernum", user.size());
            return "member-list";
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/member-edit")
    public String getuser(Model model, @RequestParam("userid") int userid) throws BusinessException{
        User user=userService.getuserbyid(userid);
        if(user!=null) {
            model.addAttribute("user",user);
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
        return "member-edit";
    }

    @RequestMapping("/member-password")
    public String getuserto(Model model, @RequestParam("userid") int userid) throws BusinessException{
        User user=userService.getuserbyid(userid);
        if(user!=null) {
            model.addAttribute("user",user);
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
        return "member-password";
    }


    @RequestMapping("/edituser")
    @ResponseBody
    public CommonReturnType memberedit(User user) throws BusinessException {
        if(userService.updateuser(user)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/deluser")
    @ResponseBody
    public CommonReturnType deluser(User user) throws BusinessException {
        if(userService.deleteByPrimaryKey(user)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/selectuser")
    public String selectuser(Model model, @RequestParam("username") String username) {
        List<User> userlist=userService.getuserquery(username);
        model.addAttribute("userlist",userlist);
        model.addAttribute("usernum", userlist.size());
        return "member-list";
    }
}
