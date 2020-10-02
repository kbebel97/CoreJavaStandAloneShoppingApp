package com.shoppingapp.function;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Order;

public class CustomerFunctions {
	private static Scanner sc = new Scanner(System.in);
	
	public static boolean verifyProduct(String product, Customer customer) {
		Long Id = null;
		try{
			Id = Long.valueOf(product);
			for(int k = 0; k < customer.getPurchases().size();k++) {
				if(customer.getPurchases().get(k).getOrderId()==Id) {
					Duration duration = Duration.between(customer.getPurchases().get(k).getPurchaseDate(), LocalDateTime.now());
					if(duration.toDays()>15) {
						System.out.println("Sorry, " + customer.getPurchases().get(k).getItem().getItemName() + " cannot be returned");
						System.out.println("Items can only be returned within 15 days of purchase");
						return false;
					}
					else {
						System.out.println("Item has successfully been returned");
						customer.getPurchases().remove(customer.getPurchases().get(k));
						try {
							GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
						}
						catch(IOException e) {
							e.printStackTrace();
						}
						return true;
					}
				}
			}
		}
		catch(NumberFormatException e) {
			
		}
			for(int i = 0; i < customer.getPurchases().size(); i++) {
				if(customer.getPurchases().get(i).getItem().getItemName().equals(product)) {
					Duration duration = Duration.between(customer.getPurchases().get(i).getPurchaseDate(), LocalDateTime.now());
					if(duration.toDays()>15) {
						System.out.println("Sorry, " + customer.getPurchases().get(i).getItem().getItemName() + " cannot be returned");
						System.out.println("Items can only be returned within 15 days of purchase");
						return false;
					}
					else {
						System.out.println("Item has successfully been returned");
						customer.getPurchases().remove(customer.getPurchases().get(i));
						try {
							GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
						}
						catch(IOException e) {
							e.printStackTrace();
						}
						return true;
					}
				}
				
			}
		
		System.out.println("Item was not found");
		return false;
	}
	
	
	public static void deletefromcart(Customer customer) {
			int choice = -2;
			while(choice > customer.getCart().size() || choice < -1) {
				choice = GeneralFunctions.confirmInt("Enter item or type 'quit'");
			}
			if(choice == -1) {
				return;
			}
			else {
				customer.deletefromcart(customer.getCart().get(choice-1));
				try {
					GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}	
	}
	
	
	public static void checkout(Customer customer) {
		double total = 0.0;
		for(int i = 0; i < customer.getCart().size(); i++) {
			total += customer.getCart().get(i).getPrice();
		}
		while(true) {
			System.out.println("Total is " + total);
			verifyPayment(customer, total);
			break;
		}
	}
	
	
	public static void verifyPayment(Customer customer, double total) {
		String answer = null;
		while(true) {
			System.out.println("Enter payment method: debit || credit ");
			answer = sc.next();
			if(answer.equals("debit")||answer.equals("credit")) {
				while(true) {
					if(GeneralFunctions.loop("Your total is $" + total + " ; Enter 'confirm' or 'cancel'","confirm", "cancel")) {
						for(int i = 0; i < customer.getCart().size(); i++) {
							customer.getPurchases().add(0, new Order
									(LocalDateTime.now(), customer.getCart().get(i)));
						}
						customer.getCart().clear();
						try {
							GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
						}
						catch(IOException e) {
							e.printStackTrace();
						}
						System.out.println("Order has been placed");
						break;
					}
					else break;
				}
			}
			else {
				System.out.println("only debit or credit is accepted");
				continue;
			}
			break;
		}
	}
	
	
}
