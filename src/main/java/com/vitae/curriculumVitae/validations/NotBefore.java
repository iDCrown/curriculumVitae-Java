package com.vitae.curriculumVitae.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBeforeValidator.class)
@Documented
public @interface NotBefore {
    String message() default "La fecha no puede ser anterior a {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    /**
     * Fecha en formato ISO: "yyyy-MM-dd"
     */
    String value();
}