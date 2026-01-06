package com.bank.bank;

import com.bank.bank.model.Account;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BankApplicationTests extends BaseTest {

	// Unit tests
	@Test
	public void should_save_success() throws Exception {
		Account account = new Account();
		account.setIban("NL25BANQ0234567894");
		this.mockMvc.perform(post("/account/save")
						.content(objectMapper.writeValueAsString(account))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void should_save_unsuccessfully() throws Exception {
		Account account = new Account();
		account.setIban("NL25BANQ0234567895");
		this.mockMvc.perform(post("/account/save")
						.content(objectMapper.writeValueAsString(account))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void should_get_all_account() throws Exception {

		this.mockMvc.perform(get("/account/list")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
