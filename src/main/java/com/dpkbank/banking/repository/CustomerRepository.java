package com.dpkbank.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpkbank.banking.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	List<Customer> findByCustomerIdAndPassword(Integer customerId, String password);
	
	@Query(value = "select count(*) from account where account_no =:accountNo and (balance_amt - :amount) >= 0", nativeQuery = true)
	int transactionAllowed(@Param("accountNo") Long accountNo, @Param("amount") Long amount);
	
	List<Customer> findByAadharNo(Long aadharNo);
//	@Query("select new com.yesbank.banking.dto.CustomerLoginDto(firstName, lastName, customerAccount, count(*)) from Customer where customerId=:customerId and password=:password")
//	CustomerLoginDto customerLogin(@Param("customerId") Integer customerId, @Param("password") String password);
	
	Optional<Customer> findByMobileNo(Long mobileNo);
}
