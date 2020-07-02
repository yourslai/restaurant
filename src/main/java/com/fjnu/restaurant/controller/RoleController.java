package com.fjnu.restaurant.controller;


import com.fjnu.restaurant.bean.Authority;
import com.fjnu.restaurant.bean.Role;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.AuthorityService;
import com.fjnu.restaurant.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiresPermissions("管理员管理")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/admin-role")
    public String getRoleList(Model model) {
        List<Role> rolelist = roleService.getRoleList();
        model.addAttribute("rolelist", rolelist);
        model.addAttribute("rolelistsize", rolelist.size());
        return "admin-role";
    }

    @RequestMapping("/role-add")
    public String addRoleUI(Model model) {
        List<Authority> authorityList = authorityService.getAllAuthorities();
        model.addAttribute("authoritylist", authorityList);
        return "role-add";
    }

    @RequestMapping("/addrole")
    @ResponseBody
    public CommonReturnType addRole(Role role, Integer[] authorityid) throws BusinessException {
        if(roleService.addRole(role, authorityid)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }
}
