package com.guestbook.app.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

 
import java.util.ArrayList;
import java.util.List;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.guestbook.app.entity.Guest;

public class RepositoryTest {
	
	@InjectMocks
	Guest guest;

	@Mock
	GuestRepository rep;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void getGuestNameTest()
    {
		Guest guest = rep.findByName("suma");
		 assertEquals("suma", guest.getName());
    }
}