/**
 * School Management System
 * This software belong to Peter Mwenda's and Miwgi Ndungu's Company
 * copywrite peter&MigwiSoftwares.co.ltd
 */
package com.yahoo.petermwenda83.view.login;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.yahoo.petermwenda83.cache.Init;
/**
 * @author peter
 * @author <h1>mwendapeter72@gmail.com </h1>
 * @author <h1>migwindungu0@gmail.com </h1>
 *
 */
public class Main implements Runnable{
	
	 private final JFrame frame;
	    public Main(JFrame frm){
	        this.frame=frm;
	    }//constructor closed
	    public void run(){
	        frame.setVisible(true);
	    }//run method closed
	    public static void main(String args[]){
	       EventQueue.invokeLater(new Main(new LoginScreen()));
	       new Init();
	    }//main method closed


}//class closed
