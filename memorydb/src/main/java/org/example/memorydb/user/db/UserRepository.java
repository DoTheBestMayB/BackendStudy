package org.example.memorydb.user.db;

import org.example.memorydb.db.SimpleDataRepository;
import org.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

    public List<UserEntity> findAllScoreGreaterThen(int score) {
        return findAll().stream()
                .filter(
                        it -> {
                            return it.getScore() >= score;
                        }
                ).toList();
    }
}
