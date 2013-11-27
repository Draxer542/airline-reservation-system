import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class FlightAdmin extends Admin{

	
	
	public FlightAdmin(DatabaseConnection c)
	{
		connect = c;
	}
	
	public void admin()
	{
		final JFrame frame = new JFrame();
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
		
		btnCloseWindow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				frame.dispose();
			}
		});
		
		JLabel flightLabel = new JLabel("Flight ID");
		
		final JTextField flightField = new JTextField();
		flightField.setColumns(10);
		
		JLabel destLabel = new JLabel("Destination");
		
		final JTextField destField = new JTextField();
		destField.setColumns(10);
		
		JLabel depDateLabel = new JLabel("Departure Date");
		
		final JTextField depDateField = new JTextField();
		depDateField.setColumns(10);
		
		JLabel depTimeLabel = new JLabel("Departure Time");
		
		final JTextField depTimeField = new JTextField();
		depTimeField.setColumns(10);
		
		JLabel planeLabel = new JLabel("Plane ID");
		
		final JTextField planeField = new JTextField();
		planeField.setColumns(10);
		
		JLabel gateLabel = new JLabel("Gate ID");
		
		final JTextField gateField = new JTextField();
		gateField.setColumns(10);
		
		JLabel pilotLabel = new JLabel("PilotID");
		
		final JTextField pilotField = new JTextField();
		pilotField.setColumns(10);
		
		final JCheckBox befDateBox = new JCheckBox("Before");
		
		final JCheckBox afDateBox = new JCheckBox("After");
		
		final JCheckBox befTimeBox = new JCheckBox("Before");
		
		final JCheckBox afTimeBox = new JCheckBox("After");
		
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					if(deleteFlight(flightField.getText()))
						JOptionPane.showMessageDialog(frame, "Flight " + flightField.getText() +" has been deleted from the database.");
					else
						JOptionPane.showMessageDialog(frame, "Please enter a Flight ID that exists in the database!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!addFlight(flightField.getText(), destField.getText(), depDateField.getText(), depTimeField.getText(), planeField.getText(),
						pilotField.getText(), gateField.getText()))
						JOptionPane.showMessageDialog(frame, "Please enter an unused flightID, make sure your date field is in the form YYYY-MM-DD and "
								+"your time is in the form HH:MM:SS!");
				else
					JOptionPane.showMessageDialog(frame, "Flight " + flightField.getText() + " has been added to database.");
			}
		});
		
		btnView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = 0;
				int j = 0;
				
				if (befDateBox.isSelected())
					i = -1;
				else if(afDateBox.isSelected())
					i = 1;
				
				if(befTimeBox.isSelected())
					j = -1;
				else if(afTimeBox.isSelected())
					j = 1;
				viewFlights(flightField.getText(), destField.getText(), depDateField.getText(), depTimeField.getText(), planeField.getText(),
						pilotField.getText(), gateField.getText(), i, j);
			}
		});
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
	
	public boolean deleteFlight(String id) throws SQLException {
		if (id.equals(""))
			return false;
		ResultSet rs;

		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Flight WHERE flightID = " + id + "";
		rs = connect.execute(sql);
		
		if (!rs.next()) {
			return false;
		}

		if (id.compareTo("") != 0)
			sql = "DELETE FROM Flight WHERE flightID = \'" + id + "\'";

		try {
			System.out.println(sql);
			connect.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean addFlight(String flightID, String destination, String depDate, String depTime, String planeID
			, String pilotID, String gateID) {
		
		String sql = "INSERT INTO Flight VALUES(" + flightID + ", \"" + destination + "\", \'" + depDate + "\', \'" + depTime + "\', " + planeID + ", " +
				pilotID + ", \"" + gateID + "\")";
		System.out.println(sql);
		if (flightID.equals("") || destination.equals("") || depDate.equals("") || depTime.equals("") || planeID.equals("") 
				|| pilotID.equals("") || gateID.equals(""))
			return false;
		
		 sql = "INSERT INTO Flight VALUES(" + flightID + ", \"" + destination + "\", \'" + depDate + "\', \'" + depTime + "\', " + planeID + ", " +
						pilotID + ", \"" + gateID + "\")";
		
		String sqlCheck = "SELECT * FROM Flight WHERE flightID = " + flightID;
		System.out.println(sqlCheck);
		ResultSet rs;
		rs = connect.execute("SELECT * FROM Flight WHERE flightID = " + flightID);
		
		try {
			if(AirlineReservationSystem.getRowNum(rs) == 0)
			{
				
					connect.executeUpdate(sql);
					return true;
				
					
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}// addPilot

	public void viewFlights(String flightID, String destination, String depDate, String depTime, String planeID
			, String pilotID, String gateID, int compareDate, int compareTime) {

		ResultSet rs;

		// default sql statement(if no attributes are specified
		String sql = "SELECT * FROM Flight";

		// if any attribute is specified, append WHERE to sql
		if (flightID.compareTo("") + destination.compareTo("") + depDate.compareTo("") + depTime.compareTo("") + 
				planeID.compareTo("") + pilotID.compareTo("") + gateID.compareTo("") != 0) {
			sql = sql + " WHERE ";
			
			if(flightID.compareTo("") != 0)
				sql = sql + "flightID = " + flightID + " AND ";
			
			if(destination.compareTo("") != 0)
				sql += "destination = \"" + destination + "\" AND ";
			
			if(depDate.compareTo("") != 0 && compareDate == 0)
				sql += "depDate = \'" + depDate + "\' AND ";
			else if(compareDate > 0)
				sql += "depDate >" + "\'" + depDate + "\' AND ";
			else if(compareDate<0)
				sql+= "depDate <" + "\'" + depDate + "\' AND ";
			
			if(depTime.compareTo("") != 0 && compareTime == 0)
				sql += "depTime = \'" + depTime + "\' AND ";
			else if(compareTime>0)
				sql += "depTime > \'" + depTime + "\' AND ";
			else if(compareTime<0)
				sql += "depTime < \'" + depTime + "\' AND ";
			
			if(planeID.compareTo("") != 0)
				sql += "planeID = " + planeID + " AND ";
			
			if(pilotID.compareTo("") != 0)
				sql += "pilotID = " + pilotID + " AND ";
			
			if(gateID.compareTo("") != 0)
				sql += "gateID = \"" + gateID + "\" AND ";
			
			sql = sql.substring(0, sql.length()-4);

		}

		// CHECK, DELETE WHEN DONE
		System.out.println(sql);
		rs = connect.execute(sql);
		
		// Create frame
		final JFrame frame = new JFrame();
		frame.setBounds(400, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		frame.add(panel);

		// get number of rows
		int rowNum = AirlineReservationSystem.getRowNum(rs);

		// fill Object[][] array with values
		int i = 0;
		Object[][] data = new Object[rowNum][7];
		try {
			while (rs.next()) {

				data[i][0] = rs.getString("flightID");
				data[i][1] = rs.getString("destination");
				data[i][2] = rs.getDate("depDate");
				data[i][3] = rs.getTime("depTime");
				data[i][4] = rs.getInt("planeID");
				data[i][5] = rs.getInt("pilotID");
				data[i][6] = rs.getString("gateID");

				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// header table
		String columnNames[] = { "FlightID", "Destination", "DepDate", "DepTime", "PlaneID", "PilotID", "GateID" };

		// fill table
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(450, 300));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JLabel text = new JLabel("Edit database except for PilotID");
		panel.add(text);
		panel.add(scrollPane);

		// tablemodellisten for editing database
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();
				String columnName = model.getColumnName(column);
				Object data = model.getValueAt(row, column);
				String flightID = (String) table.getValueAt(row, 0);
				if (column != 1)
					;
				if(column < 2 || column == 6)
					editFlight(columnName, data, flightID, 0);
				else if(column == 0 || (column > 3 && column < 5))
					editFlight(columnName, data, flightID, 1);
				else
					editFlight(columnName, data, flightID, 2);

			}
		});
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(closeButton);

	}// viewPilots

	public void editFlight(String columnName, Object data, String flightID, int type) {
		String sql = "";
		if(type == 0)
		sql = "UPDATE Flight SET " + columnName + " = \"" + data
				+ "\" WHERE flightID = " + flightID;
		
		else if(type == 1)
			sql = "UPDATE Flight SET " + columnName + " = " + data
			+ " WHERE flightID = " + flightID;
		else
			sql = "UPDATE Flight SET " + columnName + " = \'" + data
			+ "\' WHERE flightID = " + flightID;
		System.out.println(data.getClass());
		System.out.println(sql);
		try {
			connect.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
