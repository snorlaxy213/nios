package com.springboot.validation;

import com.springboot.commons.Constants;
import com.springboot.validation.impl.UserNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UserNameValidator.class)
public @interface UserNameValidation {
    boolean needsValid() default true;

    Class<?>[] groups() default {};

    String message() default Constants.NAMEERROR;

    Class<? extends Payload>[] payload() default {};
}
