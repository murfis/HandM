package com.example.demo;

public class Execution {

	private String date;
	private String howLong;
	private String howMuch;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHowLong() {
		return howLong;
	}
	public void setHowLong(String howLong) {
		this.howLong = howLong;
	}
	public String getHowMuch() {
		return howMuch;
	}
	public void setHowMuch(String howMuch) {
		this.howMuch = howMuch;
	}
	public Execution(String date, String howLong, String howMuch) {
		super();
		this.date = date;
		this.howLong = howLong;
		this.howMuch = howMuch;
	}
	
		
		
}
