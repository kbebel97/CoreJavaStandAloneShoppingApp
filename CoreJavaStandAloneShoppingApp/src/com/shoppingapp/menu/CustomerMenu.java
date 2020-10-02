package com.shoppingapp.menu;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shoppingapp.function.CustomerFunctions;
import com.shoppingapp.function.GeneralFunctions;
import com.shoppingapp.model.Catalog;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;
import com.shoppingapp.model.Order;

public class CustomerMenu {
	private static Scanner sc = new Scanner(System.in);
	public static void customerMenu(Customer customer, boolean firstlogin) {
		while(true) {
			if(firstlogin == true) {
				System.out.println("Welcome " + customer.getFirstName() + "!");
			}
			else {
				System.out.println("Welcome back " + customer.getFirstName() + "!");
			}
			System.out.println("1. View catalog");
			System.out.println("2. View cart");
//			System.out.println("3. Make a return");
			System.out.println("3. View purchase history");
			System.out.println("4. Change your account information");
			System.out.println("6. Exit");
			String option = sc.next();
			
			   switch(option) {
				case "1":
					ViewCatalog(customer);
					break;
				case "2":
					viewCart(customer);
					break;
//				case "3":
//					returnItem(customer);
//					break;
				case "3":
					viewPurchaseHistory(customer);
					break;
				case "4":
					changeAccountInfo(customer);
					break;
			   }
			   if(option.equals("6"))
				   break;
		}
		
	}
	
	public static void ViewCatalog(Customer customer) {
		while(true) {
			for(int i = 0; i < Catalog.items.size(); i++) {
				Item item = Catalog.items.get(i);
				int j = i + 1;
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println(j + " || " + item.getItemName() + " || " + item.getDescription() + " || " + item.getPrice());
				System.out.println("---------------------------------------------------------------------------------------------");																												}
						while(true) {
							int choice = GeneralFunctions.confirmInt("Type item # to add to cart or type 'quit'");
							if(choice == -1) {
								break;
							}
							else if(choice > Catalog.items.size() || choice < -1) {
								System.out.println("Not a valid item!");
								continue;
							}
							else {
								customer.addtocart(Catalog.items.get(choice - 1));
								try {
									GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
								}
								catch(IOException e) {
									e.printStackTrace();
								}
							}
						}
					break;
				}
			}
	
	public static void viewCart(Customer customer) {
		while(true) {
			if(customer.getCart().isEmpty()) {
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println("Cart is empty");
				System.out.println("---------------------------------------------------------------------------------------------");
			}
			else {
				for(int i = 0; i < customer.getCart().size(); i++) {
					Item item = customer.getCart().get(i);
					int j = i + 1;
					System.out.println("---------------------------------------------------------------------------------------------");
					System.out.println("ItemID: " + j + " || " + "Item Name: " + item.getItemName() + " || " + "Item Description: " + item.getDescription() + " || " + "Item Price: " + item.getPrice());
					System.out.println("---------------------------------------------------------------------------------------------");}
			}
				System.out.println("1. Checkout");
				System.out.println("2. Delete");
				System.out.println("3. Exit");
				String option = sc.next();
				switch(option) {
					case "1":
						CustomerFunctions.checkout(customer);
						break;
					case "2":
						CustomerFunctions.deletefromcart(customer);
						break;
				}
				if(option.equals("3"))
					break;
			}
		}
	
//	public static void returnItem(Customer customer) {
//		while(true) {
//			if(customer.getPurchases().isEmpty()) {
//				System.out.println("---------------------------------------------------------------------------------------------");
//				System.out.println("No purchases have been made yet");
//				System.out.println("---------------------------------------------------------------------------------------------");
//			}
//			else {
//				for(int i = 0; i < customer.getPurchases().size() ; i++) {
//					Order order = customer.getPurchases().get(i);
//					System.out.println("---------------------------------------------------------------------------------------------");
//					System.out.println("OrderID: " + order.getOrderId() + " || " + "Date: " + order.getPurchaseDate() + " || " + "Item Name: " + order.getItem().getItemName());
//					System.out.println("---------------------------------------------------------------------------------------------");
//				}
//			}
//			System.out.println("Return Item by name or orderId or type 'quit'");
//			String product = sc.next();
//			if(product.equals("quit"))
//				break;
//			if(CustomerFunctions.verifyProduct(product, customer))
//				break;
//			else continue;
//		}
//	}
	
