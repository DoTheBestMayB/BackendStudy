package org.example.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.validation.validator.YearMonthValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {YearMonthValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank // 공백을 허용하지 않음
//@Size(min = 8, max = 8) // 사용자가 2025-09-16과 같이 보낼 수도 있기 때문에 size는 지정하지 않는 것이 좋다.
public @interface YearMonth {
    String message() default "year month 양식에 맞지 않습니다. ex) 202509";

    String pattern() default "yyyyMM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
