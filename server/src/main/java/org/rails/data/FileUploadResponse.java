package org.rails.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String filePath;
    private String fileUrl;
    private LocalDateTime dateTime;

    public FileUploadResponse(String mediaUrl) {
    }
}