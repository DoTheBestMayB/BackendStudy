package org.example.validation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.validation.model.Api;
import org.example.validation.model.UserRegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    /**
     * 에러가 발생할 때 Api<Object>를 리턴하기 때문에 타입을 Object로 지정
     * 정상적인 응답은 Api<UserRegisterRequest>를 리턴하기 때문에 Api<? Extends Object>>로 변경
     * 에러가 발생할 때 200 OK 아닌 에러 status code를 지정하기 위해 ResponseEntity<Api<? extends Object>>로 변경
     *
     * 그런데 이렇게 작성할 경우, 비즈니스 로직 외 코드도 추가되어 코드의 복잡도가 증가한다.
     * 따라서 예외 처리를 ExceptionHandler에서 처리하도록 한다.
     */
//    @PostMapping("")
//    public ResponseEntity<Api<? extends Object>> register(
//            // @Valid 애노테이션을 추가하면 각 값을 설정한 애노테이션 규칙에 따라 검증해준다.
//            @Valid @RequestBody Api<UserRegisterRequest> userRegisterRequest,
//            BindingResult bindingResult // Valid의 실행 결과가 BindingResult에 담긴다.
//    ) {
//        log.info("init: {}", userRegisterRequest);
//
//        if (bindingResult.hasErrors()) {
//            var errorMessageList = bindingResult.getFieldErrors().stream()
//                    .map(it -> {
//                        var format = "%s : { %s } 은 %s";
//                        var message = String.format(format, it.getField(), it.getRejectedValue(), it.getDefaultMessage());
//                        return message;
//                    }).toList();
//
//            var error = Api.Error
//                    .builder()
//                    .errorMessage(errorMessageList)
//                    .build();
//
//            var errorResponse = Api.builder()
//                    .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
//                    .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
//                    .error(error)
//                    .build();
//
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(errorResponse);
//        }
//
//        var body = userRegisterRequest.getData();
//        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
//                .resultCode(String.valueOf(HttpStatus.OK.value()))
//                .resultMessage(HttpStatus.OK.getReasonPhrase())
//                .data(body)
//                .build();
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("")
    public Api<UserRegisterRequest> register(
            // @Valid 애노테이션을 추가하면 각 값을 설정한 애노테이션 규칙에 따라 검증해준다.
            @Valid @RequestBody Api<UserRegisterRequest> userRegisterRequest
    ) {
        log.info("init: {}", userRegisterRequest);

        var body = userRegisterRequest.getData();
        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .data(body)
                .build();
        return response;
    }
}
