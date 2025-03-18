package org.rails.controllers;

import io.jsonwebtoken.Jwts;
import org.rails.domain.Result;
import org.rails.domain.UserProfileService;
import org.rails.models.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    private UserProfileService service;

    private SecretSigningKey secretSigningKey;

    public UserProfileController(UserProfileService service, SecretSigningKey secretSigningKey) {
        this.service = service;
        this.secretSigningKey = secretSigningKey;
    }

    @GetMapping
    public ResponseEntity<Object> create(@RequestBody UserProfile userProfile) {
        Result<UserProfile> result = service.create(userProfile);
        if (result.isSuccess()) {
            Map<String, String> output = createJwtFromUser(result.getPayload());
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    private Map<String, String> createJwtFromUser(UserProfile user) {
        String jwt = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("userId", user.getUserId())
                .signWith(secretSigningKey.getKey())
                .compact();
        Map<String, String> output = new HashMap<>();
        output.put("jwt", jwt);
        return output;
    }

}
