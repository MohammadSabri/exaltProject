package com.exalt.petclinic.vallidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements

		ConstraintValidator<ValidateEmail, String> {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public void initialize(ValidateEmail validateEmail) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		return matcher.matches();
	}

}
