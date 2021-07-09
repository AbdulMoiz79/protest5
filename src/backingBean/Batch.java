package backingBean;

public class Batch {
    private int id;
    private String batchname;
    private String roadmapName;
    private int rmid;
    private int degid;


    public Batch() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public int getRmid() {
        return rmid;
    }

    public void setRmid(int rmid) {
        this.rmid = rmid;
    }

    public int getDegid() {
        return degid;
    }

    public void setDegid(int degid) {
        this.degid = degid;
    }

    public String getRoadmapName() {
        return roadmapName;
    }

    public void setRoadmapName(String roadmapName) {
        this.roadmapName = roadmapName;
    }
}
