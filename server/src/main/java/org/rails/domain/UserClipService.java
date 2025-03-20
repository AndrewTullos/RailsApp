package org.rails.domain;

import org.rails.data.UserClipRepository;
import org.rails.models.UserClip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClipService {

    private final UserClipRepository userClipRepository;

    public UserClipService(UserClipRepository userClipRepository) {
        this.userClipRepository = userClipRepository;
    }

    public Result<UserClip> findById(int id) {
        Result<UserClip> result = new Result<>();
        UserClip userClip = userClipRepository.findById(id);
        if (userClip == null) {
            result.addErrorMessage("User clip not found.", ResultType.NOT_FOUND);
        } else {
            result.setPayload(userClip);
        }
        return result;
    }

    public Result<List<UserClip>> findAllClipsByUserId(int userId) {
        Result<List<UserClip>> result = new Result<>();
        List<UserClip> userClips = userClipRepository.findAllClipsByUserId(userId);
        if (userClips.isEmpty()) {
            result.addErrorMessage("User clips not found.", ResultType.NOT_FOUND);
        } else {
            result.setPayload(userClips);
        }
        return result;
    }

    public Result<List<UserClip>> findAllClipsByFollowees(int userId) {
        Result<List<UserClip>> result = new Result<>();
        List<UserClip> userClips = userClipRepository.findAllClipsByFollowees(userId);
        if (userClips.isEmpty()) {
            result.addErrorMessage("User clips not found.", ResultType.NOT_FOUND);
        } else {
            result.setPayload(userClips);
        }
        return result;
    }

    public Result<UserClip> create(UserClip userClip) {
        Result<UserClip> result = validate(userClip);
        if (!result.isSuccess()) {
            return result;
        }

        userClip = userClipRepository.create(userClip);
        result.setPayload(userClip);
        return result;
    }

    public boolean delete(int id) {
        return userClipRepository.deleteById(id);
    }

    private Result<UserClip> validate(UserClip userClip) {
        Result<UserClip> result = new Result<>();
        if (userClip == null) {
            result.addErrorMessage("User clip cannot be null.", ResultType.INVALID);
            return result;
        }
        if (userClip.getUserProfile().getUserId() == 0) {
            result.addErrorMessage("User ID cannot be 0.", ResultType.INVALID);
        }
        if (userClip.getMediaUrl() == null || userClip.getMediaUrl().isBlank()) {
            result.addErrorMessage("Media URL cannot be null or blank.", ResultType.INVALID);
        }
        if (userClip.getMediaUrl().length() > 255) {
            result.addErrorMessage("Media URL cannot be longer than 255 characters.", ResultType.INVALID);
        }
        if (userClip.getCaption() == null || userClip.getCaption().isBlank()) {
            result.addErrorMessage("Caption cannot be null or blank.", ResultType.INVALID);
        }
        if (userClip.getCaption().length() > 255) {
            result.addErrorMessage("Caption cannot be longer than 255 characters.", ResultType.INVALID);
        }
        return result;
    }
}
