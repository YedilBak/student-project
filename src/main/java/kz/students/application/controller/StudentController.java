package kz.students.application.controller;

import kz.students.application.db.DBManager;
import kz.students.application.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @GetMapping(value = "/") //http://localhost:8080/
    public String getMain(Model m){
        m.addAttribute("stud", DBManager.getStudents());
        return "index";
    }


    @GetMapping(value = "/details/{id}")
    public String getStudent(@PathVariable int id,
                             Model m){
        m.addAttribute("st", DBManager.getStudentById(id));
        return "details";
    }

    @GetMapping(value = "/add-student")
    public String addStudentPage(){
        return "add-student-page";
    }

    @PostMapping(value = "/add-student")
    public String addStudent(Student student){

        DBManager.addStudent(student);

        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String updateStudent(Student student){

        DBManager.updateSt(student);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteSt(@RequestParam int id){
        DBManager.deleteSt(id);
        return "redirect:/";
    }


}