	public static void viewPurchaseHistory(Customer customer) {
		while(true) {
			if(customer.getPurchases().isEmpty()) {
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println("No purchases have been made yet");
				System.out.println("---------------------------------------------------------------------------------------------");
			}
			else {
				for(int i = 0; i < customer.getPurchases().size() ; i++) {
					Order order = customer.getPurchases().get(i);
					System.out.println("---------------------------------------------------------------------------------------------");
					System.out.println("OrderID: " + order.getOrderId() + "||" + " Date: " + order.getPurchaseDate() + "||" + " Item " + order.getItem().getItemName());
					System.out.println("---------------------------------------------------------------------------------------------");
				}		
			}
//			if(GeneralFunctions.loop("Exit? type 'yes'", "yes", "no"))
//				break;
//			else continue;
//			boolean quit = false;
//			while(true) {
//				if(customer.getPurchases().isEmpty()) {
//					System.out.println("---------------------------------------------------------------------------------------------");
//					System.out.println("No purchases have been made yet");
//					System.out.println("---------------------------------------------------------------------------------------------");
//				}
//				else {
//					for(int i = 0; i < customer.getPurchases().size() ; i++) {
//						Order order = customer.getPurchases().get(i);
//						System.out.println("---------------------------------------------------------------------------------------------");
//						System.out.println("OrderID: " + order.getOrderId() + " || " + "Date: " + order.getPurchaseDate() + " || " + "Item Name: " + order.getItem().getItemName());
//						System.out.println("---------------------------------------------------------------------------------------------");
//					}
//				}
				System.out.println("Return Item by name or orderId or type 'quit'");
				String product = sc.next();
				if(product.equals("quit")) {
//					quit = true;
					break;
				}
				if(CustomerFunctions.verifyProduct(product, customer))
					continue;
//			}
//			if(quit==true) {
//				break;
//
//			}
		}
	}
	
	public static void changeAccountInfo(Customer customer) {
		while(true) {
			StringBuffer outputBuffer = new StringBuffer(customer.getPassword().length());
			for(int i = 0; i < customer.getPassword().length(); i++) {
				if(i < 2 || i > customer.getPassword().length() - 2)
					outputBuffer.append("*");

				else 
					outputBuffer.append(customer.getPassword().charAt(i));
			}
			String password = outputBuffer.toString();
			System.out.println("1 || " + "CustomerId: " + customer.getCustomerId());
			System.out.println("2 || " + "Email: " + customer.getEmail());
			System.out.println("3 || " + "Password: " + password);
			System.out.println("4 || " + "FirstName: " + customer.getFirstName());
			System.out.println("5 || " + "LastName: " + customer.getLastName());
	
			System.out.println("Enter the # of the field you would like to edit or type 'quit'");
			String option = sc.next();
			if(option.equals("1")||option.equals("2")) {
				System.out.println("Sorry, you cannot edit your email or ID #");
				continue;
			}
			if(option.equals("4")) {
				while(true) {
					System.out.println("Enter first name or type 'quit'");
					String firstName = sc.next();
					if(firstName.equals("quit"))
						break;
					else {
						customer.setFirstName(firstName);
						try {
							GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
						}
						catch(IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				
			}
			if(option.equals("5")) {
				while(true) {
					System.out.println("Enter name or type 'quit'");
					String lastName = sc.next();
					if(lastName.equals("quit"))
						break;
					else {
						customer.setLastName(lastName);
						try {
							GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
						}
						catch(IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				
			}
			if(option.equals("3")) {
				while(true) {
					System.out.println("Enter your current password or type 'quit'");
					String currentpassword = sc.next();
					if(currentpassword.equals("quit")) {
						break;
					}
					if(currentpassword.equals(customer.getPassword())) {
						while(true) {
							System.out.println("Enter a new password that contains a minimum of eight characters, at least one letter, one number and one special character or type 'quit'");
							String p = sc.next();
							if(p.equals("quit")) {
								break;
							}
							String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(p);
							if(matcher.matches()) {
								System.out.println("Confirm password or type 'quit'");
								String verifyPassword = sc.next();
								if(verifyPassword.equals("quit")) {
									break;
								}
								if(verifyPassword.equals(p)) {
									customer.setPassword(p);
									try {
										GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
									}
									catch(IOException e) {
										e.printStackTrace();
									}
									System.out.println("Password has been changed");
									break;
								}
								else {
									System.out.println("Second password does not match the first!");
									continue;
								}
							}
							if(!matcher.matches()) {
								System.out.println("Invalid password. Password must contain a minimum of eight characters, at least one letter, one number and one special character");
								continue;
							}	
						}
						
					}
					else {
						System.out.println("Entered wrong password");
						continue;
					}
					break;
					
				}
				

			}
				if(option.equals("quit")) {
					break;
				}
			
			
			
		}
		
	}
}
