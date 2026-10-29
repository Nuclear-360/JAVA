package br.com.nuclear360.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOfValidator implements ConstraintValidator<MultipleOf, Integer>{
	
	private int multipleOf;
	
	@Override
	public void initialize(MultipleOf annotation) {
		this.multipleOf = annotation.value();
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value % multipleOf == 0;
	}
	
	
}
