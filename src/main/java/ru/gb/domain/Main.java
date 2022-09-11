package ru.gb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            students.add(new Student("student" + i, false));
        }
        dao.saveList(students);
        printFindAll(dao);
        System.out.println("Saving Student with id = 1001");
        dao.saveList(new Student("name10000", true));
        System.out.println("Student with id 1001: " + dao.findById(1001));
        Student studentForUpdateAndDelete = new Student(1, "name10001", false);
        dao.update(studentForUpdateAndDelete);
        System.out.println("Student with id 1 after updating: " + dao.findById(1));
        printFindAll(dao);
        dao.delete(studentForUpdateAndDelete);
        printFindAll(dao);
    }

    private static void printFindAll(StudentDAO dao) {
        System.out.println(dao.findAll().stream().map(Object::toString)
                .collect(Collectors.joining(";")));
    }
}
