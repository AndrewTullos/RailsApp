package org.rails.controllers;

import org.rails.domain.CommentService;
import org.rails.domain.Result;
import org.rails.models.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/{clipId}")
    public List<Comments> findAllCommentsByClipId(@PathVariable int clipId) {
        return service.findAllCommentsByClipId(clipId).getPayload();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Comments comments) {
        Result<Comments> result = service.create(comments);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{commentId}")
    public boolean deleteById(@PathVariable int commentId) {
        return service.deleteById(commentId).getPayload();
    }
}
