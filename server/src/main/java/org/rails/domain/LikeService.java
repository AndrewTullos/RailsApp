package org.rails.domain;

import org.rails.data.LikesRepository;
import org.rails.models.Likes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private final LikesRepository likesRepository;

    public LikeService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public Result<List<Likes>> findAllLikesByClipId(int clipId) {
        Result<List<Likes>> result = new Result<>();
        List<Likes> likes = likesRepository.findAllLikesByClipId(clipId);
        if (likes == null) {
            result.addErrorMessage("Likes not found.", ResultType.NOT_FOUND);
        } else {
            result.setPayload(likes);
        }
        return result;
    }

    public Result<Likes> create(Likes like) {
        Result<Likes> result = new Result<>();
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(likesRepository.create(like));
        return result;
    }

    public Result<Boolean> deleteById(int likeId) {
        Result<Boolean> result = new Result<>();
        result.setPayload(likesRepository.deleteById(likeId));
        return result;
    }

}
