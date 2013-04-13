import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class loginScreen extends JFrame implements ActionListener {
	
	JPanel p1;
	JLabel lname, lpasswd, lstring,logo;
	JTextField tname;
	JPasswordField passwd;
	JButton blogin, bregister;
	String name;
	String password;

	// Constructor
	loginScreen() {
		p1 = new JPanel();
		p1.setLayout(null);

		lstring = new JLabel("Login to Proceed!");
		lstring.setBounds(165, 20, 200, 15);

		lpasswd = new JLabel("Password");
		lpasswd.setBounds(50, 97, 200, 15);

		lname = new JLabel("User Name");
		lname.setBounds(50, 67, 200, 15);

		passwd = new JPasswordField(20);
		passwd.setBounds(130, 95, 200, 20);

		tname = new JTextField(10);
		tname.setBounds(130, 65, 200, 20);

		blogin = new JButton("Login");
		blogin.setBounds(230, 140, 80, 25);

		bregister = new JButton("Register");
		bregister.setBounds(100, 140, 80, 25);
		
		logo = new JLabel(new ImageIcon("olx.png"));
		logo.setBounds(55, 10, 50, 50);

		p1.add(logo);
		p1.add(lname);
		p1.add(lstring);
		p1.add(lpasswd);
		p1.add(blogin);
		p1.add(tname);
		p1.add(passwd);
		p1.add(bregister);

		add(p1);

		setVisible(true);
		setResizable(false); // prevents from changing dialog box
		setSize(400, 200);
		setTitle("Welcome to OLX");

		blogin.addActionListener(this);
		bregister.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // This is to make login screen appear in
										// the center of screen
	}
	
	
	
	public void actionPerformed(ActionEvent ea) {
		
		
		//Login button action
		if (ea.getSource() == blogin) {
			name = tname.getText();
			
			password= passwd.getText();
			
			
			if (name.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Please Fill all Fields and Try again", "Login Error", 2);
			}
			else{
				DBConnect();
			}
		}	
		//Register Button action
		else if(ea.getSource() == bregister){
			new register();	//Calling constructor of register class
			dispose();
		}
	}	
	
	public void DBConnect(){
		Connection conn = null;
		 
		try {
			// SETTINGS: userName, passWord and dataBase.
			String userName = "root";
			String passWord = "root";
			String dataBase = "olx";
 
			// Creating class.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
 
			// creating connection.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+dataBase, userName, passWord);
 
			// Do we have a connection?
			if (!conn.isClosed())
			{
				// We have a MySQL Connection.
				Statement s = conn.createStatement ( );
				s.executeQuery ("SELECT * FROM users where username = '"+name+"' and password = '"+password+"'");
				ResultSet rs = s.getResultSet( );
				if (rs.next()) // loop through rows of result set
				{	try{
					JOptionPane.showMessageDialog(null, "Congratulations!\nLogin Successful", "Success", 1);
					new options();
					dispose();
					
					throw null;
					
					}
					catch(Exception e){
						
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Login not Successful!\ntry again", "Login Error", 2);

				}
				rs.close(); // close result set
				s.close(); // close statement
			}
		}
		catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
		finally
		{
			// If we don't have a error, close the connection!
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException e)
			{
 
			}
		}
	
	}
}
	
	
