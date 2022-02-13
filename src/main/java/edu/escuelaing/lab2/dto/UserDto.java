package edu.escuelaing.lab2.dto;

import edu.escuelaing.lab2.controller.auth.RoleEnum;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class UserDto {

    private String name;
    private String email;
    private String lastName;
    private String password;
    //private List<RoleEnum> roles = new LinkedList<>();

    public UserDto(String name, String email, String lastName, String password) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
        //this.roles.add(RoleEnum.valueOf(role));
    }
    /*
    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }


     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




    public UserDto(JSONObject jsObject){
        this.name = jsObject.get("name").toString();
        this.email = jsObject.get("email").toString();
        this.lastName = jsObject.getString("lastName");
    }
}
