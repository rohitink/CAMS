import javax.swing.*;
import java.awt.event.*;

public class options  implements MouseListener{
	JPanel p1;
	//JButton post_ad;
	JFrame f;
	JLabel post_l, browse_l;
	
		options()
		{
			p1 = new JPanel();
			p1.setLayout(null);
			f = new JFrame();
			post_l = new JLabel(new ImageIcon("post-ad.png"));
			browse_l = new JLabel(new ImageIcon("browse.png"));
			post_l.setBounds(-150,-150,600,850);
			browse_l.setBounds(150,-150,600,850);
			post_l.addMouseListener(this);
			browse_l.addMouseListener(this);
			
			p1.add(post_l);
			p1.add(browse_l);
			
			f.add(p1);
			f.setLocation(200,100);
			f.setVisible(true);
			f.setResizable(false); // prevents from changing dialog box
			f.setSize(650, 650);
			f.setTitle("Welcome to OLX");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		}
	
		
	@Override
	public void mouseClicked(MouseEvent ae) {
		if(ae.getSource()==post_l)
		{
			f.remove(p1);
			new PostAd(f);
			
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
