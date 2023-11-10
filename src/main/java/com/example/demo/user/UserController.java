package com.example.demo.user;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.ApiUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    // @PostMapping("/join")
    // public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO
    // requestDTO, Errors errors) {

    // if (errors.hasErrors()) {
    // FieldError fieldError = errors.getFieldErrors().get(0);
    // String key = fieldError.getField();
    // String value = fieldError.getDefaultMessage();
    // return new ResponseEntity<>(ApiUtil.error(value + " : " + key),
    // HttpStatus.BAD_REQUEST);
    // }
    // User user = userRepository.save(requestDTO.toEntity());

    // return ResponseEntity.ok().body(ApiUtil.success(user));
    // }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            FieldError filedError = errors.getFieldErrors().get(0);
            String key = filedError.getField();
            String value = filedError.getDefaultMessage();
            return new ResponseEntity<>(ApiUtil.error(value + " : " + key),
                    HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.save(requestDTO.toEntity());

        // 컨트롤러는 뷰리졸버 발동이지만
        // 리스판스텐티티 하면 바디를 붙이지 않아도된다

        // return new ResponseEntity<>("null", HttpStatus.OK);
        // return ResponseEntity.ok(null);
        // 추천은 마지막 형태
        return ResponseEntity.ok().body(ApiUtil.success(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Integer id) {

        Optional<User> userOP = userRepository.findById(id);

        if (userOP.isPresent()) {
            return ResponseEntity.ok().body(ApiUtil.success(userOP.get()));
        } else {
            return new ResponseEntity<>(
                    ApiUtil.error("해당 아이디가 존재하지 않습니다"),
                    HttpStatus.NOT_FOUND);
        }
    }

}
