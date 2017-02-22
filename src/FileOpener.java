import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.FileDialog;
import java.awt.TextComponent;
import java.io.*;

public class FileOpener{

	//private JFileChooser chooser = new JFileChooser();
	private JFrame f = new JFrame();
	private FileDialog fd ;
	
     public FileOpener(JTabbedPane tp,NotePad frm) throws IOException {
		
    	// int result = chooser.showOpenDialog(frm);
    	// File file = chooser.getSelectedFile();
    	
    	 try
    	 {
    	 fd = new FileDialog(f, "OPEN",FileDialog.LOAD);
    	 fd.setSize(400,400);
    	 fd.setLocationRelativeTo(frm);
    	 fd.setVisible(true);
    	 
    	 String file = fd.getDirectory()+fd.getFile();
    	 int flag=0;
    	 
    	 for(int i=0 ;i<tp.getTabCount();i++)
    	 {
             TxtArea t = (TxtArea) ((JScrollPane)tp.getComponentAt(i)).getNextFocusableComponent();
             if( t.FilePath_Read()!= null && t.FilePath_Read().equals(file))
             {
            	flag=1;
            	tp.setSelectedIndex(i);
            	break;
             }
    	 }
    	// if(result == chooser.APPROVE_OPTION)
    	 if(fd.getFile()!=null && flag!=1)
    	 {
    		TextArea_Tab temp = new TextArea_Tab(tp,frm,false);
    		String str="";
    		StringBuffer sb = new StringBuffer();
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				try
				{
					while((str = br.readLine())!=null)
					{
						sb.append(str+"\n");
					}
				}
				catch(Exception e){}
				
				finally {
					br.close();
				}
    		 temp.ta.setText(sb.toString());
    		 temp.ta.FileData_Set(file,"  "+fd.getFile());
    		 temp.Title.setText("  "+fd.getFile());
    	 }
    	}
    	catch(Exception e)
    	{}
	}
	
}
