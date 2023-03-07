package com.timepass.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class User {

    @Pattern(regexp = "^[\\w.]*@[\\w]*.[a-zA-Z]{2,3}$")
    @CsvBindByName(column = "Email")
    private String email;

    @Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
    @CsvBindByName(column = "Phone Number")
    private String phoneNumber;

    @CsvBindByName(column = "Birth Date")
    @CsvDate(value = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Pattern(regexp = "^[a-zA-Z0-9.]*$")
    @CsvBindByName(column = "First Name")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z0-9.]*$")
    @CsvBindByName(column = "Last Name")
    private String lastName;
}
