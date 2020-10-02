package com.shoppingapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable{
	
    private static final long serialVersionUID = 4L;     
	public static Long orderIdincrement = 0L;
	private Long orderId = 0L;
	
	private LocalDateTime purchaseDate = null;
	private Item item = null;
	private List<Item> items = null;
	
	public Order(LocalDateTime date, List<Item> items) {
		this.purchaseDate = date;
		this.items = items;
		this.orderId = orderIdincrement++;
		
	}
	
	public Order(LocalDateTime date, Item item) {
		this.purchaseDate = date;
		this.item = item;
		this.orderId = orderIdincrement++;
		
	}

	public static Long getOrderIdincrement() {
		return orderIdincrement;
	}

	public static void setOrderIdincrement(Long orderIdincrement) {
		Order.orderIdincrement = orderIdincrement;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	
	

}
