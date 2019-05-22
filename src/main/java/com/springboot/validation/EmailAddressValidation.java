package com.springboot.validation;


import com.springboot.commons.Constants;
import com.springboot.validation.impl.EmailAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = EmailAddressValidator.class)
public @interface EmailAddressValidation {
    boolean needsValid() default true;

    Class<?>[] groups() default {};

    String message() default Constants.EMAILERROR;

    Class<? extends Payload>[] payload() default {};
}
