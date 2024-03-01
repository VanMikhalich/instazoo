package ru.ivan.instazoo.validators.annotations.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivan.instazoo.repositories.UserRepository;
import ru.ivan.instazoo.validators.annotations.CheckUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class CheckUsernameValidator implements ConstraintValidator<CheckUsername, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
