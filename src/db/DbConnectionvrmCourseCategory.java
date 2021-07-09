package db;

import java.sql.*;

public class DbConnectionvrmCourseCategory {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    public DbConnectionvrmCourseCategory(){
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

    public void insertRecord(String vrmccname, int vrmcccourse){
        try {
            String sqlQuery = "INSERT INTO vrmcctb(vrmccName, vrmccCourse) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, vrmccname);
            preparedStatement.setInt(2, vrmcccourse);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int sem,String col,String tname,int ccnum,int ccid){
        try {
            String sqlQuery = "UPDATE "+ tname +" SET "+ col +"=? WHERE Semester=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,ccnum);
            preparedStatement.setInt(2,sem);
            preparedStatement.executeUpdate();
            DbConnectionCourses dbConnectionCourses=new DbConnectionCourses();
            dbConnectionCourses.unselectallcategorycourse(ccid,sem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id){
        try {
            String sqlQuery = "DELETE from coursecategorytb WHERE CCategoryID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(String  tname,String colname,int sem){
        try {
            String sqlQuery = "SELECT "+ colname +" FROM "+ tname +" where Semester=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,sem);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getRecords(int sem,String tname){
        try {
            String sqlQuery = "SELECT * FROM "+ tname +" where Semester=?";
            System.out.println(sqlQuery+sem);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,sem);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ResultSet getAllRecords(String tname){
        try {
            String sqlQuery = "SELECT * FROM "+ tname;
            System.out.println(sqlQuery);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
