package org.rails.domain;


import org.rails.data.CommentsRepository;
import org.rails.models.Comments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;

    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Result<List<Comments>> findAllCommentsByClipId(int clipId) {
        Result<List<Comments>> result = new Result<>();
        List<Comments> comments = commentsRepository.findAllCommentsByClipId(clipId);
        if (comments == null) {
            result.addErrorMessage("Comments not found.", ResultType.NOT_FOUND);
        } else {
            result.setPayload(comments);
        }
        return result;
    }

    public Result<Comments> create(Comments comments) {
        Result<Comments> result = new Result<>();
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(commentsRepository.create(comments));
        return result;
    }

    public Result<Boolean> deleteById(int commentId) {
        Result<Boolean> result = new Result<>();
        result.setPayload(commentsRepository.deleteById(commentId));
        return result;
    }
}
