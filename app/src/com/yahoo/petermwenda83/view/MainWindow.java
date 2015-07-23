/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
 */
package com.yahoo.petermwenda83.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
/**
 * @author peter
 * @author <h1>mwendapeter72@gmail.com </h1>
 * @author <h1>migwindungu0@gmail.com </h1>
 *
 */
public class MainWindow extends JFrame implements WindowListener {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -3543603760216095482L;
	private JMenu mnuManage,mnuExamination;
    private JMenuItem mnuUser,nmuTeacher,mnuNonTeaching,mnuExit;
    private JMenuItem mnuExam; 
    private JLabel welcome = new JLabel("Welcome:  Today is " + new java.util.Date() + "  Peter and Migwi S/W DEV ", JLabel.CENTER);
    public static JDesktopPane desktop;
    public JButton NewJButton;
	
	
	/**
	 * 
	 */
	public MainWindow() {
		super("School Management System");

        this.setJMenuBar(CreateJMenuBar());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocation(0, 0);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.addWindowListener(this);

        welcome.setFont(new Font("monospaced", Font.PLAIN, 12));
        welcome.setForeground(Color.blue);
        desktop = new JDesktopPane();
        desktop.setBorder(BorderFactory.createEmptyBorder());
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        getContentPane().add(welcome, BorderLayout.PAGE_END, JLabel.CENTER);
        getContentPane().add(desktop, BorderLayout.CENTER);

        setVisible(true);
	}
	
	protected JMenuBar CreateJMenuBar() {
        JMenuBar menubar = new JMenuBar();
        /**********CREATING Principal MENU***********************/
        mnuManage = new JMenu("Principal");
        mnuManage.setForeground((Color.blue));
        mnuManage.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuManage.setMnemonic('P');
        mnuManage.setEnabled(false);

        mnuUser = new JMenuItem("Manage Users");
        mnuUser.setForeground(Color.blue);
        mnuUser.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuUser.setMnemonic('U');
        mnuUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        mnuUser.setActionCommand("newuser");
        mnuUser.addActionListener(menulistener);
        
        nmuTeacher = new JMenuItem("Manage Teachers");
        nmuTeacher.setForeground(Color.blue);
        nmuTeacher.setFont(new Font("monospaced", Font.PLAIN, 12));
        nmuTeacher.setMnemonic('T');
        nmuTeacher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        nmuTeacher.setActionCommand("nmuTeacher");
        nmuTeacher.addActionListener(menulistener);
        
        mnuNonTeaching = new JMenuItem("Manage NonTeaching staff");
        mnuNonTeaching.setForeground(Color.blue);
        mnuNonTeaching.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuNonTeaching.setMnemonic('N');
        mnuNonTeaching.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mnuNonTeaching.setActionCommand("mnuNonTeaching");
        mnuNonTeaching.addActionListener(menulistener);
        
        mnuExit = new JMenuItem("Exit");
        mnuExit.setForeground(Color.blue);
        mnuExit.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuExit.setMnemonic('X');
        mnuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        mnuExit.setActionCommand("exit");
        mnuExit.addActionListener(menulistener);
        
        mnuManage.add(mnuUser);
        mnuManage.addSeparator();
        mnuManage.add(nmuTeacher);
        mnuManage.addSeparator();
        mnuManage.add(mnuNonTeaching);
        mnuManage.addSeparator();
        mnuManage.add(mnuExit);
        menubar.add(mnuManage);
        
        /**********CREATING Examination MENU***********************/
        mnuExamination = new JMenu("Examination");
        mnuExamination.setForeground((Color.blue));
        mnuExamination.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuExamination.setMnemonic('E');
        mnuExamination.setEnabled(false);
        
        mnuExam = new JMenuItem("Manage Main Exams");
        mnuExam.setForeground(Color.blue);
        mnuExam.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuExam.setMnemonic('I');
        mnuExam.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        mnuExam.setActionCommand("newuser");
        mnuExam.addActionListener(menulistener);
        
        mnuExamination.add(mnuExam);
        menubar.add(mnuExamination);
        
        
        

        return menubar;
    }//CreateJMenuBar()closed
	 ActionListener menulistener = new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 String ActCmd = e.getActionCommand();
			 if (ActCmd.equalsIgnoreCase("exit")) {
	                ConfirmExit();
	            } 
		 }
	 };
	
	

	
	/**
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	public void windowOpened(WindowEvent e) {
		
		
	}

	
	/**
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent e) {
		 ConfirmExit();
		
	}

	private void ConfirmExit() {
		 String ObjButtons[] = {"Yes", "No"};
	        int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure to exit?",
	                "Confirm exit", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
	        if (PromptResult == 0) {
	            System.exit(0);
	        }//if closed
		
	}

	
	/**
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	protected boolean isLoaded(String FormTitle) {
        JInternalFrame Form[] = desktop.getAllFrames();
        for (int i = 0; i < Form.length; i++) {
            if (Form[i].getTitle().equalsIgnoreCase(FormTitle)) {
                Form[i].show();
                try {
                    Form[i].setIcon(true);
                    Form[i].setSelected(true);
                } catch (Exception e) {
                }
                return true;
            }
        }
        return false;
    }//isLoaded() closed
	

	public void LoginManager() {
		mnuManage.setEnabled(true);
		mnuExamination.setEnabled(true);
	      
		
	}

	@SuppressWarnings("deprecation")
	public void LoginSecretary() {
		mnuManage.hide();
		mnuExamination.hide(); 
		
	}
	@SuppressWarnings("deprecation")
	public void LoginTeacher() {
		mnuManage.hide();
		mnuExamination.setEnabled(true); 
		
	}
	@SuppressWarnings("deprecation")
	public void LoginHOD() {
		mnuManage.hide();
		mnuExamination.setEnabled(true);
		
	}
	@SuppressWarnings("deprecation")
	public void LoginCMaster() {
		mnuManage.hide();
		mnuExamination.setEnabled(true);
		
	}
	@SuppressWarnings("deprecation")
	public void LoginClerk() {
		mnuManage.hide();
		mnuExamination.hide(); 
		
	}

}

