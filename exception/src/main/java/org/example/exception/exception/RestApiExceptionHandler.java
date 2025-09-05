package org.example.exception.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// ExceptionHandler 클래스로 만들어주는 애노테이션
@RestControllerAdvice
public class RestApiExceptionHandler {

    // 함수가 처리할 Exception을 선언하는 애노테이션
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity exception(Exception e) {
        /**
         * ERROR 19356 --- [exception] [nio-8080-exec-2] o.e.e.exception.RestApiExceptionHandler  : RestApiExceptionHandler
         *
         * java.lang.IndexOutOfBoundsException: Index: 1 Size: 1
         * ...
         */
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
}
