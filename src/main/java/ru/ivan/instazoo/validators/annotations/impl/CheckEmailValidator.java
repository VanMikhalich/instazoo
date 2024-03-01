package ru.ivan.instazoo.validators.annotations.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.instazoo.repositories.UserRepository;
import ru.ivan.instazoo.validators.annotations.CheckEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

    private final UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
