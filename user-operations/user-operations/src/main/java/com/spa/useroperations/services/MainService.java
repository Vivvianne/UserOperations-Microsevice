package com.spa.useroperations.services;


import com.spa.useroperations.models.apis.Response;


import java.util.HashMap;


public interface MainService {

    // Users:
    
    public Response loginOperation(HashMap<String, String> request);
    public Response updateProfileOperation(HashMap<String, String> request);
    public Response fetchProfileOperation(HashMap<String, String> request);
    

    // Services:

    public Response createServiceOperation(HashMap<String, String> request);
    public Response deleteServiceOperation(HashMap<String, String> request);
    public Response editServiceOperation(HashMap<String, String> request);
    public Response fetchServiceOperation(HashMap<String, String> request);
    public Response fetchAllServicesOperation(HashMap<String, String> request);

}
