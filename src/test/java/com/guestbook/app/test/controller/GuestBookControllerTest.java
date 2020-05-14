/**
 * 
 */
package com.guestbook.app.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.guestbook.app.controller.GuestBookController;

/**
 * @author SV00494257
 *
 */
public class GuestBookControllerTest {
	
	@InjectMocks
	private GuestBookController guestBookController;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(guestBookController).build();
    }
	
	@Test
	public void testGuestBookController() throws Exception{
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	@Test
	public void testLogin() throws Exception{
		this.mockMvc.perform(get("/login")).andExpect(status().isOk());
	}
	
	@Test
	public void testLoginSuccessful() throws Exception{
		this.mockMvc.perform(get("/logoutSuccessful")).andExpect(status().isOk());
	}

}
