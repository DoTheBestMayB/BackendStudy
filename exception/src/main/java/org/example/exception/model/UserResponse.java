package org.example.exception.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 객체를 생성할 때 빌더 페턴을 사용하기 위한 애노테이션
// 빌더 패턴 : 객체의 함수를 체이닝 방식으로 연속해서 호출할 수 있는 패턴
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {

    private String id;
    private String name;
    private Integer age;
}
