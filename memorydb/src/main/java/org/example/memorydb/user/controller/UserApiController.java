package org.example.memorydb.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.memorydb.user.model.UserEntity;
import org.example.memorydb.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    // Controller에서 UserRequest를 받아야 하지만, 강의에서는 구분 없이 사용함
    @PutMapping("")
    public UserEntity create(
            @RequestBody UserEntity userEntity
    ) {
        return userService.save(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/id/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        userService.delete(id);
    }

    @GetMapping("/id/{id}")
    public UserEntity findOne(
            @PathVariable Long id
    ) {
        var response = userService.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else {
            return null;
        }
    }

    @GetMapping("/score")
    public List<UserEntity> filterScore(
            @RequestParam int score
    ) {
        return userService.filterScore(score);
    }
}
