package com.bank.bank;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.exception.AccountAlreadyExistException;
import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UnitTests {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    @Autowired
    private AccountService accountService;

    @Test
    public void shouldReturnAccountList() {
        // Arrange
        Account mockAccount1 = new Account();
        mockAccount1.setIban("NL25BANQ0234567894");
        Account mockAccount2 = new Account();
        mockAccount2.setIban("NL25BANQ0334567895");

        List<Account> accountList = List.of(mockAccount1,mockAccount2);

        MockitoAnnotations.openMocks(this);

        List<Account> accounts = List.of(mockAccount1,mockAccount2);
        Page<Account> pagedAccounts = new PageImpl(accounts);
        when(accountRepository.findAll(org.mockito.ArgumentMatchers.isA(Pageable.class))).thenReturn(pagedAccounts);

        Page<Account> pagedAccountsReturn = accountService.listAccount(0,10);
        // Assert
        assertEquals("IBAN gevonden","NL25BANQ0234567894", pagedAccountsReturn.getContent().getFirst().getIban());
    }

    @Test
    public void shouldNotSaveAccountDuplicate() {
        // Arrange
        Account mockAccount1 = new Account();
        mockAccount1.setIban("NL25BANQ0234567894");

        MockitoAnnotations.openMocks(this);

        when(accountRepository.existsByIban(Mockito.any()))
                .thenReturn(true);

        when(accountRepository.save(Mockito.any(Account.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        // Act
        Exception exception = assertThrows(AccountAlreadyExistException.class, () -> {
            accountService.saveAccount(mockAccount1);
        });

        String expectedMessage = "Iban is al aanwezig";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
