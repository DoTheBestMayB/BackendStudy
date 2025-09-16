package org.example.memorydb.user.service;

import lombok.RequiredArgsConstructor;
import org.example.memorydb.user.db.UserRepository;
import org.example.memorydb.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Spring이 UserRepository를 주입해달라고 요청하는 애노테이션
public class UserService {

//    @Autowired // AutoWired를 이용해서 주입을 원하는 property만 요청할 수 있다.
    // AutoWired를 사용하려면 final 접근자를 없애야 한다.
    private final UserRepository userRepository;

    public UserEntity save(UserEntity user  ) {
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> filterScore(int score) {
        return userRepository.findAllScoreGreaterThen(score);
    }
}
