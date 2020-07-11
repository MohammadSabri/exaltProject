package com.exalt.petclinic.vallidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements

		ConstraintValidator<ValidatePhoneNumber, String> {

	public static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^[0][5][0|2|3|4|5|6|9][0-9]{7}$");

	@Override
	public void initialize(ValidatePhoneNumber validatePhoneNumber) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(value);
		return (matcher.matches() && value.length() == 10);
	}
}
