package com.fjnu.restaurant.serviceimpl;

import com.fjnu.restaurant.bean.Empower;
import com.fjnu.restaurant.bean.Role;
import com.fjnu.restaurant.dao.AuthorityMapper;
import com.fjnu.restaurant.dao.EmpowerMapper;
import com.fjnu.restaurant.dao.RoleMapper;
import com.fjnu.restaurant.error.BusinessException;
import com.fjnu.restaurant.error.EmError;
import com.fjnu.restaurant.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    EmpowerMapper empowerMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    @Transactional
    public List<Role> getRoleList() {
        List<Role> role = roleMapper.queryAllRole();
        for (Role r : role) {
            StringBuilder authority = new StringBuilder();
            List<Empower> empower = empowerMapper.selectByRoleID(r.getRoleid());
            for (Empower e : empower) {
                authority.append(authorityMapper.selectByPrimaryKey(e.getAuthorityid()).getAuthorityname()+" ");
            }
            r.setAuthority(authority.toString());
        }
        return role;
    }

    @Override
    @Transactional
    public int addRole(Role role, Integer[] authorityid) throws BusinessException {
        roleMapper.insertSelective(role);
        if(role.getRoleid()==null) {
            throw new BusinessException(EmError.PARAMETER_VALIDATION_ERROR);
        }
        for (Integer a : authorityid) {
            Empower e = new Empower();
            e.setAuthorityid(a);
            e.setRoleid(role.getRoleid());
            empowerMapper.insertSelective(e);
        }
        return 1;
    }

    @Override
    public List<String> getRolesByEmail(String email) {
        return roleMapper.getRoleByEmail(email);
    }

    @Override
    public Role findRoleByID(Integer roleid) {
        return roleMapper.selectByPrimaryKey(roleid);
    }

}
