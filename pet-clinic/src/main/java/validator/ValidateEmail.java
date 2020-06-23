package validator;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
@Retention(RUNTIME)
@Constraint(validatedBy =EmailValidator.class)
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER})
public @interface ValidateEmail {
	String message() default "Not valid Email";
	String regex() default "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
}
