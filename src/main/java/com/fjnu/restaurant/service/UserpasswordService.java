package com.fjnu.restaurant.service;

import com.fjnu.restaurant.error.BusinessException;
import org.springframework.stereotype.Service;

@Service
public interface UserpasswordService {

    int modifypass(String newpass, int userid) throws BusinessException;

    int checkpass(String oldpass, int userid) throws BusinessException;

}
