package com.meteor.s3fileupload.file.domain;

import com.meteor.s3fileupload.enumpackge.FileCategory;
import com.meteor.s3fileupload.file.domain.dto.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Entity
@Table(name = "tb_file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "fileIdx")
    private List<FileListEntity> files;

    @Enumerated(EnumType.STRING)
    private FileCategory category;

    @CreationTimestamp
    private LocalDateTime createDate;

    public FileDTO toDTO() {
        return FileDTO.builder()
                .idx(idx)
                .category(category)
                .files(files.stream().map(FileListEntity::toDTO).collect(Collectors.toList()))
                .build();
    }

}
