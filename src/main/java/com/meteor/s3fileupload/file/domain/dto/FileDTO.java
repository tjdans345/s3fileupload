package com.meteor.s3fileupload.file.domain.dto;

import com.meteor.s3fileupload.enumpackge.FileCategory;
import com.meteor.s3fileupload.file.domain.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private Long idx;
    @Enumerated(EnumType.STRING)
    private FileCategory category;
    private List<FileListDTO> files;

    public FileEntity toEntity() {
        return FileEntity.builder()
                .idx(idx)
                .category(category)
                .files(files.stream()
                        .map(FileListDTO::toEntity)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
