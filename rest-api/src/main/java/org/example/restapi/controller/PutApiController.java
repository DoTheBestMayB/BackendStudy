package org.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.restapi.model.UserRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그 출력을 위한 애노테이션. System.out 대신 사용할 수 있음
 * System.out을 사용하면 비용이 큰 출력이 완료되기 전까지 다음 코드를 실행할 수 없다. -> overhead
 * <p>
 * log는 버퍼를 사용하기 때문에, 로그에 남길 데이터를 버퍼에 전달하고 바로 다음 코드를 실행할 수 있다.
 * 하지만 버퍼가 가득차면 버퍼가 비워질 때까지 다음 코드가 실행되지 않는다.
 * <p>
 * 그 외 log를 사용할 때 다음과 같은 장점이 있다.
 * 1. log로 출력되는 형식을 커스텀할 수 있다.
 * 2. 언제 처리되었고, 어떤 쓰레드가 처리했고, 어떤 클래스가 해당 데이터를 가지고 있는지 알 수 있다.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put")
    public void put(
            @RequestBody UserRequest userRequest
    ) {
        // 2025-08-29T22:27:03.767+09:00  INFO 12068 --- [rest-api] [nio-8080-exec-6] o.e.restapi.controller.PutApiController  : Request : UserRequest(userName=마스터, userAge=100, email=spring@naver.com, isKorean=false)
        log.info("Request : {}", userRequest);
    }
}
