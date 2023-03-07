package com.timepass.validator;

import com.timepass.model.Record;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.stream.Collectors;

public class DefaultValidator<T> implements com.timepass.validator.Validator<T> {

    private final jakarta.validation.Validator validator;

    public DefaultValidator() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validatorFactory.getValidator();
        }
    }

     public Record<T> validate(T data) {

        String error = validator.validate(data)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));

        return Record.<T>builder()
                .data(data)
                .error(error)
                .build();
    }
}
