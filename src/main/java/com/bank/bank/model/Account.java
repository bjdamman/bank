package com.bank.bank.model;

import com.bank.bank.validator.Iban;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Iban
    @NotBlank
    @Column(name = "iban", nullable = false)
    private String iban;

}
