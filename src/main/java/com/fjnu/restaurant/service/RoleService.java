package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.Role;
import com.fjnu.restaurant.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getRoleList();

    List<String> getRolesByEmail(String email);

    int addRole(Role role, Integer[] authorityid) throws BusinessException;

    Role findRoleByID(Integer roleid);

}
