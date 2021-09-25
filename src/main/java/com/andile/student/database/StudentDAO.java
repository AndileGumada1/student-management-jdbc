package com.andile.student.database;

import com.andile.student.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
* This is a Java Data Access Object class which provides the CRUD Database operationa
* for the student table
**/
public class StudentDAO {

    //Database properties username , password and the database URL
   private final String dbURL = "jdbc:mysql://localhost:3306/studentdb";
   private final String username = "root";
   private final String password = "root";

    /**
     * SQL STATIC QUERIES FOR SELECTING ALL STUDENTS, INSERT A NEW STUDENT DELETE
     * AND UPDATING A STUDENT RECORD IN THE DATABASE.
    **/
    private static final String SELECT_ALL_STUDENTS_QUERY = "select * from student";

    private static final String INSERT_STUDENT_QUERY = "insert into student "+
            "(id,name,address,mobile,email)VALUES " +
            "(?,?,?,?,?)";
    private static final String DELETE_STUDENT_QUERY = "delete from student where id = ?";

    private static final String UPDATE_STUDENT_QUERY = "update student set name=?,address=?,mobile =?,email = ? where id = ?";

    //empty constructor to instantiate the StudentDAO object
    public StudentDAO(){

    }
    //create a connection to the database
    public Connection getConnection(){

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// The forName() method will load the class from the fully qualified class name specified as an argument.
            connection = DriverManager.getConnection(dbURL,username,password);

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
    * This method is used for returning a list of students in the database
     * @return List of students
    * */
    public  List<Student> getStudentList(){
        List<Student> studentList = new ArrayList<>();
        try (//try with resource statement will auto close connection
            Connection connection = getConnection();
            //create a statement using the connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS_QUERY);){
            connection.prepareStatement(SELECT_ALL_STUDENTS_QUERY);

            //Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            //Process the ResultSet using the query object
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String mobile = resultSet.getString("mobile");
                String email = resultSet.getString("email");

                //print out all the students in the database
                System.out.println( id + " ," + name + " " + address + "  " + mobile + " " + email);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return studentList;
    }
    /**
    * This method used to insert new student record into the database
     * @param student represents the student object to be saved in the database
     **/
    public void insertStudent(Student student){

        try (
            //get the database connection from the connection object
            Connection connection = getConnection();

            //create a prepared statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_QUERY);){

            preparedStatement.setLong(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setString(4,student.getMobile());
            preparedStatement.setString(5,student.getEmail());

            System.out.println(preparedStatement);

            //Execute the query or update query
            preparedStatement.executeUpdate();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    /**
    * This method used to delete a student in a database using a student ID
     * @param id represents a Student id
     * @throws SQLException
     * @return boolean true when student is deleted
    **/
    public boolean deleteStudent(Long id) throws SQLException {
        boolean rowDeleted;

        try ( //try with resource statement will auto close connection
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_QUERY);){
            preparedStatement.setLong(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    /**
     * This method used to update student record in database using a student object
     * @param student represents a Student object to be updated
     * @throws SQLException
     * @return boolean true when student is updated
     **/
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;

        try (Connection connection = getConnection();//try with resource statement will auto close connection
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_QUERY);){

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getAddress());
            preparedStatement.setString(3,student.getMobile());
            preparedStatement.setString(4,student.getEmail());
            preparedStatement.setLong(5,student.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
