import java.awt.FileDialog;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class FileSaver {

	//private JFileChooser chooser = new JFileChooser();
	public int fs = 0;
	private JFrame f = new JFrame();
	private FileDialog fd = new FileDialog(f,"Saver", FileDialog.SAVE);
	
    public FileSaver(JTabbedPane tp,JFrame frm,JLabel Title,Boolean Save_set) 
    {
		
	   //int result = chooser.showSaveDialog(frm);
	   //File file = chooser.getSelectedFile();
    	
    	TxtArea t = (TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent();
    	if(t.changeFlag||t.FileName_Read()==null)
    	{
	    	String file = "";
	    	String str_File = null ;
	    	
		   	if(Save_set||t.FilePath_Read() == null )
		   	{
		   	 fd.setSize(400,400);
		   	 fd.setLocationRelativeTo(frm);
		   	 fd.setVisible(true);
		   	 
		   	 String str_Dir = fd.getDirectory();
		   	  str_File =  fd.getFile();
		   	  file = str_Dir+str_File;
		   	  System.out.println(str_File);
		   	}
		   	else 
		   	{
		   		str_File=t.FileName_Read();
		   		file = t.FilePath_Read();
		   	}
		   	 // if(result == chooser.APPROVE_OPTION)
		   	if(str_File!=null)
					 {
				        String str = new String();
				        str = ((TxtArea)((JScrollPane)tp.getSelectedComponent()).getNextFocusableComponent()).getText();
				        BufferedWriter bw = null;
				       
				  try {
				  	 
				  /*	
				     if (!file.exists()) 
				  	  {
				  	     file.createNewFile();
				  	  }
				  	  
	              */
					  
					  Title.setText(" "+str_File);
				  	  FileWriter fw = new FileWriter(file);
				  	  t.FileData_Set(file,str_File);
				  	  bw = new BufferedWriter(fw);
				  	  bw.write(str);
				        }
				        
				        catch (IOException ioe) 
				        {
				  	       ioe.printStackTrace();
				  	    }
				  	finally
				  	{ 
				  	   try
				  	   {
				  	      if(bw!=null)
				  		 bw.close();
				  	   }
				  	   catch(Exception ex) 
				  	    {}
				  	}
				  t.changeFlag=false;
				   	Title.setText("  "+Title.getText().substring(2));
				}
    	}
    }
}
