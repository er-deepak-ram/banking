package com.dpkbank.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpkbank.banking.entity.Account;
import com.dpkbank.banking.entity.Customer;
import com.dpkbank.banking.utils.NativeQueries;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByCustId(Customer customer);
	
	@Query(value = NativeQueries.EXTRACT_ACCOUNT_NO_FROM_MOBILE_NO, nativeQuery = true)
	Optional<Long> getAccountNumberOfCustomer(@Param("mobileNo") Long mobileNo);
}
