package db;

import java.sql.*;

public class DbConnectionStudentCourses {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    public DbConnectionStudentCourses(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL,username,password);
            if(connection!=null){
                System.out.println("Success");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(String ccode, String cname,int crhr, int ccid){
        try {
            String sqlQuery = "INSERT INTO coursestb(courseCode, courseName, courseCreditHour, courseSelected, courseSelectedSem, CCategoryID) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, ccode);
            preparedStatement.setString(2, cname);
            preparedStatement.setInt(3,crhr);
            preparedStatement.setBoolean(4,false);
            preparedStatement.setInt(5,0);
            preparedStatement.setInt(6, ccid);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id,String cc,String cn,int crhr){
        try {
            String sqlQuery = "UPDATE coursestb SET courseCode=?,courseName=?,courseCreditHour=? WHERE courseID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,cc);
            preparedStatement.setString(2,cn);
            preparedStatement.setInt(3,crhr);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setselectcourse(int s, String ccode){
        try {
            String sqlQuery = "UPDATE coursestb SET courseSelected=TRUE ,courseSelectedSem=? WHERE courseCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,s);
            preparedStatement.setString(2,ccode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setstudentselectcourse(String ccode){
        try {
            String sqlQuery = "UPDATE coursestb SET StudentSelectSubject=1 WHERE courseCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,ccode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void unsetstudentselectcourse(){
        try {
            String sqlQuery = "UPDATE coursestb SET StudentSelectSubject=0";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void unselectcourse(String ccode){
        try {
            String sqlQuery = "UPDATE coursestb SET courseSelected=FALSE ,courseSelectedSem=0 WHERE courseCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,ccode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void unselectallcategorycourse(int ccid,int s){
        try {
            String sqlQuery = "UPDATE coursestb SET courseSelected=FALSE ,courseSelectedSem=0 WHERE CCategoryID=? AND courseSelectedSem=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,ccid);
            preparedStatement.setInt(2,s);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id){
        try {
            String sqlQuery = "DELETE from coursestb WHERE courseID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM coursestb where courseID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getRecords(int depid){
        try {
            String sqlQuery = "SELECT * FROM coursestb where CCategoryID=? AND courseSelected = true AND StudentSelectSubject = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getTotalCredithours(int depid){
        try {
            String sqlQuery = "SELECT courseCreditHour FROM coursestb where CCategoryID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getTotalCredithoursselected(int depid){
        try {
            String sqlQuery = "SELECT courseCreditHour FROM coursestb where CCategoryID=? AND courseSelected=TRUE";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getunselectedRecords(int depid){
        try {
            String sqlQuery = "SELECT * FROM coursestb where CCategoryID=? AND courseSelected=FALSE";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getselectedRecords(int depid,int s){
        try {
            String sqlQuery = "SELECT * FROM coursestb where CCategoryID=? AND courseSelected=TRUE AND courseSelectedSem=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            preparedStatement.setInt(2,s);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getCCRecords(String ccname){
        try {
            String sqlQuery = "SELECT * FROM coursestb where CCategoryName=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,ccname);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
