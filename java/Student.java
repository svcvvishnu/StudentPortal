
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Niroop
 */
public class Student {
    
    public int STUDENT_ID;
    public String STUDENT_NAME;
    public String STUDENT_LEVEL;
    public String MAJOR;
    public int AGE;
    
    
    public void setStudentID(int studentID){
        STUDENT_ID = studentID;
    }
    
    public void setStudentName(String studentName){
        STUDENT_NAME = studentName;
    }
    
    public void setStudentLevel(String studentLevel){
        STUDENT_LEVEL = studentLevel;
    }
    
    public void setMajor(String major){
        MAJOR = major ;
    }
    
    public void setAge(int age){
        AGE = age ;
    }
    
    public int getStudentID(){
        return STUDENT_ID ;
    }
    
    public String getStudentName(){
        return STUDENT_NAME ;
    }
    
    public String getStudentLevel(){
        return STUDENT_LEVEL ;
    }
    
    public String getMajor(){
        return MAJOR  ;
    }
    
    public int getAge(){
        return AGE ;
    }
    
    public static Student getStudent(int studentId){
        String studentQuery = "select sid,sname,major,s_level,age from student s  where  s.sid = " + studentId ;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(studentQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Student student = new Student();
                 student.setStudentID(resultSet.getInt(1));
                 student.setStudentName(resultSet.getString(2));
                 student.setMajor(resultSet.getString(3));
                 student.setStudentLevel(resultSet.getString(4));
                 student.setAge(resultSet.getInt(5));
                 return student;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Student getStudentbyName( String studentName){
        String depQuery = "SELECT sid, sname from student where sname =  '" + studentName +"'";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(depQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Student student = new Student();
                 student.setStudentID(resultSet.getInt(1));
                 student.setStudentName(resultSet.getString(2));
                 return student;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean save(Student student){
        
        String insertQuery = "INSERT INTO student(sid,sname,major,s_level,age) \n" +
                    "values("+student.getStudentID()+",'"+student.getStudentName()+"',"
                + "'"+student.getMajor()+"','"+student.getStudentLevel()+"',"+student.getAge()+")";
        return PostgresSQLConnection.executeUpdate(insertQuery);
    }
    
    public static boolean update(Student student){
        
        String updateQuery = "UPDATE student set  sname = '"+student.getStudentName()+"' , major ='"+student.getMajor()+"' ,"
                + "s_level = '"+student.getStudentLevel()+"' , age = "+student.getAge()+ " where sid ="+student.getStudentID()+"";
        
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean delete(Student student){
        String updateQuery = "UPDATE courses set enrolled_count = (enrolled_count - 1) where cid in ( select cid from enrolled where sid  = "+student.getStudentID() + ")";
        boolean status = PostgresSQLConnection.executeUpdate(updateQuery);
        updateQuery = "DELETE from enrolled where sid = "+student.getStudentID();
        status = status & PostgresSQLConnection.executeUpdate(updateQuery);
        updateQuery = " DELETE from student where sid = "+student.getStudentID();
        return status && PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
}
