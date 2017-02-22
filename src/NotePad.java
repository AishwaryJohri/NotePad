
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NotePad extends JFrame implements ActionListener {

	NotePad frm ;
	
	JMenuBar bar;
    JMenu File,Edit,View,Format,Help;
    JMenu New,Theme;
    
    JMenuItem Files,Project;
	JMenuItem Open,Save,SaveAs,Exit;
	JMenuItem Cut,Copy,Paste,Undo,Redo;
	JMenuItem font;
	JCheckBoxMenuItem LineWrap;
	
	JTabbedPane tp;
	int TabCount = 1;
	
	JMenuItem darktheme,codertheme,classictheme,brighttheme,normaltheme,customtheme;
	
	JScrollPane pane;
	static JLabel  col_row_la = new JLabel("COL : 0    ROW : 0       ");
	
	String cutout = "";
	int fst,lst;
	
    Color Hover = Color.black;
    Color Unhover = Color.black;
	
	public NotePad(String title) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(title);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frm = this;
		try{
			StartUp s = new StartUp(this);
			s.setVisible(true);
			
			Thread.sleep(500);
			
			s.dispose();
		   }
		catch(Exception e){};
		
		setSize(getToolkit().getScreenSize());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		bar = new JMenuBar();
		
		File = new JMenu("File");    
		Edit = new JMenu("Edit");
		Format = new JMenu("Format");
		View = new JMenu("View");
		Help = new JMenu("Help");
		
		bar.add(File);
		bar.add(Edit);
		bar.add(Format);
		bar.add(View);
		bar.add(Help);
		
		New = new JMenu("New");    	         File.add(New);       File.addSeparator();
		Files = new JMenuItem("File");       New.add(Files);
		Project = new JMenuItem("Project");  New.add(Project);
		
		Open = new JMenuItem("Open");        File.add(Open);      File.addSeparator();
		Save = new JMenuItem("Save");        File.add(Save);
		SaveAs = new JMenuItem("SaveAs");    File.add(SaveAs);    File.addSeparator();
		Exit = new JMenuItem("Exit");        File.add(Exit);
		
	    Cut = new JMenuItem("Cut");          Edit.add(Cut);		  Cut.setEnabled(false);
	    Copy = new JMenuItem("Copy");        Edit.add(Copy);      Copy.setEnabled(false);     Edit.addSeparator(); 
	    Undo = new JMenuItem("Undo");        Edit.add(Undo);	  
	    Redo = new JMenuItem("Redo");        Edit.add(Redo);
	    Paste = new JMenuItem("Paste");      Edit.add(Paste);     Paste.setEnabled(false);
	    
	    font = new JMenuItem("Font");                  Format.add(font);
	    LineWrap = new JCheckBoxMenuItem("LineWrap");  Format.add(LineWrap);
	    
	    
	    Theme = new JMenu("Themes");                                View.add(Theme);
	    darktheme=new JMenuItem("DarkTheme");                       Theme.add(darktheme);
		codertheme=new JMenuItem("CoderTheme");                      Theme.add(codertheme);
		classictheme=new JMenuItem("BrightTheme");                   Theme.add(classictheme);
		brighttheme=new JMenuItem("ClassicTheme");                   Theme.add(brighttheme); 
		normaltheme=new JMenuItem("NormalTheme");                    Theme.add(normaltheme);
		customtheme=new JMenuItem("CustomTheme");                    Theme.add(customtheme);
		
		
	    setJMenuBar(bar);
	    
	   tp = new JTabbedPane();
	   new TextArea_Tab(tp,frm,true);
	   add(tp);
	   
	   
	   col_row_la.setHorizontalAlignment(SwingConstants.RIGHT);
	   add( col_row_la,BorderLayout.SOUTH);
	   
	   new O_Off(this,tp);
	   
	   tp.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e)
		   {
			   if(tp.getTabCount()!=0)
			   {
				   TxtArea t = (TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent();
				   int cursor,row=0,col=0;
				   try{
				   cursor = t.getCaretPosition();
				   row = t.getLineOfOffset(cursor);
				   col= cursor-t.getLineStartOffset(row);
				   }
				   catch(Exception ee){}
				   col_row_la.setText("COL : "+col+"    ROW : "+row+"       ");
				   JPanel p =(JPanel) tp.getTabComponentAt(tp.getSelectedIndex());
					JLabel l = (JLabel) p.getComponent(0);
				   frm.setTitle(l.getText().substring(2));
			   }
				   col_row_la.setText("COL : 0    ROW : 0       ");
		   }
	});
		File.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				File.setForeground(Hover);
			}
			public void mouseExited(MouseEvent e)
			{
				File.setForeground(Unhover);
			}
		});
		
		Edit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				Edit.setForeground(Hover);
			}
			public void mouseExited(MouseEvent e)
			{
				Edit.setForeground(Unhover);
			}
		});
		
		View.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e)
			{
				View.setForeground(Hover);
			}
			public void mouseExited(MouseEvent e)
			{
				View.setForeground(Unhover);
			}
		});
		
		Format.addMouseListener(new MouseAdapter() {			
			public void mouseEntered(MouseEvent e)
			{
				Format.setForeground(Hover);
			}
			public void mouseExited(MouseEvent e)
			{
				Format.setForeground(Unhover);
			}
		});	
		
		Help.addMouseListener(new MouseAdapter() {			
			public void mouseEntered(MouseEvent e)
			{
				Help.setForeground(Hover);
			}
			public void mouseExited(MouseEvent e)
			{
				Help.setForeground(Unhover);
			}
		});	
		
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e)
			{
				Exit.doClick();
			/*	 int co=3;
				 int i=0;
				while(i<tp.getTabCount())
				{
					co=3;
					tp.setSelectedIndex(0);
					JPanel p =(JPanel) tp.getTabComponentAt(tp.getSelectedIndex());
					JLabel l = (JLabel) p.getComponent(0);
					TxtArea t = (TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent();
					if(t.changeFlag)
					{
					   co = JOptionPane.showConfirmDialog(frm,l.getText().substring(2)+" :Save it ????");
					   if(co==0)
					      new FileSaver(tp, frm,l,true);
					}
					if(co!=2)
						   tp.remove(0);
					   else
						   break;
				}
				if(co!=2)
				  System.exit(2210);
				  */
			}
		});
		
		
		Files.addActionListener(this);
		Open.addActionListener(this);
	    font.addActionListener(this);
	    LineWrap.addActionListener(this);
	    Save.addActionListener(this);
	    Exit.addActionListener(this);
	    
	    Files.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
		font.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
		LineWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
	    
	    Cut.addActionListener(this);
        Copy.addActionListener(this);	
        Paste.addActionListener(this);
        
        Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        Undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
        Redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,KeyEvent.CTRL_DOWN_MASK));
	    
	    darktheme.addActionListener(this);
		codertheme.addActionListener(this);
		brighttheme.addActionListener(this);
		normaltheme.addActionListener(this);
		classictheme.addActionListener(this);
		customtheme.addActionListener(this);
		
	    
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{	
		 NotePad obj =	new NotePad("WildEditor");
		 ImageIcon i =new ImageIcon("Icon.png");
		 obj.setIconImage(i.getImage());
		 obj.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		String str = e.getActionCommand();
		
		if(e.getSource()==font)
			new Font_Set("Font Schemes",this,(TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent());
		
		if(e.getSource()==Exit)
		  {
			 int co=3;
			 int i=0;
			while(i<tp.getTabCount())
			{
				co=3;
				tp.setSelectedIndex(0);
				JPanel p =(JPanel) tp.getTabComponentAt(tp.getSelectedIndex());
				JLabel l = (JLabel) p.getComponent(0);
				TxtArea t = (TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent();
				if(t.changeFlag)
				{
				   co = JOptionPane.showConfirmDialog(frm,l.getText().substring(2)+" :Save it ????");
				   if(co==0)
				      new FileSaver(tp, frm,l,true);
				}
				if(co!=2)
					   tp.remove(0);
				   else
					   break;
			}
			if(co!=2)
			  System.exit(2210);
		}
		
		if(e.getSource()==Files)
		    new TextArea_Tab(tp,frm,true);
		
		if(e.getSource()==Open)
			{
				try
				{
					new FileOpener(tp,frm);
				}
			 catch (IOException e1) {}
			}
		
		if(e.getSource()==Save)
		{
			JPanel p =(JPanel) tp.getTabComponentAt(tp.getSelectedIndex());
			JLabel l = (JLabel) p.getComponent(0);
			new FileSaver(tp, frm,l,false);
			frm.setTitle(l.getText().substring(2));
		}
		
		if(e.getSource()==Cut)
		{
			cutout =((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectedText();
			if(cutout!=null)
			{
				Paste.setEnabled(true);
				fst = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectionStart();
				lst = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectionEnd();
				String TempStr = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getText();
				((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).setText(TempStr.substring(0, fst)+TempStr.substring(lst));
				((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).setCaretPosition(fst);
			}
		}
		
		if(e.getSource()==Copy)
		{
			cutout = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectedText();
			if(cutout!=null)
			{
				Paste.setEnabled(true);
			    fst = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectionStart();
			    lst = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectionEnd();
			    ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).setCaretPosition(lst);
			}
		}
		
		if(e.getSource()==Paste)
		{
			String TempStr = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getText();
			fst = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectionStart();
			((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).setText(TempStr.substring(0, fst)+cutout+TempStr.substring(fst));
			((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).setCaretPosition(fst+cutout.length());
		}
		
		if(e.getSource()==LineWrap)
		{
			if(((JCheckBoxMenuItem)e.getSource()).getState())
			{
				 int i=0;
					while(i<tp.getTabCount())
					{
				         ((TxtArea)((JScrollPane)tp.getComponentAt(i)).getNextFocusableComponent()).setLineWrap(true);
				         i++;
					}
			}
			else
				{
				int i=0;
				while(i<tp.getTabCount())
				{
			         ((TxtArea)((JScrollPane)tp.getComponentAt(i)).getNextFocusableComponent()).setLineWrap(false);
			         i++;
				}
				}
		}
		
		if(str.equalsIgnoreCase("DarkTheme"))
		{
			bar.setBackground(Color.BLACK);
			File.setFont(null);
			Edit.setFont(null);
			Format.setFont(null);
			View.setFont(null);
			Help.setFont(null);
			File.setForeground(Color.ORANGE);
			Edit.setForeground(Color.ORANGE);
			Format.setForeground(Color.ORANGE);
			View.setForeground(Color.ORANGE);
			Help.setForeground(Color.ORANGE);
			tp.getSelectedComponent().setBackground(Color.BLACK);		
			tp.getSelectedComponent().setForeground(Color.ORANGE);
		//	Hover = Color.green;
		//	Unhover = Color.orange;
			
		}
		
		if(str.equalsIgnoreCase("CoderTheme"))
		{
			bar.setBackground(Color.BLACK);
			File.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
			Edit.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
			Format.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
			View.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
			Help.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
			File.setForeground(Color.GREEN);
			Edit.setForeground(Color.GREEN);
			Format.setForeground(Color.green);
			View.setForeground(Color.green);
			Help.setForeground(Color.green);
			tp.getSelectedComponent().setBackground(Color.BLACK);		
			tp.getSelectedComponent().setForeground(Color.green);
		//	Hover = Color.orange;
		//	Unhover = Color.green;
		}
		
		if(str.equalsIgnoreCase("BrightTheme"))
		{
			bar.setBackground(Color.ORANGE);
			File.setForeground(Color.BLACK);
			Edit.setForeground(Color.BLACK);
			Format.setForeground(Color.BLACK);
			View.setForeground(Color.BLACK);
			Help.setForeground(Color.BLACK);
			tp.getSelectedComponent().setBackground(Color.ORANGE);		
			tp.getSelectedComponent().setForeground(Color.BLACK);
		//	Hover = Color.red;
		//	Unhover = Color.black;
		}
		
		if(str.equalsIgnoreCase("ClassicTheme"))
		{	bar.setBackground(Color.blue);
			File.setFont(null);
			Edit.setFont(null);
			Format.setFont(null);
			View.setFont(null);
			Help.setFont(null);
			File.setForeground(null);
			Edit.setForeground(null);
			Format.setForeground(null);
			View.setForeground(null);
			Help.setForeground(null);
			tp.getSelectedComponent().setBackground(Color.GRAY);		
			tp.getSelectedComponent().setForeground(Color.white);
		//	Hover = Color.white;
		//	Unhover = Color.black;
		}
		
		if(str.equalsIgnoreCase("NormalTheme"))
		{
			bar.setBackground(null);
			File.setFont(null);
			Edit.setFont(null);
			Format.setFont(null);
			View.setFont(null);
			Help.setFont(null);
			File.setForeground(null);
			Edit.setForeground(null);
			Format.setForeground(null);
			View.setForeground(null);
			Help.setForeground(null);
			tp.getSelectedComponent().setBackground(Color.white);		
			tp.getSelectedComponent().setForeground(Color.black);
		//	Hover = Color.orange;
		//	Unhover = Color.black;
		}
	}

}
