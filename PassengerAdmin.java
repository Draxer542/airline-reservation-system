import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class PassengerAdmin extends Admin{

	
	public PassengerAdmin(DatabaseConnection c){connect = c;}
	
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
}
