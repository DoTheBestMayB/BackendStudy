## SpringBoot 구조
Controller(사용자로부터 요청을 받음) -> Service(비즈니스 로직 처리) -> Repository(데이터 접근)

## 주입

Spring이 실행될 때, `@Configuration`에 적힌 내용에 따라 Spring Context에 객체를 생성함

```java
@Configuration
public class DatabaseConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
```

Configuration 없이 다음과 같이 생성자에 애노테이션을 붙여서 주입할 수도 있다. </p>
외부 라이브러리와 같이 코드 수정 권한이 없는 코드를 주입해야 하는 경우, Configuration을 이용해서 주입해야 한다.
```java
@Service
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {
}
```

Spring은 이것을 필요로 하는 Service, Controller, Bean 등에 주입해준다. </p>

Service, Controller, Bean에서는 `@RequiredArgsConstructor` 애노테이션을 붙이고, 필요한 객체를 property로 선언함으로써 Spring에게 생성자 주입을 요청할 수 있다.  

```java
@Service
@RequiredArgsConstructor // Spring에게 주입해달라고 요청하는 애노테이션
public class UserService {

    private final UserRepository userRepository;
}
```

