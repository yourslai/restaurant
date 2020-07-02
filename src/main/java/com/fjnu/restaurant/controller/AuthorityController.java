package com.fjnu.restaurant.controller;

import com.fjnu.restaurant.bean.Authority;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.AuthorityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiresPermissions("管理员管理")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/admin-rule")
    public String getAuthorityList(Model model){
        List<Authority> authorityList = authorityService.getAllAuthorities();
        model.addAttribute("authoritylist", authorityList);
        model.addAttribute("authoritylistsize", (authorityList==null)?0:authorityList.size());
        return "admin-rule";
    }

    @PostMapping("/addrule")
    @ResponseBody
    public CommonReturnType addAuthority(Authority authority) throws BusinessException {
        if(authorityService.addAuthority(authority)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

}
