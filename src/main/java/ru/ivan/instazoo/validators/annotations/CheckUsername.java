package ru.ivan.instazoo.validators.annotations;

import ru.ivan.instazoo.validators.annotations.impl.CheckEmailValidator;
import ru.ivan.instazoo.validators.annotations.impl.CheckUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckUsernameValidator.class)
public @interface CheckUsername {
    String message() default "User with this username is present";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}