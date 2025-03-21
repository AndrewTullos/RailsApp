package org.rails.controllers;

import org.rails.domain.Result;
import org.rails.domain.UserClipService;
import org.rails.models.UserClip;
import org.rails.models.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/profile/{userId}")
    public List<UserClip> findAllClipsByUserId (@PathVariable int userId) {
        return service.findAllClipsByUserId(userId).getPayload();
    }

    @GetMapping("/followees/{userId}")
    public List<UserClip> findAllClipsByFollowees (@PathVariable int userId) {
        return service.findAllClipsByUserId(userId).getPayload();
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

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody UserClip clip) {
//        Result<UserClip> result = service.update(id, clip);
//
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id, @RequestBody UserClip clip, @RequestHeader Map<String, String> headers) {
        Result<UserClip> result = service.deleteById(id);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/{panelId}")
//    public ResponseEntity<?> deleteById(@PathVariable int panelId, @RequestHeader Map<String, String> headers) {
//        Integer userId = getUserIdFromHeaders(headers);
//        if (userId == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        SolarPanelResult existingPanelResult = service.findById(panelId);
//
//        if (existingPanelResult.getResultType() == ResultType.NOT_FOUND) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        if (existingPanelResult.getSolarPanel().getUserId() != userId) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        SolarPanelResult result = service.deleteById(panelId);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
//    }

}
