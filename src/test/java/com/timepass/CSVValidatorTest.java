package com.timepass;

import com.timepass.model.User;
import com.timepass.response.ValidatorResponse;
import com.timepass.validator.DefaultValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVValidatorTest {

    private final ValidatorResponse<User> userValidatorResponse;

    @SneakyThrows
    private byte[] loadFile(String fileName) {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(fileName)).readAllBytes();
    }
    public CSVValidatorTest() {
        CSVValidator<User> csvValidator = CSVValidator.<User>builder()
                .file(loadFile("Users.csv"))
                .validator(new DefaultValidator<>())
                .modelClass(User.class)
                .build();

        userValidatorResponse = csvValidator.parseAndValidate();
    }

    @Test
    public void shouldValidateSuccessfulRecords() {
        assertEquals(3,userValidatorResponse.successfulRecords().size());
    }

    @Test
    public void shouldValidateFailureRecords() {
        assertEquals(3,userValidatorResponse.failureRecords().size());
    }

    @Test
    public void shouldValidateAllRecords() {
        assertEquals(6,userValidatorResponse.records().size());
    }
}
