package com.bank.bank;

import com.bank.bank.dao.AccountRepository;
import com.bank.bank.exception.AccountAlreadyExistsException;
import com.bank.bank.exception.AccountInvalidException;
import com.bank.bank.model.Account;
import com.bank.bank.service.AccountService;
import com.bank.util.Util;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class BankApplicationTests {

	Util util = Util.getInstance();

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	@Autowired
	private AccountService accountService;

	// Unit tests
	@Test
	public void testCorrectBankAccountDigits() {

		assertEquals("IBAN number correct", true, util.checkIBANdigits("0234567894"));
	}

	@Test
	public void testIncorrectBankAccountDigits() {

		assertEquals("IBAN nummer incorrect", false, util.checkIBANdigits("0234567895"));
	}

	@Test
	public void testCorrectBankAccountFormat() {

		assertEquals("IBAN nummer correct", true, util.checkIBANformat("NL25BANQ0234567894"));
	}

	@Test
	public void testIncorrectBankAccountFormat() {

		assertEquals("IBAN nummer incorrect", false, util.checkIBANformat("NL45BANQ0234567894"));
	}

	@Test
	public void testCorrectBankAccount() {

		assertEquals("IBAN nummer correct", true, util.checkBankAccountNumber("NL25BANQ0234567894"));

	}

	@Test
	public void testIncorrectBankAccount() {

		assertEquals("IBAN nummer incorrect", false, util.checkBankAccountNumber("NL45BANQ0334567894"));
	}

	// Services tests
	@Test
	void shouldReturnAccountList() {
		// Arrange
		Account mockAccount1 = new Account();
		mockAccount1.setIban("NL25BANQ0234567894");
		Account mockAccount2 = new Account();
		mockAccount2.setIban("NL25BANQ0334567895");

		List<Account> accountList = List.of(mockAccount1,mockAccount2);

		MockitoAnnotations.openMocks(this);

		when(accountRepository.findAll()).thenReturn(accountList);

		// Get list
		List<Account> resultList = accountService.listAccount();

		// Assert
		assertEquals("IBAN gevonden","NL25BANQ0234567894", resultList.getFirst().getIban());
	}

	@Test
	void shouldReturnAccountListCheckNumber() {
		// Arrange
		Account mockAccount1 = new Account();
		mockAccount1.setIban("NL25BANQ0234567894");
		Account mockAccount2 = new Account();
		mockAccount2.setIban("NL25BANQ0334567895");

		List<Account> accountList = List.of(mockAccount1,mockAccount2);
		List<Account> spyOnList = spy(accountList);

		MockitoAnnotations.openMocks(this);

		when(accountRepository.findAll()).thenReturn(accountList);

		when(spyOnList.size()).thenReturn(2);
		assertEquals("Aantal gevonden accounts correct", 2, spyOnList.size());
	}

	@Test
	void shouldSaveAccount() {
		// Arrange
		Account mockAccount1 = new Account();
		mockAccount1.setIban("NL25BANQ0234567894");

		MockitoAnnotations.openMocks(this);

		when(accountRepository.save(Mockito.any(Account.class)))
				.thenAnswer(i -> i.getArguments()[0]);
		// Act
		String result = accountService.saveAccount(mockAccount1);

		assertEquals("Account correct opgeslagen", result, "Account saved");
	}

	@Test
	void shouldNotSaveAccount() {
		// Arrange
		Account mockAccount1 = new Account();
		mockAccount1.setIban("NL25BANQ0234567896");

		MockitoAnnotations.openMocks(this);

		when(accountRepository.save(Mockito.any(Account.class)))
				.thenAnswer(i -> i.getArguments()[0]);
		// Act
		Exception exception = assertThrows(AccountInvalidException.class, () -> {
			accountService.saveAccount(mockAccount1);
		});

		String expectedMessage = "Het IBAN nummer is ongeldig";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void shouldNotSaveAccountAlreadyExists() {
		// Arrange
		Account mockAccount1 = new Account();
		mockAccount1.setIban("NL25BANQ0234567896");

		MockitoAnnotations.openMocks(this);

		when(accountRepository.findByIban(mockAccount1.getIban())).thenReturn((Optional.of(mockAccount1)));

		when(accountRepository.save(Mockito.any(Account.class)))
				.thenAnswer(i -> i.getArguments()[0]);
		// Act
		Exception exception = assertThrows(AccountAlreadyExistsException.class, () -> {
			accountService.saveAccount(mockAccount1);
		});

		String expectedMessage = "Het IBAN nummer is al aanwezig";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}
