package org.example.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.validator.PhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { PhoneNumberValidator.class}) // 이 애노테이션을 검증할 클래스 지정
@Target({ElementType.FIELD}) // 애노테이션 적용 대상
@Retention(RetentionPolicy.RUNTIME) // 실행 중에만 애노테이션이 동작하도록 설정
public @interface PhoneNumber {
    String message() default "핸드폰 번호 양식에 맞지 않습니다 ex) 000-0000-0000";
    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";


    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
