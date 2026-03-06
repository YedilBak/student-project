package kz.students.application.db;



import kz.students.application.entity.Student;
import kz.students.application.entity.University;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class DBConnector {
    private static Connection connection;
    private static String login = "postgres";
    private static String password = "postgres";
    private static String url = "jdbc:postgresql://localhost:5433/tech?currentSchema=orda";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getAllStudents(){

        ArrayList<Student> students = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.students st INNER JOIN " +
                    "orda.universities u ON st.university_id = u.id ORDER BY st.id ASC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setCity(resultSet.getString("city"));
                student.setHeight(resultSet.getDouble("height"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setFullName(resultSet.getString("full_name"));

                University university = new University();
                university.setId(resultSet.getInt("university_id"));
                university.setName(resultSet.getString("name"));
                university.setCountStudents(resultSet.getInt("count_students"));

                student.setUniversity(university);

                students.add(student);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return students;

    }

    public static Student getStudentById(int id){

        Student student = new Student();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.students st INNER JOIN " +
                    "orda.universities u ON st.university_id = u.id WHERE st.id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setCity(resultSet.getString("city"));
                student.setHeight(resultSet.getDouble("height"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setFullName(resultSet.getString("full_name"));

                University university = new University();
                university.setId(resultSet.getInt("university_id"));
                university.setName(resultSet.getString("name"));
                university.setCountStudents(resultSet.getInt("count_students"));

                student.setUniversity(university);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return student;

    }


    public static void updateStudent(Student student){

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE orda.students SET full_name=?, " +
                    "city=?, gpa=?, height=?, age=?, university_id=? WHERE id=?");
            statement.setString(1, student.getFullName());
            statement.setString(2, student.getCity());
            statement.setDouble(3, student.getGpa());
            statement.setDouble(4, student.getHeight());
            statement.setInt(5, student.getAge());
            statement.setInt(6, student.getUniversity().getId());
            statement.setInt(7, student.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int id){
        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM orda.students WHERE id=?");
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addStudent(Student student){

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orda.students (full_name, city, " +
                    "gpa, height, age, university_id) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, student.getFullName());
            statement.setString(2, student.getCity());
            statement.setDouble(3, student.getGpa());
            statement.setDouble(4, student.getHeight());
            statement.setInt(5, student.getAge());
            statement.setInt(6, student.getUniversity().getId());

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static ArrayList<University> getAllUniversities() {

        ArrayList<University>list  = new ArrayList();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.universities");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                University university = new University();
                university.setId(resultSet.getInt("id"));
                university.setName(resultSet.getString("name"));
                university.setCountStudents(resultSet.getInt("count_students"));

                list.add(university);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
