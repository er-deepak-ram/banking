package com.dpkbank.banking.utils;

public class CommonUtils {

	public static boolean isNull(Object str) {
		if(str == null || str.toString().equals("") || str.toString().equals(" ") || str.toString().equals("-")
				|| str.toString().trim().length() == 0 || str.toString().trim().equals("null")) {
			return true;
		}
		return false;
	}
}
