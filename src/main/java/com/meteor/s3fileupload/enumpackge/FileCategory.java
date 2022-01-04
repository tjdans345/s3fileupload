package com.meteor.s3fileupload.enumpackge;

public enum FileCategory {

    BOARD("게시판"),
    MEMBER("회원");

    private final String value;

    FileCategory(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return this.value;
    }


}
