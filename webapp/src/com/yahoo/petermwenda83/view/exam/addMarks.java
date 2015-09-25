/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.view.exam;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yahoo.petermwenda83.view.MainWindow;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class addMarks extends JInternalFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7429249620324909894L;
	private JLabel lblexamtype, lblyear, lblterm, lblclass,lblsubject,lbloutof,lbldescr;
	private JLabel lbmessage,lbexam, lbyear,  lbterm, lbclass,  lbsubject, lboutof;
	 private JButton Next,Cancel;
	 private JPanel panel1,  panel2;
	 
	// private static ExamDAO examDAO; 

	 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * 
	 */
	 //examtype,year,term,clasz,subject,outof,descr//7
	public addMarks(String examtype,String year,String term,String room,String subject,String outof,String descr) {
		 super("Add Marks ", false, true, false, true);
		 
		 lblexamtype = new  JLabel();
		 lblyear  = new  JLabel();
		 lblterm  = new  JLabel();
		 lblclass  = new  JLabel();
		 lblsubject  = new  JLabel();
		 lbloutof  = new  JLabel();
		 lbldescr  = new  JLabel();
		
		 lbmessage = new  JLabel("You Have Selected The Following:");
		 
		 
		 lbyear = new  JLabel("YEAR:");
		 lbterm  = new  JLabel("TERM:");
		 lbclass  = new  JLabel("CLASS:");
		 lbexam  = new  JLabel("EXAM:");
		 lbsubject = new  JLabel("SUBJECT");
		 lboutof = new  JLabel("OUToF");
		
		 lblyear.setText(year);
		 lblterm.setText(term);
		 lblclass.setText(room);
		 
		 lblexamtype.setText(examtype);
		 lbldescr.setText("("+descr+")");
		 
		 lblsubject.setText(subject);
		 lbloutof.setText(outof);
		
		 Next = new JButton("Add");
		 Cancel = new JButton("Cancel");
		 
		 
		 // examDAO = ExamDAO.getInstance(); 
		 
		 
		 
	     panel1 = new JPanel();
	     panel1.setLayout(null); 
	     panel2 = new JPanel();
	     panel2.setLayout(null); 
	     
	     panel1.setBounds(0, 0, 500, 500); // x, y, width, height
	     panel2.setBounds(10, 10, 450, 400); 
	     
	     
	     //lbmessage,lbexam,lbyear,  lbterm, lbclass,  lbsubject, lboutof;
	     lbmessage.setBounds( 20,10,   300, 30);	
	     
	     lbyear.setBounds(20,40,       80, 30);
	     lbterm.setBounds(20,70,       80, 30);
	     lbclass.setBounds(20,100,     80, 30);
	     lbexam.setBounds(20,130,     200, 30);
	     lbsubject.setBounds(20,160,  100, 30);
	     lboutof.setBounds(20, 190,    80, 30);
	    
	     // lblexamtype, lblyear, lblterm, lblclass,lblsubject,lbloutof,lbldescr;
	     lblyear.setBounds(130, 40,        80, 30);	
	     lblterm.setBounds(130, 70,       80, 30);
	     lblclass.setBounds(130, 100,     100, 30);	 
	     lblexamtype.setBounds(130,130,    80, 30);
	     lbldescr.setBounds(180,  130,     200, 30);
	     lblsubject.setBounds(130, 160,   100, 30);
	     lbloutof.setBounds(130, 190,      80, 30);
	    
	  
	     Next.setBounds(20, 230, 100, 30);
	     Cancel.setBounds(150, 230, 100, 30);
	     
	     ButtonListener listener = new ButtonListener();
	     Next.addActionListener(listener);
	     Cancel.addActionListener(listener);
	     
	     // lblexamtype, lblyear, lblterm, lblclass,lblsubject,lbloutof,lbldescr;
		 panel2.add(lblexamtype);
		 panel2.add(lblyear);
		 panel2.add(lblterm);
		 panel2.add(lblclass);
		 panel2.add(lblsubject);
		 panel2.add(lbloutof);
		 panel2.add(lbldescr);
		 
		 panel2.add(Next);
		 panel2.add(Cancel);
		 
		 //lbmessage,lbexam,lbyear,  lbterm, lbclass,  lbsubject, lboutof;
		 panel2.add(lbmessage);
		 panel2.add(lbexam);
		 panel2.add(lbyear);
		 panel2.add(lbterm);
		 panel2.add(lbclass);
		 panel2.add(lbsubject);
		 panel2.add(lboutof);
		
		
		 
	     panel1.add(panel2);
	        
	     add(panel1);
	     setSize(500, 500);//width, height
	     setLocation((screen.width - 500) / 2, ((screen.height - 700) / 2));
	}
	
	 

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 
			 
			   if (e.getSource() == Next) {
				   String descr,term,room,subject,exam;
				   descr = lbldescr.getText();
			       term = lblterm.getText();
			       room =lblclass.getText();
			       subject =lblsubject.getText(); //
			       exam = lblexamtype.getText();
			      
				   marksView frm = new marksView(descr, term, room,subject,exam);
			        MainWindow.desktop.add(frm);
			        frm.setVisible(true);
			        try {
			            frm.setSelected(true);
			        } catch (Exception ex) {
			        }
			        setVisible(false);
			        
               	 }
               
			    if(e.getSource() == Cancel){
			 dispose();
			  }
	} //end ButtonListener

	
	
		}
		 
	
}
