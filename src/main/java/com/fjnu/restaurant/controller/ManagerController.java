package com.fjnu.restaurant.controller;


import com.fjnu.restaurant.bean.Manager;
import com.fjnu.restaurant.bean.Managerpassword;
import com.fjnu.restaurant.bean.Role;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.ManagerService;
import com.fjnu.restaurant.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiresPermissions("管理员管理")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Autowired
    RoleService roleService;

    @Autowired
    HttpSession session;

    @RequestMapping("/admin-list")
    public String getManagerList(@RequestParam(defaultValue="1") String page, @RequestParam(defaultValue="") String username, Model model) throws BusinessException {
        List<Manager> managerlist = managerService.querymanager(page,username);
        int num = managerService.getManagerNum(username);
        model.addAttribute("managerlist",managerlist);
        model.addAttribute("page",page);
        model.addAttribute("num",num);
        return "admin-list";
    }

    @RequestMapping("/admin-edit")
    public String editManagerUI(@RequestParam int managerid, Model model) {
        Manager manager = managerService.getManagerInfo(managerid);
        Role role = roleService.findRoleByID(manager.getRole());
        model.addAttribute("manager", manager);
        model.addAttribute("rolename", role.getRolename());
        return "admin-edit";
    }

    @PostMapping("/addmanager")
    @ResponseBody
    public CommonReturnType addmanager(Manager manager, Managerpassword managerpassword) throws BusinessException {
        if(managerService.addManager(manager,managerpassword)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @PostMapping("/updatemanager")
    @ResponseBody
    public CommonReturnType updateManager(Manager manager, Managerpassword managerpassword) throws BusinessException {
        if(managerService.updateManager(manager,managerpassword)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @PostMapping("/deletemanager")
    @ResponseBody
    public CommonReturnType deleteManager(@RequestParam int managerid) throws BusinessException {
        if(managerService.deleteManager(managerid)==1) {
            return CommonReturnType.create("null");
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @RequestMapping("/manager-show")
    public String getManagerInfo(Model model) {
        String email = (String) session.getAttribute("name");
        Manager manager = managerService.getManagerInfoByEmail(email);
        model.addAttribute("manager", manager);
        return "manager-show";
    }
}
