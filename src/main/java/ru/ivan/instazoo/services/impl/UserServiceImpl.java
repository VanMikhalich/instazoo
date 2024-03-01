package ru.ivan.instazoo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivan.instazoo.entities.User;
import ru.ivan.instazoo.entities.enums.Role;
import ru.ivan.instazoo.payload.RegistrationRequest;
import ru.ivan.instazoo.repositories.UserRepository;
import ru.ivan.instazoo.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(RegistrationRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER);

        return userRepository.save(user);
    }
}
