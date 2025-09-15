package org.example.exception.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.controller.RestApiBController;
import org.example.exception.controller.RestApiController;
import org.example.exception.model.Api;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
// ExceptionHandler 클래스로 만들어주는 애노테이션
// 파라미터에 아무 값도 전달하지 않으면 모든 Controller에서 나오는 예외를 잡아준다.
// basePackages를 이용해서 특정 패키지에 있는 Controller에 대한 예외만 catching
//@RestControllerAdvice(basePackages = "com.example.exception.controller")
// 특정 Controller의 예외만 catching
@RestControllerAdvice(basePackageClasses = {RestApiController.class, RestApiBController.class})
@Order(1)
public class RestApiExceptionHandler {

    // 함수가 처리할 Exception을 선언하는 애노테이션
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity exception(Exception e) {
        log.error("RestApiExceptionHandler", e);

        return ResponseEntity.status(200).build();
    }

    /**
     * Exception은 하위 ExceptionHandler에 전달된다.
     * 따라서 exception 함수에서 Exception을 catching 하지만, IndexOutOfBoundsException은 여기로 전달된다.
     */
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity outOfBound(
            IndexOutOfBoundsException e
    ) {
        // [exception] [nio-8080-exec-1] o.e.e.exception.RestApiExceptionHandler  : IndexOutOfBoundsException ...
        log.error("IndexOutOfBoundsException", e);

        return ResponseEntity.status(200).build();
    }

    // 아래와 같이 설정하면 에러가 발생해도 200 OK로 전달된다.
//    @ExceptionHandler(value = {NoSuchElementException.class})
//    public Api noSuchElement(
//            NoSuchElementException e
//    ) {
//        log.error("", e);
//
//        return Api.builder()
//                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
//                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
//                .build();
//    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api> noSuchElement(
            NoSuchElementException e
    ) {
        log.error("", e);

        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

}
