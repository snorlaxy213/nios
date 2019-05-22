package com.springboot.validation.impl;

import com.springboot.validation.UserNameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserNameValidation, String> {
    private boolean needsValid;

    @Override
    public void initialize(UserNameValidation constraintAnnotation) {
        this.needsValid = constraintAnnotation.needsValid();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
}
