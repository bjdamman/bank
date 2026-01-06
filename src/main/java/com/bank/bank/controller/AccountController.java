package com.bank.bank.controller;

import com.bank.bank.model.AccountRepresentation;
import com.bank.bank.model.AccountSaveRequest;
import com.bank.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<Page<AccountRepresentation>> listAccount(
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

            Page<AccountRepresentation> allAccount = accountService.listAccount(pageNum, pageSize);
            return ResponseEntity.ok().body(allAccount);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveAccount(@RequestBody @Valid AccountSaveRequest accountSaveRequest) {

        accountService.saveAccount(accountSaveRequest);
        return ResponseEntity.ok().build();
    }

}
