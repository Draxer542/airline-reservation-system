
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AirlineReservationSystem {

	// Connection to the mySQL database
	static DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"1234");
        static private String seatID ="";
       
        
	// static ArrayList<Integer> ids = new ArrayList<Integer>();

	public static void main(String[] args) throws SQLException {

	
	//opens main menu, determines whether user is regular user or admin
	mainMenu();
		
	

	}// main

	/**			mainMenu
	 * main menu for the application
	 * 
	 * - user selects between user/admin/exit button
	 * 
	 */
	public static void mainMenu()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel(
				"                                                                          User or Admin?                                                                         ");

		//button declaration
		JButton userButton = new JButton("User");
		JButton adminButton = new JButton("Admin");
		JButton closeButton = new JButton("Exit");
		
		//frame declaration, initialization
		final JFrame frame = new JFrame();
        frame.setTitle("Airline Reservation System");
        frame.setBounds(100, 100, 500, 200);
		
        
        //User clicked
		userButton.addActionListener(new ActionListener() {
			//close frame, open user menu
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				userMenu();
			}
		});
		
		//admin clicked
		adminButton.addActionListener(new ActionListener() {
			//close frame, open admin menu
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				adminMenu();
			}
		});

		//exit clicked 
	    closeButton.addActionListener(new ActionListener(){
	        //close frame
	    	public void actionPerformed(ActionEvent e){
	            frame.dispose();
	        }
	    });
		
	    //add components to panel
		panel.add(label);
		panel.add(userButton);
		panel.add(adminButton);
		panel.add(closeButton);

		//create container               
		Container con = frame.getContentPane();
		con.add(panel);

                
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**			adminMenu
	 * Menu for administrators
	 * 
	 */
	public static void adminMenu(){
		JPanel panel = new JPanel();
		JLabel label = new JLabel(
				"                                                                          What database would you like to access?                                                                         ");

		//button declaration
		JButton pilotButton = new JButton("Pilot");
		JButton planeButton = new JButton("Plane");
		JButton flightButton = new JButton("Flight");
		JButton passengerButton = new JButton("Passenger");
		JButton seatButton = new JButton("Seat");
		
		//button actionlisteners
		pilotButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				viewPilots();
				
			}
		});
		
		planeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				viewPlanes();
				
			}
		});
		
		flightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				viewFlights();
				
			}
		});
		
		passengerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				viewPassengers();
				
			}
		});
		
		seatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				viewSeats();
				
			}
		});
		
		//frame declaration, initialization
		final JFrame frame = new JFrame();
        frame.setTitle("Airline Reservation System");
        frame.setBounds(100, 100, 500, 200);
        
        
		
		
	    //add components to panel
		panel.add(label);
		panel.add(pilotButton);
		panel.add(planeButton);
		panel.add(flightButton);
		panel.add(passengerButton);
		panel.add(seatButton);

		//create container               
		Container con = frame.getContentPane();
		con.add(panel);

                
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**			viewPilots
	 * 
	 */
	public static void viewPilots(){
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 578, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Please enter information about the pilot. Leave fields blank if you wish to view all pilots.");
		
		JLabel label = new JLabel("");
		
		JLabel nameLabel = new JLabel("Name");
		
		JTextField nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel pilotLabel = new JLabel("Pilot ID");
		
		JTextField pilotField = new JTextField();
		pilotField.setColumns(10);
		
		JLabel expLabel = new JLabel("Years of Experience");
		
		JTextField expField = new JTextField();
		expField.setColumns(10);
		
		JCheckBox greaterBox = new JCheckBox("Greater than");
		
		JCheckBox lessBox = new JCheckBox("Less Than");
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnView = new JButton("View");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnCloseWindow = new JButton("Close Window");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(nameLabel)
										.addComponent(pilotLabel))
									.addGap(80)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(expLabel)
									.addGap(18)
									.addComponent(expField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(greaterBox)
							.addGap(18)
							.addComponent(lessBox))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd)
							.addGap(36)
							.addComponent(btnEdit)
							.addGap(39)
							.addComponent(btnView)
							.addGap(41)
							.addComponent(btnDelete)
							.addGap(49)
							.addComponent(btnCloseWindow)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pilotLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(expLabel)
						.addComponent(expField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(greaterBox)
						.addComponent(lessBox))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnEdit)
						.addComponent(btnView)
						.addComponent(btnDelete)
						.addComponent(btnCloseWindow))
					.addGap(22)
					.addComponent(label)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);

	}
	
	
	public static void viewPlanes(){
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 578, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Please enter information about the pilot. Leave fields blank if you wish to view all pilots.");
		
		JLabel label = new JLabel("");
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnView = new JButton("View");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnCloseWindow = new JButton("Close Window");
		
		JLabel idLabel = new JLabel("Plane ID");
		
		JTextField idField = new JTextField();
		idField.setColumns(10);
		
		JLabel ageLabel = new JLabel("Plane Age");
		
		JTextField  ageField = new JTextField();
		ageField.setColumns(10);
		
		JCheckBox olderBox = new JCheckBox("Older Than");
		
		JCheckBox youngerBox = new JCheckBox("Younger Than");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(idLabel)
							.addGap(18)
							.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(ageLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdd)
									.addGap(34)
									.addComponent(btnEdit)))
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(olderBox)
								.addComponent(btnView))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnDelete)
									.addGap(46)
									.addComponent(btnCloseWindow))
								.addComponent(youngerBox))))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(idLabel)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ageLabel)
						.addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(olderBox)
						.addComponent(youngerBox))
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnEdit)
						.addComponent(btnView)
						.addComponent(btnDelete)
						.addComponent(btnCloseWindow))
					.addGap(51)
					.addComponent(label)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	
	
	public static void viewFlights()
	{
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 578, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Please enter information about the pilot. Leave fields blank if you wish to view all pilots.");
		
		JLabel label = new JLabel("");
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnView = new JButton("View");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnCloseWindow = new JButton("Close Window");
		
		JLabel flightLabel = new JLabel("Flight ID");
		
		JTextField flightField = new JTextField();
		flightField.setColumns(10);
		
		JLabel destLabel = new JLabel("Destination");
		
		JTextField destField = new JTextField();
		destField.setColumns(10);
		
		JLabel depDateLabel = new JLabel("Departure Date");
		
		JTextField depDateField = new JTextField();
		depDateField.setColumns(10);
		
		JLabel depTimeLabel = new JLabel("Departure Time");
		
		JTextField depTimeField = new JTextField();
		depTimeField.setColumns(10);
		
		JLabel planeLabel = new JLabel("Plane ID");
		
		JTextField planeField = new JTextField();
		planeField.setColumns(10);
		
		JLabel gateLabel = new JLabel("Gate ID");
		
		JTextField gateField = new JTextField();
		gateField.setColumns(10);
		
		JLabel pilotLabel = new JLabel("PilotID");
		
		JTextField pilotField = new JTextField();
		pilotField.setColumns(10);
		
		JCheckBox befDateBox = new JCheckBox("Before");
		
		JCheckBox afDateBox = new JCheckBox("After");
		
		JCheckBox befTimeBox = new JCheckBox("Before");
		
		JCheckBox afTimeBox = new JCheckBox("After");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdd)
									.addGap(34)
									.addComponent(btnEdit))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(flightLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(33)
									.addComponent(btnView)
									.addGap(42)
									.addComponent(btnDelete)
									.addGap(46)
									.addComponent(btnCloseWindow))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(planeLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(planeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(gateLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(gateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(pilotLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(depDateLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(depDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(befDateBox)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(afDateBox))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(depTimeLabel)
								.addComponent(destLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(destField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(depTimeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(befTimeBox)
							.addGap(4)
							.addComponent(afTimeBox)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(flightLabel)
						.addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(planeLabel)
						.addComponent(planeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gateLabel)
						.addComponent(pilotLabel)
						.addComponent(pilotField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(depDateLabel)
						.addComponent(depDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(befDateBox)
						.addComponent(afDateBox))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(depTimeLabel)
						.addComponent(depTimeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(afTimeBox)
						.addComponent(befTimeBox))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(destLabel)
						.addComponent(destField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnEdit)
						.addComponent(btnView)
						.addComponent(btnDelete)
						.addComponent(btnCloseWindow))
					.addGap(51)
					.addComponent(label)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public static void viewPassengers()
	{
	
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 578, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Please enter information about the pilot. Leave fields blank if you wish to view all pilots.");
		
		JLabel label = new JLabel("");
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnView = new JButton("View");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnCloseWindow = new JButton("Close Window");
		
		JLabel nameLabel = new JLabel("Name");
		
		JTextField nameField = new JTextField();
		nameField.setColumns(10);
		
		JLabel ageLabel = new JLabel("Age");
		
		JTextField ageField = new JTextField();
		ageField.setColumns(10);
		
		JLabel flightLabel = new JLabel("Flight ID");
		
		JTextField flightField = new JTextField();
		flightField.setColumns(10);
		
		JLabel seatLabel = new JLabel("Seat ID");
		
		JTextField seatField = new JTextField();
		seatField.setColumns(10);
		
		JCheckBox firstClassBox = new JCheckBox("First Class");
		
		JLabel passLabel = new JLabel("Passenger ID");
		
		JTextField pasField = new JTextField();
		pasField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(flightLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdd)
									.addGap(34)
									.addComponent(btnEdit))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(nameLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(ageLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(passLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(seatLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(firstClassBox))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnView)
									.addGap(42)
									.addComponent(btnDelete)
									.addGap(46)
									.addComponent(btnCloseWindow)))))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(seatLabel)
						.addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstClassBox))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ageLabel)
						.addComponent(ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passLabel)
						.addComponent(pasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(flightLabel)
						.addComponent(flightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnEdit)
						.addComponent(btnView)
						.addComponent(btnDelete)
						.addComponent(btnCloseWindow))
					.addGap(51)
					.addComponent(label)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	
	public static void viewSeats(){
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 578, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Please enter information about the seat. Leave fields blank if you wish to view all seatss.");
		
		JLabel label = new JLabel("");
		
		JLabel lblSeatId = new JLabel("Seat ID");
		
		JTextField seatField = new JTextField();
		
		
		
		seatField.setColumns(10);
		
		JLabel rowLabel = new JLabel("Row");
		
		JTextField rowField = new JTextField();
		rowField.setColumns(10);
		
		JLabel seatNoLabel = new JLabel("Seat Number");
		
		JTextField seatNoField = new JTextField();
		seatNoField.setColumns(10);
		
		JLabel planeIDLabel = new JLabel("Plane ID");
		
		JTextField planeIDField = new JTextField();
		planeIDField.setColumns(10);
		
		JCheckBox takenBox = new JCheckBox("Taken");
		
		JButton btnAdd = new JButton("Add");
		
		JButton btnEdit = new JButton("Edit");
		
		JButton btnView = new JButton("View");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnCloseWindow = new JButton("Close Window");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSeatId)
						.addComponent(rowLabel))
					.addContainerGap(516, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(lblNewLabel))
							.addGap(66))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(seatNoLabel)
								.addComponent(planeIDLabel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(planeIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(seatNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(takenBox))
								.addComponent(rowField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd)
								.addComponent(btnEdit)
								.addComponent(btnView)
								.addComponent(btnDelete)
								.addComponent(btnCloseWindow))
							.addContainerGap(58, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeatId)
						.addComponent(seatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rowLabel)
								.addComponent(rowField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(seatNoLabel)
								.addComponent(seatNoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(takenBox))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(planeIDLabel)
								.addComponent(planeIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(btnEdit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnView)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCloseWindow)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	
	
	/**
	 * getFlights
	 * 
	 * @return all current flights in ResultSet form
	 */
	public static ResultSet getFlights() {
		String query = "SELECT * FROM Flight";
		return connect.execute(query);
	}

	/**
	 * getFlightDepTimes
	 * 
	 * @return all flight departure times in ResultSet form
	 */
	public static ResultSet getFlightDepTimes() {
		String query = "SELECT depTime FROM Flight";
		return connect.execute(query);
	}

	/**
	 * getSeats
	 * 
	 * Gets all available(non-taken) seats from a plane
	 * 
	 * @param s
	 *            - the planeID to get the seats from
	 * @return available seats in ResultSet form
	 */
	public static ResultSet getSeats(String s) {
		String query = "SELECT * FROM Seat WHERE taken = false AND planeID ="
				+ s;
		return connect.execute(query);
	}

	/**
	 * getRowNum get number of rows in a result set
	 * 
	 * @param rs
	 *            - ResultSet to operate on
	 * @return number of rows
	 */
	public static int getRowNum(ResultSet rs) {
		int num = 0;

		try {
			rs.last();
			num = rs.getRow();
			rs.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;

	}

	/**
	 * editFlight Performs process of booking a flight
	 * 
	 * @param b - whether the action is to view or book a flight
	 * @throws SQLException
	 * 
	 */
	public static void editFlight(boolean b) throws SQLException {

		final JFrame frame = new JFrame();
                final boolean isbook  = b;
		ResultSet rs;
                frame.setTitle("Flight Table");
		frame.setBounds(300, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		JLabel label = new JLabel("Please select a flight");
		panel.add(label);
		String[] columnNames = { "Flight ID", "Destination", "Departure Date",
				"Departure Time", "Gate" };

		// get Flights
		rs = getFlights();

		// get number of rows
		int rowNum = getRowNum(rs);

		int i = 0;
		Object[][] data = new Object[rowNum][rowNum];
		while (rs.next()) {

			data[i][0] = rs.getInt("flightID");
			data[i][1] = rs.getString("destination");
			data[i][2] = rs.getDate("depDate");
			data[i][3] = rs.getTime("depTime");
			data[i][4] = rs.getInt("planeID");
			data[i][5] = rs.getInt("pilotID");
			data[i][6] = rs.getString("gateID");

			i++;
		}

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(750, 200));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		
                // mouse click listen to select the flight. it will return flightID
                table.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                                int column = table.getColumnModel().getColumnIndexAtX(
                                                e.getX());
                                int row = e.getY() / table.getRowHeight();
                                Object value = "";
                                if (row < table.getRowCount() && row >= 0
                                                && column < table.getColumnCount())
                                        value = table.getValueAt(row, 4);
                                System.out.println(value);
                                String planeID = value.toString();
                                value = table.getValueAt(row, 0);
                                String flightID = value.toString();

                                try {

                                        bookSeat(planeID, flightID, isbook);

                                } catch (SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                                frame.dispose();
                        }
                });
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                                frame.dispose();
                        }
                });
                panel.add(closeButton);
	}// editFlight

	/**			bookSeat
	 * This is the menu to book a seat
	 * @param planeID - ID of plane to book seat on
	 * @param flightID - ID of flight to book seat for
	 * @throws SQLException
	 */
	public static void bookSeat(String planeID, final String flightID, boolean isbook)
			throws SQLException {
		final JFrame frame = new JFrame();
		frame.setBounds(300, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		JLabel label = new JLabel(
				"Please select a seat and enter your information. 'F' seats  = First class.");
		final JLabel nameLabel = new JLabel("Name: ");
		final JLabel ageLabel = new JLabel("Age: ");
		final JTextField nameField = new JTextField(20);
		final JTextField ageField = new JTextField(5);
		panel.add(label);

		// final String chosen = "";

		String[] columnNames = { "Seat ID", "Row", "Seat Number", };
		// sample data
		ResultSet rs = getSeats(planeID);

		int rowNum = getRowNum(rs);

		int i = 0;
		Object[][] data = new Object[rowNum][rowNum];
		while (rs.next()) {

			data[i][0] = rs.getString("sID");
			data[i][1] = rs.getString("Row");
			data[i][2] = rs.getInt("seatNo");
			data[i][3] = rs.getBoolean("taken");
			data[i][4] = rs.getInt("planeID");

			i++;
		}

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(450, 300));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

		

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / table.getRowHeight();
				String value = "";
				if (row < table.getRowCount() && row >= 0
						&& column < table.getColumnCount())
					value = (String) table.getValueAt(row, 0);
                                seatID = value;

			}
		});
                if(isbook)
                {
                    panel.add(nameLabel);
                    panel.add(nameField);
                    panel.add(ageLabel);
                    panel.add(ageField);
                    JButton submitButton = new JButton("Submit");
                   panel.add(submitButton);
                   submitButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                   String age = ageField.getText();
                                   String name = nameField.getText();

                                   System.out.println(name);
                                   int pasNum = 0;
                                   while (pasNum < 99) 
                                       pasNum = (int) (Math.random()*1000);
                                   String pasID = pasNum  + "C";
                                   addSeatQuery(age, name, seatID, flightID, pasID);
                                   JOptionPane.showMessageDialog(frame, "Thank you for using our system! Your passenger ID is " + pasID + ".");
                                   frame.dispose();
                                }
                   });
                }
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                                frame.dispose();
                        }
                });
                panel.add(closeButton);
	}

	/**			addSeatQuery
	 * 
	 * Performs insertion of passenger to database
	 * @param age - age of passenger
	 * @param name - name of passenger
	 * @param seatID - seat of passenger
	 * @param flightID - flight of passenger
	 */
	public static void addSeatQuery(String age, String name, String seatID,
			String flightID, String pasID) {
		boolean firstClass = false;

		if (seatID.charAt(2) == '7' || seatID.charAt(2) == '8')
			firstClass = true;

		String sql = "INSERT INTO Passenger(name, age, flightID, firstClass, seatID, pasID) "
				+ "VALUES(\""
				+ name
				+ "\", "
				+ age
				+ ", "
				+ flightID
				+ ", "
				+ firstClass
				+ ", \'"
				+ seatID
				+ "\', \'"
				+ pasID + "\')";

		try {
			connect.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**          userMenu
	 * 
	 * Creates the menu for a user
	 */
	public static void userMenu() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(
				"                                       WELCOME TO  AIRLINE RESERVATION SYSTEM                                     ");

		JButton cancelRevButton = new JButton("Cancel Reservation");
		JButton selectFlightButton = new JButton("Book Flight");
		JButton viewButton = new JButton("View Flights");

		selectFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editFlight(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		cancelRevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelRev();
			}
		});

		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editFlight(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(label);
		panel.add(selectFlightButton);
		panel.add(viewButton);

		panel.add(cancelRevButton);

                
		final JFrame frame = new JFrame();
                frame.setTitle("Airline Reservation System");
		frame.setBounds(100, 100, 500, 200);
                
                JButton closeButton = new JButton("Exit");
                closeButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                                frame.dispose();
                        }
                });
                panel.add(closeButton);
		Container con = frame.getContentPane();
		con.add(panel);

                
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// user

	/**			cancelRev
	 * This is the menu to cancel a reservation
	 */
	public static void cancelRev() {
		final JFrame frame = new JFrame();
                frame.setTitle("Cancel Reservation");
		frame.setBounds(300, 100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		final JLabel text = new JLabel("Please enter your passenger ID");
		panel.add(text);

		final JTextField seat = new JTextField(10);

		panel.add(seat);
		JButton submitButton = new JButton("Submit");
		panel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSeat(seat.getText());
                                JOptionPane.showMessageDialog(frame, "Your reservation has been cancelled.");
				frame.dispose();
			}
		});
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                                frame.dispose();
                        }
                });
                panel.add(closeButton);
	}

	/**			cancelSeat
	 * Cancels the seat reservation of a passenger(pasID) 
	 * @param pasID - ID of passenger to cancel seat of
	 */
	public static void cancelSeat(String pasID) {

		String sql = "DELETE FROM Passenger WHERE pasID = \'" + pasID + "\'";
		// System.out.println(sql);
		try {
			connect.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
