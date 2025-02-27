package com.customermanagement.customermanagement.Service;

import com.customermanagement.customermanagement.DTO.GetCustomerDTO;
import com.customermanagement.customermanagement.DTO.RequestDTO;
import com.customermanagement.customermanagement.DTO.ResponseDTO;
import com.customermanagement.customermanagement.Model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerManagementService {

    public ResponseDTO createCustomer(RequestDTO requestDTO);

    public GetCustomerDTO getCustomerById(Integer id);

    public GetCustomerDTO updateCustomerById(Integer id, RequestDTO requestDTO);

    public List<GetCustomerDTO> getAllCustomers();

    public List<GetCustomerDTO> getAllCustomersByPlanId(Integer id);

}
