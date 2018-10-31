package de.andreaslambert.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> mapValidationService(BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            Map<String, String> fieldErrorMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return new ResponseEntity<>( fieldErrorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
