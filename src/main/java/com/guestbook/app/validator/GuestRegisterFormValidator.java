/**
 * 
 */
package com.guestbook.app.validator;

/**
 * @author SV00494257
 *
 */
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.guestbook.app.dao.GuestRepository;
import com.guestbook.app.entity.Guest;

@Component
public class GuestRegisterFormValidator implements Validator {

	Logger logger = LoggerFactory.getLogger(GuestRegisterFormValidator.class);

	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	 @Autowired
	 GuestRepository guestRep;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Guest.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.info("Entering into validate() method");
		Guest guestForm = (Guest) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.guest.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.guest.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.guest.phone");
		
		if(guestRep.findByName(guestForm.getName()) != null){
			errors.rejectValue("name", "Duplicate.registerForm.username");
		}

		if (!emailValidator.isValid(guestForm.getEmail())) {
			errors.rejectValue("email", "Pattern.guest.email");
		}
	}
}
