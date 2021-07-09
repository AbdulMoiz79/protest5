package db;

import backingBean.CourseCategory;

import java.sql.*;

public class DbConnectionCourseCategory {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    private DbConnectionViewRoadmap dbConnectionViewRoadmap;
    public DbConnectionCourseCategory(){
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

    public void insertRecord(String cname, int tcourses, int rid, String tname){
        try {
            String sqlQuery = "INSERT INTO coursecategorytb(CategoryName, TotalCourses, rmID) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, cname);
            preparedStatement.setInt(2, tcourses);
            preparedStatement.setInt(3, rid);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
                dbConnectionViewRoadmap.addcoursecategorycolumn(cname.replace(" ","_"),tname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id,String ccn,int tc,String oname,String tname){

        try {
            String sqlQuery = "UPDATE coursecategorytb SET CategoryName=?,TotalCourses=? WHERE CCategoryID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,ccn);
            preparedStatement.setInt(2,tc);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
            dbConnectionViewRoadmap.updatecoursecategorycolumn(oname.replace(" ","_"),ccn.replace(" ","_"),tname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id,String tname){
        try {
            ResultSet resultSet=getRecord(id);

            String sqlQuery = "DELETE from coursecategorytb WHERE CCategoryID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            resultSet.next();
            dbConnectionViewRoadmap.deletecoursecategorycolumn(resultSet.getString("CategoryName").replace(" ","_"),tname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM coursecategorytb where CCategoryID=?";
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
            String sqlQuery = "SELECT * FROM coursecategorytb where rmID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getidRecord(int depid,String ccname){
        try {
            String sqlQuery = "SELECT CCategoryID FROM coursecategorytb where rmID=? AND CategoryName=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,depid);
            preparedStatement.setString(2,ccname);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
