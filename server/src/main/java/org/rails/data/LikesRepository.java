package org.rails.data;

import org.rails.models.Likes;

import java.util.List;

public interface LikesRepository {

    List<Likes> findAllLikesByClipId(int clipId);

    Likes create(Likes like);

    boolean deleteById(int likeId);

}
