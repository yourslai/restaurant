package com.fjnu.restaurant.serviceimpl;

import com.fjnu.restaurant.bean.User;
import com.fjnu.restaurant.bean.Userpassword;
import com.fjnu.restaurant.dao.UserMapper;
import com.fjnu.restaurant.dao.UserpasswordMapper;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.service.UserService;
import com.fjnu.restaurant.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserpasswordMapper userpasswordMapper;

    @Override
    @Transactional
    public int addUser(User user, String password) throws BusinessException {
        if(userMapper.insertSelective(user)==1) {
            Userpassword userpassword = new Userpassword();
            userpassword.setUserid(user.getUserid());
            userpassword.setPassword(Md5.encodePassword(password, user.getEmail()));
            return userpasswordMapper.insertSelective(userpassword);
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @Override
    public List<User> queryUser() {
        return userMapper.queryuser();
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(User user) throws BusinessException {
        if(userpasswordMapper.deleteByUserID(user.getUserid())==1) {
            userMapper.deleteByPrimaryKey(user.getUserid());
            return 1;
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @Override
    public User getuserbyid(int userid) {
        return userMapper.getuserbyid(userid);
    }

    @Override
    public int updateuser(User user) throws BusinessException {
        if(userMapper.updateByPrimaryKeySelective(user)==1) {
            return 1;
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @Override
    public List<User> getuserquery(String username) {
        String username1="%"+username+"%";
        return userMapper.getuserquery(username1);
    }

    @Override
    public String getPasswordByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        if(user==null) {
            return null;
        }
        Userpassword userpassword = userpasswordMapper.selectByUserID(user.getUserid());
        return userpassword==null ? null : userpassword.getPassword();
    }
}
