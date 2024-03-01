package ru.ivan.instazoo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseErrorValidator {
    public Map<String, String> validate(BindingResult result){
        if(result.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorsMap.put(error.getField() + "Error", error.getDefaultMessage());
            }
            return errorsMap;
        }
        return null;
    }
}
