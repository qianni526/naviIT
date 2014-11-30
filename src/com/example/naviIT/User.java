package com.example.naviIT;

public class User {
    
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String isAdmin;
    
    public User(){}
     
    public User(int id, String username, String firstname, String lastname, String isAdmin){
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }
    
    public User(int id, String username, String firstname, String lastname){
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
       
    }
    
     
    public int getId(){
        return this.id;
    }
     
    public String getUserName(){
        return this.username;
    }
 
    public String getFirstName(){
        return this.firstname;
    }
 
    public String getLastName(){
        return this.lastname;
    }
    
    public String getIsAdmin(){
        return this.isAdmin;
    }
    
}