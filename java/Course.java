
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
public class Course {
    
    public String COURSE_ID;
    public String COURSE_NAME;
    public int FACULTY_ID;
    public String FACULTY_NAME;
    public String MEETS_AT;
    public String ROOM;
    public int COURSE_LIMIT;
    public int ENROLLED_COUNT;
    
    
    public void setCourseName(String courseName){
        COURSE_NAME = courseName;
    }
    
    public void setCourseId(String courseId){
        COURSE_ID = courseId;
    }
    
    public void setFacultyId(int facultyId){
        FACULTY_ID  = facultyId;
    }
    
    public void setFacultyName(String facultyName){
        FACULTY_NAME  = facultyName;
    }
    
    public void setMeetsAt(String meetsAt){
        MEETS_AT  = meetsAt ;
    }
    
    public void setRoom(String room){
        ROOM  = room ;
    }
    
    public void setCourseLimit(int courseLimit){
        COURSE_LIMIT  = courseLimit ;
    }
    
    public void setEnrolledCount(int courseLimit){
        ENROLLED_COUNT  = courseLimit ;
    }
    
    public String getCourseName(){
        return COURSE_NAME ;
    }
    
    public String getCourseId(){
        return COURSE_ID ;
    }
    
    public int getFacultyId(){
        return FACULTY_ID ;
    }
    
    public String getFacultyName(){
        return FACULTY_NAME ;
    }
    
    public String getMeetsAt(){
        return MEETS_AT  ;
    }
    
    public String getRoom(){
        return ROOM  ;
    }
    
    public int getCourseLimit(){
        return COURSE_LIMIT;
    }
    
    public int getEnrolledCount(){
        return ENROLLED_COUNT;
    }
    
    public static Course getCourse(String courseId){
        String staffQuery = "select c.cid,c.cname,c.meets_at,c.room,f.fname, c.course_limit,f.fid from courses c , faculty f where c.f_id = f.fid and c.cid = " + courseId;
        ResultSet resultSet = PostgresSQLConnection.executeQuery(staffQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Course course = new Course();
                 course.setCourseId(courseId);
                 course.setCourseName(resultSet.getString(2));
                 course.setMeetsAt(resultSet.getString(3));
                 course.setRoom(resultSet.getString(4));
                 course.setFacultyName(resultSet.getString(5));
                 course.setCourseLimit(resultSet.getInt(6));
                 course.setFacultyId(resultSet.getInt(7));
                 return course;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Course getCoursebyName( String courseName){
        String courseQuery = "SELECT cid, cname from courses where cname =  '" + courseName +"'";
        ResultSet resultSet = PostgresSQLConnection.executeQuery(courseQuery);
        
        try {
            assert resultSet != null;
             if(resultSet.next()){
                 Course course = new Course();
                 course.setCourseId(resultSet.getString(2));
                 course.setCourseName(resultSet.getString(1));
                 return course;
            }
                
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean save(Course course){
        
        String insertQuery = "INSERT INTO courses(cid,cname,meets_at,room,f_id,course_limit,enrolled_count) \n" +
                    "values('"+course.getCourseId()+"','"+course.getCourseName()+"',"
                + "'"+course.getMeetsAt()+"','"+course.getRoom()+"', "+course.getFacultyId()+" ,"+course.getCourseLimit()+",0)";
        return PostgresSQLConnection.executeUpdate(insertQuery);
    }
    
    public static boolean update(Course course){
        
        String updateQuery = "UPDATE courses set  cname = '"+course.getCourseName()+"' , meets_at ='"+course.getMeetsAt()+"',"
                + "room = '"+course.getRoom()+"' , f_id = "+course.getFacultyId()+" ,"
                + "course_limit = "+course.getCourseLimit()+" where cid ='"+course.getCourseId()+"'";
        
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean incrementEnrolCount(String courseID){
        String updateQuery = "UPDATE courses set  enrolled_count = (enrolled_count + 1) where cid ='"+courseID+"'";
        return PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
    public static boolean delete(Course course){
        String updateQuery = "DELETE from enrolled where cid = '"+course.getCourseId()+"'";
        boolean status = PostgresSQLConnection.executeUpdate(updateQuery);
         updateQuery = " DELETE from courses where cid ='"+course.getCourseId()+"'";
        return status && PostgresSQLConnection.executeUpdate(updateQuery);
    }
    
}
