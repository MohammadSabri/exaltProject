package com.exalt.petclinic.vallidator;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
public @interface ValidatePhoneNumber {
	String message() default "Not valid Phone Number";

	String regex() default "^[0][5][0|2|3|4|5|6|9][0-9]{7}$";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
