package kz.students.application.db;



import kz.students.application.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orda.students");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setCity(resultSet.getString("city"));
                student.setHeight(resultSet.getDouble("height"));
                student.setGpa(resultSet.getDouble("gpa"));
                student.setFullName(resultSet.getString("full_name"));

                students.add(student);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return students;

    }

}
