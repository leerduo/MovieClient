package com.dy.ustc.lifeclient.domain;

public class CinemaItem {
	
	private String name;
	
	private String address;
	
	private String rating;
	
	

	public CinemaItem(String name, String address, String rating) {
		this.name = name;
		this.address = address;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "CinemaItem [name=" + name + ", address=" + address
				+ ", rating=" + rating + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	

}
