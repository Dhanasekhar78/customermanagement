package com.customermanagement.customermanagement.Repository;

import com.customermanagement.customermanagement.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

   @Query(

           value = "select * from customer c where c.plan_id = :planId", nativeQuery = true

   )
   List<Customer> findAllCustomersByPlanId(Integer planId);


}
