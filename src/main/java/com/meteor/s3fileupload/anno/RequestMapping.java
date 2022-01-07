package com.meteor.s3fileupload.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션 만들기
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public  @interface RequestMapping {



}
