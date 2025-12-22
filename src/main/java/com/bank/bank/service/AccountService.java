package com.bank.bank.service;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.exception.AccountAlreadyExistsException;
import com.bank.bank.exception.AccountInvalidException;
import com.bank.bank.model.Account;
import com.bank.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    private int checkAccount(String iban) {

        return Util.getInstance().checkBankAccountNumber(iban);

    }

    public String saveAccount(Account account) {

        Optional<Account> existingCustomer = accountRepository.findByIban(account.getIban());

        if (existingCustomer.isPresent()) {
            throw new AccountAlreadyExistsException("Het IBAN nummer is al aanwezig");
        }

        if (checkAccount(account.getIban()) == 0) {

            accountRepository.save(account);
            return "Account saved";
        } else {

            throw new AccountInvalidException("Het IBAN nummer is ongeldig");
        }

    }
}
