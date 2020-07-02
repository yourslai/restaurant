package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.Manager;
import com.fjnu.restaurant.bean.Managerpassword;
import com.fjnu.restaurant.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {

    int updateManager(Manager manager, Managerpassword managerpassword);

    int getManagerNum(String username);

    int deleteManager(Integer managerid);

    List<Manager> querymanager(String page, String username) throws BusinessException;

    Manager getManagerInfo(Integer managerid);

    int addManager(Manager manager, Managerpassword password) throws BusinessException;

    String getPasswordByEmail(String email);

    Manager getManagerInfoByEmail(String email);
}
