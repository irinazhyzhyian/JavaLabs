package main.lab4.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FutureDayValidator.class)
//for limit the use of annotations
@Target({ElementType.PARAMETER, ElementType.FIELD})
//will allow us to maintain JVM annotation at runtime
@Retention(RetentionPolicy.RUNTIME)

public @interface FutureDay {

    //message that returns the default violation messages
    String message() default "{lab4.service.FutureDayValidator.message}";

    //groups to restrict the set of constraints applied during validation
    Class<?>[] groups() default {};

    //payload used by clients of the Bean Validation API to assign custom payload objects to a constraint
    Class<? extends Payload>[] payload() default {};
}
