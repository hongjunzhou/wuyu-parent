package com.wuyu.account.service;


import com.wuyu.account.service.AccountService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    public double getAccountBalance(Long accountId) {
        return 1000;
    }

    public double getAccountInfo(Long accountId) {
        return 0;
    }
}
