import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class TextArea_Tab {

	static int i=1,j=1;
	JLabel Title;
	JLabel Close;
	TxtArea ta;
	JScrollPane Pane;
	JPanel Title_Panel;
	NotePad frm;
			
	public TextArea_Tab(JTabbedPane tp,NotePad frm,Boolean title) {
		try {
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		this.frm=frm;
		j++;
		if(title)
			Title = new JLabel("  New "+i++);
		else 
			Title = new JLabel("  New "+i);
		if(tp.getTabCount()==0)
			frm.setTitle(Title.getText());
		Title.setSize(20,10);
		Title.setBackground(null);                  
		Title.setBorder(null);
		
		Close = new JLabel("X");
		Close.setForeground(new Color(175, 52, 16));
		Close.setSize(10, 10);
		
		Title_Panel =new JPanel();
		Title_Panel.setOpaque(false);
		Title_Panel.setLayout(new FlowLayout());
		
		Title_Panel.add(Title);
		Title_Panel.add(Close);
		
		ta = new TxtArea();
		Pane = new JScrollPane(ta);
		
		Pane.setNextFocusableComponent(ta);
		
		tp.addTab(null,null, Pane,"Tab "+(j-1));
    	tp.setTabComponentAt(tp.getTabCount()-1, Title_Panel);
    	
    	
    	Close.addMouseListener(new MouseAdapter() {
    		
    		public void mouseEntered(MouseEvent e)
    		{
    			Close.setForeground(Color.red);
    		}
    		
    		public void mouseExited(MouseEvent e)
    		{
    			Close.setForeground(new Color(175, 52, 16));
    		}
    		
    		public void mouseReleased(MouseEvent e)
    		{
    			int i=0;
    			if(ta.changeFlag)
				{
			      i = JOptionPane.showConfirmDialog(frm,"Want to Save This File . . .");
			     if(i==0)
				new FileSaver(tp, frm,Title,false);		
				}
    			if(i==0||i==1)
				 {
    				tp.remove(tp.indexOfComponent(Pane));
    				NotePad.col_row_la.setText("COL : 0    ROW : 0       ");
				 }
    		}
		});
    	
    	ta.addMouseListener(new MouseAdapter() {
    		
			public void mousePressed(MouseEvent e)
			{
				if(e.getButton()==MouseEvent.BUTTON3)
				{
					new QuickPopUp(ta,e.getX(),e.getY(),frm);
				}
			}
		});
    	
	}
	
}
