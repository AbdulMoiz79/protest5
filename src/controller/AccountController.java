package controller;

import entities.Account;
import model.AccountModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SessionScoped
@ManagedBean(name = "accountController")
public class AccountController {
    private Account account=new Account();
    private String errorMessage="";
    private boolean remember=false;

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void Login(){
        System.out.println("in login");
        AccountModel accountModel=new AccountModel();
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpServletResponse response=(HttpServletResponse) facesContext.getExternalContext().getResponse();
        if (accountModel.login(this.account.getUsername(),this.account.getPassword())) {
            if (this.remember) {

                Cookie ckUsername = new Cookie("username", this.account.getUsername());
                ckUsername.setMaxAge(3600);
                response.addCookie(ckUsername);
                Cookie ckPassword = new Cookie("password", this.account.getPassword());
                ckPassword.setMaxAge(3600);
                response.addCookie(ckPassword);
            }
            this.errorMessage="";
            redirect("unidept.xhtml");
        }
        else {
            this.errorMessage="Invalid Username or Password";
            redirect("masterlogin.xhtml");
        }
    }

    public void logout(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpServletRequest request=(HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response=(HttpServletResponse) facesContext.getExternalContext().getResponse();
        //Remove cookie
        for (Cookie ck:request.getCookies()){
            if (ck.getName().equalsIgnoreCase("username")){
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
            if (ck.getName().equalsIgnoreCase("password")){
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
        redirect("index.xhtml");
    }

    public void verifyLogin(){
        Account acc=checkCookie();
        if (acc !=null){

            AccountModel accountModel=new AccountModel();
            if (accountModel.login(acc.getUsername(),acc.getPassword())){
                this.account=acc;
                redirect("unidept.xhtml");
                this.errorMessage="";
            }
            else{
                this.errorMessage="Invalid Username or Password";
                redirect("masterlogin.xhtml");
            }
        }
    }

    private void redirect(String page){
        try {
            FacesContext fc=FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(page);
        } catch (Exception e){

        }
    }

    private Account checkCookie(){
        Account account=null;
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpServletRequest request=(HttpServletRequest) facesContext.getExternalContext().getRequest();
        String username="",password="";
        Cookie[] cookies=request.getCookies();
        if (cookies!=null)
        {
            for (Cookie ck : cookies){
                if (ck.getName().equalsIgnoreCase("username"))
                    username=ck.getValue();
                if (ck.getName().equalsIgnoreCase("password"))
                    password=ck.getValue();
            }
        }
        if (!username.isEmpty() && !password.isEmpty())
            account=new Account(username,password);
        return account;
    }
}
