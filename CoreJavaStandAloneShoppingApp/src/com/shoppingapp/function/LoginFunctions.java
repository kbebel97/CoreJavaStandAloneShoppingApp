package com.shoppingapp.function;

import com.shoppingapp.model.Customer;

public class LoginFunctions {
	public static Customer verifyEmailandPassword(String email, String password){
		Customer customer = null;
		for(int i = 0; i < Customer.getCustomers().size(); i++) {
			if(Customer.getCustomers().get(i).getEmail().equals(email)&&Customer.getCustomers().get(i).getPassword().equals(password)){
				customer = Customer.getCustomers().get(i);
				break;
			}	
		}
		return customer;
	}

}
