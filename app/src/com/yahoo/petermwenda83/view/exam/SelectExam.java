/**
 * 
 */
package com.yahoo.petermwenda83.view.exam;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.JInternalFrame;

/**
 * @author peter
 *
 */
public class SelectExam  extends JInternalFrame{
	
	//private JFrame frame1;
//	private JPanel panel;
	private JLabel lblmessage;
	private JComboBox cmb1;
	private JTextField txt1;
	
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * 
	 */
	public SelectExam() {
		super("Exams");
		this.getContentPane().setLayout(null);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        lblmessage = new JLabel();
        
        
        this.setVisible(true); 
	}

}
