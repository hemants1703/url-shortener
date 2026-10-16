package tech.hemantsharma.urlshortener.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.hemantsharma.urlshortener.user.dtos.CreateUserRequestDto;
import tech.hemantsharma.urlshortener.user.dtos.UserResponseDto;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto user = userService.createNewUser(createUserRequestDto.username(), createUserRequestDto.password());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserByUsername(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDto user = userService.getUserByUsername(userDetails.getUsername());
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
