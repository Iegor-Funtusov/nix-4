package ua.com.alevel.io;

import lombok.SneakyThrows;

import java.io.File;

public class FileIO {

    @SneakyThrows
    public void createFile(String fileName) {
        File file = new File(fileName);
        file.createNewFile();
    }

    public void createDir(String fileName) {
        File file = new File(fileName);
        file.mkdir();
    }

    public void createDirs(String fileName) {
        File file = new File(fileName);
        file.mkdirs();
    }

    public void readDirs(String fileName) {
        File file = new File(fileName);
        read(file);
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    private void read(File file) {
        System.out.println("file = " + file.getName());
        File[] files = file.listFiles();
        for (File inner : files) {
            if (inner.isFile()) {
                System.out.println("inner = " + inner.getName());
            } else {
                read(inner);
            }
        }
    }
}
