package test;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.interfaces.RSAKey;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Demo {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "saosin314";
  
   
   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	    String queryDrop = "DROP DATABASE IF EXISTS cs";
	    Statement stmtDrop = conn.createStatement();
	    stmtDrop.execute(queryDrop);
	    create();


	      String sql = "CREATE DATABASE cs";
	      stmt.executeUpdate(sql);
	      System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
   
   System.out.println("Goodbye!");
}//end main
   
   public static void create()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("WELCOME TO ASL AIRLINE RESERVATION SYSTEM");
		JButton button = new JButton("Book Flight");
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				book();
			}
		});
		
		JButton cancelRevButton = new JButton("Cancel Reservation");
               
               JButton selectFlightButton = new JButton("Book Flight");
               selectFlightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{

					selectFlight();
			
			}
		});
               
               cancelRevButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cancelRev();
			}
		});
               panel.add(label);
               panel.add(selectFlightButton);
		//panel.add(button);
		panel.add(cancelRevButton);
               
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 500, 200);
		Container con = frame.getContentPane();
		con.add(panel);
		
		frame.setVisible(true);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
   

	public static void book(){
               JPanel panel = new JPanel();
		
		final JTextField tf = new JTextField();
		tf.setColumns(4);
		
		JButton button1 = new JButton("OK");
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				System.out.println(tf.getText());
			}
		});
		panel.add(button1);
		panel.add(tf);
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		Container con = frame.getContentPane();
		con.add(panel);
		
		frame.setVisible(true);
	}
       
       public static void selectFlight()
       {
           final JFrame frame = new JFrame();
           frame.setBounds(300, 100, 800, 300);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           JPanel panel = new JPanel();
           frame.add(panel);
           JLabel label = new JLabel("Please select a flight");
           panel.add(label);
           String[] columnNames = {"Flight ID",
                               "Destination",
                               "Departure Date",
                               "Departure Time", "Gate"};
           
           
           //getFlight();
           Object[][] data = {
               {101, "Tokyo, Japan", "2013-12-25" , "1:20:00","A001"},
               {102, "Manilla, Philippines", "2013-11-02" , "13:00:00","A002"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
               {1, "aplace", "adate" , "atime","agate"},
           };
           
           final JTable table = new JTable(data, columnNames);
           table.setPreferredScrollableViewportSize(new Dimension(700, 200));
           table.setFillsViewportHeight(true);
           JScrollPane scrollPane = new JScrollPane(table);
           panel.add(scrollPane);
           
           //mouse click listen to select the flight. it will return flightID
           table.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e) {
                   int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                   int row    = e.getY()/table.getRowHeight();
                   Object value = "";
                   if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount())
                       value = table.getValueAt(row, 0);
                   System.out.println(value);
                   String flightID = value.toString();
                   printAvailableSeats(flightID);
                   frame.dispose();
               }
           });
           
       }
       
       public static void cancelRev()
       {
           final JFrame frame = new JFrame();
           frame.setBounds(300, 100, 400, 200);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           JPanel panel = new JPanel();
           frame.add(panel);
           final JLabel text = new JLabel("Please enter your name(First Last) and seat number");
           panel.add(text);
           final JTextField name = new JTextField("<<name>>",20);
           final JTextField seat = new JTextField("<<seat number>>",10);
           panel.add(name);
           panel.add(seat);
           JButton submitButton = new JButton("Submit");
           panel.add(submitButton);
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
                           System.out.println(name.getText() + seat.getText());
                           frame.dispose();
			}
		});
           
       }
       
       //print available seats for a flight
       public static void printAvailableSeats(String flightID)
       {
           final JFrame frame = new JFrame();
           frame.setBounds(300, 100, 500, 500);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           JPanel panel = new JPanel();
           frame.add(panel);
           JLabel label = new JLabel("Please select a seat and enter your information. 'F' seats  = First class.");
           JLabel nameLabel = new JLabel("Name: ");
           JLabel ageLabel = new JLabel("Age: ");
           final JTextField nameField = new JTextField(20);
           final JTextField ageField = new JTextField(5);
           panel.add(label);
           
           String[] columnNames = {"Seat ID","Row","Seat Number",};
           //sample data
           Object[][] data = {
               {"A01", "A", 1},
               {"A02", "A", 2},
               {"A03", "B", 1},
               {"A04", "B", 2},
               {"A05", "C", 1},
               {"A06", "C", 2},
               {"A07", "F", 1},
           };
           final JTable table = new JTable(data, columnNames);
           table.setPreferredScrollableViewportSize(new Dimension(500, 300));
           table.setFillsViewportHeight(true);
           JScrollPane scrollPane = new JScrollPane(table);
           panel.add(scrollPane);
           table.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent e) {
                   int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                   int row    = e.getY()/table.getRowHeight();
                   Object value = "";
                   if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount())
                       value = table.getValueAt(row, 0);
                   System.out.println(value);
               }
           });
           
           panel.add(nameLabel);
           panel.add(nameField);
           panel.add(ageLabel);
           panel.add(ageField);
       }
       
       public static void getFlight()
       {
    
       }
}
//end JDBCExample

