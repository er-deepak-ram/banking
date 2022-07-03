package com.dpkbank.banking.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpkbank.banking.entity.TransactionDetails;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long>{

	List<TransactionDetails> findByFromAccountNoOrToAccountNoAndDotBetween(Long fromAccountNo, Long toAccountNo, Date date1, Date date2);
}
