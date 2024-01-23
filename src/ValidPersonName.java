package com.uvt.project.uvt_prime_video.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull(message = "Numele inexistent!")
@Size(min = 3, message = "Numele trebuie sa fie mai lung de doua caractere!")
@Pattern(regexp = "^[a-zA-Z\\s]+", message = "Numele poate contine doar litere!")
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface ValidPersonName {

    String message() default "Numele nu este valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
