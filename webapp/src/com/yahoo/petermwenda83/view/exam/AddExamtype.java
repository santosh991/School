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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yahoo.petermwenda83.bean.exam.ExamDetail;
import com.yahoo.petermwenda83.bean.room.ClassRoom;
import com.yahoo.petermwenda83.bean.student.Subject;
import com.yahoo.petermwenda83.persistence.room.RoomDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class AddExamtype extends JInternalFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2782233027666184065L;
	private JLabel lblexamtype,lblyear,lblterm,lblclass,lbloutof,lbldescr,examNO,lblsubject;
	 private JComboBox<String>  cmbexmtyp,cmbterm,cmbclass,cmbsubjects;
	 private JButton Next,Cancel;
	 private JTextField txtyear,txtputof,txtdescr;
	 private JPanel panel1,  panel2;
	 private String subjectUuid,roomUuid;
	 
	 List<Subject> subjectList = new ArrayList<>();
	 List<ClassRoom> classroomList = new ArrayList<>();

	 HashMap<String, String> SubHash= new HashMap<>();
	 HashMap<String, String> roomHash= new HashMap<>();
	 
	 private static SubjectDAO subjectDAO;
	 private static RoomDAO roomDAO;

	 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * 
	 */
	public AddExamtype() {
		 super("Exams ", false, true, false, true);
		 lblexamtype = new  JLabel("Exam Type");
		 lblyear  = new  JLabel("Year");
		 lblterm  = new  JLabel("Term");
		 lblclass  = new  JLabel("Class");
		 lbloutof  = new  JLabel("Exam Outof");
		 lbldescr  = new  JLabel("Description");
		 lblsubject  = new  JLabel("Subject");
		 examNO = new JLabel();
		 cmbexmtyp = new JComboBox<String>();
		 cmbterm = new JComboBox<String>();
		 cmbclass = new JComboBox<String>();
		 Next = new JButton("Add");
		 Cancel = new JButton("Cancel");
		 txtyear = new JTextField();
		 cmbsubjects = new JComboBox<String>();
		 txtputof  = new JTextField();
		 txtdescr  = new JTextField("CAT 1");
		 
		// examTypeDAO = ExamtypeDAO.getInstance(); 
		 subjectDAO = SubjectDAO.getInstance();
		 roomDAO = RoomDAO.getInstance();
		 
		 classroomList = roomDAO.getAllRooms();
		 subjectList = subjectDAO.getAllSubjects();
		 for(Subject s : subjectList){
			 SubHash.put(s.getUuid(), s.getSubjectname());
			 cmbsubjects.addItem(SubHash.get(s.getUuid()));  
		 }
		 for(ClassRoom r : classroomList){
			 roomHash.put(r.getUuid(), r.getRoomname()); 
			 cmbclass.addItem(roomHash.get(r.getUuid())); 
			
		 }
		 
		 
		 cmbexmtyp.addItem("");
		 cmbexmtyp.addItem("CAT");
		 cmbexmtyp.addItem("MAIN");
		 
		 cmbterm.addItem("");
		 cmbterm.addItem("ONE");
		 cmbterm.addItem("TWO");
		 cmbterm.addItem("THREE");
		 
		
		 
	     panel1 = new JPanel();
	     panel1.setLayout(null); 
	     panel2 = new JPanel();
	     panel2.setLayout(null); 
	     
	     panel1.setBounds(0, 0, 500, 500); // x, y, width, height
	     panel2.setBounds(0, 0, 500, 500); 
	     
	     examNO.setBounds(220, 0, 150, 30);
	     lblexamtype.setBounds(20, 30, 150, 30);
	     cmbexmtyp.setBounds(150, 30, 150, 30);
	     
	     lblyear.setBounds(20, 80, 150, 30);
	     txtyear.setBounds(150, 80, 150, 30);
	     
	     lblterm.setBounds(20, 130, 150, 30);
	     cmbterm.setBounds(150, 130, 150, 30);
	     
	     lblclass.setBounds(20, 180, 150, 30);
	     cmbclass.setBounds(150, 180, 150, 30);
	    
	     
	     lbloutof.setBounds(20, 230, 150, 30);
	     txtputof.setBounds(150, 230, 150, 30);
	     
	     lbldescr.setBounds(20, 280, 150, 30);
	     txtdescr.setBounds(150, 280, 150, 30);
	     txtdescr.setToolTipText("e.g CAT 1"); 
	     
	     lblsubject.setBounds(20, 320, 150, 30);
	     cmbsubjects.setBounds(150, 320, 150, 30);
	     
	     Next.setBounds(100, 380, 100, 30);
	     Cancel.setBounds(240, 380, 100, 30);
	     
	     AtomicInteger seq = new AtomicInteger();
	     int nextVal = seq.incrementAndGet();
	     Random rand = new Random();
	     int n = rand.nextInt(50000)+100; 
          examNO.setText("EN"+Integer.toString(nextVal+n)); 
       
	      
	     
	     
	     
	     ButtonListener listener = new ButtonListener();
	     Next.addActionListener(listener);
	     Cancel.addActionListener(listener);
	     
	     
	     
	     txtputof.addKeyListener(new KeyAdapter() {

	            @Override
				public void keyTyped(KeyEvent e) {
	                char c = e.getKeyChar();
	                if (!(Character.isDigit(c) ||
	                        (c == KeyEvent.VK_BACK_SPACE) ||
	                        (c == KeyEvent.VK_SPACE) ||
	                        (c == KeyEvent.VK_DELETE))) {

	                    getToolkit().beep();
	                    JOptionPane.showMessageDialog(null, "Digit only", "ERROR",
	                            JOptionPane.DEFAULT_OPTION);
	                    e.consume();
	                }
	            }
	        });
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     panel2.add(examNO); 
		 panel2.add(lblexamtype);
		 panel2.add(lblyear);
		 panel2.add(lblterm);
		 panel2.add(lblclass);
		 panel2.add(lbloutof);
		 panel2.add(lbldescr);
		 panel2.add(Next);
		 panel2.add(Cancel);
		 
		 panel2.add(cmbexmtyp);
		 panel2.add(txtyear);
		 panel2.add(cmbterm);
		 panel2.add(cmbclass);
		 panel2.add(txtputof);
		 panel2.add(cmbsubjects);
		 panel2.add(lblsubject);
		 panel2.add(txtdescr);
	
		 
	     panel1.add(panel2);
	        
	     add(panel1);
	     setSize(500, 500);//width, height
	     setLocation((screen.width - 500) / 2, ((screen.height - 700) / 2));
	}
	
	 

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
			 ExamDetail examDetail = new  ExamDetail();
			// String type = (String) cmbexmtyp.getSelectedItem();
			 String term = (String) cmbterm.getSelectedItem();
			// String clas = (String) cmbclass.getSelectedItem();
			 String subject = (String) cmbsubjects.getSelectedItem();
			 String classroom = (String) cmbclass.getSelectedItem();
			
			 for (Entry<String, String> entry : SubHash.entrySet()) {
		            if (entry.getValue().equals(subject)) {
		            	subjectUuid = entry.getKey();
		               // System.out.println(entry.getKey()); 
		            	
		            }
		        }

			 for (Entry<String, String> entry : roomHash.entrySet()) {
		            if (entry.getValue().equals(classroom)) {
		            	roomUuid = entry.getKey(); 
		            	// System.out.println(entry.getKey()); 
		            }
		        }
			 
			 
			 
			 
			 
			 
			  // examType.setExamtype(type.toUpperCase());
			   examDetail.setYear(txtyear.getText().toUpperCase()); 
			   examDetail.setTerm(term.toUpperCase());
			  // examType.setRoomnameUuid(roomUuid);
			  // examType.setOutof(txtputof.getText().toUpperCase());
			   //examType.setDescription(txtdescr.getText().toUpperCase()); 
			   examDetail.setSubjectUuid(subjectUuid);
			  // System.out.println("roomUuid:"+roomUuid);
			   
			  if (e.getSource() == Next) {
                if (txtyear.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Year", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtyear.requestFocus();
                    return;
                }
                
                if (cmbexmtyp.getSelectedItem() == null || cmbexmtyp.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Select exam type", "Missing field", JOptionPane.DEFAULT_OPTION);
                    cmbexmtyp.requestFocus();
                    return;
                }
                if (cmbterm.getSelectedItem() == null || cmbterm.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Select Term", "Missing field", JOptionPane.DEFAULT_OPTION);
                    cmbclass.requestFocus();
                    return;
                }
                if (cmbclass.getSelectedItem() == null || cmbclass.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Select Class", "Missing field", JOptionPane.DEFAULT_OPTION);
                    cmbclass.requestFocus();
                    return;
                }
                
                if (txtputof.getText() == null ||
                		txtputof.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Exam out of!", "ERROR",
                            JOptionPane.DEFAULT_OPTION);
                    txtputof.requestFocus();
                    return;
                }
               
                if (txtdescr.getText() == null || txtdescr.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Description", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtdescr.requestFocus();
                    return; 
                }
              //  if (examTypeDAO.get(type.toUpperCase(), clas.toUpperCase(), txtdescr.getText().toUpperCase()) !=null) {
                    JOptionPane.showMessageDialog(null, "This Exam Already exist", "Error", JOptionPane.DEFAULT_OPTION);
                   
                }else{
                	
               // 	 if(examTypeDAO.putExamType(examType)){
                	  JOptionPane.showMessageDialog(null, "Added", "Success", JOptionPane.DEFAULT_OPTION);
                	 
                }
                	 }
                
			//  }if(e.getSource() == Cancel){
				//  dispose();
			 // }
			
		//}
		 
	 }
	
}
