package com.spa.useroperations.controllers;


import com.spa.useroperations.models.apis.Response;
import com.spa.useroperations.models.apis.Request;

import com.spa.useroperations.services.commonservices.Utilities;
import com.spa.useroperations.services.MainService;



import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.time.Instant;




@RestController
@RequestMapping("/api/spa/useroperations")
public class MainController {


    // Class injections:

    @Autowired
    MainService mainService;

    @Autowired
    Utilities utilities;


    
    // Response injections:

    @Value("${useroperations.responsecode.internal_exception}")
    private String responsecode_internal_exception="";

    @Value("${useroperations.responsemsg.failure}")
    private String responsemsg_failure="";

    @Value("${useroperations.detailedmsg.internal_exception}")
    private String detailedmsg_internal_exception="";




    // Users Methods:

    @PostMapping("/login")
    public Response register(@Valid @RequestBody Request request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "login", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{

            
            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            
            mainservice_request.put("email", request.getServiceRequest().getBody().getData().get(0).getValue().toString());
            mainservice_request.put("password", request.getServiceRequest().getBody().getData().get(1).getValue().toString());

            response = mainService.loginOperation(mainservice_request);




        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "login", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }




    @PostMapping("/update/profile")
    public Response updateProfile(@Valid @RequestBody Request request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "updateProfile", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{

            
            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            

            // Dynamically handle the response body:

            for( Request.ServiceRequest.Body.Item x: request.getServiceRequest().getBody().getData()){

                mainservice_request.put(x.getKey(), x.getValue());

            }

            response = mainService.updateProfileOperation(mainservice_request);




        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "updateProfile", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }


    @PostMapping("/fetch/profile")
    public Response fetchProfile(@Valid @RequestBody Request request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "fetchProfile", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{

            
            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            
            mainservice_request.put("email", request.getServiceRequest().getBody().getData().get(0).getValue().toString());
          
            response = mainService.fetchProfileOperation(mainservice_request);




        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "fetchProfile", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;


    }




    // Services Methods:


    @PostMapping("/service/create")
    public Response createService(@Valid @RequestBody Request request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "createService", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{



            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            
            mainservice_request.put("name", request.getServiceRequest().getBody().getData().get(0).getValue().toString());
            mainservice_request.put("description", request.getServiceRequest().getBody().getData().get(1).getValue().toString());
            mainservice_request.put("status", request.getServiceRequest().getBody().getData().get(2).getValue().toString());
            mainservice_request.put("price", request.getServiceRequest().getBody().getData().get(3).getValue().toString());

          
            response = mainService.createServiceOperation(mainservice_request);




        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "createService", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }


    @PostMapping("/service/delete")
    public Response deleteService(@Valid @RequestBody Request request){


        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "deleteService", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{



            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            
            mainservice_request.put("service_id", request.getServiceRequest().getBody().getData().get(0).getValue().toString());
          
            response = mainService.deleteServiceOperation(mainservice_request);




        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "deleteService", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;


    }


    @PostMapping("/service/fetch")
    public Response fetchService(@Valid @RequestBody Request request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "fetchService", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{

            
            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            
            mainservice_request.put("service_id", request.getServiceRequest().getBody().getData().get(0).getValue().toString());
          
            response = mainService.fetchServiceOperation(mainservice_request);


        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "fetchService", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;


    }


    @PostMapping("/service/update")
    public Response updateService(@Valid @RequestBody Request request){

         // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "updateService", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{

            
            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
            

            // Dynamically handle the response body:

            for( Request.ServiceRequest.Body.Item x: request.getServiceRequest().getBody().getData()){

                mainservice_request.put(x.getKey(), x.getValue());

            }

            response = mainService.editServiceOperation(mainservice_request);



        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "updateService", startTime, endTime, request.toString(), response.toString(), error);

        }


        return response;



    }



    @PostMapping("/service/fetchall")
    public Response fetchAllServices(@Valid @RequestBody Request request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
            "fetchAllServices", request.toString());

        HashMap<String, String> mainservice_request = new HashMap<>();
        

        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{

            
            mainservice_request.put("request_ref_id", request.getServiceRequest().getHeader().getRequestRefID());
                 
            response = mainService.fetchAllServicesOperation(mainservice_request);


        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            response.getServiceResponse().getHeader().setResponseRefID(request.getServiceRequest().getHeader().getRequestRefID());

            utilities.logEnd(request.getServiceRequest().getHeader().getRequestRefID().toString(), 
                "fetchAllServices", startTime, endTime, request.toString(), response.toString(), error);

        }

        return response;


    }




}
