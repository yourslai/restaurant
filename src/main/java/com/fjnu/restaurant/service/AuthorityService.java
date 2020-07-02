package com.fjnu.restaurant.service;

import com.fjnu.restaurant.bean.Authority;
import com.fjnu.restaurant.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {

    List<Authority> getAllAuthorities();

    List<String> getPermissionsByRole(String role);

    int addAuthority(Authority authority) throws BusinessException;

}
