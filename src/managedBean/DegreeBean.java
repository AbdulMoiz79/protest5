package managedBean;

import backingBean.Degree;
import backingBean.Dept;
import db.DbConnectionDegree;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class DegreeBean {
    private Degree degree;
    private Dept deptEdited;
    private DbConnectionDegree dbConnection;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public DegreeBean() {
        degree = new Degree();
        deptEdited = new Dept();
        dbConnection = new DbConnectionDegree();
    }

    public String registerUser() {


        deptEdited= (Dept) sessionMap.get("updatedDept");
        int did=deptEdited.getId();


        String degreename = degree.getDegreename();
        String degreehead = degree.getHeadname();

        double sems=degree.getTsemesters();

        double duration= (sems/2);

        int tsem=degree.getTsemesters();

        dbConnection.insertRecord(degreename, degreehead,did,duration,tsem);
        return "unidegree.xhtml?faces-redirect=true";
    }

    public List<Degree> getDegree() {

        List<Degree> degrees = new ArrayList<Degree>();
        deptEdited= (Dept) sessionMap.get("updatedDept");
        int did=deptEdited.getId();

        ResultSet resultSet = dbConnection.getRecords(did);

        try {
            while (resultSet.next()) {
                Degree degree = new Degree();
                degree.setId(resultSet.getInt("degID"));
                degree.setDegreename(resultSet.getString("degName"));
                degree.setHeadname(resultSet.getString("degHead"));
                degree.setDuration(resultSet.getDouble("degDuration"));
                degree.setTsemesters(resultSet.getInt("degTSemesters"));
                degree.setDeptid(resultSet.getInt("deptID"));
                degrees.add(degree);
            }
        } catch (Exception e) {

        }

        return degrees;
    }
    public String delete(int id){

        dbConnection.deleteRecord(id);

        return "unidegree.xhtml?faces-redirect=true";
    }

    public String opendegree(int id){

        Degree DegreeEdited = new Degree();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            DegreeEdited = new Degree();

            DegreeEdited.setId(resultSet.getInt("degID"));
            DegreeEdited.setDegreename(resultSet.getString("degName"));
            DegreeEdited.setTsemesters(resultSet.getInt("degTSemesters"));

        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedDegree", DegreeEdited);
        return "uniroadmap.xhtml?faces-redirect=true";
    }

    public String openstudentdegree(int id){

        Degree DegreeEdited = new Degree();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            DegreeEdited = new Degree();

            DegreeEdited.setId(resultSet.getInt("degID"));
            DegreeEdited.setDegreename(resultSet.getString("degName"));
            DegreeEdited.setTsemesters(resultSet.getInt("degTSemesters"));

        }catch(Exception e){
            System.out.println(e);
        }
        sessionMap.put("updatedDegree", DegreeEdited);
        return "studentunibatch.xhtml?faces-redirect=true";
    }



    public String updateDegree(Degree updatedDegree){

        String degreename = updatedDegree.getDegreename();
        String headname = updatedDegree.getHeadname();
        int tsem=updatedDegree.getTsemesters();
        double sems=tsem;
        double duration= (sems/2);


        dbConnection.updateRecord(updatedDegree.getId(),degreename,headname,duration,tsem);
        return "unidegree.xhtml?faces-redirect=true";

    }

    public String edit(int id){

        Degree degreeEdited = new Degree();

        try{

            ResultSet resultSet=dbConnection.getRecord(id);
            resultSet.next();
            degreeEdited = new Degree();

            degreeEdited.setId(resultSet.getInt("degID"));
            degreeEdited.setDegreename(resultSet.getString("degName"));
            degreeEdited.setHeadname(resultSet.getString("degHead"));
            degreeEdited.setTsemesters(resultSet.getInt("degTSemesters"));

        }catch(Exception e){
            e.printStackTrace();
        }

        sessionMap.put("updatedDegree", degreeEdited);


        return "editdegree.xhtml?faces-redirect=true";
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Degree getDegreeobj() {
        return degree;
    }

    public void setDegreeobj(Degree degree) {
        this.degree = degree;
    }
}


