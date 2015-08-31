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

import com.yahoo.petermwenda83.contoller.users.User;
import com.yahoo.petermwenda83.model.user.UsresDAO;
import com.yahoo.petermwenda83.util.SecurityUtil;
import com.yahoo.petermwenda83.view.MainWindow;

/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
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
 private static UsresDAO userDAO;
 //private static List<User> userList = new ArrayList<>();
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
        userDAO = UsresDAO.getInstance();
       
        
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
		box.addItem("Teachers");
		box.addItem("HOD");
		box.addItem("CM");
		box.addItem("Clerk");
		
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
	 



	@SuppressWarnings("deprecation")
	public void Login(){
		
		  User use = new User();
		  //StringUtils.equals(SecurityUtil.getMD5Hash(password), use)
	      use.setUsername(txtUser.getText()); 
	      String usertyp = (String) box.getSelectedItem();
	      use.setUserType(usertyp); 
	      String pass =SecurityUtil.getMD5Hash(Passwd.getText());  
	      use.setPassword(pass);  
	      
	     //  String uname = txtUser.getText();
	      
	      boolean recordfound = userDAO.getUserName(use) != null;
	    	 if(recordfound){	     
	    	// this.dispose();
	    	 this.setVisible(false);
	    	 LoadMDIWindow(); 
	    	 
	    }else{
	    JOptionPane.showMessageDialog(null, "Wrong Credentials", "Error!!", JOptionPane.DEFAULT_OPTION);
	    	// System.exit(0);
	   // System.out.println(pass); 
	    }
	    	// System.out.println(uname); 
	}


	private void LoadMDIWindow() {
		 String  logname = txtUser.getText();
		if (box.getSelectedItem().equals("Principal")) {
            new MainWindow(logname).LoginManager();             
        } else if(box.getSelectedItem().equals("Secretary")){
        	new MainWindow(logname).LoginSecretary();
        }else if(box.getSelectedItem().equals("Teachers")){
        	new MainWindow(logname).LoginTeacher();
        }else if(box.getSelectedItem().equals("HOD")){
        	new MainWindow(logname).LoginHOD();
        }else if(box.getSelectedItem().equals("CM")){
        	new MainWindow(logname).LoginCMaster(); 
        }else{
        	new MainWindow(logname).LoginClerk();
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
                if (SecurityUtil.getMD5Hash(Passwd.getText()) == null || SecurityUtil.getMD5Hash(Passwd.getText()).equals("")) {
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
