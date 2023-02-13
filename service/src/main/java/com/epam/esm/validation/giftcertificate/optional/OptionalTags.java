package com.epam.esm.validation.giftcertificate.optional;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Size(max = 100)
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.TYPE_USE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface OptionalTags {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
