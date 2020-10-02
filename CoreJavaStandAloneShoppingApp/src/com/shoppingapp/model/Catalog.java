package com.shoppingapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Catalog {
	
	public static List<Item> items = new ArrayList<Item>();
	
	public static void addItems(List<Item> list) {
		for(int i = 0; i < list.size(); i++ ) {
			if(items.contains(list.get(i))) {
				continue;

			}
			else {
				items.add(list.get(i));
				System.out.println(list.get(i) + " has been added to the catalog");
			}
		}
	}
	
	public static void removeItems(List<Item> list) {
		for(int i = 0; i < list.size(); i++) {
			if(items.remove(list.get(i))) {
				System.out.println("Removed " + list.get(i));
			}
			else System.out.println("Item " + list.get(i).getItemName() + " was not found in catalog");
		}
	}
	


}
