package com.bank.bank.service;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.exception.AccountAlreadyExistException;
import com.bank.bank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AccountService {

    public static final String IBAN = "iban:";
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    public Page<Account> listAccount(int pageNum, int pageSize) {

        return accountRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveAccount(Account account) {

        ensureAccountNotExist(account.getIban());

        accountRepository.save(account);
    }

    private void ensureAccountNotExist(String iban) {

        if (accountRepository.existsByIban(iban)) {
            throw new AccountAlreadyExistException(Map.of(IBAN, iban));
        }
    }
}
