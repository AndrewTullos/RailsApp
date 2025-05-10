package org.rails.controllers;

import org.rails.domain.LikeService;
import org.rails.domain.Result;
import org.rails.models.Likes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/like")
public class LikeController {

    private LikeService service;

    public LikeController(LikeService service) {
        this.service = service;
    }

    @GetMapping("/{clipId}")
    public List<Likes> findAllLikesByClipId(@PathVariable int clipId) {
        return service.findAllLikesByClipId(clipId).getPayload();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Likes like) {
        Result<Likes> result = service.create(like);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{likeId}")
    public boolean deleteById(@PathVariable int likeId) {
        return service.deleteById(likeId).getPayload();
    }
}
