package com.vitae.curriculumVitae.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotBeforeValidator implements ConstraintValidator<NotBefore, LocalDate> {

    private LocalDate minDate;
    private String configuredValue;

    @Override
    public void initialize(NotBefore annotation) {
        configuredValue = annotation.value();
        // parse (si el user pasa un formato inválido, lanzará runtime; asegúrate de usar "yyyy-MM-dd")
        minDate = LocalDate.parse(annotation.value());
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return true; // @NotNull debe aplicarse si el campo es obligatorio
        return !value.isBefore(minDate);
    }
}