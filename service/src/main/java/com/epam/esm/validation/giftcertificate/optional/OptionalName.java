package com.epam.esm.validation.giftcertificate.optional;


import org.hibernate.validator.constraints.Length;

import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Length(min = 4,max = 80)
@Target({ElementType.TYPE_USE, METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptionalName {
    //error message
    String message() default "";
    //represents group of constraints
    Class<?>[] groups() default {};
    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};
}
