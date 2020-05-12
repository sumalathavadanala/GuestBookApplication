/**
 * 
 */
package com.guestbook.app.web;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.guestbook.app.dao.GuestRepository;
import com.guestbook.app.entity.Guest;
import com.guestbook.app.validator.GuestRegisterFormValidator;

/**
 * @author SV00494257
 *
 */
@Controller
public class RegistrationController implements WebMvcConfigurer {

	Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	GuestRepository guestRep;

	@Autowired
	GuestRegisterFormValidator validator;
	
	 @InitBinder
	    private void initBinder(WebDataBinder binder) {
	        binder.setValidator(validator);
	    }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/guest")
	public String showForm(Guest guest) {
		logger.info("Entering into showForm() : " + guest.getEmail());
		return "registration";
	}

	@PostMapping("/guest")
	public String saveGuestInfo(@ModelAttribute @Valid Guest guest, BindingResult bindingResult, Model model)
			throws Exception {
		logger.info("Entering into saveGuestInfo()" + guest.getEmail());
		validator.validate(guest, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		} else {
			model.addAttribute("guest", guest);
			if (guest.getName() != null) {
				try {
					String email = checkNullString(guest.getEmail());
					if (email != null) {
						// need to send message
					}
				} catch (Exception e) {
					throw new Exception(e);
				}
				guestRep.save(guest);
			}
		}
		model.addAttribute("message", "Guest Registration Successful.");
		return "userInfoPage";
	}

	public String checkNullString(String str) {
		String endString = null;
		if (str == null || str.isEmpty()) {
			System.out.println("yes it is empty");
			str = null;
			Optional<String> opt = Optional.ofNullable(str);
			endString = opt.orElse("None");
			System.out.println("endString : " + endString);
		} else {
			; // do nothing
		}
		return endString;
	}
}
