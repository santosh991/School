/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
n * ### This is My Forth Year Project#########################
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

import java.awt.EventQueue;

import javax.swing.JFrame;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class Init implements Runnable{
	
	 private final JFrame frame;
	    public Init(JFrame frm){
	        this.frame=frm;
	    }//constructor closed
	    @Override
		public void run(){
	        frame.setVisible(true);
	    }//run method closed
	    public static void main(String args[]){
	       EventQueue.invokeLater(new Init(new LoginScreen()));
	      
	    }//main method closed


}//class closed
