package com.whatsapp.apiserver.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.upload.directory.video}")
    private String videoUploadDirectory;

    @Value("${file.upload.directory.attachment}")
    private String attachmentUploadDirectory;

    public void saveVideoFile(MultipartFile videoFile) throws IOException {
        Path filePath = Paths.get(videoUploadDirectory, videoFile.getOriginalFilename());
        Files.write(filePath, videoFile.getBytes());
    }

    public void saveAttachmentFile(MultipartFile attachmentFile) throws IOException {
        Path filePath = Paths.get(attachmentUploadDirectory, attachmentFile.getOriginalFilename());
        Files.write(filePath, attachmentFile.getBytes());
    }
}

