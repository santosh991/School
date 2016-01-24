/**
 * 
 */
package com.yahoo.petermwenda83.persistence.classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.classroom.Classes;
import com.yahoo.petermwenda83.bean.money.Deposit;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class ClassesDAO extends GenericDAO implements SchoolClassesDAO {
	
	private static ClassesDAO classesDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static ClassesDAO getInstance(){
		
		if(classesDAO == null){
			classesDAO = new ClassesDAO();		
		}
		return classesDAO;
	}
	
	/**
	 * 
	 */
	public ClassesDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public ClassesDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.classroom.SchoolClassesDAO#getClass(java.lang.String)
	 */
	@Override
	public Classes getClass(String Uuid) {
		Classes Classes = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Classes WHERE Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 Classes  = beanProcessor.toBean(rset,Classes.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting Classes with Uuid: " + Uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return Classes; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.classroom.SchoolClassesDAO#getClassList()
	 */
	@Override
	public List<Classes> getClassList() {
		List<Classes>  list = null;
		 try(   
 		Connection conn = dbutils.getConnection();
 		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Classes ;");   
 		ResultSet rset = pstmt.executeQuery();
		  ) {
 	
      list = beanProcessor.toBeanList(rset, Classes.class);

    } catch(SQLException e){
 	   logger.error("SQL Exception when getting all Deposit");
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
    }
       return list;
	}

}
