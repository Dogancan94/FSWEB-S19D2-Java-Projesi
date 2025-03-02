package com.workintech.bankauth.dao;

import com.workintech.bankauth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Integer> {
}
