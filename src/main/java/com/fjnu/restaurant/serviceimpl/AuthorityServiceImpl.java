package com.fjnu.restaurant.serviceimpl;

import com.alibaba.druid.util.StringUtils;
import com.fjnu.restaurant.bean.Authority;
import com.fjnu.restaurant.dao.AuthorityMapper;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public List<Authority> getAllAuthorities() {

        return authorityMapper.queryAuthorities();
    }

    @Override
    public int addAuthority(Authority authority) throws BusinessException {
        if(authority.getAuthorityname()==null || StringUtils.equals(authority.getAuthorityname(), "")) {
            throw new BusinessException(EmError.PARAMETER_VALIDATION_ERROR);
        }else {
            return authorityMapper.insertSelective(authority);
        }
    }

    @Override
    public List<String> getPermissionsByRole(String role) {
        return authorityMapper.getByRole(role);
    }

}
