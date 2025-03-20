package org.rails.controllers;

import org.rails.domain.Result;
import org.rails.domain.UserClipService;
import org.rails.models.UserClip;
import org.rails.models.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/clip")
public class UserClipController {

    private UserClipService service;

    public UserClipController(UserClipService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserClip findById(@PathVariable int id) {
        return service.findById(id).getPayload();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserClip clip) {
        Result<UserClip> result = service.create(clip);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

}
