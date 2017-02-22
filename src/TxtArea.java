import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

public class TxtArea extends JTextArea
{
	static int i=1;
	int col,row,cursor;
	private String FileLoc = null;
	private String FileName = null;
	Boolean changeFlag = false;
	
	
	
	public TxtArea() {
		this.addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent e)
        	{
               changeFlag=true;
               getCoord();
        	}
		});
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e)
			{
				getCoord();
			}
		});
	}
	
   	public String FilePath_Read()
   	{
   		return FileLoc ;
   	}
   	
   	public String FileName_Read()
   	{
   		return FileName;
   	}
   	
   	public void FileData_Set(String path,String name)
   	{
   		FileName = name;
   		FileLoc = path;
   	}
   	
   	public void getCoord()
	{
   		try
   		{
			cursor = this.getCaretPosition();
			row = this.getLineOfOffset(cursor);
			col = cursor - this.getLineStartOffset(row);
   		}
   		catch(Exception e){}
   		finally {
   			NotePad.col_row_la.setText("COL : "+col+"    ROW : "+row+"       ");
		}
	}
   	
}
