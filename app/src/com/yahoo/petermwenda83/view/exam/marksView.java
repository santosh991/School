/**
 * 
 */
package com.yahoo.petermwenda83.view.exam;

/**
 * @author peter
 *
 */

	

	import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.contoller.student.Student;
import com.yahoo.petermwenda83.model.exam.ExamDAO;
import com.yahoo.petermwenda83.model.registration.RegistrationDAO;

	/**
	 * @author peter
	 *
	 */
	public class marksView extends JInternalFrame {
		
		private static JTable tblExamList;
	    private JScrollPane jsp;
	    private JButton btnSave,  btnRefresh,  btnClose, btnPrint;
	    private JPanel tablePanel;
	    private JPanel buttonPanel;
	    private JPanel haederPanel;
	    private JLabel lblterm,lbldescr,lblclasz,sub;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    private static int selectedRow;
	    
	    private static RegistrationDAO regDAO; 
	    List<Student> stuList = new ArrayList<Student>(); 
	    
	    public marksView(String descr, String term, String clasz,String subject)  
	    {
	        setSize(1000, 400);
	        setLayout(new BorderLayout());

	        tblExamList = new JTable(new AbstractTable());
	        
	        javax.swing.table.TableColumn column = null;
	        for (int i = 0; i < 7; i++) 
	        {
	            column = tblExamList.getColumnModel().getColumn(i);
	           
	        }//for btnClosed
	        jsp = new JScrollPane(tblExamList);
	        tablePanel = new JPanel(new GridLayout());
	        tablePanel.add(jsp);
            
	        lbldescr= new JLabel("("+descr);
	        lblterm = new JLabel(")TERM:"+term);
	        lblclasz= new JLabel(clasz);
	        sub = new JLabel(subject);
	        
	        btnSave = new JButton("Save");
	        btnRefresh = new JButton("Refresh");
	        btnClose = new JButton("Close");
	        btnPrint = new JButton("Print");
	        haederPanel= new javax.swing.JPanel(new java.awt.FlowLayout());
	        haederPanel.add(sub); 
	        haederPanel.add(lbldescr); 
	        haederPanel.add(lblterm); 
	        haederPanel.add(lblclasz); 
	        buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout());
	        buttonPanel.add(btnSave);
	        buttonPanel.add(btnRefresh);
	        buttonPanel.add(btnPrint);
	        buttonPanel.add(btnClose);
	        
	        regDAO = RegistrationDAO.getInstance(); 
	        stuList = regDAO.getAllStudents(); 

	        add(tablePanel, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.PAGE_END);
            add(haederPanel, BorderLayout.PAGE_START);
	        

	       
	        btnSave.addActionListener(new java.awt.event.ActionListener()
	        {

	            public void actionPerformed(java.awt.event.ActionEvent e) 
	            {
	            	
	            }
	        });
	        btnClose.addActionListener(new ActionListener() 
	        {

	            public void actionPerformed(ActionEvent e)
	            {
	               dispose();
	            }
	        });
	        btnPrint.addActionListener(new ActionListener() 
	        {

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

	        private String[] columnNames = {"AdmNo","Name","Marks","Total","Points","Grade","Remarks"};
	        private Object[][] data = new Object[50][50];
	        
	       
	       
	        
	        public int getColumnCount() {
	            return columnNames.length;
	        }

	        public int getRowCount() {
	            return data.length;
	        }

	        public String getColumnName(int col) {
	            return columnNames[col];
	        }

	        public Object getValueAt(int row, int col) {
	            return data[row][col];
	        }

	        public void setValueAt(Object value, int row, int col) {
	            data[row][col] = value;
	            fireTableCellUpdated(row, col);
	        }
	        public boolean isCellEditable(int row, int col){
	        	return (col ==2);
	        }
	        
	    }

	    public void reloaded() {
	    	 
	    	 int Numrow = 0;
	    	for(Student sub : stuList){
	    	
	     	tblExamList.setValueAt(sub.getAdmno().trim(), Numrow, 0);
	     	tblExamList.setValueAt(sub.getSurname().trim(), Numrow, 1);
	     	//System.out.println(sub);
	    	
	         Numrow++;
	    	}

	    }
	

}
