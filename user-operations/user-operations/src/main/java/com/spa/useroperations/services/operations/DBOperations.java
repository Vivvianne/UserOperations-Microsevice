package com.spa.useroperations.services.operations;


import com.spa.useroperations.services.commonservices.Utilities;

import com.spa.useroperations.models.db.Services;
import com.spa.useroperations.models.db.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.time.Instant;
import java.util.List;  

@Service
public class DBOperations {

    // Class Injections:

    @Autowired
    Utilities utilities;



    // JDBC injections:

    @Autowired
    private JdbcTemplate jdbc;



    public HashMap<String, String> fetchUserDetails(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "fetchUserDetails", request.toString());


        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, String> response = new HashMap<String, String>();


        try{



            String sql = "select * from spa.Users where email=?";

            Users user = (Users) jdbc.queryForObject(
                sql, new Object[]{request.get("email")}, new BeanPropertyRowMapper(Users.class));


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");



            String user_details_json = mapper.writeValueAsString(user);


            response.put("user_details", user_details_json);


        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "fetchUserDetails", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;


    
    }



    public HashMap<String, String> checkEmailisPresent(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "checkEmailisPresent", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();



        try{


            String sql = "SELECT count(*) FROM spa.Users WHERE email = ?";


            int count = jdbc.queryForObject( sql, new Object[] { request.get("email") }, Integer.class);


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");


            response.put("email_count", String.valueOf(count));


        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "checkEmailisPresent", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;

    }




    public HashMap<String, String> updateUserDetails(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "updateUserDetails", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();



        try{


            jdbc.update( "update spa.Users set first_name=? , last_name=? , contact_no = ? , status = ? , password = ? , permission_id = ? where email = ?", 
                new Object []{ request.get("first_name"), request.get("last_name"), request.get("contact_no"), request.get("status"),
                                request.get("password"), request.get("permission_id"), request.get("email")});


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");



        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "updateUserDetails", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }



    public HashMap<String, String> saveService(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "saveService", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();



        try{



            jdbc.update( "insert into spa.Services(name, description, status, price, created_on, updated_on) values (?, ?, ?, ?, current_timestamp, current_timestamp)",
                request.get("name"),
                request.get("description"),
                request.get("status"),
                request.get("price")
                );


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");



        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "saveService", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }




    public HashMap<String, String> removeService(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "removeService", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();



        try{


            jdbc.update( "delete from spa.Services where service_id=?", request.get("service_id"));


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");



        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "removeService", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;


    }



    public HashMap<String, String> checkServiceisPresent(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "checkServiceisPresent", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();



        try{


            String sql = "SELECT count(*) FROM spa.Services WHERE service_id = ?";


            int count = jdbc.queryForObject( sql, new Object[] { request.get("service_id") }, Integer.class);


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");


            response.put("service_count", String.valueOf(count));


        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "checkServiceisPresent", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;

    }




    public HashMap<String, String> fetchServiceDetails(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "fetchServiceDetails", request.toString());


        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, String> response = new HashMap<String, String>();


        try{



            String sql = "select * from spa.Services where service_id=?";

            Services service = (Services) jdbc.queryForObject(
                sql, new Object[]{request.get("service_id")}, new BeanPropertyRowMapper(Services.class));


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");



            String service_details_json = mapper.writeValueAsString(service);


            response.put("service_details", service_details_json);


        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "fetchServiceDetails", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;

    }


    public HashMap<String, String> fetchAllServices(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "fetchAllServices", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();

        ObjectMapper mapper = new ObjectMapper();



        try{


            String sql = "select * from spa.Services";

            List<Services> services = jdbc.query( sql, new BeanPropertyRowMapper(Services.class));


            // System.out.println("LIST SERVICES: " + );
    
            String service_details_json = mapper.writeValueAsString(services);


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");


            response.put("service_details", service_details_json);



        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "fetchAllServices", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;


    }


    public HashMap<String, String> updateServiceDetails(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), 
            "updateServiceDetails", request.toString());


        HashMap<String, String> response = new HashMap<String, String>();



        try{


            jdbc.update( "update spa.Services set name=? , description=? , status = ? , price = ? where service_id = ?", 
                new Object []{ request.get("name"), request.get("description"), request.get("status"), request.get("price"),
                request.get("service_id")});


            response.put("response_status", "success");
            response.put("response_description", "Successfully processed query");



        }catch(Exception e){

            error=e.toString();

            response.put("response_status", "failure");
            response.put("response_description", "Internal Exception");


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), 
                "updateServiceDetails", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }



}
