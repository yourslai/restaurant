package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.User;
import com.fjnu.restaurant.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    int addUser(User user, String password) throws BusinessException;

    List<User> queryUser();

    int deleteByPrimaryKey(User user) throws BusinessException;

    User getuserbyid(int userid);

    int updateuser(User user) throws BusinessException;

    List<User> getuserquery(String username);

    String getPasswordByEmail(String email);

}
