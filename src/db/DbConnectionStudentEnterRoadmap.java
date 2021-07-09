package db;

import java.sql.*;


public class DbConnectionStudentEnterRoadmap {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;
    private DbConnectionViewRoadmap dbConnectionViewRoadmap;
    public DbConnectionStudentEnterRoadmap(){
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

    public void deletestudenttable(){
        try {

            String sqlQuery = "DROP TABLE studentroadmap;";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQuery);
            preparedStatement1.execute();
            preparedStatement1.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public void makenewstudenttable(){
        deletestudenttable();
        try{
            String sqlQuery = "CREATE TABLE studentroadmap (id int,coursecode VARCHAR(200), course VARCHAR(200), semester int, credithour int, grade VARCHAR(50));";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insertRecord(int id, String ccode, String cname,int s,int crhr){
        try {
            String sqlQuery = "INSERT INTO studentroadmap(id, coursecode, course, semester, credithour) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, ccode);
            preparedStatement.setString(3,cname);
            preparedStatement.setInt(4, s);
            preparedStatement.setInt(5,crhr);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
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

    public ResultSet getRecords(int s){
        try {
            String sqlQuery = "SELECT * FROM studentroadmap where semester=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,s);
            ResultSet result = preparedStatement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
