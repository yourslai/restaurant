package com.fjnu.restaurant.controller;

import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.response.CommonReturnType;
import com.fjnu.restaurant.service.ManagerService;
import com.fjnu.restaurant.shiro.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ManagerService managerService;

    @Autowired
    HttpSession session;

    @PostMapping("/logincheck")
    @ResponseBody
    public CommonReturnType loginCheck(@RequestParam String username, @RequestParam String password, @RequestParam String identity) throws BusinessException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Subject subject = SecurityUtils.getSubject();
        UserToken token = new UserToken(username,password,identity);
        try {
            subject.login(token);
            session.setAttribute("name", username);
        }catch (Exception e) {
            throw new BusinessException(EmError.WRONG_USERNAME_OR_PASSWORD);
        }
        return CommonReturnType.create("null");
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login/login.html";
    }
}
