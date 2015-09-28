/**
 * 
 */
package com.yahoo.petermwenda83.view.exam;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.JInternalFrame;

import com.yahoo.petermwenda83.bean.room.ClassRoom;
import com.yahoo.petermwenda83.bean.student.Subject;
import com.yahoo.petermwenda83.persistence.room.RoomDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.view.MainWindow;

/**
 * @author peter
 *
 */
public class SelectExam  extends JInternalFrame{
	
	/*private static CatDAO catDAO;
	private static ExamDAO examDAO;*/
	private static SubjectDAO subjectDAO;
	private static RoomDAO roomDAO;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6982228568717230620L;

	private JLabel lblmessage,lblcat,lblmain,lblterm,lblroom,lblsubject;
	private JComboBox<String> cmbcat,cmbmain,cmbterm,cmbroom,cmbsubject;
	private JButton btncat,btnmain,btnclose;
	private String subjectUuid,roomUuid,mUuid,cUuid;
	
	 /*List<Cat> catList = new ArrayList<>();
	 List<Main> mainList = new ArrayList<>();*/
	 
	 List<Subject> subjectList = new ArrayList<>();
	 List<ClassRoom> classroomList = new ArrayList<>();

	 HashMap<String, String> SubHash= new HashMap<>();
	 HashMap<String, String> roomHash= new HashMap<>();
	 
