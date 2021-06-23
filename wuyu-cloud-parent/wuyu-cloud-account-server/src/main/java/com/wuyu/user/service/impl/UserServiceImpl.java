package com.wuyu.user.service.impl;

import com.wuyu.account.service.AccountService;
import com.wuyu.user.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Reference(version = "1.0.0")
    private AccountService accountService;

    public double getUserBalance(Long userId) {
        return accountService.getAccountBalance(userId);
    }
}
