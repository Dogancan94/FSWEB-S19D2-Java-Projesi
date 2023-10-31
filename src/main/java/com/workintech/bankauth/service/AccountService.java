package com.workintech.bankauth.service;

import com.workintech.bankauth.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account save(Account account);

}
