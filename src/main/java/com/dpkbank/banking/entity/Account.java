package com.dpkbank.banking.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
@SequenceGenerator(name = "seq", initialValue = 1230000, allocationSize = 1)
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "account_no")
	private Long accountNo;
	
	@Column(name = "balance_amt")
	private Long balanceAmt;
	
	@NotNull(message = "Account opening date is required")
	@Column(name = "acc_open_dt")
	private Date accOpenDt;
	
	@Column(name = "acc_type")
	private String accType;
	
	@Column(name = "acc_status")
	private Boolean accStatus;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "custId")
	private Customer custId;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<TransactionDetails> transactions;

	public Account() {
		
	}

	public Account(Long balanceAmt, Date accOpenDt, String accType, Boolean accStatus) {
		this.balanceAmt = balanceAmt;
		this.accOpenDt = accOpenDt;
		this.accType = accType;
		this.accStatus = accStatus;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Long getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(Long balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public Date getAccOpenDt() {
		return accOpenDt;
	}

	public void setAccOpenDt(Date accOpenDt) {
		this.accOpenDt = accOpenDt;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Boolean getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Boolean accStatus) {
		this.accStatus = accStatus;
	}

	public Customer getCustId() {
		return custId;
	}

	public void setCustId(Customer custId) {
		this.custId = custId;
	}

	public List<TransactionDetails> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDetails> transactions) {
		this.transactions = transactions;
	}
}
