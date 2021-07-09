package db;

import java.sql.*;


public class DbConnectionRoadmap {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    private DbConnectionViewRoadmap dbConnectionViewRoadmap;
    public DbConnectionRoadmap(){
        dbConnectionViewRoadmap=new DbConnectionViewRoadmap();
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

    public void insertRecord(String rmname, int tcrhr, int degid,int s){
        try {
            String sqlQuery = "INSERT INTO roadmaptb(rmName, rmCreditHour,degSemester, degreeID) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, rmname);
            preparedStatement.setInt(2, tcrhr);
            preparedStatement.setInt(3,s);
            preparedStatement.setInt(4, degid);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
                dbConnectionViewRoadmap.makeroadmaptable(rmname,s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id,String rmn,String oname,int rmch){
        try {
            String sqlQuery = "UPDATE roadmaptb SET rmName=?,rmCreditHour=? WHERE rmID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,rmn);
            preparedStatement.setInt(2,rmch);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
            dbConnectionViewRoadmap.updateroadmaptable(oname,rmn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id,String rmname){
        try {
            String sqlQuery = "DELETE from roadmaptb WHERE rmID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            dbConnectionViewRoadmap.deleteroadmaptable(rmname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM roadmaptb where rmID=?";
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
            String sqlQuery = "SELECT * FROM roadmaptb where degreeID=?";
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
