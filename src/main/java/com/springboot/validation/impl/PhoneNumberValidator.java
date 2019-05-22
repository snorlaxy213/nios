package com.springboot.validation.impl;


import com.springboot.validation.PhoneNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {
    private boolean needsValid;

    @Override
    public void initialize(PhoneNumberValidation constraintAnnotation) {
        this.needsValid = constraintAnnotation.needsValid();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^1[345678]\\d{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
}
