package main.lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinSalaryValidator implements ConstraintValidator<MinSalary, Double> {

    private double annotationMin;

    @Override
    public void initialize(MinSalary age) {
        this.annotationMin = age.value();
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        if(aDouble>=annotationMin)
        return true;
        else {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("must be more than " + annotationMin)
                    .addConstraintViolation();
            return false;
        }
    }
}
