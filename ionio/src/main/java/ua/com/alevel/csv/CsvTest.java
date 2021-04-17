package ua.com.alevel.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import ua.com.alevel.serial.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvTest {

    private String file = "students.csv";

    @SneakyThrows
    public void test() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();

        student.setAge(10);
        student.setFirstName("qq");
        student.setLastName("ww");
        student.setFullName("qq ww");

        students.add(student);

        student = new Student();
        student.setAge(15);
        student.setFirstName("qqq");
        student.setLastName("www");
        student.setFullName("qqq www");

        students.add(student);

        List<String[]> csvData = new ArrayList<>();

        // 1
        String[] header = { "first name", "last name", "age" };
        csvData.add(header);

        // 2
        for (Student student1 : students) {
            String[] st = new String[3];
            st[0] = student1.getFirstName();
            st[1] = student1.getLastName();
            st[2] = String.valueOf(student1.getAge());
            csvData.add(st);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(csvData);
        }

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            csvData = reader.readAll();
        }

        System.out.println("csvData = " + csvData.get(2)[1]);
    }
}
