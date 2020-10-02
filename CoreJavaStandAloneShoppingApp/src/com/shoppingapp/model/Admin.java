package com.shoppingapp.model;

import java.util.ArrayList;
import java.util.List;

public class Admin {
	
	
	public static void GenerateInitialCatalog() {
		Item item1 = new Item("Washing-Machine", 300.00, "Brand new 2020 LG Washing Machine");
		Item item2 = new Item("TV", 1000.00, "Samsung 69 inch 4K TV");
		Item item3 = new Item("Monitor", 1300.00, "Dell 55 inch 4K Monitor");
		Item item4 = new Item("SmartPhone", 1200.00, "Galaxy Note 20 Ultra");
		Item item5 = new Item("Tablet", 400.00, "Apple Ipad Pro");
		Item item6 = new Item("Speakers", 120.00, "2020 Logitech Speakers");
		Item item7 = new Item("Refrigerator", 1000.00, "Samsung Refrigerator");
		Item item8 = new Item("ToothBrush", 1.00, "State of the art toothbrush");
		Item item9 = new Item("Mouse", 25.00, "Logitech BlueTooth mouse");
		Item item10 = new Item("Console", 500.00, "PS5 console");
		
		Catalog.items.add(item1);
		Catalog.items.add(item2);
		Catalog.items.add(item3);
		Catalog.items.add(item4);
		Catalog.items.add(item5);
		Catalog.items.add(item6);
		Catalog.items.add(item7);
		Catalog.items.add(item8);
		Catalog.items.add(item9);
		Catalog.items.add(item10);
		
		
	}
	
	public static void addtoCatalog(Item item) {
		Catalog.items.add(item);
	}
	
	public static void addItemstoCatalog(List<Item> items) {
		for(Item item : items) {
			Catalog.items.add(item);
		}
	}
	
	public static void deletefromCatalog(Item item) {
		Catalog.items.remove(item);
	}
	
	
	
}

