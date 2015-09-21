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
package com.yahoo.petermwenda83.view.subject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yahoo.petermwenda83.bean.student.Subject;
import com.yahoo.petermwenda83.dao.curriculum.SubjectDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class SubjectView extends JInternalFrame {
	 private JLabel lblsubname,lblsubcode,  lblsubcategory;
	 private JComboBox<String> combosubcategory; 
	 private JButton button1;
	 private JTextField txtsubname,txtsubcode;
	 private JPanel panel1,  panel2;
	 private static SubjectDAO subjectDAO;
	 private static List<Subject> subList = new ArrayList<>();
	 
	 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	 /**
	 * Contractor
	 */
	public SubjectView(){
		 super("Add Subject ", false, true, false, true);
		 
		 lblsubname = new JLabel("Subject Name");
		 lblsubcode = new JLabel("Subject Code");
		 lblsubcategory = new JLabel("Subject Category");
		 txtsubname = new JTextField();
		 txtsubcode = new JTextField(); 
		 combosubcategory = new JComboBox<String>();
		 button1 = new JButton("Add");
		 
		 subjectDAO = SubjectDAO.getInstance();
		 subList = subjectDAO.getAllSubjects();
		 
		 combosubcategory.addItem("Compulsary");
		 combosubcategory.addItem("Science");
		 combosubcategory.addItem("Humanity");
		 combosubcategory.addItem("Techinical");
		 
		 
		 
	     panel1 = new JPanel();
	     panel1.setLayout(null); 
	     panel2 = new JPanel();
	     panel2.setLayout(null); 
	     
	     panel1.setBounds(0, 0, 500, 350); // x, y, width, height
	     panel2.setBounds(10, 10, 450, 300); 
	     
	     lblsubname.setBounds(20, 30, 150, 30); 
	     txtsubname.setBounds(150, 30, 150, 30); 
	     
	     lblsubcode.setBounds(20, 70, 150, 30); 
	     txtsubcode.setBounds(150, 70, 150, 30);  
	     
	     lblsubcategory.setBounds(20, 110, 150, 30); 
	     combosubcategory.setBounds(150, 110, 150, 30);  
	     
	     button1.setBounds(150, 150, 90, 30); 
	     
	     
	     
	     ButtonListener listener = new ButtonListener();
	     button1.addActionListener(listener);
	     
	     panel2.add(lblsubname);
	     panel2.add(txtsubname);
	     panel2.add(lblsubcode);
	     panel2.add(txtsubcode);
	     panel2.add(lblsubcategory);
	     panel2.add(combosubcategory);
	     panel2.add(button1);
	     
	     
	     panel1.add(panel2);
	        
	     add(panel1);
	     setSize(500, 350);
	     setLocation((screen.width - 500) / 2, ((screen.height - 500) / 2));
	     generator();
		
	 }

	private void generator() {
		// TODO Auto-generated method stub
		
	}
	 private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Subject subject = new Subject();
			subject.setSubjectname(txtsubname.getText()); 
			subject.setSubjectcode(txtsubcode.getText()); 
			String subcat = (String) combosubcategory.getSelectedItem();
			subject.setSubjectcategory(subcat); 
			
			
			if (e.getSource() == button1) {
                if (txtsubname.getText() == null || txtsubname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Subject Name", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtsubname.requestFocus();
                    return;
                }
                if (txtsubcode.getText() == null || txtsubcode.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Subject Code", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtsubcode.requestFocus();
                    return;
                }if(subjectDAO.getSubjects(txtsubcode.getText()) != null){
                	 JOptionPane.showMessageDialog(null, "Subject Code Already exist", "Error", JOptionPane.DEFAULT_OPTION);
                      	
                }else{
                	  subjectDAO.putSubject(subject);
                	  JOptionPane.showMessageDialog(null, "Subject Added", "Success", JOptionPane.DEFAULT_OPTION);
                }
                
              
               
		}else{
			 
             
		}
		 
	 }
	
	 }
}
