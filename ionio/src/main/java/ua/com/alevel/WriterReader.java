package ua.com.alevel;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriterReader {

    String fileName = "file.txt";
    String targetFileName = "target.txt";

    @SneakyThrows
    public void write() {

        // 1
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(str);

        writer.close();

        str = "World";
        writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(' ');
        writer.append(str);

        writer.close();

        // 2
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print('\n');
        printWriter.print("Some String");
        printWriter.print('\n');
        printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
        printWriter.close();

        // 3
        Path path = Paths.get(fileName);
//        byte[] strToBytes = str.getBytes();
//        Files.write(path, strToBytes);

        String read = Files.readAllLines(path).get(2);
        System.out.println("read = " + read);
    }
}
