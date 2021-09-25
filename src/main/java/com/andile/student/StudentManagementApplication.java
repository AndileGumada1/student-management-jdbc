package com.andile.student;


import com.andile.student.database.StudentDAO;
import com.andile.student.model.Student;

public class StudentManagementApplication {

    public static void main(String[] args) throws Exception {

        //create an instance of the studentDAO layer
        StudentDAO studentDAO = new StudentDAO();

        //inserting a new record of student in a database
        Student student = new Student();
        student.setName("Thabo Moppa");
        student.setAddress("234 Sosha Pretoria 567");
        student.setMobile("0783466732");
        student.setEmail("thabomoppa@gmail.com");
        studentDAO.insertStudent(student);

        //delete a student record from the database using the student ID
        System.out.println("Student deleted :"+studentDAO.deleteStudent(4L));

        //update a student record in the database
        studentDAO.updateStudent(new Student(1,"Andile","23793 Orange Street","0614723334","andilegumada@gmail.com"));

        //calling the method to show the list of students in the database
        System.out.println(studentDAO.getStudentList());
    }

}
