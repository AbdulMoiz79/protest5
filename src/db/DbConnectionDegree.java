package db;

import java.sql.*;

public class DbConnectionDegree {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    public DbConnectionDegree(){
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

    public void insertRecord(String dname, String dhead, int dptid, double dur, int ts){
        try {
            String sqlQuery = "INSERT INTO degreetb(degName, degHead, degDuration,degTSemesters, deptID) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, dname);
            preparedStatement.setString(2, dhead);
            preparedStatement.setDouble(3,dur);
            preparedStatement.setInt(4,ts);
            preparedStatement.setInt(5, dptid);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id, String dname, String hname, double dur, int ts){
        try {
            String sqlQuery = "UPDATE degreetb SET degName=?,degHead=?,degDuration=?,degTSemesters=? WHERE degID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,dname);
            preparedStatement.setString(2,hname);
            preparedStatement.setDouble(3,dur);
            preparedStatement.setInt(4,ts);
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id){
        try {
            String sqlQuery = "DELETE from degreetb WHERE degID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM degreetb where degID=?";
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
            String sqlQuery = "SELECT * FROM degreetb where deptID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
