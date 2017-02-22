import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class O_Off {

	NotePad pad;
	JTabbedPane tp;
	
	public O_Off(NotePad pad,JTabbedPane tp) {
		this.pad=pad;
		this.tp=tp;
		new editTh(pad,tp);
		new saveTh(pad,tp);
	}
}


class editTh implements Runnable
{
	NotePad pad;
	JTabbedPane tp;
	
	public editTh(NotePad pad,JTabbedPane tp) {
		this.pad=pad;
		this.tp=tp;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if( tp.getTabCount()!=0 && ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getSelectedText()!=null)
			{
				pad.Cut.setEnabled(true);
				pad.Copy.setEnabled(true);
			}
			else
			{
				pad.Cut.setEnabled(false);
				pad.Copy.setEnabled(false);
			}
		}
	}
}

class saveTh implements Runnable
{
	NotePad pad;
	JTabbedPane tp;
	public saveTh(NotePad pad,JTabbedPane tp) {
		this.pad=pad;
		this.tp=tp;
		new Thread(this).start();
	}

	@Override
	public void run() {
		
		while(true)
		{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(tp.getTabCount()!=0 )
				{
					pad.font.setEnabled(true);
					TxtArea ta=((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent());
					JPanel pa =(JPanel) tp.getTabComponentAt(tp.getSelectedIndex());
					JLabel la = (JLabel) pa.getComponent(0);
					pad.setTitle(la.getText().substring(2));
					if( ta.changeFlag)
					{
						pad.Save.setEnabled(true);
						JPanel p =(JPanel) tp.getTabComponentAt(tp.getSelectedIndex());
						JLabel l = (JLabel) p.getComponent(0);
						l.setText("*"+l.getText().substring(1));
					}
					else
					{
						pad.Save.setEnabled(false);
					}
			     }
				else{
					pad.setTitle("WildEditor");
					pad.font.setEnabled(false);
				}
		}
		
	}
}
