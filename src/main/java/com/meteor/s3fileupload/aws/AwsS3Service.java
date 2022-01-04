package com.meteor.s3fileupload.aws;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.meteor.s3fileupload.utill.StringUtills;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 실질적인 파일 업로드처리
 */
@Service
@RequiredArgsConstructor
public class AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    String bucket;
    @Value("${cloud.aws.s3.location}")
    String path;
    private final AmazonS3 amazonS3;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucket,
                path + createRandomFileName()+"_"+StringUtills.getCurrentDate() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()),
                multipartFile.getInputStream(),
                null
        );
        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl(putObjectRequest.getBucketName(), putObjectRequest.getKey()).toString();
    }

    private String createRandomFileName() {
        return UUID.randomUUID().toString();
    }


}
