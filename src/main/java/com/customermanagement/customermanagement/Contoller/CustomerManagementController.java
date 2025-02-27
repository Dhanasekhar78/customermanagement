package com.customermanagement.customermanagement.Contoller;

import com.customermanagement.customermanagement.DTO.GetCustomerDTO;
import com.customermanagement.customermanagement.DTO.RequestDTO;
import com.customermanagement.customermanagement.DTO.ResponseDTO;
import com.customermanagement.customermanagement.Model.Customer;
import com.customermanagement.customermanagement.Service.CustomerManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerManagementController {

    @Autowired
    CustomerManagementService customerManagementService;

    public CustomerManagementController(CustomerManagementService customerManagementService) {
        this.customerManagementService = customerManagementService;
    }

    @PostMapping("/customer")
    @Operation(summary = "Persist customer")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public ResponseEntity<ResponseDTO> createCustomer(@RequestBody RequestDTO requestDTO){
        return new ResponseEntity<>(customerManagementService.createCustomer(requestDTO), HttpStatus.CREATED);

    }

    @GetMapping("/customers/{id}")
    @Operation(summary = "Get customer by ID", description = "Returns a single customer based on their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved customer"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<GetCustomerDTO> getCustomerById(@PathVariable Integer id){

        return new ResponseEntity<>(customerManagementService.getCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    @Operation(summary = "Update customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<GetCustomerDTO> updateCustomerById(@PathVariable Integer id, @RequestBody RequestDTO requestDTO){

        return new ResponseEntity<>(customerManagementService.updateCustomerById(id, requestDTO), HttpStatus.OK);

    }

    @GetMapping("/customers/all")
    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<List<GetCustomerDTO>> getAllCustomers(){

        return new ResponseEntity<>(customerManagementService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/planId/{planId}")
    @Operation(summary = "Get Customers by PlanId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<List<GetCustomerDTO>> getAllCustomersByPlanId(@PathVariable Integer planId){
        return  new ResponseEntity<>(customerManagementService.getAllCustomersByPlanId(planId), HttpStatus.OK);
    }





}
