package managedBean;

import backingBean.Dept;
import db.DbConnectionDept;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class DeptBean {
    private Dept dept;
    private DbConnectionDept dbConnectionDept;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public DeptBean() {
        dept = new Dept();
        dbConnectionDept = new DbConnectionDept();
    }

    public String registerUser() {

        String username = dept.getUsername();
        String hod = dept.getHod();

        dbConnectionDept.insertRecord(username, hod);
        return "unidept.xhtml?faces-redirect=true";
    }

    public List<Dept> getDept() {

        List<Dept> depts = new ArrayList<Dept>();

        ResultSet resultSet = dbConnectionDept.getRecords();

        try {
            while (resultSet.next()) {
                Dept dept = new Dept();
                dept.setId(resultSet.getInt("deptID"));
                dept.setUsername(resultSet.getString("deptName"));
                dept.setHod(resultSet.getString("HODName"));
                depts.add(dept);
            }
        } catch (Exception e) {
        }

        return depts;
    }
    public String delete(int id){

        dbConnectionDept.deleteRecord(id);

        return "unidept.xhtml?faces-redirect=true";
    }

    public String opendept(int id){

        Dept DeptEdited = new Dept();

        try{

            ResultSet resultSet= dbConnectionDept.getRecord(id);
            resultSet.next();
            DeptEdited = new Dept();

            DeptEdited.setId(resultSet.getInt("deptID"));
            DeptEdited.setUsername(resultSet.getString("deptName"));

        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedDept", DeptEdited);
        return "unidegree.xhtml?faces-redirect=true";
    }
    public String openstudentdept(int id){

        Dept DeptEdited = new Dept();

        try{

            ResultSet resultSet= dbConnectionDept.getRecord(id);
            resultSet.next();
            DeptEdited = new Dept();

            DeptEdited.setId(resultSet.getInt("deptID"));
            DeptEdited.setUsername(resultSet.getString("deptName"));

        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedDept", DeptEdited);
        return "studentunidegree.xhtml?faces-redirect=true";
    }

    public String updateDept(Dept updatedDept){

        String username = updatedDept.getUsername();
        String hod = updatedDept.getHod();


        dbConnectionDept.updateRecord(updatedDept.getId(),username,hod);
        return "unidept.xhtml?faces-redirect=true";

    }

    public String edit(int id){

        Dept deptEdited = new Dept();

        try{

            ResultSet resultSet=dbConnectionDept.getRecord(id);
            resultSet.next();
            deptEdited = new Dept();

            deptEdited.setId(resultSet.getInt("deptID"));
            deptEdited.setUsername(resultSet.getString("deptName"));
            deptEdited.setHod(resultSet.getString("HODName"));

        }catch(Exception e){
            e.printStackTrace();
        }

        sessionMap.put("updatedDept", deptEdited);


        return "editdept.xhtml?faces-redirect=true";
    }
    void resetForm(){

        dept.setId(0);
        dept.setUsername("");
        dept.setHod("");

    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Dept getDeptobj() {
        return dept;
    }

    public void setDeptobj(Dept dept) {
        this.dept = dept;
    }
}


