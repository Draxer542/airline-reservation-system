
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

public class AirlineReservationSystem {

	// Connection to the mySQL database
	static DatabaseConnection connect = new DatabaseConnection(
			"jdbc:mysql://localhost/cs157a", "com.mysql.jdbc.Driver", "root",
			"1234");

	// static ArrayList<Integer> ids = new ArrayList<Integer>();

	public static void main(String[] args) throws SQLException {

		/* TESTS!! */
		ResultSet rs;

		// test getflights
		rs = getFlights();

		// result set of query
		while (rs.next()) {
			String name = rs.getString("flightID");
			System.out.println(name);
		}

		// test getFlightDepTimes
		rs = getFlightDepTimes();

		// result set of query
		while (rs.next()) {
			String name = rs.getString("depTime");
			System.out.println(name);
		}

		// test getSeats
		rs = getSeats("1867");

		// result set of query
		while (rs.next()) {
			String name = rs.getString("sID");
			System.out.println(name);
		}

		/* End TESTS! */

		userMenu();

	}// main

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

		ResultSet rs;
		frame.setBounds(300, 100, 800, 300);
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
		table.setPreferredScrollableViewportSize(new Dimension(700, 200));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

		//if adding a flight
		if (b)
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

						bookSeat(planeID, flightID);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.dispose();
				}
			});
	}// editFlight

	/**			bookSeat
	 * This is the menu to book a seat
	 * @param planeID - ID of plane to book seat on
	 * @param flightID - ID of flight to book seat for
	 * @throws SQLException
	 */
	public static void bookSeat(String planeID, final String flightID)
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
		table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(ageLabel);
		panel.add(ageField);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / table.getRowHeight();
				String value = "";
				if (row < table.getRowCount() && row >= 0
						&& column < table.getColumnCount())
					value = (String) table.getValueAt(row, 0);

				String age = ageField.getText();
				String name = nameField.getText();

				System.out.println(name);
				addSeatQuery(age, name, value.toString(), flightID);

			}
		});

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
			String flightID) {
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
				+ (int) (Math.random() * 100) + 1 + "C\')";

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

		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 500, 200);
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
		frame.setBounds(300, 100, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);
		final JLabel text = new JLabel("Please enter your passenger ID");
		panel.add(text);

		final JTextField seat = new JTextField("<<passenger id>>", 10);
		// final JTextField flight = new JTextField("<<flight ID>>",10);

		panel.add(seat);
		// panel.add(flight);
		JButton submitButton = new JButton("Submit");
		panel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSeat(seat.getText());
				frame.dispose();
			}
		});
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
