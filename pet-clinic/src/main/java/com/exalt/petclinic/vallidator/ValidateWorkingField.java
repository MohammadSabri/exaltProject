package com.exalt.petclinic.vallidator;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.exalt.petclinic.model.Employee.WorkingField;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WorkingFieldValidator.class)
@Documented
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
public @interface ValidateWorkingField {
	String message() default "Working filed is one of three (Owner,Admin,Worker)";

	WorkingField[] anyOf();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
