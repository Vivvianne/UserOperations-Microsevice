package com.spa.useroperations.models.db;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "spa.Services")
public class Services {

    
    @Column(name="service_id", nullable=true)
    private String service_id;
    
    @Column(name="name", nullable=true)
    private String name;
    
    @Column(name="description", nullable=true)
    private String description;

    @Column(name="status", nullable=true)
    private String status;

    @Column(name="price", nullable=true)
    private String price;

    public String toString(){
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ex){
            return super.toString();
        }
    }


}

