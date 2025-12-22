package com.bank.bank.controller;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/list")
    public String listAccount() {

        //return accountRepository.findAll();

        return "test";
    }


    @PostMapping("/save")
    public String saveAccount(@RequestBody Account account) {

        return accountService.saveAccount(account);

    }


}
