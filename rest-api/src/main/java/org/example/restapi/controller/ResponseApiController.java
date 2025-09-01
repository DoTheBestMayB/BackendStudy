package org.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.restapi.model.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController // Rest API를 만들 때 주로 사용하며, 응답을 JSON으로만 리턴함
//@Controller // 응답으로 JSON 뿐만 아니라 XML, HTML, protoBuf 등의 타입을 사용할 수 있다.
@RequestMapping("/api/v1")
public class ResponseApiController {

    // path를 적지 않으면 http://localhost:8080/api/v1 에 mapping 된다.
    @GetMapping("")
    // RequestMapping을 이용해서 작성한 GetMapping과 동일한 애노테이션
    // method를 정의하지 않으면 GET, POST, PUT, DELETE 등 모든 method에 mapping 된다.
//    @RequestMapping(path = "", method = RequestMethod.GET)
    // @Controller 애노테이션으로 지정된 Controller에서 응답 타입을 JSON으로 지정할 때 사용
//    @ResponseBody
    public ResponseEntity<UserRequest> user() {
        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");

        log.info("user: {}", user);

        // Http Status code와 함께 데이터를 전달할 때 ResponseEntity를 사용한다.
        var response = ResponseEntity
                .status(HttpStatus.OK) // 상태값 정의
                .header("x-custom", "hi") // 헤더 설정
                .body(user); // body 설정

        return response;
    }
}
