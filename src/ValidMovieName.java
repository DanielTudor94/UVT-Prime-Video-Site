package com.uvt.project.uvt_prime_video.validation;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@NotNull(message = "Numele filmului inexistent!")
@NotBlank(message = "Numele filmului nu poate fi format doar din spatii goale!")
@Size(min = 2, message = "Numele filmului trebuie sa fie de minim doua caractere!")
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface ValidMovieName {

    String message() default "Numele nu este valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
