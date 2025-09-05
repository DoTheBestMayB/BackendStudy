package org.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/b")
public class RestApiBController {

    // localhost:8080/api/b/hello
    @GetMapping("/hello")
    public void hello() {
        throw new NumberFormatException("number format exception");
    }


/*    // Global Exception Handler가 아닌 Controller에서 직접 catching 하는 방법
    // hello 함수에서 발생한 exception은 여기서 catching 된다.
    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity numberFormatException(NumberFormatException e) {
        log.error("RestApiBController", e);

        return ResponseEntity.ok().build();
    }*/
}
