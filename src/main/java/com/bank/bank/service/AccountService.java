package com.bank.bank.service;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.model.Account;
import com.bank.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    private int checkAccount(String iban) {

        return Util.getInstance().checkBankAccountNumber(iban);

    }

    public String saveAccount(Account account) {

        if (checkAccount(account.getIban()) == 0) {

            accountRepository.save(account);
            return "Account saved";
        } else {


            return "Account incorrect "+account.getIban();
        }

    }
}
