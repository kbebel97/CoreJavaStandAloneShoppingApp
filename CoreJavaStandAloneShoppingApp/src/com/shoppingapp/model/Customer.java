package com.shoppingapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Customer implements Serializable{
    private static final long serialVersionUID = 5L;     

	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private List<Item> cart; 
	private List<Order> purchases;
	
	static Long customerIdincrement = 0L;
	private Long customerId = 0L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	public Customer(String email,  String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerId = customerIdincrement++;
		this.cart = new ArrayList<Item>();
		this.purchases = new ArrayList<Order>();
	}
	
	public void addtocart(Item item) {
		this.cart.add(0, item);
		System.out.println("Item " + item.getItemName() + " has been added to your cart");
	}
	
	public void deletefromcart(Item item) {
		if(this.cart.remove(item)) {
			System.out.println("item " + item.getItemName() + " has been removed from your cart");
		}
		else System.out.println("Item " + item.getItemName() + " was not found");
	}
	

	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Item> cart) {
		this.cart = cart;
	}

	public List<Order> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Order> purchases) {
		this.purchases = purchases;
	}

	public static Long getCustomerIdincrement() {
		return customerIdincrement;
	}

	public static void setCustomerIdincrement(Long customerIdincrement) {
		Customer.customerIdincrement = customerIdincrement;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
	

	
	
	

}
