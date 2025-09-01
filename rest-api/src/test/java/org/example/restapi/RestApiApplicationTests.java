package org.example.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.restapi.model.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

    // Spring에서 관리되는 Bean들 중에서 자동으로 생성되는 ObjectMapper를 가져오기 위한 애노테이션
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        var user = new UserRequest("홍길동", 10, "hong@gmail.com", true);

        // 직렬화
        // 직렬화 과정에서 UserRequest class에 있는 getter(getXxx) 함수를 이용해 json 응답을 생성한다.
        // "Xxx": {return 값}
        // writeValueAsString 함수는 exception이 발생할 수 있기 때문에, try catch로 감싸거나 함수에 발생할 수 있는 예외를 명시해야한다.
        var json = objectMapper.writeValueAsString(user);
        System.out.println(json); // {"user_name":"홍길동","user_age":10,"email":"hong@gmail.com","is_korean":true}

        // 역직렬화
        // 역직렬화 과정에서 UserRequest class에 있는 setter 함수를 이용해 object를 생성한다.
        // setter 함수가 없다면 getter 함수를 이용한다.
        // getter 함수도 없으면 `@JsonProperty`를 이용한다.
        // `@JsonProperty`도 없으면 해당 값은 무시된다.
        // 가장 권장 되는 방법은 lombok을 통해 생성자, getter, setter 함수 생성하고, 이름이 다른 부분만 JsonProperty 이용
        var dto = objectMapper.readValue(json, UserRequest.class);
        System.out.println(dto); // UserRequest(userName=홍길동, userAge=10, email=hong@gmail.com, isKorean=true)
    }

}
