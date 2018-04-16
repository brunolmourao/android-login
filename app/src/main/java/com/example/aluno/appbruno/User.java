package com.example.aluno.appbruno;

import java.security.PrivilegedAction;

/**
 * Created by aluno on 12/04/2018.
 */

public class User {
    private String login;
    private String password;
    private String name;
    public User(String login,String password,String name){
        this.setLogin(login);
        this.setPassword(password);
    }
    public String getLogin(){
        return login;
    }
    public void setLogin(String login ){
        this.login = login;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password ){
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name ){
        this.name = name;
    }
    public int compare(String login, String password){
        int igual = 0;
        if(this.login.equals(login) && this.password.equals(password)){
            igual = 1;
        }else if(this.login.equals(login)){
            igual = 2;
        }else if(this.password.equals(password)){
            igual = 3;
        }
        return igual;
    }

}
