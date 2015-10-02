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



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.yahoo.petermwenda83.bean.room.ClassRoom;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.StudentSubject;
import com.yahoo.petermwenda83.bean.student.Subject;
import com.yahoo.petermwenda83.persistence.room.RoomDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
//import com.yahoo.petermwenda83.persistence.subject.StudSubjectDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
	public class marksView extends JInternalFrame {
		
		private static JTable tblExamList;
	    private JScrollPane jsp;
	    private JButton btnSave,  btnRefresh,  btnClose, btnPrint;
	    private JPanel tablePanel;
	    private JPanel buttonPanel;
	    private JPanel haederPanel;
	    private JLabel lblterm,lbldescr,lblclasz,lblsubject,lblexam;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    private static int selectedRow;
	    
	    private static StudentDAO regDAO; 
	   // private static StudSubjectDAO studSubjectDAO;
	    private static SubjectDAO subjectDAO;
	    private static RoomDAO roomDAO;
	    
	    private String classroom =""; 
	    
	    List<Student> stuList = new ArrayList<Student>(); 
	    List<StudentSubject> studentsubList = new ArrayList<StudentSubject>();
	    List<StudentSubject> studentsubList2 = new ArrayList<StudentSubject>(); 
	    List<Subject> subjectList = new ArrayList<Subject>();  
	    List<ClassRoom> roomList = new ArrayList<>();
	    HashMap<String, String> SutudentSRUNANMEHash= new HashMap<>();
	    HashMap<String, String> SutudentADMnoHash= new HashMap<>();
	    HashMap<String, String> SutudentFIRSTNAMEHash= new HashMap<>();
	    HashMap<String, String> SutudentLASTNAMEHash= new HashMap<>();
	    HashMap<String, String> SutudentSubjectHash= new HashMap<>();
	    HashMap<String, String> SubjecttHash= new HashMap<>();
	    HashMap<String, String> roomHash= new HashMap<>();
	    
	    public marksView(String descr, String term, String room,String subject,String exam)  
	    {
	    	classroom = room;
	        setSize(1000, 400);
	        setLayout(new BorderLayout());

	        tblExamList = new JTable(new AbstractTable());
	        
	        javax.swing.table.TableColumn column = null;
	        for (int i = 0; i < 11; i++) 
	        {
	            column = tblExamList.getColumnModel().getColumn(i);
	           
	        }//for btnClosed
	        jsp = new JScrollPane(tblExamList);
	        tablePanel = new JPanel(new GridLayout());
	        tablePanel.add(jsp);
            
	        lbldescr= new JLabel(exam+":"+descr);
	        lblterm = new JLabel("TERM:"+term);
	        lblclasz= new JLabel(classroom);
	        lblsubject = new JLabel(subject);
	       // lblexam = new JLabel(exam);
	       
	        btnSave = new JButton("Save");
	        btnRefresh = new JButton("Refresh");
	        btnClose = new JButton("Close");
	        btnPrint = new JButton("Print");
	        haederPanel= new javax.swing.JPanel(new java.awt.FlowLayout());
	        haederPanel.add(lblsubject);
	       // haederPanel.add(lblexam); 
	        haederPanel.add(lbldescr); 
	        haederPanel.add(lblterm); 
	        haederPanel.add(lblclasz); 
	        buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout());
	        buttonPanel.add(btnSave);
	        buttonPanel.add(btnRefresh);
	        buttonPanel.add(btnPrint);
	        buttonPanel.add(btnClose);
	        
	        regDAO = StudentDAO.getInstance(); 
	       // studSubjectDAO = StudSubjectDAO.getInstance();
	        subjectDAO =  SubjectDAO .getInstance();
	        roomDAO = RoomDAO.getInstance();
	        
	        roomList = roomDAO.getAllRooms();
	        stuList = regDAO.getAllStudents(); 
	       // studentsubList = studSubjectDAO.getAllStudentSubject();
	        
	        for(ClassRoom r : roomList){
	        	roomHash.put(r.getRoomName(), r.getUuid()); 
	        }
	         
	          StudentSubject stsub = new StudentSubject();
	    	 // stsub.setRoomnameUuid(roomHash.get(classroom));  
	    	  //studentsubList2  = studSubjectDAO.getStudentSubject(stsub,classroom); 
	       
	        subjectList = subjectDAO.getAllSubjects();
	        
	        for(Student st : stuList){
	        	SutudentSRUNANMEHash.put(st.getUuid(), st.getSurname());
	        	SutudentADMnoHash.put(st.getUuid(), st.getAdmno());
	        	SutudentFIRSTNAMEHash.put(st.getUuid(), st.getFirstname());
	        	SutudentLASTNAMEHash.put(st.getUuid(), st.getLastname());
	        }
	        for(StudentSubject ss : studentsubList){
	        	SutudentSubjectHash.put(ss.getStudentUuid(), ss.getSubjectUuid());
	        	
	        }
	        for(Subject sb : subjectList){
	        	SubjecttHash.put(sb.getUuid(), sb.getSubjectcode());
	        }
	       

	        add(tablePanel, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.PAGE_END);
            add(haederPanel, BorderLayout.PAGE_START);
	        

	       
	        btnSave.addActionListener(new java.awt.event.ActionListener()
	        {

	            @Override
				public void actionPerformed(java.awt.event.ActionEvent e) 
	            {
	            	
	            }
	        });
	        btnClose.addActionListener(new ActionListener() 
	        {

	            @Override
				public void actionPerformed(ActionEvent e)
	            {
	               dispose();
	              
	            }
	        });
	        btnPrint.addActionListener(new ActionListener() 
	        {

	            @Override
				public void actionPerformed(ActionEvent e)
	            {
	               
	            }
	        });
	       
	        reloaded();
	     }//constructor closed
	    
	    
	    public static int getSelectedRow() 
	    {
	    	tblExamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    	
	        javax.swing.ListSelectionModel rowSel = tblExamList.getSelectionModel();
	        rowSel.addListSelectionListener(new ListSelectionListener()
	        {

	            @Override
				public void valueChanged(ListSelectionEvent e)
	            {
	                if (e.getValueIsAdjusting())
	                {
	                    return;
	                }

	                javax.swing.ListSelectionModel sel = (ListSelectionModel) e.getSource();
	                if (!sel.isSelectionEmpty())
	                {
	                    selectedRow = sel.getMinSelectionIndex();
	                   
	                }
	            }
	        });

	        return selectedRow;
	    }

	    class AbstractTable extends AbstractTableModel {

	        private String[] columnNames = {"ADM_NO","FIRSTNAME","LASTNAME","SURNAME","SUBJECT","MARK","TOTAL","POINTS","GARDE","POSITION","REMARKS"};
	        private Object[][] data = new Object[50][50];
	        
	       
	       
	        
	        @Override
			public int getColumnCount() {
	            return columnNames.length;
	        }

	        @Override
			public int getRowCount() {
	            return data.length;
	        }

	        @Override
			public String getColumnName(int col) {
	            return columnNames[col];
	        }

	        @Override
			public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }

	        @Override
			public void setValueAt(Object value, int row, int col) {
	            data[row][col] = value;
	            fireTableCellUpdated(row, col);
	        }
	        @Override
			public boolean isCellEditable(int row, int col){
	        	return (col ==5);
	        }
	        
	    }

	    public void reloaded() {
	    	
	    	 int Numrow = 0;
	    	for(StudentSubject s : studentsubList2){
	     	tblExamList.setValueAt(SutudentADMnoHash.get(s.getStudentUuid()).trim(), Numrow, 0);
	        tblExamList.setValueAt(SutudentFIRSTNAMEHash.get(s.getStudentUuid()).trim(), Numrow, 1);
	        tblExamList.setValueAt(SutudentLASTNAMEHash.get(s.getStudentUuid()).trim(), Numrow, 2);
	     	tblExamList.setValueAt(SutudentSRUNANMEHash.get(s.getStudentUuid()).trim(), Numrow, 3);
	     	tblExamList.setValueAt(SubjecttHash.get(SutudentSubjectHash.get(s.getStudentUuid())) .trim(), Numrow, 4);
	   
	    	
	         Numrow++;
	    	}
	    	
	    }
	

}
