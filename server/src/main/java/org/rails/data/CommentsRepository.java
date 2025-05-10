package org.rails.data;

import org.rails.models.Comments;

import java.util.List;

public interface CommentsRepository {

    public List<Comments> findAllCommentsByClipId(int clipId);

    public Comments create(Comments comment);

    public boolean deleteById(int commentId);


}
