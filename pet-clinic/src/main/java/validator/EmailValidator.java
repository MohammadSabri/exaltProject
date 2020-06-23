package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
ConstraintValidator<ValidateEmail, String> {
	private String message;
	private String regex;
	@Override
    public void initialize(ValidateEmail validateEmail) {
		this.message=validateEmail.message();
		this.regex=validateEmail.regex();
    }
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	System.out.println("the value of the test email="+value);
		return value.matches(regex);
	}

}
