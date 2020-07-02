package com.fjnu.restaurant.controller;

import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.UserpasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiresPermissions("用户管理")
public class UserpasswordController {

    @Autowired
    UserpasswordService userpasswordService;

    @RequestMapping("/modifypass")
    @ResponseBody
    public CommonReturnType modifypass(@RequestParam("oldpass") String oldpass, @RequestParam("repass") String newpass, @RequestParam("userid") int userid) throws BusinessException {
        if(userpasswordService.checkpass(oldpass, userid)==1) {
            userpasswordService.modifypass(newpass, userid);
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.WRONG_OLDPASSWORD_ERROR);
        }
    }
}