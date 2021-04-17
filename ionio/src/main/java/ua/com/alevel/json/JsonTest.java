package ua.com.alevel.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import ua.com.alevel.serial.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    private final String fileName = "test.json";

    @SneakyThrows
    public void testJackson() {
        ObjectMapper objectMapper = new ObjectMapper();
//        Student student = new Student();
//        student.setAge(10);
//        student.setFirstName("qq");
//        student.setLastName("ww");
//        student.setFullName("qq ww");
//
//        Gson gson = new Gson();
//        String s = gson.toJson(student);
//
//        System.out.println("s = " + s);
//
//        Student student1 = objectMapper.readValue(s, Student.class);
//
//        System.out.println("student1 = " + student1);



        List<Student> students = new ArrayList<>();
        Student student = new Student();

        student.setAge(10);
        student.setFirstName("qq");
        student.setLastName("ww");
        student.setFullName("qq ww");

        students.add(student);

        student.setAge(15);
        student.setFirstName("qqq");
        student.setLastName("www");
        student.setFullName("qqq www");

        students.add(student);

        Gson gson = new Gson();
        String s = gson.toJson(students);

        System.out.println("s = " + s);

        List<Student> list = objectMapper.readValue(s, new TypeReference<>() { });

        System.out.println("list = " + list);
    }

    @SneakyThrows
    public void test() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();

        student.setAge(10);
        student.setFirstName("qq");
        student.setLastName("ww");
        student.setFullName("qq ww");

        students.add(student);

        student.setAge(15);
        student.setFirstName("qqq");
        student.setLastName("www");
        student.setFullName("qqq www");

        students.add(student);

        Gson gson = new Gson();
        String s = gson.toJson(students);

        System.out.println("s = " + s);

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(s);
        writer.close();

        File initialFile = new File(fileName);
        String json = FileUtils.readFileToString(initialFile, "UTF-8");
        System.out.println("json = " + json);

        JsonArray jsonElements = new Gson().fromJson(json, JsonArray.class);
        jsonElements.forEach(jsonElement -> {
            JsonObject convertedObject = new Gson().fromJson(jsonElement, JsonObject.class);
            System.out.println("convertedObject = " + convertedObject.isJsonObject());
            System.out.println("convertedObject = " + convertedObject.get("firstName").getAsString());
            System.out.println("convertedObject = " + convertedObject.get("age").getAsInt());
        });
    }
}
