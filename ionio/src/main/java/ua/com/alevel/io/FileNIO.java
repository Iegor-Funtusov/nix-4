package ua.com.alevel.io;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileNIO {

    @SneakyThrows
    public void createFile(String pathTo) {
        Path path = Paths.get(pathTo);
        Files.createFile(path);
    }

    @SneakyThrows
    public void createDir(String pathTo) {
        Path path = Paths.get(pathTo);
        Files.createDirectory(path);
    }

    @SneakyThrows
    public void createDirs(String pathTo) {
        Path path = Paths.get(pathTo);
        Files.createDirectories(path);
    }

    @SneakyThrows
    public void removeDir(String pathTo) {
        Path path = Paths.get(pathTo);
        Files.delete(path);
    }

    public void copy(String srcDir, String targetDir) {
        Path pathSource = Paths.get(srcDir);
        Path pathDestination = Paths.get(targetDir);
        try {
            Files.move(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Source file copied successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchFiles(String srcDir) {
        Set<String> fileList = new LinkedHashSet<>();
        try {
            Stream<Path> pathStream = Files.walk(Paths.get(srcDir));
            fileList = pathStream
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileList.forEach(System.out::println);
    }
}
