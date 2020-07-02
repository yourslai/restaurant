package com.fjnu.restaurant.serviceimpl;

import com.alibaba.druid.util.StringUtils;
import com.fjnu.restaurant.bean.User;
import com.fjnu.restaurant.bean.Userpassword;
import com.fjnu.restaurant.dao.UserMapper;
import com.fjnu.restaurant.dao.UserpasswordMapper;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.service.UserpasswordService;
import com.fjnu.restaurant.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserpasswordServiceImpl implements UserpasswordService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserpasswordMapper userpasswordmapper;

    @Override
    @Transactional
    public int modifypass(String newpass,int userid) throws BusinessException {
        User user = userMapper.getuserbyid(userid);
        Userpassword userpassword = new Userpassword();
        userpassword.setUserid(userid);
        userpassword.setPassword(Md5.encodePassword(newpass, user.getEmail()));
        if(userpasswordmapper.updateByPrimaryKeySelective(userpassword)==1) {
            return 1;
        }else {
            throw new BusinessException(EmError.UNKNOWN_ERROR);
        }
    }

    @Override
    @Transactional
    public int checkpass(String oldpass,int userid) throws BusinessException{
        User user = userMapper.getuserbyid(userid);
        if(StringUtils.equals(Md5.encodePassword(oldpass, user.getEmail()), userpasswordmapper.selectByUserID(userid).getPassword())) {
            return 1;
        }else {
            throw new BusinessException(EmError.WRONG_OLDPASSWORD_ERROR);
        }

    }

}
