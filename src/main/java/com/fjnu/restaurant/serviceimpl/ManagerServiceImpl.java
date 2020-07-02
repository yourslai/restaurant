package com.fjnu.restaurant.serviceimpl;

import com.alibaba.druid.util.StringUtils;
import com.fjnu.restaurant.bean.Manager;
import com.fjnu.restaurant.bean.Managerpassword;
import com.fjnu.restaurant.dao.ManagerMapper;
import com.fjnu.restaurant.dao.ManagerpasswordMapper;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.service.ManagerService;
import com.fjnu.restaurant.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    ManagerpasswordMapper managerpasswordMapper;

    @Override
    public List<Manager> querymanager(String page, String username) throws BusinessException {
        int pages;
        try {
            pages = Integer.parseInt(page);
        }catch (NumberFormatException e) {
            throw new BusinessException(EmError.PARAMETER_VALIDATION_ERROR);
        }
        return managerMapper.querymanager((pages-1)*5,username);
    }

    @Override
    public int getManagerNum(String username) {
        return managerMapper.getmanagersize(username);
    }

    @Override
    public int deleteManager(Integer managerid) {
        return managerMapper.deleteByPrimaryKey(managerid);
    }

    @Override
    @Transactional
    public int updateManager(Manager manager, Managerpassword managerpassword) {
        managerMapper.updateByPrimaryKeySelective(manager);
        if(managerpassword.getPassword()!=null && !StringUtils.equals(managerpassword.getPassword(), "")) {
            managerpassword.setPassword(Md5.encodePassword(managerpassword.getPassword(),manager.getEmail()));
            managerpasswordMapper.updateByManagerIDSelective(managerpassword);
        }
        return 1;
    }

    @Override
    @Transactional
    public int addManager(Manager manager, Managerpassword managerpassword) throws BusinessException {
        managerMapper.insertSelective(manager);
        if(manager.getManagerid()==null) {
            throw new BusinessException(EmError.PARAMETER_VALIDATION_ERROR);
        }
        managerpassword.setManagerid(manager.getManagerid());
        managerpassword.setPassword(Md5.encodePassword(managerpassword.getPassword(), manager.getEmail()));
        return managerpasswordMapper.insertSelective(managerpassword);
    }

    @Override
    public Manager getManagerInfo(Integer managerid) {
        return managerMapper.selectByPrimaryKey(managerid);
    }

    @Override
    public Manager getManagerInfoByEmail(String email) {
        return managerMapper.selectByEmail(email);
    }

    @Override
    @Transactional
    public String getPasswordByEmail(String email) {

        Manager manager = managerMapper.selectByEmail(email);
        System.out.println(manager.getTelephone());

        if(manager==null) {
            return null;
        }
        Managerpassword managerpassword = managerpasswordMapper.selectByManagerID(manager.getManagerid());
        if(managerpassword==null) {
            return null;
        }
        return managerpassword.getPassword();
    }

}
