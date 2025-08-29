package org.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.restapi.model.BookQueryParam;
import org.springframework.web.bind.annotation.*;

/**
 * Spring은 Annotation으로 역할을 지정
 * 이전에는 xml로 설정함
 * 8080 포트가 이미 사용 중인 경우, cmd 창에서 다음과 같은 명령어로 종료시킬 수 있다.
 * 1. `netstat -ano | findstr 8080`를 입력해서 8080 포트를 사용중인 process id를 확인한다.
 * 2. `taskkill /f /pid {pid Number}` 명령어로 해당 process를 종료한다.
 */
@Slf4j
@RestController // RestController 설정
@RequestMapping("/api") // 요청 받을 주소
public class RestApiController {


    /**
     * 테스트 주소 : http://localhost:8080/api/hello
     * html은 문자열이지만, Content-Type이 "text/html;charset=UTF-8"이기 때문에, 브라우저가 html로 표시할 수 있다.
     * 서버는 문자열을 생성해서 리턴하고, 이것을 어떻게 표시할지는 수신하는 클라리언트(브라우저)의 역할이다.
     * 이미지, 동영상도 텍스트로 전달하고, 이것을 바이트로 변환한 후 인코딩해서 이미지, 동영상으로 표시한다.
     */
    @GetMapping(path = "/hello")
    public String hello() {
        var html = "<html> <body> <h1> Hello Spring Boot </h1> </body> </html>";

        return html;
    }

    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable(name = "message") String msg,
            @PathVariable int age,
            @PathVariable boolean isMan
    ) {
        System.out.println("echo message: " + msg);

        return msg.toUpperCase();
    }

    // http://localhost:8080/api/book?category=IT&issuedYear=2025&issued-month=08&issued_day=24
    @GetMapping(path = "/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear, // url에 대문자를 적지 않는 것이 원칙이지만, 편의를 위해 사용하는 경우
            @RequestParam(name = "issued-month") String issuedMonth, // url에 대문자를 적지 않고 원칙에 따라 -를 사용하는 경우
            @RequestParam String issued_day // 추천하지 않는 방법
    ) {

    }

    // http://localhost:8080/api/book?category=IT&issuedYear=2025&issuedMonth=08&issuedDay=24
    @GetMapping(path = "/book2")
    public void queryParamDto(
            BookQueryParam bookQueryParam
    ) {

    }

    //    @DeleteMapping("delete") // 그냥 delete도 가능
    // path를 이용하면 여러 주소를 설정할 수 있다. 주소 migration 시 유용하다.
    @DeleteMapping(path = {
//            "/user/{userName}/delete/", 주소 마지막에 '/'는 적지 않는 것이 관례
            "/user/{userName}/delete",
            "/user/{userName}/del"
        }
    )
    public void delete(
        @PathVariable String userName
    ) {
        log.info("user-name : {}", userName);
    }
}
