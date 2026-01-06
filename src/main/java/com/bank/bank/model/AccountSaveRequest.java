package com.bank.bank.model;

import com.bank.bank.validator.Iban;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * @author shuang.kou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSaveRequest {
    @Iban
    @NotBlank
    private String iban;

    public Account toAccount() {
        return Account.builder()
                .iban(this.getIban())
                .build();
    }
}