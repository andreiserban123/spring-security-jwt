package com.example.app.user;

import com.example.app.auth.request.RegistrationRequest;
import com.example.app.user.request.ProfileUpdateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public void mergeUserInfo(User user, ProfileUpdateRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName())
                && !user.getFirstName().equals(request.getFirstName())
        ) {
            user.setFirstName(request.getFirstName());
        }
        if (StringUtils.isNotBlank(request.getLastName())
                && !user.getLastName().equals(request.getLastName())
        ) {
            user.setLastName(request.getLastName());
        }

        if (request.getBirthDate() != null
                && !user.getDateOfBirth().equals(request.getBirthDate())) {
            user.setDateOfBirth(request.getBirthDate());
        }
    }

    public User toUser(RegistrationRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .enabled(true)
                .locked(false)
                .credentialExpired(false)
                .emailVerified(false)
                .phoneVerified(false)
                .build();
    }
}
