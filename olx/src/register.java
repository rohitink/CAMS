/*-----------Let's START-------------*/
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
	JLabel user_l, email_l, city_l, name_l, pass_l, cpass_l, dob_l, border;
	JTextField user_t, email_t, city_t, name_t, dob_t;
	JPasswordField pass_p, cpass_p;
	JPanel p1;

	public register() {

		p1 = new JPanel();
		p1.setLayout(null);

		name_l = new JLabel("Name");
		name_l.setBounds(50, 50, 200, 30);

		name_t = new JTextField();
		name_t.setBounds(200, 50, 200, 30);

		user_l = new JLabel("Username");
		user_l.setBounds(50, 150, 200, 30);

		user_t = new JTextField();
		user_t.setBounds(200, 150, 200, 30);

		city_l = new JLabel("City");
		city_l.setBounds(50, 100, 200, 30);

		city_t = new JTextField();
		city_t.setBounds(200, 100, 200, 30);

		email_l = new JLabel("Email");
		email_l.setBounds(50, 300, 200, 30);

		email_t = new JTextField("example@gmail.com");
		email_t.setBounds(200, 300, 200, 30);

		pass_l = new JLabel("Password");
		pass_l.setBounds(50, 200, 200, 30);

		pass_p = new JPasswordField();
		pass_p.setBounds(200, 200, 200, 30);

		cpass_l = new JLabel("Confirm Password");
		cpass_l.setBounds(50, 250, 200, 30);

		cpass_p = new JPasswordField();
		cpass_p.setBounds(200, 250, 200, 30);

		dob_l = new JLabel("Date Of Birth");
		dob_l.setBounds(50, 350, 200, 30);

		dob_t = new JTextField("Year-Month-Date");
		dob_t.setBounds(200, 350, 200, 30);

		submit = new JButton("Submit");
		submit.setBounds(120, 430, 70, 25);
		submit.addActionListener(this);

		back = new JButton("Back");
		back.setBounds(250, 430, 70, 25);
		back.addActionListener(this);

		p1.add(name_l);
		p1.add(name_t);
		p1.add(city_l);
		p1.add(city_t);
		p1.add(user_l);
		p1.add(user_t);
		p1.add(email_l);
		p1.add(email_t);
		p1.add(pass_p);
		p1.add(pass_l);
		p1.add(cpass_p);
		p1.add(cpass_l);
		p1.add(submit);
		p1.add(back);
		p1.add(dob_l);
		p1.add(dob_t);
		add(p1);

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

			// this is the place where we make use of sql commands

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

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "error", 2);

		}
	}
}