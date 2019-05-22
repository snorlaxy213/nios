package com.springboot.validation;

import com.springboot.commons.Constants;
import com.springboot.validation.impl.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberValidation {
    boolean needsValid() default true;

    Class<?>[] groups() default {};

    String message() default Constants.PHONEERROR;

    Class<? extends Payload>[] payload() default {};
}
