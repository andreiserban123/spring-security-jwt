package com.example.app.user;

import com.example.app.user.request.ChangePasswordRequest;
import com.example.app.user.request.ProfileUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @PatchMapping("/me")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateProfileInfo(
            @RequestBody
            @Valid final ProfileUpdateRequest request,
            final Authentication principal
    ) {
        this.userService.updateProfileInfo(request, getUserId(principal));
    }


    @PostMapping("/me/password")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void changePassword(
            @RequestBody
            @Valid final ChangePasswordRequest request,
            final Authentication principal
    ) {
        this.userService.changePassword(request, getUserId(principal));
    }

    @PatchMapping("/me/deactivate")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deactivateAccount(final Authentication principal) {
        this.userService.deactivateAccount(getUserId(principal));
    }

    private String getUserId(Authentication principal) {
        return ((User) Objects.requireNonNull(principal.getPrincipal())).getId();
    }
}
