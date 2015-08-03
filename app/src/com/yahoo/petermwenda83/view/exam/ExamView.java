/**
 * 
 */
package com.yahoo.petermwenda83.view.exam;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.yahoo.petermwenda83.contoller.exam.ExamType;
import com.yahoo.petermwenda83.model.exam.ExamDAO;

/**
 * @author peter
 *
 */
public class ExamView extends JInternalFrame {
	 private JLabel lblexamtype,lblyear,lblterm,lblclass,lbloutof,lbldescr,examNO;
	 private JComboBox<String>  cmbexmtyp,cmbterm;
	 private JButton Next;
	 private JTextField txtyear,txtclass,txtputof,txtdescr;
	 private JPanel panel1,  panel2;
	 
	 private static ExamDAO examDAO; 

	 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * 
	 */
	public ExamView() {
		 super("Exams ", false, true, false, true);
		 lblexamtype = new  JLabel("Exam Type");
		 lblyear  = new  JLabel("Year");
		 lblterm  = new  JLabel("Term");
		 lblclass  = new  JLabel("Class");
		 lbloutof  = new  JLabel("Exam Outof");
		 lbldescr  = new  JLabel("Description");
		 examNO = new JLabel();
		 cmbexmtyp = new JComboBox<String>();
		 cmbterm = new JComboBox<String>();
		 Next = new JButton("Next");
		 txtyear = new JTextField();
		 txtclass = new JTextField();
		 txtputof  = new JTextField();
		 txtdescr  = new JTextField();
		 
		  examDAO = ExamDAO.getInstance(); 
		 
		 cmbexmtyp.addItem("CAT");
		 cmbexmtyp.addItem("MAIN");
		 
		 cmbterm.addItem("ONE");
		 cmbterm.addItem("TWO");
		 cmbterm.addItem("THREE");
		 
		 
	     panel1 = new JPanel();
	     panel1.setLayout(null); 
	     panel2 = new JPanel();
	     panel2.setLayout(null); 
	     
	     panel1.setBounds(0, 0, 500, 500); // x, y, width, height
	     panel2.setBounds(10, 10, 450, 400); 
	     
	     examNO.setBounds(220, 0, 150, 30);
	     lblexamtype.setBounds(20, 30, 150, 30);
	     cmbexmtyp.setBounds(150, 30, 150, 30);
	     
	     lblyear.setBounds(20, 80, 150, 30);
	     txtyear.setBounds(150, 80, 150, 30);
	     
	     lblterm.setBounds(20, 130, 150, 30);
	     cmbterm.setBounds(150, 130, 150, 30);
	     
	     lblclass.setBounds(20, 180, 150, 30);
	     txtclass.setBounds(150, 180, 150, 30);
	     
	     lbloutof.setBounds(20, 230, 150, 30);
	     txtputof.setBounds(150, 230, 150, 30);
	     
	     lbldescr.setBounds(20, 280, 150, 30);
	     txtdescr.setBounds(150, 280, 150, 30);
	     
	     Next.setBounds(150, 320, 150, 30);
	     
	     AtomicInteger seq = new AtomicInteger();
	     int nextVal = seq.incrementAndGet();
	     Random rand = new Random();
	     int n = rand.nextInt(50000)+100; 
          examNO.setText("EN"+Integer.toString(nextVal+n)); 
       
	      
	     
	     
	     
	     ButtonListener listener = new ButtonListener();
	     Next.addActionListener(listener);
	     
	     
	     panel2.add(examNO); 
		 panel2.add(lblexamtype);
		 panel2.add(lblyear);
		 panel2.add(lblterm);
		 panel2.add(lblclass);
		 panel2.add(lbloutof);
		 panel2.add(lbldescr);
		 panel2.add(Next);
		 
		 panel2.add(cmbexmtyp);
		 panel2.add(txtyear);
		 panel2.add(cmbterm);
		 panel2.add(txtclass);
		 panel2.add(txtputof);
		 panel2.add(txtdescr);
	
		 
	     panel1.add(panel2);
	        
	     add(panel1);
	     setSize(500, 500);//width, height
	     setLocation((screen.width - 500) / 2, ((screen.height - 700) / 2));
	}
	
	 private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			 ExamType examType = new  ExamType();
			 String type = (String) cmbexmtyp.getSelectedItem();
			 String term = (String) cmbterm.getSelectedItem();
			// int outof = Integer.parseInt(txtputof.getText().toString());   
			
			
			  examType.setExamtype(type);
			   examType.setYear(txtyear.getText()); 
			  examType.setTerm(term);
			   examType.setClasz(txtclass.getText());
			 //  examType.setOutof(outof);
			   examType.setDescription(txtdescr.getText()); 
			 
			  if (e.getSource() == Next) {
                if (txtputof.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Year", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtyear.requestFocus();
                    return;
                }
                if (txtclass.getText() == null || txtclass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Class", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtclass.requestFocus();
                    return;
                }
                if (txtputof.getText() == null || txtputof.getText().equals("") ) {
                    JOptionPane.showMessageDialog(null, "Enter Exam outof", "Missing field", JOptionPane.DEFAULT_OPTION);
                      txtputof.requestFocus();
                    return;
                }
                if (txtdescr.getText() == null || txtdescr.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Description", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtdescr.requestFocus();
                    return;
                }
                if (examDAO.getExamTypes(examNO.getText()) !=null) {
                    JOptionPane.showMessageDialog(null, "This Exam Already exist", "Error", JOptionPane.DEFAULT_OPTION);
                   
                }else{
                	 examDAO.putExamType(examType); 
                	  JOptionPane.showMessageDialog(null, "Procced", "Success", JOptionPane.DEFAULT_OPTION);
                	  //ExamPanel epanel = new ExamPanel();
                }
			  }else{
				  
			  }
			
		}
		 
	 }

}
