package com.shoppingapp.function;

import com.shoppingapp.model.Customer;

public class RegistrationFunctions {
	
	
	public static boolean verifynewEmail(String email) {
		for(int i = 0; i< Customer.getCustomers().size(); i++) {
			if(email.equals(Customer.getCustomers().get(i).getEmail())) {
				return false;
			}
		}
		return true;
		
	}
	


}
