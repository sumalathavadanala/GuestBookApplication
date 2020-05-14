/**
 * 
 */
package com.guestbook.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/reject")
	public String rejectGuestRecord(@ModelAttribute @Valid Guest guest, BindingResult bindingResult, Model model,
			@RequestParam(value = "id") Long id) throws Exception {
		logger.info("Entering into rejectGuestRecord()" + id);
		model.addAttribute("guest", guest);
		if (guest.getId() != null) {
			logger.info("Record that need to delete/disable is " + id);
			guestRep.deleteById(guest.getId());
		}
		List<Guest> guestDetails = guestRep.findByEnabledNull();
		model.addAttribute("guestDetails", guestDetails);

		List<Guest> guestApprovedDetails = guestRep.findByEnabledNotNull();
		logger.info("-------" + guestApprovedDetails.size());
		model.addAttribute("guestApprovedDetails", guestApprovedDetails);

		model.addAttribute("message", "Guest Record has been Deleted.");
		return "adminPage";
	}

	@RequestMapping(value = "/approveReject")
	public String approveGuestRecord(@ModelAttribute @Valid Guest guest, BindingResult bindingResult, Model model,
			@RequestParam(value = "id") Long id) throws Exception {
		logger.info("Entering into approveGuestRecord()" + id);
		model.addAttribute("guest", guest);
		if (guest.getId() != null) {
			logger.info("Record that need to approve is " + guest.getId());
			guestRep.editRecordById(guest.getId());
		}
		List<Guest> guestDetails = guestRep.findByEnabledNull();
		model.addAttribute("guestDetails", guestDetails);

		List<Guest> guestApprovedDetails = guestRep.findByEnabledNotNull();
		logger.info("-------" + guestApprovedDetails.size());
		model.addAttribute("guestApprovedDetails", guestApprovedDetails);

		model.addAttribute("message", "Guest Record has been Deleted.");
		return "adminPage";
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
