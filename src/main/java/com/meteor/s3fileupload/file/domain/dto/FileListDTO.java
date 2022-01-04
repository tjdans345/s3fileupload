package com.meteor.s3fileupload.file.domain.dto;

import com.meteor.s3fileupload.file.domain.FileListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileListDTO {
    private Long            idx;                // 파일 고유번호
    private String          originalName;   // 원본 파일명
    private String          extension;          // 파일 확장자
    private String          url;            // AWS S3 URL
    private String          deleteYn;

    public FileListEntity toEntity() {
        return FileListEntity.builder()
                .idx(idx)
                .originalName(originalName)
                .extension(extension)
                .fileUrl(url)
                .deleteYn(deleteYn)
                .build();
    }
}
