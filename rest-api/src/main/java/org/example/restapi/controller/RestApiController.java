package org.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring은 Annotation으로 역할을 지정
 * 이전에는 xml로 설정함
 * 8080 포트가 이미 사용 중인 경우, cmd 창에서 다음과 같은 명령어로 종료시킬 수 있다.
 * 1. `netstat -ano | findstr 8080`를 입력해서 8080 포트를 사용중인 process id를 확인한다.
 * 2. `taskkill /f /pid {pid Number}` 명령어로 해당 process를 종료한다.
 */
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

    @GetMapping(path = "/echo/{message}")
    public String echo(@PathVariable(name = "message") String msg) {
        System.out.println("echo message: " + msg);

        return msg.toUpperCase();
    }
}
