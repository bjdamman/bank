package com.bank.bank.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "iban", nullable = false)
    private String iban;

    public AccountRepresentation toAccountRepresentation() {
        return AccountRepresentation.builder().iban(this.iban)
                .iban(this.iban).build();
    }
}
