package com.shoppingapp.model;

import java.io.Serializable;

public class Item implements Serializable{
    private static final long serialVersionUID = 6L;     

	static Long itemIDincrement = 0L;
	private Long Id = null;
	private String itemName = null;
	private Double price = 0.0;
	private String description = null;
	
	public Item(String itemName, Double price, String description) {
		super();
		Id = itemIDincrement++;
		this.itemName = itemName;
		this.price = price;
		this.description = description;
	}

	public Long getId() {
		return Id;
	}

	public String getItemName() {
		return itemName;
	}

	public Double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	

}
