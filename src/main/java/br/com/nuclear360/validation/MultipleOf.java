package br.com.nuclear360.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {MultipleOfValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface MultipleOf {
	
	int value();
	
	String message() default "deve ser multiplo de {value}";
			
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
}
