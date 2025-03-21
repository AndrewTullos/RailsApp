package org.rails.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rails.data.FileUploadResponse;
import org.rails.domain.FileService;
import org.rails.domain.Result;
import org.rails.domain.UserClipService;
import org.rails.models.UserClip;
import org.rails.models.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/v1/s3")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;

    private final UserClipService service;


    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata") UploadMetadata metadata) {

        // Handles AWS upload
        String mediaUrl = fileService.uploadFile(file).getFileUrl();

        // Create UserProfile & UserClip objects
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(metadata.getUserId());

        UserClip userClip = new UserClip();
        userClip.setUserProfile(userProfile);
        userClip.setMediaUrl(mediaUrl);
        userClip.setCaption(metadata.getCaption());

        service.create(userClip);

        return new ResponseEntity<>(new FileUploadResponse(mediaUrl), HttpStatus.OK);
    }
}