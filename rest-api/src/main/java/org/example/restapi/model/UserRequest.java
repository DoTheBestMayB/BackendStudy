package org.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// json은 snake case를 자주 사용한다. snake case로 작성된 변수를 camelCase와 매칭되도록 해준다.
// request로 받을 때 camel case로 변환해줄 뿐만 아니라, 응답으로 전달할 때 snake case로 변환해준다.
// PropertyNamingStrategy는 deprecated 되었기 때문에, PropertyNamingStrategies를 사용해야 한다.
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    private String userName;
    // Object(주고 받는 데이터)에서 int 대신 Reference 타입인 Integer 사용이 권장된다.
    // userAge 값이 전달되지 않으면 int는 기본 값인 0으로 설정되어 실제 값 0과 구분이 어렵다.
    // Integer는 값이 전달되지 않으면 null로 설정된다.
    private int userAge;

    // `@JsonProperty` 애노테이션을 이용해서 Json 변환시 mapping 되는 key 이름을 변경할 수 있다.
//    @JsonProperty("user_email")
    private String email;
    /**
     * boolean의 기본 값은 false
     * isXxx로 변수를 선언하면 Data 애노테이션에 의해 생성되는 함수는 setIsXxx가 아니라 setXxx이다.
     * json에서 xxx로 데이터를 전달하면 isXxx에 맵핑되어 변환된다.
     * 하지만 이렇게 사용할 경우 다음과 같은 문제가 있다.
     * 1. json과 변수 이름이 매칭되지 않는다.
     * 2. 값이 들어오지 않으면 null로 설정되는 것이 바람직하다.
     * <p>
     * boolean 대신 wrapper 타입인 Boolean을 사용하면 위 두 문제가 해결된다.
     */
    private Boolean isKorean;

    // getXxx 함수를 정의하면 직렬화 과정에서 JSON의 key: value로 사용된다.
    // ex) "User": "민수"
    @JsonIgnore // JSON 값으로 생성되지 않도록 제외하려면 JsonIgnore 애노테이션을 추가한다.
    public String getUser() {
        return userName;
    }
}
