package com.philipnerubenko.spring.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

    private String endOfEmail;
    
    @Override
    public void initialize(CheckEmail constraintAnnotation) {
        endOfEmail = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String enteredValue, ConstraintValidatorContext cvc) {
        if (enteredValue == null) {
            return true;
        }
        return enteredValue.endsWith(endOfEmail);
    }
    
}
