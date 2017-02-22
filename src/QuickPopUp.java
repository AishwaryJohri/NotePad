import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class QuickPopUp extends JPopupMenu {

	JMenuItem m1,m2,m3,m4;
	
	public QuickPopUp(Component f,int x,int y,JFrame frm) {
		
		 setPopupSize(150,150);
		 setBackground(Color.black);
		 setForeground(Color.YELLOW);
		
		
		m1 = new JMenuItem("Paste");
		m2 = new JMenuItem("Cut");
		m3 = new JMenuItem("Copy");
		m4 = new JMenuItem("Font");
		
		m1.setBackground(Color.black);
		m1.setForeground(Color.YELLOW);
		m2.setBackground(Color.black);
		m2.setForeground(Color.YELLOW);
		m3.setBackground(Color.black);
		m3.setForeground(Color.YELLOW);
		m4.setBackground(Color.black);
		m4.setForeground(Color.YELLOW);
		
		 add(m1); add(m2); add(m3); addSeparator(); add(m4);
		
		 show(f, x, y);
		
		m4.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e)
			{
				  new Font_Set("Font Schemes",frm,f);
			}
		
		});
		
	}
	
}
