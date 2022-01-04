package com.meteor.s3fileupload.file.domain;

import com.meteor.s3fileupload.file.domain.dto.FileListDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@Entity
@Table(name = "tb_file_list")
public class FileListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long fileIdx;
    private String originalName;
    private String extension;
    private Long size;
    private String fileUrl;

    @CreationTimestamp
    private LocalDateTime createDate;
    private String deleteYn;

    public FileListEntity toEntity(MultipartFile file, String url, Long fileIdx) {
        return FileListEntity.builder()
                .fileIdx(fileIdx)
                .fileUrl(url)
                .originalName(file.getOriginalFilename())
                .size(file.getSize())
                .extension(FilenameUtils.getExtension(file.getOriginalFilename())) // TODO : FileNameUtils 정리하기
                .build();
    }

    public FileListDTO toDTO () {
        return FileListDTO.builder()
                .idx(idx)
                .originalName(originalName)
                .extension(extension)
                .deleteYn(deleteYn)
                .url(fileUrl)
                .build();
    }
}
