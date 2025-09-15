package org.example.validation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.validation.model.UserRegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @PostMapping("")
    public UserRegisterRequest register(
            // @Valid 애노테이션을 추가하면 각 값을 설정한 애노테이션 규칙에 따라 검증해준다.
            @Valid @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        log.info("init: {}", userRegisterRequest);

        return userRegisterRequest;
    }
}
