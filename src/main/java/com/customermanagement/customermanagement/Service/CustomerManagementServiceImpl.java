package com.customermanagement.customermanagement.Service;

import com.customermanagement.customermanagement.DTO.GetCustomerDTO;
import com.customermanagement.customermanagement.DTO.RequestDTO;
import com.customermanagement.customermanagement.DTO.ResponseDTO;
import com.customermanagement.customermanagement.Exception.MyException;
import com.customermanagement.customermanagement.Model.Customer;
import com.customermanagement.customermanagement.Repository.CustomerRepo;
import com.customermanagement.customermanagement.utils.CurrentDateAndTime;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {

    Logger logger = LoggerFactory.getLogger(CustomerManagementServiceImpl.class);

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CurrentDateAndTime currentDateAndTime;

    public CustomerManagementServiceImpl() {
    }

    public CustomerManagementServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public CustomerManagementServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomerManagementServiceImpl(CurrentDateAndTime currentDateAndTime) {
        this.currentDateAndTime = currentDateAndTime;
    }

    @Override
    public ResponseDTO createCustomer(RequestDTO requestDTO) {

        Customer customer = new Customer();
        customer.setName(requestDTO.getName());
        customer.setEmail(requestDTO.getEmail());
        customer.setPhone(requestDTO.getPhone());
        customer.setAddress(requestDTO.getAddress());
        customer.setPlanId(requestDTO.getPlanId());
        customer.setCreatedAt(CurrentDateAndTime.getCurrentDateTime());

        customerRepo.save(customer);
        logger.info("Customer Saved Successfully!");

        ResponseDTO responseDTO = modelMapper.map(customer,ResponseDTO.class);

        return responseDTO;
    }

    @Override
    public GetCustomerDTO getCustomerById(Integer id) {


            Customer customer = customerRepo.findById(id).orElseThrow(
                    ()->  new MyException("Customer with Id : " + id + " is not found")
            );
        GetCustomerDTO getCustomerDTO = modelMapper.map(customer, GetCustomerDTO.class);
        return getCustomerDTO;
    }

    @Override
    public GetCustomerDTO updateCustomerById(Integer id, RequestDTO requestDTO) {

        Customer customer = customerRepo.findById(id).get();

        customer.setName(requestDTO.getName());
        customer.setEmail(requestDTO.getEmail());
        customer.setPhone(requestDTO.getPhone());
        customer.setAddress(requestDTO.getAddress());
        customer.setPlanId(requestDTO.getPlanId());
        customer.setUpdatedAt(CurrentDateAndTime.getCurrentDateTime());

        customerRepo.save(customer);

        logger.info("Customer Updated Successfully!! ");

        GetCustomerDTO getCustomerDTO = modelMapper.map(customer, GetCustomerDTO.class);


        return getCustomerDTO;
    }

    @Override
    public List<GetCustomerDTO> getAllCustomers() {

       List<Customer> customers = customerRepo.findAll();

        List<GetCustomerDTO> getCustomerDTOSList = customers.stream()
                .map(customer -> modelMapper.map(customer, GetCustomerDTO.class))
                .toList();

       return getCustomerDTOSList;

    }

    @Override
    public List<GetCustomerDTO> getAllCustomersByPlanId(Integer id) {
        List<Customer> customers = customerRepo.findAllCustomersByPlanId(id);
        List<GetCustomerDTO> getCustomerDTOS = customers.stream().map(customer -> modelMapper.map(customer, GetCustomerDTO.class))
                .toList();

        return getCustomerDTOS;
    }


}
