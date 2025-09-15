package org.example.validation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.validation.annotation.PhoneNumber;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

//    @NotBlank
    // 둘 중 하나는 반드시 존재해야 하는 등의 복잡한 검증 로직은 함수를 이용해 직접 구현한다.
    private String name;
    private String nickname;

    // AssertTrue : 리턴 값이 True여야 통과
    // 함수 이름은 반드시 is로 시작해야 한다.
    @AssertTrue(message = "name or nickName 은 반드시 존재해야 합니다.")
    public boolean isNameCheck() {
        return !name.isBlank() || !nickname.isBlank();
    }

    @Size(min = 1, max = 12)
    @NotBlank
    private String password;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer age;

    @Email
    private String email;

    // custom annotation
    @PhoneNumber
    private String phoneNumber;

    // LocalDateTime은 ISO 8601 사용. "2025-09-15T10:00:00"
    @FutureOrPresent
    private LocalDateTime registerAt;
}
