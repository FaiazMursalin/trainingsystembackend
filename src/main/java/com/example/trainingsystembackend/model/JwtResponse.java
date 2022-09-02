package com.example.trainingsystembackend.model;



//import com.example.studentbackend.view.View;
//import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
//    @JsonView(View.Student.class)
    private final String jwttoken;

//    @JsonView(View.Student.class)
    private String role;
    private Long id;

    public JwtResponse(String jwttoken, String role,Long id) {
        this.jwttoken = jwttoken;
        this.role=role;
        this.id=id;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
