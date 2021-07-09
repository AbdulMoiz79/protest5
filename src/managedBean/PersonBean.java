package managedBean;

import backingBean.User;
import db.DbConnectionDept;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class PersonBean {
    private User user;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    private int number = 5;
    public PersonBean() {
        user = new User();
    }


    void resetForm(){

        user.setId(0);
        user.setUsername("");
        user.setPassword("");

    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
