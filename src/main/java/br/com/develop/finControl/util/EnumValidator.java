package br.com.develop.finControl.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Long> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Enum<?>[] enumValues = this.enumClass.getEnumConstants();
        for (Enum<?> enumConstant : enumValues) {
            try {
                Long enumId = (Long) this.enumClass.getMethod("getId").invoke(enumConstant);
                if (enumId.equals(value)) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}