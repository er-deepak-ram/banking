package com.dpkbank.banking.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@SequenceGenerator(name = "transeq", initialValue = 3330000, allocationSize = 2)
@Table(name = "transaction_details")
public class TransactionDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transeq")
	@Column(name = "transaction_id", unique = true, nullable = false)
	private Long transactionId;
	
	@Column(name = "from_account_no")
	private Long fromAccountNo;
	
	@Column(name = "to_account_no")
	private Long toAccountNo;
	
	@Min(value = 1, message = "Amount cannot be 0 or negative")
	@Column(name = "amount")
	private Long amount;
	
	@Column(name = "dot")
	private Timestamp dot;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_account_no", referencedColumnName = "account_no", nullable = false, insertable = false, updatable = false)
	private Account account;

	public TransactionDetails() {
		
	}

	public TransactionDetails(Long transactionId, Long fromAccountNo, Long toAccountNo,
			@Min(value = 1, message = "Amount cannot be 0 or negative") Long amount, Timestamp dot, String description,
			Account account) {
		this.transactionId = transactionId;
		this.fromAccountNo = fromAccountNo;
		this.toAccountNo = toAccountNo;
		this.amount = amount;
		this.dot = dot;
		this.description = description;
		this.account = account;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(Long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public Long getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(Long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Timestamp getDot() {
		return dot;
	}

	public void setDot(Timestamp dot) {
		this.dot = dot;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
