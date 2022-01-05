package com.meteor.s3fileupload.file.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meteor.s3fileupload.enumpackge.FileCategory;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestDTO {
    private MultipartFile file;
    private FileCategory category;
//    private MemberDTO memberDTO; 이렇게는 messageConverter Binding 안됨
}
