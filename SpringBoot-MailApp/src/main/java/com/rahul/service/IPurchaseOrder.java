package com.rahul.service;

public interface IPurchaseOrder {

	public String purchase(String[] items, Double[] prices, String[] toEmails) throws Exception;

}
