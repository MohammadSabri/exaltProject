package com.exalt.petclinic.vallidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements

		ConstraintValidator<ValidateEmail, String> {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

//	private String message;
	// private String regex;

	@Override
	public void initialize(ValidateEmail validateEmail) {
		// this.message = validateEmail.message();
		// this.regex = validateEmail.regex();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		System.out.println(
				"the value of the test email=" + value + " and the value pf the boolean is equal to " + matcher.matches());
		return  matcher.matches();
	}

}
