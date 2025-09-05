package org.example.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    /**
     * 서버 오류로 500 Internal server Error 발생
     *
     * Exception이 발생할 수 있는 코드마다 try catch block으로 감싸 대응하는 것은 비효율적이다.
     * ExceptionHandler를 이용해 Global 하게 처리하는 것이 바람직하다.
     */
    @GetMapping(path = "")
    public void hello() {
        var list = List.of("hello");
        var element = list.get(1); // RuntimeException이 발생해도 ExceptionHandler가 catch해서 에러에 대응되는 응답 코드를 생성한다.

//        throw new RuntimeException("run time exception call");
    }
}
