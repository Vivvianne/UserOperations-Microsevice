package com.spa.useroperations.services.operations;


import com.spa.useroperations.services.commonservices.Utilities;

import com.spa.useroperations.models.apis.Response;

import com.spa.useroperations.services.MainService;

import com.spa.useroperations.models.db.Services;
import com.spa.useroperations.models.db.Users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.Instant;
import java.util.List;  


@Service
public class MainServiceImpl implements MainService {



    // Class Injections:

    @Autowired
    Utilities utilities;

    @Autowired
    DBOperations dbOperations;



    // Response Injections:

    @Value("${useroperations.responsecode.success}")
    private String responsecode_success="";

    @Value("${useroperations.responsemsg.success}")
    private String responsemsg_success="";

    @Value("${useroperations.detailedmsg.success}")
    private String detailedmsg_success="";

    @Value("${useroperations.responsemsg.failure}")
    private String responsemsg_failure="";


    @Value("${useroperations.responsecode.internal_exception}")
    private String responsecode_internal_exception="";

    @Value("${useroperations.detailedmsg.internal_exception}")
    private String detailedmsg_internal_exception="";


    @Value("${useroperations.responsecode.email_not_present}")
    private String responsecode_email_not_present="";

    @Value("${useroperations.detailedmsg.email_not_present}")
    private String detailedmsg_email_not_present="";


    @Value("${useroperations.responsecode.invalid_password}")
    private String responsecode_invalid_password="";

    @Value("${useroperations.detailedmsg.invalid_password}")
    private String detailedmsg_invalid_password="";

    @Value("${useroperations.responsecode.failed_to_update_profile}")
    private String responsecode_failed_to_update_profile="";

    @Value("${useroperations.detailedmsg.failed_to_update_profile}")
    private String detailedmsg_failed_to_update_profile="";

    @Value("${useroperations.responsecode.failed_to_create_service}")
    private String responsecode_failed_to_create_service="";

    @Value("${useroperations.detailedmsg.failed_to_create_service}")
    private String detailedmsg_failed_to_create_service="";

    @Value("${useroperations.responsecode.service_not_present}")
    private String responsecode_service_not_present="";

    @Value("${useroperations.detailedmsg.service_not_present}")
    private String detailedmsg_service_not_present="";

    @Value("${useroperations.responsecode.failed_removing_service}")
    private String responsecode_failed_removing_service="";

    @Value("${useroperations.detailedmsg.failed_removing_service}")
    private String detailedmsg_failed_removing_service="";


    @Value("${useroperations.responsecode.failed_to_update_service}")
    private String responsecode_failed_to_update_service="";

    @Value("${useroperations.detailedmsg.failed_to_update_service}")
    private String detailedmsg_failed_to_update_service="";



    
    public Response loginOperation(HashMap<String, String> request){



        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "loginOperation", request.toString());

        ObjectMapper mapper = new ObjectMapper();

        


        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Check if the user exists:

            HashMap<String, String> checkEmailisPresent_response = dbOperations.checkEmailisPresent(request);

