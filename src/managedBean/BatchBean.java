package managedBean;

import backingBean.Batch;
import backingBean.CourseCategory;
import backingBean.Degree;
import backingBean.Roadmap;
import db.DbConnectionBatch;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class BatchBean {
    private CourseCategory courseCategory;
    private Batch batch;
    private Degree degree;
    private Roadmap roadmapEdited;
    private DbConnectionBatch dbConnection;
    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;
    private List<CourseCategory> courseCategories =new ArrayList<>();

    public BatchBean() {
        batch=new Batch();
        courseCategory = new CourseCategory();
        roadmapEdited=new Roadmap();
        degree=new Degree();
        dbConnection = new DbConnectionBatch();
    }
    public String registerUser() {
        roadmapEdited=(Roadmap) sessionMap.get("updatedroadmap");
        degree=(Degree) sessionMap.get("updatedDegree");
        int did=degree.getId();
        int rmid=roadmapEdited.getId();
        String rmname=roadmapEdited.getRoadmapversion();

        String batchname = batch.getBatchname();


        dbConnection.insertRecord(batchname,rmname, rmid,did);
        return "unibatch.xhtml?faces-redirect=true";
    }

    public List<Batch> getBatch() {

        List<Batch> batches = new ArrayList<Batch>();
        roadmapEdited= (Roadmap) sessionMap.get("updatedroadmap");
        int did=roadmapEdited.getId();
        ResultSet resultSet = dbConnection.getRecords(did);
        try {
            while (resultSet.next()) {
                Batch batch = new Batch();
                batch.setId(resultSet.getInt("batchID"));
                batch.setBatchname(resultSet.getString("batchName"));
                batch.setRmid(resultSet.getInt("rmID"));
                batches.add(batch);
            }
        } catch (Exception e) {

        }
        return batches;
    }

    public List<Batch> getallBatches() {

        List<Batch> batches = new ArrayList<Batch>();
        degree=(Degree) sessionMap.get("updatedDegree");
        int did=degree.getId();
        ResultSet resultSet = dbConnection.getallRecords(did);
        try {
            while (resultSet.next()) {
                Batch batch = new Batch();
                batch.setId(resultSet.getInt("batchID"));
                batch.setBatchname(resultSet.getString("batchName"));
                batch.setRoadmapName(resultSet.getString("roadmapName").replace("deg"+did+"v",""));
                batch.setRmid(resultSet.getInt("rmID"));
                batches.add(batch);
            }
        } catch (Exception e) {

        }
        return batches;
    }


    public String delete(int id){

        dbConnection.deleteRecord(id);

        return "unibatch.xhtml?faces-redirect=true";
    }

    public String updateBatch(Batch updatedbatch){

        String bname = updatedbatch.getBatchname();


        dbConnection.updateRecord(updatedbatch.getId(),bname);
        return "unibatch.xhtml?faces-redirect=true";

    }

    public String edit(int id){

        Batch batchEdited = new Batch();

        try{

            ResultSet resultSet=dbConnection.getRecord(id);
            resultSet.next();
            batchEdited = new Batch();

            batchEdited.setId(resultSet.getInt("batchID"));
            batchEdited.setBatchname(resultSet.getString("batchName"));

        }catch(Exception e){
            System.out.println(e);
        }

        sessionMap.put("updatedbatch", batchEdited);

        System.out.println("Edited batch is::" +batchEdited.getId());

        return "editbatch.xhtml?faces-redirect=true";
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public Batch getBatchobj() {
        return batch;
    }

    public void setBatchobj(Batch batch) {
        this.batch = batch;
    }

}


