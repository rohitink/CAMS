import javax.swing.*;
import java.awt.event.*;

public class options  implements ActionListener{
	JPanel p1;
	JButton post_ad;
	JFrame f;
	
	options()
	{
		p1 = new JPanel();
		p1.setLayout(null);
		f = new JFrame();
		
		post_ad = new JButton("Post Ad");
		post_ad.setBounds(30,30,40,40);
		post_ad.addActionListener(this);
		
		p1.add(post_ad);
		f.add(p1);
		f.setLocation(200,100);
		f.setVisible(true);
		f.setResizable(false); // prevents from changing dialog box
		f.setSize(650, 650);
		f.setTitle("Welcome to OLX");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==post_ad){
			f.remove(p1);
			new PostAd(f);
			
		
		}
		
	}

}