            if(checkEmailisPresent_response.get("response_status").equals("success") && 
                    checkEmailisPresent_response.get("email_count").equals("1")){


                // Fetch user details:

                HashMap<String, String> fetchUserDetails_response = dbOperations.fetchUserDetails(request);


                if(fetchUserDetails_response.get("response_status").equals("success")){

                    
                    Users user = mapper.readValue(fetchUserDetails_response.get("user_details"), Users.class);


                    // Validate password:

                    if(request.get("password").equals(user.getPassword())){

                        // Valid password:

                        response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                        response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                        response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

                    }else{

                        // Invalid password:

                        response.getServiceResponse().getHeader().setResponseCode(responsecode_invalid_password);
                        response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                        response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_invalid_password);


                    }

                }else{


                    // Internal Exception:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


                }


            }else{

                // User Doesn't Exists:


                response.getServiceResponse().getHeader().setResponseCode(responsecode_email_not_present);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_email_not_present);


            }

            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "register", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;


    }
    






    public Response updateProfileOperation(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "updateProfileOperation", request.toString());

        ObjectMapper mapper = new ObjectMapper();

        


        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Check if the user exists:

            HashMap<String, String> checkEmailisPresent_response = dbOperations.checkEmailisPresent(request);

            if(checkEmailisPresent_response.get("response_status").equals("success") && 
                    checkEmailisPresent_response.get("email_count").equals("1")){


                // Fetch user details:

                HashMap<String, String> fetchUserDetails_response = dbOperations.fetchUserDetails(request);


                if(fetchUserDetails_response.get("response_status").equals("success")){

                    
                    Users user = mapper.readValue(fetchUserDetails_response.get("user_details"), Users.class);


                    // Override the database values:

                    request.put("first_name", 
                        (request.get("first_name") == null) ? user.getFirst_name(): request.get("first_name")) ;
                    
                    request.put("last_name", 
                        (request.get("last_name") == null) ? user.getLast_name(): request.get("last_name")) ;
                    
                    request.put("contact_no", 
                        (request.get("contact_no") == null) ? user.getContact_no(): request.get("contact_no")) ;
                    
                    request.put("status", 
                        (request.get("status") == null) ? user.getStatus(): request.get("status")) ;
                    
                    request.put("permission_id", 
                        (request.get("permission_id") == null) ? String.valueOf(user.getPermission_id()) : 
                        request.get("permission_id")) ;


                    request.put("password", 
                        (request.get("password") == null) ? user.getPassword() : request.get("password")) ;


                    // email is not changeable:

                     request.put("email", user.getEmail());
                    

                    // Update the database values:


                    HashMap<String, String> updateUserDetails_response = dbOperations.updateUserDetails(request);

                    if(updateUserDetails_response.get("response_status").equals("success")){

                        response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                        response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                        response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

                    }else{


                        response.getServiceResponse().getHeader().setResponseCode(responsecode_failed_to_update_profile);
                        response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                        response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_failed_to_update_profile);

                    }



                }else{


                    // Internal Exception:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


                }


            }else{

                // User Doesn't Exists:


                response.getServiceResponse().getHeader().setResponseCode(responsecode_email_not_present);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_email_not_present);


            }

            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "updateProfileOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;



    }



    public Response fetchProfileOperation(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "fetchProfileOperation", request.toString());

        ObjectMapper mapper = new ObjectMapper();

        


        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Check if the user exists:

            HashMap<String, String> checkEmailisPresent_response = dbOperations.checkEmailisPresent(request);

            if(checkEmailisPresent_response.get("response_status").equals("success") && 
                    checkEmailisPresent_response.get("email_count").equals("1")){


                // Fetch user details:

                HashMap<String, String> fetchUserDetails_response = dbOperations.fetchUserDetails(request);


                if(fetchUserDetails_response.get("response_status").equals("success")){

                    
                    Users user = mapper.readValue(fetchUserDetails_response.get("user_details"), Users.class);


                    // Extract the user details:


                    Response.ServiceResponse.Body.Item email_item = new Response.ServiceResponse.Body.Item();
                    email_item.setKey("email");
                    email_item.setValue(user.getEmail());

                    Response.ServiceResponse.Body.Item first_name_item = new Response.ServiceResponse.Body.Item();
                    first_name_item.setKey("first_name");
                    first_name_item.setValue(user.getFirst_name());

                    Response.ServiceResponse.Body.Item last_name_item = new Response.ServiceResponse.Body.Item();
                    last_name_item.setKey("last_name");
                    last_name_item.setValue(user.getLast_name());

                    Response.ServiceResponse.Body.Item contact_no_item = new Response.ServiceResponse.Body.Item();
                    contact_no_item.setKey("contact_no");
                    contact_no_item.setValue(user.getContact_no());

                    Response.ServiceResponse.Body.Item permission_id_item = new Response.ServiceResponse.Body.Item();
                    permission_id_item.setKey("permission_id");
                    permission_id_item.setValue(String.valueOf(user.getPermission_id()));


                    ArrayList <Response.ServiceResponse.Body.Item> data = new ArrayList <Response.ServiceResponse.Body.Item>();


                    data.add(email_item);
                    data.add(first_name_item);
                    data.add(last_name_item);
                    data.add(contact_no_item);
                    data.add(permission_id_item);

                        
                    response.getServiceResponse().getBody().setData(data);
    

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

                }else{


                    // Internal Exception:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


                }


            }else{

                // User Doesn't Exists:


                response.getServiceResponse().getHeader().setResponseCode(responsecode_email_not_present);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_email_not_present);


            }

            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "fetchProfileOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;


    }
    

    // Services:

    public Response createServiceOperation(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "createServiceOperation", request.toString());




        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Check if the user exists:

            HashMap<String, String> saveService_response = dbOperations.saveService(request);

            if(saveService_response.get("response_status").equals("success")){


                response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

                
            }else{

                // Failed to create Service

                response.getServiceResponse().getHeader().setResponseCode(responsecode_failed_to_create_service);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_failed_to_create_service);


            }

            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "createServiceOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;



    }

    public Response deleteServiceOperation(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "deleteServiceOperation", request.toString());




        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Validate whether the service exists

            HashMap<String, String> checkServiceisPresent_response = dbOperations.checkServiceisPresent(request);


            if( checkServiceisPresent_response.get("response_status").equals("success") && 
                    checkServiceisPresent_response.get("service_count").equals("1")){


  

                // Remove service from database:

                HashMap<String, String> removeUser_response = dbOperations.removeService(request);


                if(removeUser_response.get("response_status").equals("success")){

                    // Success:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);


                }else{

                    // Failure to save into database:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_failed_removing_service);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_failed_removing_service);

                }



            }else{

                // Service not present in db

                response.getServiceResponse().getHeader().setResponseCode(responsecode_service_not_present);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_service_not_present);


            }
            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "deleteServiceOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;



    }
    



    public Response editServiceOperation(HashMap<String, String> request){


        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "editServiceOperation", request.toString());

        ObjectMapper mapper = new ObjectMapper();

        
        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Check if the service exists:

            HashMap<String, String> checkServiceisPresent_response = dbOperations.checkServiceisPresent(request);

            if(checkServiceisPresent_response.get("response_status").equals("success") && 
                    checkServiceisPresent_response.get("service_count").equals("1")){


                // Fetch service details:

                HashMap<String, String> fetchServiceDetails_response = dbOperations.fetchServiceDetails(request);


                if(fetchServiceDetails_response.get("response_status").equals("success")){

                    
                    Services service = mapper.readValue(fetchServiceDetails_response.get("service_details"), Services.class);


                    // Override the database values:

                    request.put("service_id", 
                        (request.get("service_id") == null) ? service.getService_id(): request.get("service_id")) ;
                    
                    request.put("name", 
                        (request.get("name") == null) ? service.getName(): request.get("name")) ;
                    
                    request.put("description", 
                        (request.get("description") == null) ? service.getDescription(): request.get("description")) ;
                    
                    request.put("status", 
                        (request.get("status") == null) ? service.getStatus(): request.get("status")) ;
                    
                    request.put("price", 
                        (request.get("price") == null) ? service.getPrice() : request.get("price")) ;


                    // service_id is not changeable:

                     request.put("service_id", service.getService_id());
                    

                    // Update the database values:


                    HashMap<String, String> updateServiceDetails_response = dbOperations.updateServiceDetails(request);

                    if(updateServiceDetails_response.get("response_status").equals("success")){

                        response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                        response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                        response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

                    }else{


                        response.getServiceResponse().getHeader().setResponseCode(responsecode_failed_to_update_service);
                        response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                        response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_failed_to_update_service);

                    }


                }else{


                    // Internal Exception:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


                }


            }else{

                // Service Doesn't Exists:

                response.getServiceResponse().getHeader().setResponseCode(responsecode_service_not_present);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_service_not_present);


            }

            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "editServiceOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;


    }




    public Response  fetchServiceOperation(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "fetchServiceOperation", request.toString());

        ObjectMapper mapper = new ObjectMapper();

        
        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Check if the service exists:

            HashMap<String, String> checkServiceisPresent_response = dbOperations.checkServiceisPresent(request);

            if(checkServiceisPresent_response.get("response_status").equals("success") && 
                    checkServiceisPresent_response.get("service_count").equals("1")){


                // Fetch service details:

                HashMap<String, String> fetchServiceDetails_response = dbOperations.fetchServiceDetails(request);


                if(fetchServiceDetails_response.get("response_status").equals("success")){

                    
                    Services service = mapper.readValue(fetchServiceDetails_response.get("service_details"), Services.class);


                    // Extract the service details:

                    Response.ServiceResponse.Body.Item service_item = new Response.ServiceResponse.Body.Item();
                    service_item.setKey("service_id");
                    service_item.setValue(service.getService_id());

                    Response.ServiceResponse.Body.Item name_item = new Response.ServiceResponse.Body.Item();
                    name_item.setKey("name");
                    name_item.setValue(service.getName());

                    Response.ServiceResponse.Body.Item description_item = new Response.ServiceResponse.Body.Item();
                    description_item.setKey("description");
                    description_item.setValue(service.getDescription());

                    Response.ServiceResponse.Body.Item status_item = new Response.ServiceResponse.Body.Item();
                    status_item.setKey("status");
                    status_item.setValue(service.getStatus());

                    Response.ServiceResponse.Body.Item price_item = new Response.ServiceResponse.Body.Item();
                    price_item.setKey("price");
                    price_item.setValue(String.valueOf(service.getPrice()));
                    

                    ArrayList <Response.ServiceResponse.Body.Item> data = new ArrayList <Response.ServiceResponse.Body.Item>();


                    data.add(service_item);
                    data.add(name_item);
                    data.add(description_item);
                    data.add(status_item);
                    data.add(price_item);

                        
                    response.getServiceResponse().getBody().setData(data);
    

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

                }else{


                    // Internal Exception:

                    response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
                    response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                    response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


                }


            }else{

                // Service Doesn't Exists:


                response.getServiceResponse().getHeader().setResponseCode(responsecode_service_not_present);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_service_not_present);

            }

            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "fetchServiceOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;


    }



    public Response  fetchAllServicesOperation(HashMap<String, String> request){

        // Start Timer:

        Instant startTime = Instant.now();

        Response response = new Response();

        String error = "";
    
        utilities.logStart(request.get("request_ref_id"), "fetchAllServicesOperation", request.toString());

        ObjectMapper mapper = new ObjectMapper();

        
        response.setServiceResponse(new Response.ServiceResponse());
        response.getServiceResponse().setHeader(new Response.ServiceResponse.Header());
        response.getServiceResponse().setBody(new Response.ServiceResponse.Body());
        


        try{


            // Fetch service details:

            HashMap<String, String> fetchAllServices_response = dbOperations.fetchAllServices(request);


            if(fetchAllServices_response.get("response_status").equals("success")){


                // List<Services> services_list = mapper.readValue(fetchAllServices_response.get("service_details"), List.class);

                List<Services> services_list = mapper.readValue(fetchAllServices_response.get("service_details"), 
                    new TypeReference<List<Services>>() {});



                // Extract the service details:

                ArrayList <Response.ServiceResponse.Body.Item> data = new ArrayList <Response.ServiceResponse.Body.Item>();

                for ( Services service: services_list){

                    Response.ServiceResponse.Body.Item service_item = new Response.ServiceResponse.Body.Item();
                    service_item.setKey("service_id");
                    service_item.setValue(service.getService_id());

                    Response.ServiceResponse.Body.Item name_item = new Response.ServiceResponse.Body.Item();
                    name_item.setKey("name");
                    name_item.setValue(service.getName());

                    Response.ServiceResponse.Body.Item description_item = new Response.ServiceResponse.Body.Item();
                    description_item.setKey("description");
                    description_item.setValue(service.getDescription());

                    Response.ServiceResponse.Body.Item status_item = new Response.ServiceResponse.Body.Item();
                    status_item.setKey("status");
                    status_item.setValue(service.getStatus());

                    Response.ServiceResponse.Body.Item price_item = new Response.ServiceResponse.Body.Item();
                    price_item.setKey("price");
                    price_item.setValue(String.valueOf(service.getPrice()));
                    

                    data.add(service_item);
                    data.add(name_item);
                    data.add(description_item);
                    data.add(status_item);
                    data.add(price_item);

                }

                    
                response.getServiceResponse().getBody().setData(data);


                response.getServiceResponse().getHeader().setResponseCode(responsecode_success);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_success);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_success);

            }else{


                // Internal Exception:

                response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
                response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
                response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


            }



            

        }catch(Exception e){



            error=e.toString();

            response.getServiceResponse().getHeader().setResponseCode(responsecode_internal_exception);
            response.getServiceResponse().getHeader().setResponseMsg(responsemsg_failure);
            response.getServiceResponse().getHeader().setDetailedMsg(detailedmsg_internal_exception);


        }finally{

            Instant endTime = Instant.now();

            utilities.logEnd(request.get("request_ref_id"), "fetchAllServicesOperation", startTime, 
                endTime, request.toString(), response.toString(), error);

        }


        return response;


    }


}
