package ua.com.alevel;

import ua.com.alevel.csv.CsvTest;
import ua.com.alevel.io.FileIO;
import ua.com.alevel.io.FileNIO;
import ua.com.alevel.json.JsonTest;
import ua.com.alevel.serial.SerialTest;
import ua.com.alevel.util.FileLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoNioMain {

    public static void main(String[] args) {

        CsvTest csvTest = new CsvTest();
        csvTest.test();

//        JsonTest jsonTest = new JsonTest();
//        jsonTest.testJackson();

//        SerialTest serialTest = new SerialTest();
//        serialTest.test();

//        WriterReader writerReader = new WriterReader();
//        writerReader.write();

//        FileNIO fileNIO = new FileNIO();
//        fileNIO.copy("test1", "copy1");
//        fileNIO.fetchFiles("copy1");
//        fileNIO.createFile(FileLoader.CREATE_FILE.getFileName());
//        fileNIO.createDir(FileLoader.CREATE_DIR.getFileName());
//        fileNIO.createDirs(FileLoader.CREATE_DIRS.getFileName());
//        fileNIO.removeDir(FileLoader.CREATE_DIRS.getFileName());

//        FileIO fileIO = new FileIO();
//        fileIO.createFile(FileLoader.CREATE_FILE.getFileName());
//        fileIO.createDir(FileLoader.CREATE_DIR.getFileName());
//        fileIO.createDirs(FileLoader.CREATE_DIRS.getFileName());
//        fileIO.deleteFile(FileLoader.CREATE_DIRS.getFileName() + "/test4");
    }
}
