package org.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {

    private String resultCode;
    private String resultMessage;

    // T 타입 내 데이터들에 대한 검증을 수행하려면 여기에도 Valid 애노테이션을 지정해야 한다.
    @Valid
    private T data;
    private Error error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Error {
        private List<String> errorMessage;
    }
}
