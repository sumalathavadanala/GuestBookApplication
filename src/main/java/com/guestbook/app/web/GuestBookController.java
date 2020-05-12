/**
 * 
 */
package com.guestbook.app.web;

/**
 * @author SV00494257
 *
 */
import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guestbook.app.dao.GuestRepository;
import com.guestbook.app.entity.Guest;
import com.guestbook.app.utils.WebUtils;

@Controller
public class GuestBookController {

	Logger logger = LoggerFactory.getLogger(GuestBookController.class);

	@Autowired
	GuestRepository guestRep;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Welcome to Guest Registration Application!");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.convertToString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		List<Guest> guestDetails = guestRep.findAll();
		model.addAttribute("guestDetails", guestDetails);

		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		logger.info("Entering into loginPage()");
		return "loginPage";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {
		logger.info("Entering into userInfo()");
		// After user login successfully.
		String userName = principal.getName();
		logger.info("User Name: " + userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.convertToString(loginedUser);
		model.addAttribute("userInfo", userInfo);

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.convertToString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "403Page";
	}

	@RequestMapping(value = "/guestRegistration", method = RequestMethod.GET)
	public String registrationPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.convertToString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		logger.info("---------------" + userInfo);
		return "registration";
	}

}
