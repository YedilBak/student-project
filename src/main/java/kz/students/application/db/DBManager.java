package kz.students.application.db;

import kz.students.application.entity.Student;
import lombok.Getter;

import java.util.ArrayList;

public class DBManager {

    private static int id = 5;

    @Getter
    private static ArrayList<Student> list = new ArrayList();

    static {
        list.add(new Student(1, "Serik Serikov", "Astana", 2.0, 180,  18));
        list.add(new Student(2, "Berik Berikov", "Astana", 3.0, 180, 19));
        list.add(new Student(3, "Jerik Jerikov", "Shymkent", 3.5, 180, 20));
        list.add(new Student(4, "Kerik Kerikov", "Almaty", 4.0, 180, 21));
    }

    public static ArrayList<Student> getStudents(){
        return list;
    }

    public static Student getStudentById(int id) {

        return list.stream().filter(s-> s.getId()==id).findFirst().get();
    }

    public static void addStudent(Student student) {
        student.setId(id);
        id++;
        list.add(student);
    }

    public static void updateSt(Student student) {

        for(Student st: list){
            if(st.getId()==student.getId()){
                st.setAge(student.getAge());
                st.setCity(student.getCity());
                st.setFullName(student.getFullName());
                st.setGpa(student.getGpa());
            }
        }

    }

    public static void deleteSt(int id) {
        list.removeIf(s-> s.getId()==id);
    }
}
