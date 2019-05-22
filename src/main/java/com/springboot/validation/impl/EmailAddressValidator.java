package com.springboot.validation.impl;

import com.springboot.validation.EmailAddressValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddressValidator implements ConstraintValidator<EmailAddressValidation, String> {

    private boolean needsValid;

    @Override
    public void initialize(EmailAddressValidation constraintAnnotation) {
        this.needsValid = constraintAnnotation.needsValid();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
}
