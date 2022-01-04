package com.meteor.s3fileupload.file.service;

import com.meteor.s3fileupload.aws.AwsS3Service;
import com.meteor.s3fileupload.enumpackge.FileCategory;
import com.meteor.s3fileupload.file.domain.FileEntity;
import com.meteor.s3fileupload.file.domain.FileListEntity;
import com.meteor.s3fileupload.file.domain.dto.FileDTO;
import com.meteor.s3fileupload.file.repository.FileListRepository;
import com.meteor.s3fileupload.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FileService {

    private final AwsS3Service awsS3Service;
    private final FileRepository fileRepository;
    private final FileListRepository fileListRepository;

    // file upload
    @Transactional
    public FileDTO saveFile(MultipartFile file, FileCategory category ) throws IOException {
        // Save File
        String url = awsS3Service.uploadFile(file);

        FileEntity savedFile = fileRepository.save(
                FileEntity.builder()
                        .category(category)
                        .build());
        if(savedFile.getIdx() > 0){
            // Save File List
            FileListEntity toSaveFileList = FileListEntity.builder()
                    .fileIdx(savedFile.getIdx())
                    .fileUrl(url)
                    .originalName(file.getOriginalFilename())
                    .size(file.getSize())
                    .extension(FilenameUtils.getExtension(file.getOriginalFilename()))
                    .deleteYn("N")
                    .build();

            FileListEntity savedFileList = fileListRepository.save(toSaveFileList);
            savedFile.setFiles(List.of(savedFileList));
        } else {
            return null;
        }
        return savedFile.toDTO();
    }

    @Transactional
    public FileDTO saveAddFile(
            MultipartFile file, Long masterIdx, FileCategory category) throws IOException {

        Optional<FileEntity> optional = fileRepository.findById(masterIdx);

        if (optional.isEmpty()) {
            return saveFile(file, category);
        } else {
            String url;
            try {
                url = awsS3Service.uploadFile(file);
            } catch (IOException e) {
                return null;
            }

            var savedFile = optional.get();

            fileListEntitySave(file, url, savedFile);
            return savedFile.toDTO();
        }
    }

    /**
     * 다수의 이미지 업로드
     * @param category
     * @param files
     * @return FileDTO
     */
    @Transactional
    public FileDTO saveAddFile(FileCategory category, List<MultipartFile> files){
        FileEntity entity = FileEntity.builder()
                .category(category)
                .build();
        FileEntity saveFile = fileRepository.save(entity);
        if (saveFile.getIdx()>0){
            files.forEach(file -> {
                try {
                    String url = awsS3Service.uploadFile(file);
                    fileListEntitySave(file,url,saveFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return saveFile.toDTO();
        } else {
            return null;
        }

    }

    @Transactional
    public void fileListEntitySave(MultipartFile file, String url, FileEntity savedFile){
        FileListEntity toSaveFileList = FileListEntity.builder()
                .fileIdx(savedFile.getIdx())
                .fileUrl(url)
                .originalName(file.getOriginalFilename())
                .size(file.getSize())
                .deleteYn("N")
                .extension(FilenameUtils.getExtension(file.getOriginalFilename()))
                .build();

        FileListEntity savedFileList = fileListRepository.save(toSaveFileList);
        savedFile.getFiles().add(savedFileList);
    }

}
