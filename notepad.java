import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java .applet.Applet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class notepad implements ActionListener{
    static int active=0;
    static int fsize=17;
    
    JFrame frame1;
    JMenuBar npMenuBar;
    JMenu file,edit,view,format,help;
    JMenuItem newm,open,exit,save,saveas,copy,paste,undo,redo,cut,fontfamily,fontsize,fontstyle,vhelp,abtntpd,status;
    JTextArea maintext;
    JTextField title;
    Font font1 ;
    JPanel bottom;
    JLabel details , pastecopy;
    JList familylist,stylelist,sizelist;
    JScrollPane sb;
    FileDialog fd;
    boolean flag;   
    String name ,path,n;
    
    String familyvalue[]={"Agency FB","Antiqua","Architect","Arial","Calibri","Comic Sans","Courier","Cursive","Harlow Solid","Impact","serif"};
    String sizevalue[]={"5","10","15","20","25","30","35","40","45","50","55","60","65","70"};
    int[] stylevalue={Font.PLAIN,Font.BOLD,Font.ITALIC};
    String [] stylevalues={"PLAIN","BOLD","ITALIC"};
    String ffamily,fsizestr,fstylestr;
    
    int fstyle;
    int cl;
    int linecount;
    String tle;
    String topicstitle="";
    JScrollPane sp;
    
    notepad(){
        frame1=new JFrame("            Document-1   Microsoft Word         ");
        font1 = new Font("Arial",Font.PLAIN , 17);
        bottom= new JPanel();
        details= new JLabel();
        pastecopy= new JLabel();
        familylist= new JList(familyvalue);
        stylelist=new JList(stylevalues);
        sizelist= new JList(sizevalue);
        
        familylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stylelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        bottom.add(details);
        
        maintext= new JTextArea();
        
        sb=new JScrollPane(maintext);
        maintext.setBounds(600, 600, 600, 600);
        
        maintext.setFont(font1);
        frame1.add(maintext);
        
        npMenuBar = new JMenuBar();
        file =new JMenu("File");
        edit =new JMenu("Edit");
        view =new JMenu("View");
        format =new JMenu("Format");
        help =new JMenu("Help");
        
        newm= new JMenuItem(" New        ctrl+n");
        open= new JMenuItem(" Open       ctrl+o");
        save= new JMenuItem(" Save       ctrl+s");
        saveas= new JMenuItem(" Save       ");
        exit= new JMenuItem(" Exit         ");
        undo= new JMenuItem(" Undo       ctrl+z");
        redo= new JMenuItem(" Redo       ctrl+y");
        cut= new JMenuItem(" Cut         ctrl+x");
        copy= new JMenuItem(" Copy       ctrl+c");
        paste= new JMenuItem(" Paste     ctrl+v");
        fontfamily= new JMenuItem(" Font Family ");
        fontstyle= new JMenuItem(" Font Style ");
        fontsize= new JMenuItem(" Font Size ");
        vhelp= new JMenuItem(" View Help ");
        abtntpd= new JMenuItem(" About Notepad ");
        status = new JMenuItem("  Status         ");  
        file.add(newm);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.addSeparator();
        file.add(exit);
        newm.add(new JSeparator(SwingConstants.VERTICAL));
        open.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.LINE_START);
        
        edit.add(undo);
        edit.add(redo);
        edit.addSeparator();
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        
        format.add(fontfamily);
        format.add(fontstyle);
        format.add(fontsize);
        
        view.add(status);
        
        help.add(vhelp);
        help.addSeparator();
        help.add(abtntpd);
        
        npMenuBar.add(file);
        npMenuBar.add(edit);
        npMenuBar.add(format);
        npMenuBar.add(view);
        npMenuBar.add(help);
        
        frame1.setJMenuBar(npMenuBar);
        frame1.add(bottom,BorderLayout.SOUTH);
        
        newm.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        save.addActionListener(this);
        saveas.addActionListener(this);
        fontfamily.addActionListener(this);
        fontsize.addActionListener(this);
        fontstyle.addActionListener(this);
        exit.addActionListener(this);
        status.addActionListener(this);
        
        frame1.setSize(1950,1080);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
        frame1.setLayout(null);
        frame1.setLocationRelativeTo(null);
      
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource()== newm )
 {
 frame1.setTitle("New Document.txt");
 maintext.setText(" ");
 }
 else if (ae.getSource()== copy)
 {
  String texts= maintext.getText();
  pastecopy.setText(texts);
  JOptionPane.showMessageDialog(null, "Copy Text "+texts);
 }
 else if(ae.getSource()== undo)
 {
  maintext.setText("");
  JOptionPane.showMessageDialog(null, "Removed");
 }
 else if (ae.getSource() == paste)
 {
  if(maintext.getText().length() != 0)
  {
   maintext.setText(maintext.getText());
  }
  else
  {
  maintext.setText(pastecopy.getText());
  }
 }
 else if(ae.getSource()== status)
 {
  try{
   if(active ==0)
   {
    File f = new File(tle+".txt");
    details.setText("Size: "+f.length());
   }
  }
  catch (Exception e)
  {
   
  }
 }
 else if (ae.getSource()== fontfamily)
  {

     JOptionPane.showConfirmDialog(null, familylist, "Choose Font Family", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  ffamily=String.valueOf(familylist.getSelectedValue());
  font1=new Font(ffamily,fstyle,fsize);
  maintext.setFont(font1);
  }
 else if (ae.getSource()== fontstyle)
  {

     JOptionPane.showConfirmDialog(null, stylelist, "Choose Font Style", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  fstyle=stylevalue[stylelist.getSelectedIndex()];
  font1=new Font(ffamily,fstyle,fsize);
  maintext.setFont(font1);
  }
 else if (ae.getSource()== fontsize)
  {

     JOptionPane.showConfirmDialog(null, sizelist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
  fsizestr=String.valueOf(sizelist.getSelectedValue());
  fsize =Integer.parseInt(fsizestr);
  font1=new Font(ffamily,fstyle,fsize);
  maintext.setFont(font1);
  }
 else if(ae.getSource()==exit)
  {
   frame1.dispose();
  }
 else if (ae.getSource()==save )
 {
     fd=new FileDialog(frame1,"Save",FileDialog.SAVE);
     if(flag==false)
     {
         fd.setVisible(true);
         name=fd.getFile();
         path=fd.getDirectory();
         String strr=maintext.getText();
         File f=new File(path,name);
         String str1=maintext.getText();
         try{
             FileOutputStream fos = new FileOutputStream(f);
                 char arr[];
                 arr=strr.toCharArray();
                 for(int i=0;i<arr.length;i++)
                 {
                     fos.write(arr[i]);
                 }   
            }
     catch(IOException e)
                         {
                         }
     }
         else{
                 System.out.println(n);
                 String strr1=maintext.getText();
                 File f = new File(n);
                 try
                    {
                      FileOutputStream fos = new FileOutputStream(f); 
                      char arr[];
                      arr=strr1.toCharArray();
                      for(int i=0;i<arr.length;i++)
                       {
                         try 
                          {
                           fos.write(arr[i]);
                          }
                           catch (IOException ex)
                            {
                            }
                        }
                    }  
                 catch(IOException e)
                  {
                  }
             }
 }
}
    
    public static void main(String[] args)
    {
        notepad m= new notepad();
        
    }
}
