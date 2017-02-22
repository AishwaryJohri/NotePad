 import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Font_Set extends JFrame implements ItemListener,ActionListener {

	JComboBox<String> FontSty,FontTy;
	JComboBox<String> FontSiz;
	int FontType;
	String FontStyle;
	int FontSize;
	Panel p1,p2;
	String FSty[]={"Select","MONOSPACED","SANS_SERIF","SERIF"};
	String FType[]={"Select","BOLD","ITALIC","ROMAN_BASELINE"};
    String FSize[]={"10","14","18","22","28","32","36","40","44","46","50","54","58","62","66","70","84","88","92","96","100"};
    JLabel l = new JLabel("Test OF Change"); 
	Font f ;
	JButton b = new JButton("OK");
	Component c;
	
	public Font_Set(String title,JFrame frm,Component c)
	{
		this.c=c;
		setSize(400,350);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(frm);
		
		f = c.getFont();
		
		b.addActionListener(this);
		
	    p1 = new Panel();
	    p2 = new Panel();
		
	    setLayout(new BorderLayout());
	    
	    p1.setSize(400,200);
	    
	    p2.setSize(400,300);
	    p2.setBackground(Color.WHITE);
	    

	    p2.add(l);
	    
	    FontSty = new JComboBox<String>(FSty);
	    FontTy = new JComboBox<String>(FType);
	    FontSiz = new JComboBox<String>(FSize);
	    
	    p1.add(FontSty);
	    p1.add(FontTy);
	    p1.add(FontSiz);
	    
	    add(p1,BorderLayout.NORTH);
	    add(p2,BorderLayout.CENTER);
	    add(b,BorderLayout.SOUTH);
	    
	    FontSty.addItemListener(this);
	    FontTy.addItemListener(this);
	    FontSiz.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		FontType  =  FontTy.getSelectedIndex();
		FontStyle = (String)FontSty.getSelectedItem();
		FontSize =   Integer.parseInt((String)FontSiz.getSelectedItem());
		f = new Font(FontStyle,FontType,FontSize);
		l.setFont(f);
		l.setText(l.getText());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	   c.setFont(f);
	   dispose();
	}
		
}
