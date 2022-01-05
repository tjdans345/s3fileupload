package com.meteor.s3fileupload.file.controller;

import com.meteor.s3fileupload.aws.AwsS3Service;
import com.meteor.s3fileupload.common.ResponseDTO;
import com.meteor.s3fileupload.enumpackge.FileCategory;
import com.meteor.s3fileupload.enumpackge.ResponseEnum;
import com.meteor.s3fileupload.file.domain.dto.FileDTO;
import com.meteor.s3fileupload.file.domain.dto.MemberDTO;
import com.meteor.s3fileupload.file.domain.dto.TestDTO;
import com.meteor.s3fileupload.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/s3")
@RestController
public class FileRestController {

    private final FileService fileService;

    /**
     * file Upload & DB Insert
     * @param file
     * @param category
     * @return
     * @throws IOException
     */
    @PostMapping
    public HttpEntity<?> uploadFile(@RequestPart("file")MultipartFile file,
                                    @RequestParam("category")FileCategory category) throws IOException {
        FileDTO savedFile = fileService.saveFile(file, category);
        return fileSaveResult(savedFile);
    }

    // Ver.2 file Object + DataObject 받아오기 테스트
//    @PostMapping
//    public HttpEntity<?> uploadFile(TestDTO testDTO) throws IOException {
//        FileDTO savedFile = fileService.saveFile(testDTO.getFile(), testDTO.getCategory());
//        return fileSaveResult(savedFile);
//    }

    // Ver.3 @RequestPart 사용 file Object + DataObject 받아오기 테스트
//    @PostMapping
//    public HttpEntity<?> uploadFile(TestDTO testDTO
//                                    , @RequestPart("memberDTO")MemberDTO memberDTO) throws IOException {
//        FileDTO savedFile = fileService.saveFile(testDTO.getFile(), testDTO.getCategory());
//        return fileSaveResult(savedFile);
//    }

    @PostMapping("/add")
    public HttpEntity<Object> uploadImageAddFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "masterIdx", required = false) Long masterIdx,
            @RequestParam(required = false) FileCategory category
    ) throws IOException {
        FileDTO savedFile = fileService.saveAddFile(file, masterIdx, category);
        return fileSaveResult(savedFile);
    }

    @PostMapping("/multiple")
    public HttpEntity<Object> uploadImageFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam(required = false) FileCategory category
    ) {
        // 파일 테이블에 저장
        FileDTO savedFile = fileService.saveAddFile(category, files);
        return fileSaveResult(savedFile);
    }

    // return extract method
    private HttpEntity<Object> fileSaveResult(FileDTO savedFile) {
        if (savedFile != null) {
            return new ResponseEntity<>(
                    new ResponseDTO<>(ResponseEnum.FILE_SAVE, savedFile), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new ResponseDTO<>(ResponseEnum.FILE_SAVE_FAIL),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        }
    }

}
