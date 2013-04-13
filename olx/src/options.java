import javax.swing.*;
import java.awt.event.*;

public class options extends JFrame implements ActionListener{
	JPanel p1;
	JButton post_ad;
	
	options()
	{
		
		p1.setLayout(null);
		
		post_ad = new JButton("Post Ad");
		post_ad.setBounds(30,30,40,40);
		post_ad.addActionListener(this);
		
		p1.add(post_ad);
		add(p1);
		setVisible(true);
		setResizable(false); // prevents from changing dialog box
		setSize(650, 650);
		setTitle("Welcome to OLX");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==post_ad){
			new PostAd();
		}
		
	}

}
