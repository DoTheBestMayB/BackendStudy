package org.example.exception.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 서버에서 Response로 전달하는 데이터는 일관되어야 한다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 객체를 생성할 때 빌더 페턴을 사용하기 위한 애노테이션
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {

    private String resultCode;
    private String resultMessage;
    private T data;
}
