package com.bank.bank.service;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.exception.AccountAlreadyExistsException;
import com.bank.bank.exception.AccountInvalidException;
import com.bank.bank.model.Account;
import com.bank.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    private boolean isIBANAccount(String ibanFromApi) {

        return Util.getInstance().isIBANAccountNumberValid(ibanFromApi);
    }

    public List<Account> listAccount() {

        return accountRepository.findAll();
    }

    public Account saveAccount(Account accountFromApi) {

        Optional<Account> accountFromDb = accountRepository.findByIban(accountFromApi.getIban());

        if (accountFromDb.isPresent()) {
            throw new AccountAlreadyExistsException("Het IBAN nummer is al aanwezig");
        }

        if (isIBANAccount(accountFromApi.getIban())) {

           return accountRepository.save(accountFromApi);

        } else {

            throw new AccountInvalidException("Het IBAN nummer is ongeldig");
        }
    }
}
