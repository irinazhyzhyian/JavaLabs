package main.lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FutureDayValidator implements ConstraintValidator<FutureDay, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(localDate.isBefore(LocalDate.now())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("It`s can`t be past!")
                    .addConstraintViolation();
            return false;
        }
        else
            return true;
    }
}
