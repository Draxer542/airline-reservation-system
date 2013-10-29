package com.airline.reservation.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

/**
 * DatabaseConnection.java
 * 
 * This class is used to connect to the database.
 * 
 * @author Shivalik
 *
 */
public class DatabaseConnection {
	public ResultSet rs;
	protected Connection conn;
	protected PreparedStatement pst;
	protected  Statement stmt;

	/*
	 * DatabaseConnection()
	 */
	public DatabaseConnection(String URL, String driver, String user,
			String password) {
		try {
			Class.forName(driver);
			System.out.println("Connecting to database...");			
			conn = DriverManager.getConnection(URL, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * executeQuery()
	 */
	public void executeQuery(String sql, String value1, String value2)	{
		try	{
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			//to assign the argument passed in the TestConnection class to the
			//"app_num" in SQL Query
			pst.setString(1, value1);
			pst.setString(2, value2);
			rs = pst.executeQuery();
		} catch (Exception e) {
			System.out.println("Error in Command \n" + e);
			e.printStackTrace();
		}
	}

	public void execute1(String query) throws SQLException
	{
		//stmt.execute("USE cs157a");
		//stmt.executeQuery(query);
	}
	
	/*
	 * execute()
	 */
	public void execute(String sql)	{
		try	{
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
		} catch (Exception e) {
			System.out.println("Error in Command\n" + e);
			e.printStackTrace();
		}
	}

	/*
	 * closeConnection()
	 */
	public void closeConnection() {
		if(rs != null) {
			try	{
				rs.close();
			} catch(SQLException e)	{

			}
		}
		if(pst != null)	{
			try	{
				pst.close();
			} catch(SQLException e)	{

			} 
		}
		if(conn != null) {
			try	{
				conn.close();
			} catch(SQLException e)	{

			}
		}
	}
}