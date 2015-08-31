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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.model.exam.ExamDAO;
import com.yahoo.petermwenda83.model.exam.ExamtypeDAO;
import com.yahoo.petermwenda83.view.MainWindow;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ExamTypeView extends JInternalFrame {
	
	private static JTable tblExamList;
    private JScrollPane jsp;
    private JButton btnAddNew,  btnRefresh,  btnClose,  btnUpdate,  btnaddMarks;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private static int selectedRow;
    
    private static ExamtypeDAO examtypeDAO; 
    List<ExamType> etypList = new ArrayList<ExamType>(); 
    
    public ExamTypeView() 
    {
        setSize(1000, 400);
        setLayout(new BorderLayout());

        tblExamList = new JTable(new AbstractTable());
        javax.swing.table.TableColumn column = null;
        for (int i = 0; i < 6; i++) 
        {
            column = tblExamList.getColumnModel().getColumn(i);
            if (i == 5) 
            {
                sdf.format(i);
            }//if btnClosed
        }//for btnClosed
        jsp = new JScrollPane(tblExamList);
        tablePanel = new JPanel(new GridLayout());
        tablePanel.add(jsp);

        btnAddNew = new JButton("Add New");
        btnUpdate = new JButton("Update");
        btnRefresh = new JButton("Refresh");
        btnClose = new JButton("Close");
        btnaddMarks = new JButton("AddMarks");
        buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout());
        buttonPanel.add(btnAddNew);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnaddMarks);
        buttonPanel.add(btnClose);
        
        examtypeDAO = ExamtypeDAO.getInstance(); 
        etypList = examtypeDAO.getAllExamtype();

        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        

       
        btnAddNew.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
            	AddExamtype frm = new AddExamtype();
                MainWindow.desktop.add(frm);
                frm.setVisible(true);
                try {
                    frm.setSelected(true);
                } catch (Exception ex) {
                }
            }
        });
        btnClose.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });
        btnRefresh.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e) 
            {
            	 reloaded();
            }
            });
        btnaddMarks.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent evt)
            {
              addMarks();
            }

			
        });
        btnUpdate.addActionListener(new ActionListener() 
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

        private String[] columnNames = {"examtype","year","term","class","outof","descr"};
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
    }

    public void reloaded() {
    	 int Numrow = 0;
    	for(ExamType typ : etypList){
    	
     	tblExamList.setValueAt(typ.getExamtype().trim(), Numrow, 0);
     	tblExamList.setValueAt(typ.getYear().trim(), Numrow, 1);
     	tblExamList.setValueAt(typ.getTerm().trim(), Numrow, 2);
     	tblExamList.setValueAt(typ.getClasz().trim(), Numrow, 3);
     	tblExamList.setValueAt(typ.getOutof().trim(), Numrow, 4);
     	tblExamList.setValueAt(typ.getDescription().trim(), Numrow, 5);
    	
         Numrow++;
    	}

    }
    
    private void addMarks() {
    	
    	        String examtype,year,term,clasz, outof,descr;
    	   	
    			examtype = tblExamList.getValueAt(getSelectedRow(), 0).toString();
    			year = tblExamList.getValueAt(getSelectedRow(), 1).toString();
    			term = tblExamList.getValueAt(getSelectedRow(), 2).toString();
    			clasz = tblExamList.getValueAt(getSelectedRow(), 3).toString();
    			outof = tblExamList.getValueAt(getSelectedRow(), 4).toString();
    			descr = tblExamList.getValueAt(getSelectedRow(), 5).toString();
               
    			addMarks frm = new addMarks(examtype,year,term,clasz, outof,descr);
                MainWindow.desktop.add(frm);
                frm.setVisible(true);
    			
    			
    			
   
		
	}

}
