package org.example.restapi.model;

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
    private String email;
}
