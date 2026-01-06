package com.bank.bank.controller;

import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<Page<Account>> listAccount(
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

            Page<Account> allAccount = accountService.listAccount(pageNum, pageSize);
            return ResponseEntity.ok().body(allAccount);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveAccount(@RequestBody @Valid Account account) {

        accountService.saveAccount(account);
        return ResponseEntity.ok().build();
    }

}
