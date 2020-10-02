package com.shoppingapp.function;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import com.shoppingapp.model.Catalog;
import com.shoppingapp.model.Customer;
import com.shoppingapp.model.Item;
import com.shoppingapp.model.Order;

public class GeneralFunctions {
	private static Scanner sc = new Scanner(System.in);
	
	
	public static void fileWriterCatalog(List<Item> catalog) throws IOException{
		Path locPath = FileSystems.getDefault().getPath("Catalog.dat");
		try(ObjectOutputStream outstream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
			for(Item item : catalog) {
				outstream.writeObject(item);
			}
		}
	}
	
	public static void fileReaderCatalog(List<Item> items) {
		Path locPath = FileSystems.getDefault().getPath("Catalog.dat");
		try(ObjectInputStream instream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))){
			boolean eof = false;
			while(!eof) {
				try {
					Item item = (Item) instream.readObject();
					Catalog.items.add(item);
				}
				catch(EOFException e) {
					eof = true;
				}
			}
		}
		catch(InvalidClassException e) {
			System.out.println("InvalidClassException " + e.getMessage() );
		}
		catch(IOException e) {
			System.out.println("IOException " + e.getMessage() );
		}
		catch(ClassNotFoundException e) {
			System.out.println("IOException " + e.getMessage() );
		}
	}
	
	public static void fileWriterCustomer(List<Customer> customers) throws IOException{
		Path locPath = FileSystems.getDefault().getPath("Customer.dat");
		try(ObjectOutputStream outstream = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
			for(Customer customer : customers) {
				outstream.writeObject(customer);
			}
		}
	}
	
	public static void fileReaderCustomer(List<Customer> customers) {
		Path locPath = FileSystems.getDefault().getPath("Customer.dat");
		try(ObjectInputStream instream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))){
			boolean eof = false;
			while(!eof) {
				try {
					Customer customer = (Customer) instream.readObject();
					customers.add(customer);
				}
				catch(EOFException e) {
					eof = true;
				}
			}
		}
		catch(InvalidClassException e) {
			System.out.println("InvalidClassException " + e.getMessage() );
		}
		catch(IOException e) {
			System.out.println("IOException " + e.getMessage() );
		}
		catch(ClassNotFoundException e) {
			System.out.println("IOException " + e.getMessage() );
		}
	}
	

	public static Integer confirmInt(String message) {
		Integer temp = null;
		
		while(true) {
			System.out.println(message);
			String quit  = sc.next();
			if(quit.equals("quit")) {
				temp = -1;
				break;
			}
			try {
				temp = Integer.parseInt(quit);
			}
			catch(NumberFormatException e) {
			}
			if(temp==null) {
				System.out.println("Enter a number!");
				continue;
			}
			else break;
		}
		return temp;
	}
	
	public static boolean loop(String message, String option1, String option2) {
		String response = null;
		while(true) {
			System.out.println(message);
			response = sc.next();
			if(response.equals(option1)||response.equals(option2)) 
				break;
		}
		if(response.equals(option1)) {
			return true;
		}
		else return false;
	}

}
