package main.lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    private int annotationAge;

    @Override
    public void initialize(Age age) {
        this.annotationAge = age.value();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintContext) {

        if ((LocalDate.now().minusYears(annotationAge)).isBefore(localDate)) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("must be oder than " + annotationAge + " years")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
