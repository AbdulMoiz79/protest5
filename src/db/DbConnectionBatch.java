package db;

import java.sql.*;

public class DbConnectionBatch {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    public DbConnectionBatch(){
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

    public void insertRecord(String bname, String rmname,int rid,int did){
        try {
            String sqlQuery = "INSERT INTO batchtb(batchName,roadmapName, rmID, degID) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, bname);
            preparedStatement.setString(2,rmname);
            preparedStatement.setInt(3, rid);
            preparedStatement.setInt(4,did);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id,String bn){
        try {
            String sqlQuery = "UPDATE batchtb SET batchName=? WHERE batchID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,bn);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id){
        try {
            String sqlQuery = "DELETE from batchtb WHERE batchID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM batchtb where batchID=?";
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
            String sqlQuery = "SELECT * FROM batchtb where rmID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getallRecords(int did){
        try {
            String sqlQuery = "SELECT * FROM batchtb WHERE degID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,did);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
