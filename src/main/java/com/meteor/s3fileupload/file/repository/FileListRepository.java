package com.meteor.s3fileupload.file.repository;

import com.meteor.s3fileupload.file.domain.FileListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileListRepository extends JpaRepository<FileListEntity, Long> {



}
