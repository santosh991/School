/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
 */
package com.yahoo.petermwenda83.view.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.yahoo.petermwenda83.view.MainWindow;

/**
 * @author peter
 * @author <h1>mwendapeter72@gmail.com </h1>
 * @author <h1>migwindungu0@gmail.com </h1>
 *
 */
public class LoginScreen extends JFrame{
 private static final long serialVersionUID = 7276302193588633540L;
 private JLabel lblUsername,  lblPasswd,  lblCategory;
 public JTextField txtUser;
 private JPasswordField Passwd;
 private JButton btnLogin,  btnCancel;
 private JComboBox<String> box;
 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * 
	 */
	public LoginScreen() {
		super("System LoginScreen");
        this.getContentPane().setLayout(null);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		txtUser = new JTextField();
		txtUser.setBackground(Color.CYAN);
		Passwd = new JPasswordField();
		Passwd.setBackground(Color.CYAN); 
		lblUsername = new JLabel("UserName");
		lblPasswd = new JLabel("Password");
		lblCategory = new JLabel("Category");
		btnLogin = new JButton("Login");
		btnCancel = new JButton("Cancel");
		box = new JComboBox<String>(); 
		
        lblUsername.setBounds(40, 30, 100, 25);
        lblPasswd.setBounds(40, 65, 100, 25);
        lblCategory.setBounds(40, 100, 100, 25);
        txtUser.setBounds(150, 30, 160, 25);
        Passwd.setBounds(150, 65, 160, 25);
        box.setBounds(150, 100, 180, 25);
        btnLogin.setBounds(70, 150, 100, 25);
        btnCancel.setBounds(190, 150, 100, 25); 
		box.addItem("Principal");
		box.addItem("Secretary");
		box.addItem("Teacher");
		box.addItem("HOD");
		box.addItem("Curriculum Master");
		box.addItem("Account Clerk");
		
		    lblUsername.setFont(new Font("monospaced", Font.BOLD, 16));
	        lblPasswd.setFont(new Font("monospaced", Font.BOLD, 16));
	        lblCategory.setFont(new Font("monospaced", Font.BOLD, 16));
	        box.setFont(new Font("monospaced", Font.BOLD, 16));
	        txtUser.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
	        Passwd.setFont(new Font("monospaced", Font.CENTER_BASELINE, 16));
		
	        ButtonListener listener = new ButtonListener();
	        btnLogin.addActionListener(listener);
	        btnCancel.addActionListener(listener);
	
		
	        this.add(lblUsername);
	        this.add(lblPasswd);
	        this.add(box);
	        this.add(lblCategory);
	        this.add(txtUser);
	        this.add(Passwd);
	        this.add(btnLogin);
	        this.add(btnCancel);
	       this.setVisible(true); 
	}
	 
	public void Login(){
		 String username = txtUser.getText();
		@SuppressWarnings("deprecation")
		String password =  Passwd.getText();
	    if(username.equalsIgnoreCase("admin")&& password.equalsIgnoreCase("admin")){
	     
	    	 this.dispose();
	    	 LoadMDIWindow(); 
	    }else{
	    	 System.exit(0);
	    }
	    
	}


	private void LoadMDIWindow() {
		if (box.getSelectedItem().equals("Principal")) {
            new MainWindow().LoginManager();            
        } else if(box.getSelectedItem().equals("Secretary")){
        	new MainWindow().LoginSecretary();
        }else if(box.getSelectedItem().equals("Teacher")){
        	new MainWindow().LoginTeacher();
        }else if(box.getSelectedItem().equals("HOD")){
        	new MainWindow().LoginHOD();
        }else if(box.getSelectedItem().equals("Curriculum Master")){
        	new MainWindow().LoginCMaster(); 
        }else{
        	new MainWindow().LoginClerk();
        }
		
	}

	  private class ButtonListener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogin) {
                if (txtUser.getText() == null || txtUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter username", "Missing field", JOptionPane.DEFAULT_OPTION);
                    txtUser.requestFocus();
                    return;
                }
                if (Passwd.getText() == null || Passwd.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter password", "Missing field", JOptionPane.DEFAULT_OPTION);
                    Passwd.requestFocus();
                    return;
                }
                Login();
            } else if (e.getSource() == btnCancel) {
                System.exit(0);
            }//if else closed
			
		}
		  
	  }
	
	
	
}
