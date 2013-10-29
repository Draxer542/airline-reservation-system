package com.airline.reservation.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.Statement;



public class AirlineReservationSystem {
	public static void main(String[] args) throws SQLException {		
		DatabaseConnection connect = new DatabaseConnection(
				"jdbc:mysql://localhost/cs157a", 
				"com.mysql.jdbc.Driver", 
				"root", 
				"shiv@lik19" );		
		
		
		//working query
		String query = "SELECT pName FROM pilot";
		connect.execute(query);
		
		try{
			while(connect.rs.next())
			{
				String name = connect.rs.getString("pName");
				System.out.println(name);
			}
			
		}catch(SQLException e) {
			
		}

		
		System.out.println("Connection has now been closed!");		
	}
	

}
