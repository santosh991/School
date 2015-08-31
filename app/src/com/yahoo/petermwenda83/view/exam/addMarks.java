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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.yahoo.petermwenda83.model.exam.ExamDAO;
import com.yahoo.petermwenda83.view.MainWindow;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class addMarks extends JInternalFrame{

	
	private JLabel lblexamtype,lblyear,lblterm,lblclass,lbloutof,lbldescr;
	private JLabel lblsubject,lbterm,lbexam,lboutof;
	private JComboBox<String>  cmbsubject;
	 private JButton Next,Cancel;
	 private JPanel panel1,  panel2;
	 
	 private static ExamDAO examDAO; 

	 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * 
	 */
	public addMarks(String examtype,String year,String term,String clasz,String outof,String descr) {
		 super("Add Marks ", false, true, false, true);
		 lblexamtype = new  JLabel();
		 lblyear  = new  JLabel();
		 lblterm  = new  JLabel();
		 lblclass  = new  JLabel();
		 lbloutof  = new  JLabel();
		 lbldescr  = new  JLabel();
		 lbterm  = new  JLabel("Term:");
		 lbexam  = new  JLabel("Exam:");
		 lblsubject = new  JLabel("Subject");
		 lboutof = new  JLabel("outof");
		 
		 lblexamtype.setText(examtype);
		 lblyear.setText(year);
		 lblterm.setText(term);
		 lblclass.setText(clasz);
		 lbloutof.setText(outof);
		 lbldescr.setText(descr);
		 
		
		 cmbsubject = new JComboBox<String>();
		 
		 cmbsubject.addItem("English");
		 cmbsubject.addItem("Kiswahili");
		 cmbsubject.addItem("Mathematics");
		
		 Next = new JButton("Add");
		 Cancel = new JButton("Cancel");
		 
		 
		  examDAO = ExamDAO.getInstance(); 
		 
		 
		 
	     panel1 = new JPanel();
	     panel1.setLayout(null); 
	     panel2 = new JPanel();
	     panel2.setLayout(null); 
	     
	     panel1.setBounds(0, 0, 500, 500); // x, y, width, height
	     panel2.setBounds(10, 10, 450, 400); 
	     
	    //lblyear
	     lblyear.setBounds(0, 0, 100, 30);	
	     lbterm.setBounds(0, 25, 80, 30);
	     lblterm.setBounds(60, 25, 60, 30);
	     lblclass.setBounds(120, 25, 100, 30);	 
	     
	     lbexam.setBounds(0, 50, 150, 30);	  
	     lbldescr.setBounds(60, 50, 150, 30);
	     lboutof.setBounds(150, 50, 120, 30);
	     lbloutof.setBounds(200, 50, 60, 30);	
	     
	    
	     
	     lblsubject.setBounds(20, 90, 100, 30);
	     cmbsubject.setBounds(20, 90, 150, 30);
		    
	    
	     
	     Next.setBounds(20, 130, 100, 30);
	     Cancel.setBounds(150, 130, 100, 30);
	     
	     ButtonListener listener = new ButtonListener();
	     Next.addActionListener(listener);
	     Cancel.addActionListener(listener);
	     
	    
		 panel2.add(lblexamtype);
		 panel2.add(lblyear);
		 panel2.add(lblterm);
		 panel2.add(lblclass);
		 panel2.add(lbloutof);
		 panel2.add(lbldescr);
		 panel2.add(Next);//,
		 panel2.add(Cancel);
		 
		 panel2.add(lbterm);
		 panel2.add(lbexam);
		 panel2.add(lboutof);
		 
		 panel2.add(cmbsubject);
		 panel2.add(lblsubject);
		
		 
	     panel1.add(panel2);
	        
	     add(panel1);
	     setSize(500, 500);//width, height
	     setLocation((screen.width - 500) / 2, ((screen.height - 700) / 2));
	}
	
	 

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 
			 
			   if (e.getSource() == Next) {
				   String descr,term,clasz,subject;
				   descr = lbldescr.getText();
			       term = lblterm.getText();
			       clasz =lblclass.getText();
			       subject = (String) cmbsubject.getSelectedItem();
				   marksView frm = new marksView(descr, term, clasz,subject);
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
