package ua.com.alevel.util;

import lombok.Getter;

@Getter
public enum FileLoader {

    CREATE_FILE("test.txt"),
    CREATE_DIR("test"),
    CREATE_DIRS("test1/test2/test3"),
    COPY_DIRS("copy1/copy2/copy3");

    private final String fileName;

    FileLoader(String fileName) {
        this.fileName = fileName;
    }
}
