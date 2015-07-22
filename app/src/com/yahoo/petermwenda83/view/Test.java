/**
 * 
 */
package com.yahoo.petermwenda83.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.yahoo.petermwenda83.contoller.student.Subject;
import com.yahoo.petermwenda83.model.curriculum.SubjectDAO;

/**
 * @author peter
 *
 */
public class Test  {
 private JFrame frame;
 private JPanel panel;
 private JLabel l1;
 private JTextField t1;
 private JComboBox<String> box; 
 private SubjectDAO subDAO = SubjectDAO.getInstance();
 List<Subject> subList;
	/**
	 * 
	 */
	public Test() {
		super();
		frame = new JFrame("My Frame");
		panel = new JPanel();
		t1 = new JTextField();
		l1 = new JLabel("SubjectName");
		box = new JComboBox<String>(); 
		
		frame.setLayout(null);
		panel.setLayout(null);
		frame.setBounds(50, 50, 600, 600); 
		panel.setBounds(0, 0, 600, 600);
		
		l1.setBounds(20, 20, 100, 30); 
		//t1.setBounds(20, 50, 100, 30); 
		box.setBounds(20, 50, 200, 30);
		
		t1.setBackground(Color.MAGENTA); 
		panel.setBackground(Color.MAGENTA); 
		
		subList  = new ArrayList<>();
		subList = subDAO.getAllSubjects();
		
		
		
		

	    
		
		for(Subject sub : subList){
			box.addItem(sub.getSubjectname()); 
			
		}
		
		  panel.add(l1);
		  //panel.add(t1);
		  panel.add(box);
		
		  frame.add(panel).setVisible(true); 
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  frame.setVisible(true); 
		
	}
	 
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		new Test(); 
	}

}
