package com.exalt.petclinic.vallidator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.exalt.petclinic.model.Employee.WorkingField;

public class WorkingFieldValidator implements

		ConstraintValidator<ValidateWorkingField, WorkingField> {
	private WorkingField[] subset;

	@Override
	public void initialize(ValidateWorkingField validateWorkingField) {
		this.subset =validateWorkingField.anyOf();
	}

	@Override
	public boolean isValid(WorkingField value, ConstraintValidatorContext context) {
		
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTT");
		//return (value.toString().equals("Owner") || value.toString().equals("Admin")
				//|| value.toString().equals("Worker"));
        return value == null || Arrays.asList(subset).contains(value);

	}

}
