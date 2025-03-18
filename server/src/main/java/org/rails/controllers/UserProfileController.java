package org.rails.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;
import org.rails.domain.Result;
import org.rails.domain.ResultType;
import org.rails.domain.UserProfileService;
import org.rails.models.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/{id}")
    public UserProfile findById(@PathVariable int id) {
        return service.findById(id).getPayload();
    }


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserProfile user) {
        Result<UserProfile> result = service.create(user);
        if (result.isSuccess()) {
            Map<String, String> output = createJwtFromUser(result.getPayload());
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserProfile user) {
        Result<UserProfile> userResult = service.findByUsername(user.getUsername());

        if (userResult.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(userResult.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

//        if (userResult.getPayload().getPassword().equals(user.getPassword())) {


        char[] proposedPassword = user.getPassword().toCharArray();
        char[] existingPassword = userResult.getPayload().getPassword().toCharArray();

        if (BCrypt.verifyer().verify(proposedPassword, existingPassword).verified) {

//            return new ResponseEntity<>(userResult.getPayload(), HttpStatus.OK);
            Map<String, String> jwtMap = createJwtFromUser(userResult.getPayload());
            return new ResponseEntity<>(jwtMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of("Username or password do not match"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UserProfile user) {
        Result<UserProfile> result = service.update(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        Result<UserProfile> result = service.deleteById(id);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
