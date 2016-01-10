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
 * ##### SchoolAccount Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.persistence.guardian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.student.guardian.StudentSponsor;
import com.yahoo.petermwenda83.persistence.GenericDAO;


/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class SponsorsDAO extends GenericDAO implements SchoolSponsorsDAO {

	private static SponsorsDAO sponsorsDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static SponsorsDAO getInstance(){
		
		if(sponsorsDAO == null){
			sponsorsDAO = new SponsorsDAO();		
		}
		return sponsorsDAO;
	}
	
	/**
	 * 
	 */
	public SponsorsDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public SponsorsDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	@Override
	public StudentSponsor getSponsor(String studentUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean putSponsor(StudentSponsor sponser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSponsor(StudentSponsor sponsor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSponsor(StudentSponsor sponser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StudentSponsor> getStudentSponsorList() {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
