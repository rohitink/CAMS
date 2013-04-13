import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class register extends JFrame implements ActionListener {

	JButton submit, back;
	JLabel user_l, email_l, city_l, name_l, pass_l, cpass_l, dob_l, border, logo, heading;
	JTextField user_t, email_t, city_t, name_t, dob_t;
	JPasswordField pass_p, cpass_p;
	JPanel CONTAINER, side_p, main_p;
	Color c1, c2, c3;
	
	public register() {
		c1 = new Color(12,232,12);
		c2 = new Color(988798);
		c3 = new Color(333333);
		
		CONTAINER = new JPanel();
		CONTAINER.setLayout(null);

		side_p = new JPanel();
		side_p.setBounds(5, 5, 150, 617);
		side_p.setBackground(c1);
		side_p.setLayout(null);
		CONTAINER.add(side_p);
		
		main_p = new JPanel();
		main_p.setLayout(null);
		main_p.setBounds(160, 5, 480, 570);
		//main_p.setBackground(c2);
		CONTAINER.add(main_p);
		
		//Add Logo to Top Left in Side Panel
		logo = new JLabel(new ImageIcon("icon.png"));
		logo.setBounds(-10,0,150,150);
		side_p.add(logo);
		
		heading = new JLabel("Regiser For Olx!");
		Font f1 = new Font("Arial", Font.BOLD, 18);
		heading.setFont(f1);
		heading.setBounds(170,5,340,40);
		
		name_l = new JLabel("Name");
		name_l.setBounds(50, 70, 200, 30);

		name_t = new JTextField();
		name_t.setBounds(200, 70, 200, 30);

		user_l = new JLabel("Username");
		user_l.setBounds(50, 170, 200, 30);

		user_t = new JTextField();
		user_t.setBounds(200, 170, 200, 30);

		city_l = new JLabel("City");
		city_l.setBounds(50, 120, 200, 30);

		city_t = new JTextField();
		city_t.setBounds(200, 120, 200, 30);

		email_l = new JLabel("Email");
		email_l.setBounds(50, 320, 200, 30);

		email_t = new JTextField("example@gmail.com");
		email_t.setBounds(200, 320, 200, 30);

		pass_l = new JLabel("Password");
		pass_l.setBounds(50, 220, 200, 30);

		pass_p = new JPasswordField();
		pass_p.setBounds(200, 220, 200, 30);

		cpass_l = new JLabel("Confirm Password");
		cpass_l.setBounds(50, 270, 200, 30);

		cpass_p = new JPasswordField();
		cpass_p.setBounds(200, 270, 200, 30);

		dob_l = new JLabel("Date Of Birth");
		dob_l.setBounds(50, 370, 200, 30);

		dob_t = new JTextField("Year-Month-Date");
		dob_t.setBounds(200, 370, 200, 30);

		submit = new JButton("Submit");
		submit.setBounds(120, 450, 70, 25);
		submit.addActionListener(this);

		back = new JButton("Back");
		back.setBounds(250, 450, 70, 25);
		back.addActionListener(this);

		main_p.add(name_l);
		main_p.add(name_t);
		main_p.add(city_l);
		main_p.add(city_t);
		main_p.add(user_l);
		main_p.add(user_t);
		main_p.add(email_l);
		main_p.add(email_t);
		main_p.add(pass_p);
		main_p.add(pass_l);
		main_p.add(cpass_p);
		main_p.add(cpass_l);
		main_p.add(submit);
		main_p.add(back);
		main_p.add(dob_l);
		main_p.add(dob_t);
		main_p.add(heading);
		add(CONTAINER);

		setVisible(true);
		setResizable(false);
		setLocation(320,130);
		setSize(650, 650);
		setTitle("Register");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == submit) {
			String name = name_t.getText();
			String username1 = user_t.getText();
			String password1 = pass_p.getText();
			String dob = dob_t.getText();
			String email = email_t.getText();
			String city = city_t.getText();
			
			if (!pass_p.getText().equals(cpass_p.getText())) {
				JOptionPane.showMessageDialog(null, "Passwords do not match");
				
			}

			else if (name.isEmpty()
					|| username1.isEmpty()
					|| dob.isEmpty()
					|| email.isEmpty()
					|| city.isEmpty()
					|| password1.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Please Fill all Fields and Try again", "Failure", 2);
			}

			else {
				try {
					insert_into_database();
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(register.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (InstantiationException ex) {
					Logger.getLogger(register.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IllegalAccessException ex) {
					Logger.getLogger(register.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

		}

		if (ae.getSource() == back) {
			new loginScreen();
			dispose();
		}

	}
	

	public void insert_into_database() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Connection conn = null;

		try {
			String username = "root";
			String password = "root";
			String database = "olx";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/"
					+ database, username, password);

			Statement state = conn.createStatement();
			String name = name_t.getText();
			String username1 = user_t.getText();
			String password1 = pass_p.getText();
			String dob = dob_t.getText();
			String email = email_t.getText();
			String city = city_t.getText();

			String query = "INSERT INTO users(name,username,password,dob,email,city) VALUES ('"
					+ name
					+ "','"
					+ username1
					+ "','"
					+ password1
					+ "','"
					+ dob + "','" + email + "','" + city + "')";
			state.executeUpdate(query);
			state.close();
			
			//Closing this Windows, and Opening Log in Window
			dispose();
			JOptionPane.showMessageDialog(null, "Registration Successful. Log in to Continue.");
			new loginScreen();

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "error", 2);

		}
	}
}