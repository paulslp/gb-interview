package ru.gb.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.spring.domain.Student;
import ru.gb.spring.service.StudentService;

@Controller
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private static final String STUDENTS_JSP_NAME = "students";

    private static final String STUDENTS_ATTRIBUTE_NAME = "students";

    private static final String STUDENT_ATTRIBUTE_NAME = "student";

    @GetMapping
    public String getAllStudents(Model model) {
        log.info("getAllStudents executing ...");
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

//    @GetMapping("/student")
//    public String getStudentById(Model model, @RequestParam Long id) {
//        log.info("getAllStudents executing ...");
//        model.addAttribute(STUDENTS_ATTRIBUTE_NAME, List.of(studentService.findById(id)));
//        return STUDENTS_JSP_NAME;
//    }

    @GetMapping("/add")
    public String showAddingForm(Model model) {
        log.info("showAddingForm executing ...");
        model.addAttribute(STUDENT_ATTRIBUTE_NAME, new Student());
        model.addAttribute("actionType", "Add");
        return "save-student";
    }

    @PostMapping("/edit")
    public String showUpdatingForm(Model model, @RequestParam Long id) {
        log.info("showUpdatingForm executing ...");
        model.addAttribute(STUDENT_ATTRIBUTE_NAME, studentService.findById(id));
        model.addAttribute("actionType", "Edit");
        return "save-student";
    }

    @GetMapping("/delete")
    public String showDeletingForm(Model model) {
        log.info("showDeletingForm executing ...");
        model.addAttribute(STUDENT_ATTRIBUTE_NAME, new Student());
        model.addAttribute(STUDENTS_ATTRIBUTE_NAME, studentService.findAll());
        return "delete-student";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {
        log.info("saveStudent executing ...");
        log.info("student = {}", student);
        studentService.save(student);
        return "redirect:/students";
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Long id) {
        log.info("deleteStudent executing ...");
        studentService.deleteById(id);
        return "redirect:/students";
    }
}
