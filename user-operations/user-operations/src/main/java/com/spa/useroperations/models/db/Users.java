package com.spa.useroperations.models.db;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "spa.Users")
public class Users {
    
    @Column(name="email", nullable=true)
    private String email;

    @Column(name="first_name", nullable=true)
    private String first_name;

    @Column(name="last_name", nullable=true)
    private String last_name;

    @Column(name="contact_no", nullable=true)
    private String contact_no;

    @Column(name="status", nullable=true)
    private String status;

    @Column(name="password", nullable=true)
    private String password;

    @Column(name= "permission_id")
    private int permission_id;


    public String toString(){
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ex){
            return super.toString();
        }
    }

}
