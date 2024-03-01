package ru.ivan.instazoo.services;

import ru.ivan.instazoo.entities.User;
import ru.ivan.instazoo.payload.RegistrationRequest;

public interface UserService {
    User createUser(RegistrationRequest request);
}
