package com.shoppingapp.menu;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shoppingapp.function.GeneralFunctions;
import com.shoppingapp.function.LoginFunctions;
import com.shoppingapp.function.RegistrationFunctions;
import com.shoppingapp.model.Admin;
import com.shoppingapp.model.Catalog;
import com.shoppingapp.model.Customer;
public class MainMenu {
	
static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
//		Admin.GenerateInitialCatalog();
//		try {
//			GeneralFunctions.fileWriterCatalog(Catalog.items);
//		}
//		catch(IOException e) {
//			
//		}
		   GeneralFunctions.fileReaderCatalog(Catalog.items);
		   GeneralFunctions.fileReaderCustomer(Customer.getCustomers());

	
	   while(true) {
		   System.out.println("Welcome to KB shopping!");
		   System.out.println("1. Login");
		   System.out.println("2. Register");
		   System.out.println("3. Exit");
		   String option = sc.next();
		   
		   switch(option) {
			case "1":
				Login();
				break;
			case "2":
				Register();
				break;
			case "3":
				System.exit(0);
			default:
				break;
		   }
	   	}
	}
	
	public static void Login() {
		while(true) {
			System.out.println("Enter Email:");
			String email = sc.next();
			System.out.println("Enter Password:");
			String password = sc.next();
			Customer customer = LoginFunctions.verifyEmailandPassword(email, password);
			if(customer!=null) {
				CustomerMenu.customerMenu(customer, false);
			}
			else {
				if(GeneralFunctions.loop("Login Error. No such user exists. Would you like to login with a different email or password? yes or no", "yes", "no")) 
					continue;
				else break;
			}
			break;
		}	
	}
	
	public static void Register() {
		while(true) {
			boolean quit = false;
			
			String email = null;
			while(true) {
				System.out.println("Enter an email or type 'quit'");
				email = sc.next();
				if(email.equals("quit")) {
					quit = true;
					break;
				}
				else {
					String regex = "(?!.*\\.\\.)(^[^\\.][^@\\s]+@[^@\\s]+\\.[^@\\s\\.]+$)";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(email);
					if(matcher.matches()&&RegistrationFunctions.verifynewEmail(email)) {
						break;
					}
					else if(matcher.matches()&&!RegistrationFunctions.verifynewEmail(email)) {
						System.out.println("Email is already linked to another account");
						continue;
					}
					else if(!matcher.matches()) {
						System.out.println("Invalid email. Try again.");
						continue;
					}			
				}
			
			}
			if(quit == true) {
				break;
			}
			
			String password = null;
			while(true) {
				System.out.println("Enter a password that contains a minimum of eight characters, at least one letter, one number and one special character or type 'quit'");
				password = sc.next();
				if(password.equals("quit")) {
					quit = true;
					break;
				}
				String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(password);
				if(matcher.matches()) {
					System.out.println("Confirm password or type 'quit'");
					String verifyPassword = sc.next();
					if(verifyPassword.equals("quit")) {
						quit = true;
						break;
					}
					if(verifyPassword.equals(password)) {
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
			if(quit == true) {
				break;
			}

			
			String firstName = null;
			while(true) {
				System.out.println("Enter your first name or type 'quit'");
				firstName = sc.next();
				if(firstName.equals("quit")) {
					quit = true;
					break;
				}
				else {
					break;
				}
				
			}
			if(quit == true) {
				break;
			}
			
			String lastName = null;
			while(true) {
				System.out.println("Enter an last name or type 'quit'");
				lastName = sc.next();
				if(lastName.equals("quit")) {
					quit = true;
					break;
				}
				else {
					break;
				}
				
			}
			if(quit == true) {
				break;
			}

		
			Customer customer = new Customer(email, password, firstName, lastName);
			Customer.getCustomers().add(customer);
			try {
				GeneralFunctions.fileWriterCustomer(Customer.getCustomers());
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			CustomerMenu.customerMenu(customer, true);
			break;
			
		}
	}
}