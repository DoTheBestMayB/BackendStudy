package org.example.restapi.controller;

import org.example.restapi.model.BookRequest;
import org.example.restapi.model.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * query parameter는 특정 데이터에 필터링을 적용하는 것이기 때문에 데이터를 생성하는 POST 와는 어울리지 않다.
 * POST는 GET과 달리 DataBody를 이용한다.
 * url이 아닌 body를 통해 데이터를 전달하기 떄문에, 로그, 방문 기록 등에 남지 않아 비교적 안전한다.
 * 하지만 패킷을 통해 body의 내용을 확인할 수 있기 때문에 암호화는 필요하다.
 * <p>
 * POST 요청 시 Header에 Content-Type, Content-Length, Host를 함께 보내지 않으면 4XX 에러가 발생한다.
 * Content-Type이 없으면 415 Unsupported Media Type
 * Content-Length, Host가 없으면 400 Bad Request
 */
@RestController
@RequestMapping("/api")
public class PostApiController {

    /**
     * @param bookRequest HttpBody로 전달되는 데이터를 bookRequest에 mapping하기 위해 RequestBody 애노테이션을 추가함
     * @return 리턴 타입에 따라 Content-Type이 달라진다.
     * 클래스를 리턴하면 "application/json"
     * String을 리턴하면 "text/plain;charset=UTF-8"이다.
     */
    @PostMapping("/post")
    public BookRequest post(
            @RequestBody BookRequest bookRequest
    ) {
        return bookRequest;
    }

    @PostMapping("/user")
    public UserRequest User(
            @RequestBody UserRequest userRequest // JSON을 역직렬화해서 DTO로 변환
    ) {
        System.out.println(userRequest);
        return userRequest; // DTO를 직렬화해서 JSON으로 변환
    }
}
