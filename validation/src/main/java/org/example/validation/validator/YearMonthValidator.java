package org.example.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.validation.annotation.YearMonth;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // "2025-09-16T00:00:00" yyyy-MM-ddTHH:mm:ss


        var reValue = s + "01";
        var rePattern = pattern + "dd";

        try {
            LocalDate date = LocalDate.parse(reValue, DateTimeFormatter.ofPattern(rePattern));

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
