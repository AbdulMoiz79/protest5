package managedBean;

import backingBean.Degree;
import backingBean.Roadmap;
import db.DbConnectionRoadmap;
import db.DbConnectionViewRoadmap;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class RoadmapBean {
    private Roadmap roadmap;
    private Degree degreeEdited;
    private DbConnectionRoadmap dbConnection;
    private DbConnectionViewRoadmap dbConnectionViewRoadmap;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;

    public RoadmapBean() {
        roadmap =new Roadmap();
        dbConnectionViewRoadmap= new DbConnectionViewRoadmap();
        degreeEdited = new Degree();
        dbConnection = new DbConnectionRoadmap();
    }

    public String registerUser() {


        degreeEdited= (Degree) sessionMap.get("updatedDegree");
        int did=degreeEdited.getId();
        int dsem=degreeEdited.getTsemesters();

        String rmversion = roadmap.getRoadmapversion().replace(" ","_");
        int tcrhr = roadmap.getRequiredcredithour();
        dbConnection.insertRecord("deg"+did+"v"+rmversion, tcrhr, did,dsem);
        return "uniroadmap.xhtml?faces-redirect=true";
    }

    public List<Roadmap> getRoadmap() {

        List<Roadmap> roadmaps = new ArrayList<Roadmap>();
        degreeEdited= (Degree) sessionMap.get("updatedDegree");
        int did=degreeEdited.getId();

        ResultSet resultSet = dbConnection.getRecords(did);

        try {
            while (resultSet.next()) {
                Roadmap roadmap = new Roadmap();
                roadmap.setId(resultSet.getInt("rmID"));
                roadmap.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
                roadmap.setRequiredcredithour(resultSet.getInt("rmCreditHour"));
                roadmap.setDsemesters(resultSet.getInt("degSemester"));
                roadmap.setDegreeid(resultSet.getInt("degreeID"));
                roadmaps.add(roadmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roadmaps;
    }

    public String delete(int id,int did, String version){
        version=version.replace(" ","_");
        dbConnection.deleteRecord(id,"deg"+did+"v"+version);
        return "uniroadmap.xhtml?faces-redirect=true";
    }

    public String openroadmap(int id){
        degreeEdited= (Degree) sessionMap.get("updatedDegree");
        int did=degreeEdited.getId();
        Roadmap roadmapEdited = new Roadmap();
        try{
            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            roadmapEdited = new Roadmap();

            roadmapEdited.setId(resultSet.getInt("rmID"));
            roadmapEdited.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
            roadmapEdited.setRequiredcredithour(resultSet.getInt("rmCreditHour"));
            roadmapEdited.setDsemesters(resultSet.getInt("degSemester"));



        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedroadmap", roadmapEdited);
        return "unicoursecategory.xhtml?faces-redirect=true";
    }
    public String viewroadmap(int id){

        Roadmap roadmapEdited = new Roadmap();
        Roadmap roadmap=new Roadmap();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            roadmapEdited = new Roadmap();
            degreeEdited= (Degree) sessionMap.get("updatedDegree");
            int did=degreeEdited.getId();
            roadmapEdited.setId(resultSet.getInt("rmID"));
            roadmapEdited.setRoadmapversion(resultSet.getString("rmName"));
            roadmap.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
            roadmapEdited.setRequiredcredithour(resultSet.getInt("rmCreditHour"));
            roadmapEdited.setDsemesters(resultSet.getInt("degSemester"));


        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedroadmap", roadmapEdited);
        sessionMap2.put("roadmapname", roadmap);

        return "viewroadmap.xhtml?faces-redirect=true";
    }

    public String studentviewroadmap(int id){

        Roadmap roadmapEdited = new Roadmap();
        Roadmap roadmap=new Roadmap();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            roadmapEdited = new Roadmap();
            degreeEdited= (Degree) sessionMap.get("updatedDegree");
            int did=degreeEdited.getId();
            roadmapEdited.setId(resultSet.getInt("rmID"));
            roadmapEdited.setRoadmapversion(resultSet.getString("rmName"));
            roadmap.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
            roadmapEdited.setRequiredcredithour(resultSet.getInt("rmCreditHour"));
            roadmapEdited.setDsemesters(resultSet.getInt("degSemester"));


        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedroadmap", roadmapEdited);
        sessionMap2.put("roadmapname", roadmap);

        return "studentviewroadmap.xhtml?faces-redirect=true";
    }

    public String openbatch(int id){
        degreeEdited= (Degree) sessionMap.get("updatedDegree");
        int did=degreeEdited.getId();
        Roadmap roadmapEdited = new Roadmap();
        Roadmap nameroadmap=new Roadmap();

        try{

            ResultSet resultSet= dbConnection.getRecord(id);
            resultSet.next();
            roadmapEdited = new Roadmap();

            roadmapEdited.setId(resultSet.getInt("rmID"));
            roadmapEdited.setRoadmapversion(resultSet.getString("rmName"));
            nameroadmap.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
            roadmapEdited.setRequiredcredithour(resultSet.getInt("rmCreditHour"));
            roadmapEdited.setDsemesters(resultSet.getInt("degSemester"));



        }catch(Exception e){
            e.printStackTrace();
        }
        sessionMap.put("updatedroadmap", roadmapEdited);
        sessionMap.put("nameroadmap",nameroadmap);
        return "unibatch.xhtml?faces-redirect=true";
    }

    public String updateRoadmap(Roadmap updatedroadmap){
        degreeEdited= (Degree) sessionMap.get("updatedDegree");
        Roadmap oldroadmap=(Roadmap) sessionMap.get("oldroadmapupdated");
        int did=degreeEdited.getId();
        String rmname = updatedroadmap.getRoadmapversion();
        int tcrhr = updatedroadmap.getRequiredcredithour();


        dbConnection.updateRecord(updatedroadmap.getId(),"deg"+did+"v"+rmname,"deg"+did+"v"+oldroadmap.getRoadmapversion(),tcrhr);
        return "uniroadmap.xhtml?faces-redirect=true";

    }

    public String edit(int id){

        degreeEdited= (Degree) sessionMap.get("updatedDegree");
        int did=degreeEdited.getId();
        Roadmap roadmapEdited = new Roadmap();
        Roadmap oldroadmap=new Roadmap();
        try{

            ResultSet resultSet=dbConnection.getRecord(id);
            resultSet.next();
            roadmapEdited = new Roadmap();
            oldroadmap=new Roadmap();
            roadmapEdited.setId(resultSet.getInt("rmID"));
            roadmapEdited.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
            roadmapEdited.setRequiredcredithour(resultSet.getInt("rmCreditHour"));
            oldroadmap.setRoadmapversion(resultSet.getString("rmName").replace("deg"+did+"v",""));
            roadmapEdited.setRequiredcredithour(resultSet.getInt("rmCreditHour"));

        }catch(Exception e){
            e.printStackTrace();
        }

        sessionMap.put("updatedroadmap", roadmapEdited);
        sessionMap.put("oldroadmapupdated",oldroadmap);

        return "editroadmap.xhtml?faces-redirect=true";
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public Roadmap getRoadmapobj() {
        return roadmap;
    }

    public void setRoadmapobj(Roadmap roadmap) {
        this.roadmap = roadmap;
    }

}


