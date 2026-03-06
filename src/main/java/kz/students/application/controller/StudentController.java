package kz.students.application.controller;

import kz.students.application.db.DBConnector;
import kz.students.application.entity.Student;
import kz.students.application.repository.StudentRepository;
import kz.students.application.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class StudentController {

    private final DBConnector db;
    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;

    @GetMapping(value = "/") //http://localhost:8080/
    public String getMain(Model m){
        m.addAttribute("stud", studentRepository.findAll());
        return "index";
    }


    @GetMapping(value = "/details/{id}")
    public String getStudent(@PathVariable int id,
                             Model m){
        m.addAttribute("st", db.getStudentById(id));
        m.addAttribute("uniki", db.getAllUniversities());
        return "details";
    }

    @GetMapping(value = "/add-student")
    public String addStudentPage(Model m){
        m.addAttribute("uniki", db.getAllUniversities());

        return "add-student-page";
    }

    @PostMapping(value = "/add-student")
    public String addStudent(Student student){

        db.addStudent(student);

        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String updateStudent(Student student){
        System.out.println(student.getUniversity().getId());
        db.updateStudent(student);


        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteSt(@RequestParam int id){
        db.deleteStudent(id);
        return "redirect:/";
    }


}
