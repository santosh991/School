/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import com.yahoo.petermwenda83.bean.exam.result.Perfomance;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class PerformanceDAO extends DBConnectDAO implements SchoolPerformanceDAO {

	private static PerformanceDAO performanceDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static PerformanceDAO getInstance() {
		if(performanceDAO == null){
			performanceDAO = new PerformanceDAO();		
			}
		return performanceDAO;
	}
	
	public PerformanceDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public PerformanceDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerformanceDAO#getByStudentUuid(java.lang.String)
	 */
	public Perfomance get(String StudentUuid,String ExamDetailUuid) {
		Perfomance p = new Perfomance();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Perfomance "
        	      		+ "WHERE StudentUuid = ? AND ExamDetailUuid =? ;");       
     		
     		){
     	
     	     pstmt.setString(1, StudentUuid);
     	     pstmt.setString(2, ExamDetailUuid);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	        	p  = beanProcessor.toBean(rset,Perfomance.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception while trying to get Student Perfomance with StudentUuid:"
     	  		+ " " + StudentUuid+"AND ExamDetailUuid "+ExamDetailUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return p; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerformanceDAO#delete(com.yahoo.petermwenda83.bean.exam.result.Perfomance)
	 */
	public boolean delete(Perfomance perfomance) {
		 boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM perfomance WHERE"
	         	      		+ " StudentUuid = ? AND ExamDetailUuid =?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, perfomance.getStudentUuid());
	      	 pstmt.setString(2, perfomance.getExamDetailUuid());
		     pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting perfomance: " +perfomance);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerformanceDAO#getAllPerfomance()
	 */
	public List<Perfomance> getAllPerfomance(String ExamDetailUuid) {
		  List<Perfomance>  list = null;
		 try(   
 		   Connection conn = dbutils.getConnection();
 		    PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM Perfomance WHERE ExamDetailUuid =?;");   
 		
		) {
			 pstmt.setString(1, ExamDetailUuid); 
				try(ResultSet rset = pstmt.executeQuery();){
					 list = beanProcessor.toBeanList(rset, Perfomance.class);
				}
    
    } catch(SQLException e){
 	   logger.error("SQL Exception when getting all Perfomance");
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
         }
       return list;

	 }

}
