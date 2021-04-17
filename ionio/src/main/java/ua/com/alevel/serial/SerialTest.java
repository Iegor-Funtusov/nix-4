package ua.com.alevel.serial;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerialTest {

    @SneakyThrows
    public void test() {
        Student student = new Student();
        student.setFirstName("qq");
        student.setLastName("ww");
        student.setAge(10);
        student.setFullName(student.getFirstName() + " " + student.getLastName());

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student.out"));
        outputStream.writeObject(student);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student.out"));
        student = (Student) inputStream.readObject();
        inputStream.close();

        System.out.println("student = " + student);
    }
}
