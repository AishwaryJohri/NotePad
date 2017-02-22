import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartUp extends JFrame {

	public StartUp(JFrame c)
	{
		setSize(600,400);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(c);
		//                                setLayout(new BorderLayout()) -> instead of setLayout(null) ;     //this also works
		JLabel l = new JLabel(new ImageIcon("Ed.jpg"));
		setLayout(null);                     
		l.setBounds(0, 0, 600,400);         
		add(l);
	}	
}
