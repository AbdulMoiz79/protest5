package db;

import java.sql.*;

public class DbConnectionViewRoadmap {
    private String dbURL = "jdbc:mysql://localhost:3306/login";
    private String username = "root";
    private String password = "";
    private Connection connection;

    public DbConnectionViewRoadmap(){
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


    public void initsemesters(String tname,int s){
        try {
            String sqlQuery = "INSERT INTO "+ tname +"(Semester) VALUES(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, s);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println("sem inserted=" + s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void makeroadmaptable(String rmname,int s){
        try {
            rmname=rmname.replace(".","p");
            rmname=rmname.replace(",","p");
            String sqlQuery = "CREATE TABLE IF NOT EXISTS " + rmname
                    + "  (Semester int(11) NOT NULL UNIQUE)";
            PreparedStatement pstatement=connection.prepareStatement(sqlQuery);
            pstatement.execute();
            pstatement.close();
            int i=0;
            while (i<s) {
                initsemesters(rmname, i+1);
                i++;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteroadmaptable(String rmname){
        try {
            rmname=rmname.replace(".","p");
            rmname=rmname.replace(",","p");
            String sqlQuery = "DROP TABLE "+rmname+";";
            PreparedStatement pstatement=connection.prepareStatement(sqlQuery);
            pstatement.execute();
            pstatement.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateroadmaptable(String rmname, String newrmname){
        try {
            rmname=rmname.replace(".","p");
            rmname=rmname.replace(",","p");
            newrmname=newrmname.replace(".","p");
            newrmname=newrmname.replace(",","p");
            String sqlQuery = "ALTER TABLE "+ rmname +" RENAME TO "+ newrmname +";";
            PreparedStatement pstatement=connection.prepareStatement(sqlQuery);
            pstatement.execute();
            pstatement.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addcoursecategorycolumn(String colname,String tname){
        try {
            String sqlQuery = "ALTER TABLE "+ tname +" ADD "+ colname +" INT(11);";
            PreparedStatement pstatement=connection.prepareStatement(sqlQuery);
            pstatement.execute();
            pstatement.close();

        } catch (SQLException e){

        }
    }

    public void deletecoursecategorycolumn(String colname,String tname){
        try {
            String sqlQuery = "ALTER TABLE "+ tname +" DROP COLUMN "+ colname +";";
            PreparedStatement pstatement=connection.prepareStatement(sqlQuery);
            pstatement.execute();
            pstatement.close();

        } catch (SQLException e){

        }
    }
    public void updatecoursecategorycolumn(String cololdname,String colnewname,String tname){
        try {

            String sqlQuery = "ALTER TABLE "+ tname +" CHANGE "+ cololdname +" " + colnewname + " INT(100);";
            PreparedStatement pstatement=connection.prepareStatement(sqlQuery);
            pstatement.execute();
            pstatement.close();

        } catch (SQLException e){

        }
    }
    public void updateccdata(String tname,String cname, int cnum,int s){
        try {
            String sqlQuery = "UPDATE "+ tname +" SET "+ cname +"=? WHERE Semester=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,cnum);
            preparedStatement.setInt(2,s);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }
    public void insertRecord(String rmname, int tcrhr, int degid){
        try {
            String sqlQuery = "INSERT INTO roadmaptb(rmName, rmCreditHour, degreeID) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, rmname);
            preparedStatement.setInt(2, tcrhr);
            preparedStatement.setInt(3, degid);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id,String rmn,int rmch){
        try {
            String sqlQuery = "UPDATE roadmaptb SET rmName=?,rmCreditHour=? WHERE rmID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,rmn);
            preparedStatement.setInt(2,rmch);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id){
        try {
            String sqlQuery = "DELETE from roadmaptb WHERE rmID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
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

    public ResultSet getRecords(String tname){
        try {
            String sqlQuery = "SELECT * FROM "+ tname;
            PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
            ResultSet result=preparedStatement.executeQuery();
            return result;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
