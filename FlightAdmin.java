import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class FlightAdmin extends Admin{

	
	
	public FlightAdmin(DatabaseConnection c)
	{
		connect = c;
	}
	
	public void admin()
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
}
