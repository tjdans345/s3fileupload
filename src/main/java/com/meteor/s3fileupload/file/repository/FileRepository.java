package com.meteor.s3fileupload.file.repository;

import com.meteor.s3fileupload.file.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {



}
