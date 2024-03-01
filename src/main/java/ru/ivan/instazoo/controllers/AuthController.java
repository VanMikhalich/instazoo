package ru.ivan.instazoo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivan.instazoo.payload.LoginRequest;
import ru.ivan.instazoo.payload.RegistrationRequest;
import ru.ivan.instazoo.payload.response.Info;
import ru.ivan.instazoo.security.JWTTokenProvider;
import ru.ivan.instazoo.security.SecurityConstants;
import ru.ivan.instazoo.services.UserService;
import ru.ivan.instazoo.validators.ResponseErrorValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ResponseErrorValidator responseErrorValidator;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping("signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ResponseEntity<>(responseErrorValidator.validate(bindingResult), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generate(authentication);
        return new ResponseEntity<>(new Info(jwt, true), HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return new ResponseEntity<>(responseErrorValidator.validate(bindingResult), HttpStatus.BAD_REQUEST);
        userService.createUser(registrationRequest);
        return ResponseEntity.ok(new Info("User register successfully", true));
    }
}
