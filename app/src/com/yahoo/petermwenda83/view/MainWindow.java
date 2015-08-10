/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
 */
package com.yahoo.petermwenda83.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.glass.ui.Pixels.Format;
import com.yahoo.petermwenda83.view.exam.ExamTypeView;
import com.yahoo.petermwenda83.view.exam.AddExamtype;
import com.yahoo.petermwenda83.view.login.LoginScreen;
import com.yahoo.petermwenda83.view.subject.SubjectView;

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
	private JMenu mnuUsers,mnuExamination,mnuStudents,mnuSubjects,mnuReports;
    private JMenuItem mnuUser,nmuTeacher,mnuNonTeaching,mnuExit;
    private JMenuItem mnuMain; 
    private JMenuItem mnuStudent;
    private JMenuItem mnuSubject;
    private JMenuItem mnuReportForm,mnuMeritList;
    private JPanel panel;
    private JLabel welcome;
    public static JDesktopPane desktop;
    public JButton NewJButton;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
   
    
   
   
    
   
	/**
	 * 
	 */
	public MainWindow(String logname ) {
		super("School Management System");
        
        this.setJMenuBar(CreateJMenuBar());
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocation(0, 0);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.addWindowListener(this);
        
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String today = formatter.format(new Date()); 
          
        	 welcome  = new JLabel("Hi "+logname+ ",Today is on ( " +today+")", JLabel.CENTER);
        	
        	 
        
        panel = new JPanel();
        panel.setLayout(null);
       
        panel.setSize((screen.width), 50); 
        panel.setLocation(0, 650); 
       
        welcome.setBounds(0, 0,(screen.width), 30);
        
        panel.setBackground(Color.MAGENTA); 
     
        
        welcome.setFont(new Font("monospaced", Font.PLAIN, 12));
        welcome.setForeground(Color.blue);
        
        desktop = new JDesktopPane();
        desktop.setLayout(null);
        desktop.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        desktop.setLocation((screen.width - 500) / 2, ((screen.height - 350) / 2));
        desktop.setBackground(Color.CYAN); 
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        
        panel.add(welcome); 
        
        getContentPane().add(panel);
      
       // getContentPane().add(welcome);
        
        getContentPane().add(desktop);
        
       // getContentPane().add(welcome, BorderLayout.PAGE_END, JLabel.CENTER);
       // getContentPane().add(desktop, BorderLayout.CENTER);

        setVisible(true);
	}
	
	protected JMenuBar CreateJMenuBar() {
        JMenuBar menubar = new JMenuBar();
        /**********CREATING Manage User MENU***********************/
        mnuUsers = new JMenu("System Users");
        mnuUsers.setForeground((Color.blue));
        mnuUsers.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuUsers.setMnemonic('P');
        mnuUsers.setEnabled(false);

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
        
        mnuUsers.add(mnuUser);
        mnuUsers.addSeparator();
        mnuUsers.add(nmuTeacher);
        mnuUsers.addSeparator();
        mnuUsers.add(mnuNonTeaching);
        mnuUsers.addSeparator();
        mnuUsers.add(mnuExit);
        menubar.add(mnuUsers);
        
        /**********CREATING Examination MENU***********************/
        mnuExamination = new JMenu("Examination");
        mnuExamination.setForeground((Color.blue));
        mnuExamination.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuExamination.setMnemonic('E');
        mnuExamination.setEnabled(false);
       
        mnuMain = new JMenuItem("Manage Main Exams");
        mnuMain.setForeground(Color.blue);
        mnuMain.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuMain.setMnemonic('M');
        mnuMain.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        mnuMain.setActionCommand("mnuMain");
        mnuMain.addActionListener(menulistener);
        
        mnuExamination.add(mnuMain);
        menubar.add(mnuExamination);
        
       
        /**********CREATING Students MENU***********************/
        mnuStudents = new JMenu("Students");
        mnuStudents.setForeground((Color.blue));
        mnuStudents.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuStudents.setMnemonic('K');
        mnuStudents.setEnabled(false);
        
        
        mnuStudent = new JMenuItem("Manage Students");
        mnuStudent.setForeground(Color.blue);
        mnuStudent.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuStudent.setMnemonic('S');
        mnuStudent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        mnuStudent.setActionCommand("mnuStudent");
        mnuStudent.addActionListener(menulistener);
        
        mnuStudents.add(mnuStudent);
        menubar.add(mnuStudents);
        
        /**********CREATING Subjects MENU***********************/
        mnuSubjects = new JMenu("Subjects");
        mnuSubjects.setForeground((Color.blue));
        mnuSubjects.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuSubjects.setMnemonic('Z');
        mnuSubjects.setEnabled(false);
        
        
        mnuSubject = new JMenuItem("Manage Subjects");
        mnuSubject.setForeground(Color.blue);
        mnuSubject.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuSubject.setMnemonic('J');
        mnuSubject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
        mnuSubject.setActionCommand("mnuSubject");
        mnuSubject.addActionListener(menulistener);
        
        mnuSubjects.add(mnuSubject);
        menubar.add(mnuSubjects);
        
        /**********CREATING Reports MENU***********************/
        mnuReports = new JMenu("Reports");
        mnuReports.setForeground((Color.blue));
        mnuReports.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuReports.setMnemonic('O');
        mnuReports.setEnabled(false);
        
        //mnuReportForm,mnuMeritList;
        mnuReportForm = new JMenuItem("Report Forms");
        mnuReportForm.setForeground(Color.blue);
        mnuReportForm.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuReportForm.setMnemonic('F');
        mnuReportForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        mnuReportForm.setActionCommand("mnuReportForm");
        mnuReportForm.addActionListener(menulistener);
        
        
        mnuMeritList = new JMenuItem("Merit Lists");
        mnuMeritList.setForeground(Color.blue);
        mnuMeritList.setFont(new Font("monospaced", Font.PLAIN, 12));
        mnuMeritList.setMnemonic('L');
        mnuMeritList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        mnuMeritList.setActionCommand("mnuMeritList");
        mnuMeritList.addActionListener(menulistener);
        
        mnuReports.add(mnuReportForm);
        mnuReports.addSeparator();
        mnuReports.add(mnuMeritList);
        menubar.add(mnuReports);
        

        return menubar;
    }//CreateJMenuBar()closed
	 ActionListener menulistener = new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 String ActCmd = e.getActionCommand();
			 if (ActCmd.equalsIgnoreCase("exit")) {
	                ConfirmExit();//
	            }  else if (ActCmd.equalsIgnoreCase("mnuSubject")) {
	            	SubjectView frm = new SubjectView();
	                desktop.add(frm);
	                frm.setVisible(true);
	            } else if (ActCmd.equalsIgnoreCase("mnuMain")) {
	            	ExamTypeView epanel = new ExamTypeView();
	                desktop.add(epanel);
	                epanel.setVisible(true);
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
		mnuUsers.setEnabled(true);
		mnuExamination.setEnabled(true);
		mnuStudents.setEnabled(true);
		mnuSubjects.setEnabled(true);
		mnuReports.setEnabled(true);
	      
		
	}

	@SuppressWarnings("deprecation")
	public void LoginSecretary() {
		mnuUsers.hide();
		mnuExamination.hide(); 
		mnuStudents.setEnabled(true);
		mnuSubjects.hide(); 
		mnuReports.hide(); 
		
	}
	@SuppressWarnings("deprecation")
	public void LoginTeacher() {
		mnuUsers.hide();
		mnuExamination.setEnabled(true); 
		mnuStudents.hide();
		mnuSubjects.hide();
		mnuReports.setEnabled(true);
		
	}
	@SuppressWarnings("deprecation")
	public void LoginHOD() {
		mnuUsers.hide();
		mnuExamination.setEnabled(true);
		mnuStudents.hide();
		mnuSubjects.setEnabled(true);
		mnuReports.setEnabled(true);
		
	}
	@SuppressWarnings("deprecation")
	public void LoginCMaster() {
		mnuUsers.hide();
		mnuExamination.setEnabled(true);
		mnuStudents.hide();
		mnuSubjects.setEnabled(true);
		mnuReports.setEnabled(true);
		
	}
	@SuppressWarnings("deprecation")
	public void LoginClerk() {
		mnuUsers.hide();
		mnuExamination.hide(); 
		mnuStudents.setEnabled(true);
		mnuSubjects.hide(); 
		mnuReports.hide(); 
		
	}

}

