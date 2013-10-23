package com.airline.reservation.system;

public class AirlineReservationSystem {
	public static void main(String[] args) {
		DatabaseConnection connect = new DatabaseConnection(
				"jdbc:mysql://localhost/", 
				"com.mysql.jdbc.Driver", 
				"root", 
				"shiv@lik19" );
		System.out.println("Connection has now been closed!");		
	}
}
