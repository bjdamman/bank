package com.bank.bank;

import com.bank.bank.model.AccountSaveRequest;
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
		AccountSaveRequest userRegisterRequest = new AccountSaveRequest("NL25BANQ0234567894");
		this.mockMvc.perform(post("/account/save")
						.content(objectMapper.writeValueAsString(userRegisterRequest))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void should_save_unsuccessfully() throws Exception {
		AccountSaveRequest userRegisterRequest = new AccountSaveRequest("NL25BANQ0234567894");

		this.mockMvc.perform(post("/account/save")
						.content(objectMapper.writeValueAsString(userRegisterRequest))
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