	 HashMap<String, String> catHash= new HashMap<>();
	 HashMap<String, String> mainHash= new HashMap<>();
	
	
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * 
	 */
	public SelectExam() {
		super("Exams");
		this.getContentPane().setLayout(null);
        this.setSize(400, 500);
        this.setResizable(false);
        this.setLocation((screen.width - 500) / 2, ((screen.height - 600) / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        lblmessage = new JLabel("Select Exam");
        
        lblterm = new JLabel("Term");
        lblroom = new JLabel("Class");
        lblsubject = new JLabel("Subject");
        lblcat = new JLabel("CAT");
        lblmain = new JLabel("MAIN");
        
      
        cmbterm = new JComboBox<String>();
        cmbroom = new JComboBox<String>();
        cmbsubject = new JComboBox<String>(); 
        cmbcat = new JComboBox<String>();
        cmbmain = new JComboBox<String>(); 
        
        btncat = new JButton("SELECT");
        btnmain = new JButton("SELECT");
        btnclose = new JButton("CLOSE");
     
        
        lblterm.setBounds(10, 10, 150, 25);
        cmbterm.setBounds(100, 10, 100, 25);
        
        lblroom.setBounds(10, 50, 150, 25);
        cmbroom.setBounds(100, 50, 100, 25);
        
        lblsubject.setBounds(10, 90, 150, 25);
        cmbsubject.setBounds(100, 90, 100, 25);
        
        lblmessage.setBounds(100, 130, 200, 25);
        
        lblcat.setBounds(20, 170, 80, 25);
        cmbcat.setBounds(100, 170, 150, 25);
        btncat.setBounds(270, 170, 100, 25);
        
        lblmain.setBounds(20, 210, 80, 25);
        cmbmain.setBounds(100, 210, 150, 25);
        btnmain.setBounds(270,210, 100, 25);
        
        btnclose.setBounds(100,250, 80, 25);
        
        cmbterm.addItem("ONE");
        cmbterm.addItem("TWO");
        cmbterm.addItem("THREE");
        
        subjectDAO = SubjectDAO.getInstance();
		roomDAO = RoomDAO.getInstance();
        
        
        //catDAO = CatDAO.getInstance();
       // catList = catDAO.getAllCats();
        
        classroomList = roomDAO.getAllRooms();
		 subjectList = subjectDAO.getAllSubjects();
		 
		 for(Subject s : subjectList){
			 SubHash.put(s.getUuid(), s.getSubjectname());
			 cmbsubject.addItem(SubHash.get(s.getUuid()));  
		 }
		 for(ClassRoom r : classroomList){
			 roomHash.put(r.getUuid(), r.getRoomName()); 
			 cmbroom.addItem(roomHash.get(r.getUuid())); 
			
		 }
       /* 
        mainDAO = MainDAO.getInstance();
        mainList = mainDAO.getAllMain();
        
        for(Cat cat : catList){
        	catHash.put(cat.getUuid(), cat.getCatname());
        	cmbcat.addItem(cat.getCatname());
        }
       for(Main main : mainList){
    	   mainHash.put(main.getUuid(), main.getExamname());
    	   cmbmain.addItem(main.getExamname());	 
        }
        
        */
        
        ButtonListener listener = new ButtonListener();
        btncat.addActionListener(listener);
        btnmain.addActionListener(listener);
        btnclose.addActionListener(listener);
        
        

        this.add(lblmessage);
        this.add(lblcat);
        this.add(lblmain);
        this.add(cmbcat);
        this.add(cmbmain);
        this.add(btncat); 
        this.add(btnmain);
        this.add(btnclose); 
        this.add(lblterm);
        this.add(cmbterm);
        this.add(lblroom);
        this.add(lblsubject); 
        this.add(cmbroom);
        this.add(cmbsubject);
      
        this.setVisible(true); 
	}
	
	private class ButtonListener implements ActionListener {
      
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
			 String subject = (String) cmbsubject.getSelectedItem();
			 String classroom = (String) cmbroom.getSelectedItem();
			
			 for (Entry<String, String> entry : SubHash.entrySet()) {
		            if (entry.getValue().equals(subject)) {
		            	subjectUuid = entry.getKey();
		              // System.out.println(entry.getKey()+":subjectUuid"); 
		            	
		            }
		        }

			 for (Entry<String, String> entry : roomHash.entrySet()) {
		            if (entry.getValue().equals(classroom)) {
		            	roomUuid = entry.getKey(); 
		            	//System.out.println(entry.getKey()+":classroomUuid"); 
		            }
		        }
			
			
			//mUuid,cUuid
			String term = (String) cmbterm.getSelectedItem();
			String catname = (String) cmbcat.getSelectedItem();
			String mainname = (String) cmbmain.getSelectedItem();
			
			String cname = (String) cmbcat.getSelectedItem();
			String mname = (String) cmbmain.getSelectedItem();
			
			 if (e.getSource() == btncat) {
				 
				 for (Entry<String, String> entry : catHash.entrySet()) {
			            if (entry.getValue().equals(catname)) {
			            	cUuid = entry.getKey(); 
			            	//System.out.println(entry.getKey()+":catUuid"); 
			            }
			        }
				 
				 String exam = lblcat.getText();
				 
				 SelectedExamView frm = new SelectedExamView(exam,term,subjectUuid,roomUuid,cUuid,cname);
	                MainWindow.desktop.add(frm);
	                frm.setVisible(true);
	               // dispose();
	                try {
	                    frm.setSelected(true);
	                } catch (Exception ex) {
	                }
								
			 }

			 if (e.getSource() == btnmain) {
				 
				 for (Entry<String, String> entry : mainHash.entrySet()) {
			            if (entry.getValue().equals(mainname)) {
			            	mUuid = entry.getKey(); 
			            	//System.out.println(entry.getKey()+":mainUuid"); 
			            }
			        }
				 
				 String exam = lblmain.getText();
				 
				 SelectedExamView frm = new SelectedExamView(exam,term,subjectUuid,roomUuid,mUuid,mname);
	                MainWindow.desktop.add(frm);
	                frm.setVisible(true);
	               // dispose();
	                try {
	                    frm.setSelected(true);
	                } catch (Exception ex) {
	                }
				 
			 }
			 

			 if (e.getSource() == btnclose) {
				dispose(); 
			 }
			 
			 
			 
			 
			 
			
		}
		
	}

}
