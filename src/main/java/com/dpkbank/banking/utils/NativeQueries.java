package com.dpkbank.banking.utils;

public interface NativeQueries {

	String EXTRACT_ACCOUNT_NO_FROM_MOBILE_NO = "SELECT \n"
											+ "    a.account_no\n"
											+ "FROM\n"
											+ "    account a\n"
											+ "        INNER JOIN\n"
											+ "    customer c ON a.cust_id = c.customer_id\n"
											+ "WHERE\n"
											+ "    c.mobile_no = :mobileNo";
}