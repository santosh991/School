/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.ExamDetail;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class ExamDetailDAO extends DBConnectDAO implements SchoolExamDetailDAO {
	private static ExamDetailDAO examDetailDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	/**
	 * @return examDAO
	 * 
	 */
	public static ExamDetailDAO getInstance() {
		if(examDetailDAO == null){
			examDetailDAO = new ExamDetailDAO();		
			}
		return examDetailDAO;
	}
	
	public ExamDetailDAO() {
		super();
	}


	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public ExamDetailDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.detail.SchoolExamDetailDAO#get(java.lang.String)
	 */
	public ExamDetail get(String SchoolAccountUuid, String ExamUuid) {
		ExamDetail examDetail = new ExamDetail();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ExamDetail "
        	      		+ "WHERE SchoolAccountUuid = ? AND ExamUuid =?;");       
     		
     		){
     	
     	     pstmt.setString(1, SchoolAccountUuid);
     	     pstmt.setString(2, ExamUuid);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	        	examDetail  = beanProcessor.toBean(rset,ExamDetail.class);
	   }
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception while trying to get ExamDetail for SchoolAccountUuid:"
     	  		+ " " + SchoolAccountUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return examDetail; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.detail.SchoolExamDetailDAO#get(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ExamDetail get(String SchoolAccountUuid,String ExamUuid, String Term, String Year) {
		ExamDetail examDetail = new ExamDetail();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ExamDetail "
        	      		+ "WHERE SchoolAccountUuid = ? AND ExamUuid=? AND Term =? AND Year =?;");       
     		
     		){
     	
     	     pstmt.setString(1, SchoolAccountUuid);
     	     pstmt.setString(2, ExamUuid);
     	     pstmt.setString(3, Term);
     	     pstmt.setString(4, Year);
	         rset = pstmt.executeQuery();
	        while(rset.next()){
	
	        	examDetail  = beanProcessor.toBean(rset,ExamDetail.class);
	   }
     	
     }catch(SQLException e){
     	  logger.error("SQL Exception while trying to get ExamDetail for SchoolAccountUuid:"
     	  		+ " " + SchoolAccountUuid+" And Term"+Term+" And Year"+Year);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return examDetail; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.detail.SchoolExamDetailDAO#put(com.yahoo.petermwenda83.bean.exam.ExamDetail)
	 */
	public boolean put(ExamDetail examDetail) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ExamDetail (Uuid,SchoolAccountUuid,ExamUuid,ClassRoomUuid,"
						+ "SubjectUuid,Term,Year,OutOf)"
						+ " VALUES (?,?,?,?,?,?,?,?);");
    		){
			   
			   
			    pstmt.setString(1, examDetail.getUuid());
			    pstmt.setString(2, examDetail.getSchoolAccountUuid());
			    pstmt.setString(3, examDetail.getExamUuid());
	            pstmt.setString(4, examDetail.getClassRoomUuid());
	            pstmt.setString(5, examDetail.getSubjectUuid());
	            pstmt.setString(6, examDetail.getTerm());
	            pstmt.setString(7, examDetail.getYear());
	            pstmt.setInt(8, examDetail.getOutOf());
	           
	            pstmt.executeUpdate();

			 
		 }catch(SQLException e){
		   logger.error("SQL Exception trying to put ExamDetail: "+examDetail);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }  
		 		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.detail.SchoolExamDetailDAO#update(com.yahoo.petermwenda83.bean.exam.ExamDetail)
	 */
	public boolean update(ExamDetail examDetail) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement("UPDATE ExamDetail SET Term=?,"
			      			+ "Year=?, OutOf=? WHERE SchoolAccountUuid =? AND ExamUuid =? AND ClassRoomUuid =? AND SubjectUuid =?;");				
  		      ){
			   
			    pstmt.setString(1, examDetail.getTerm());
	            pstmt.setString(2, examDetail.getYear());
	            pstmt.setInt(3, examDetail.getOutOf());
			    pstmt.setString(4, examDetail.getSchoolAccountUuid());
			    pstmt.setString(5, examDetail.getExamUuid());
	            pstmt.setString(6, examDetail.getClassRoomUuid());  
	            pstmt.setString(7, examDetail.getSubjectUuid());  
	           
	            pstmt.executeUpdate();

		 }catch(SQLException e){
		   logger.error("SQL Exception trying to update ExamDetail: "+examDetail);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.detail.SchoolExamDetailDAO#delete(com.yahoo.petermwenda83.bean.exam.ExamDetail)
	 */
	public boolean delete(ExamDetail examDetail) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ExamDetail"
				  		+ " WHERE SchoolAccountUuid =? AND ExamUuid =? AND ClassRoomUuid =? AND SubjectUuid =?;");				
		      ){
			    pstmt.setString(1, examDetail.getSchoolAccountUuid());
			    pstmt.setString(2, examDetail.getExamUuid());
	            pstmt.setString(3, examDetail.getClassRoomUuid());          
	            pstmt.setString(4, examDetail.getSubjectUuid());   
	            pstmt.executeUpdate();

		 }catch(SQLException e){
		   logger.error("SQL Exception trying to update ExamDetail: "+examDetail);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.detail.SchoolExamDetailDAO#getAllExamdetails(java.lang.String)
	 */
	public List<ExamDetail> getAllExamdetails(String SchoolAccountUuid,String Term,String Year) {
		 List<ExamDetail>  list = null;
		 try(   
 		   Connection conn = dbutils.getConnection();
 		    PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM ExamDetail WHERE SchoolAccountUuid =? AND Term =? AND Year =?;");   
 		
		) {
			 pstmt.setString(1, SchoolAccountUuid); 
			 pstmt.setString(2, Term); 
			 pstmt.setString(3, Year); 
				try(ResultSet rset = pstmt.executeQuery();){
					 list = beanProcessor.toBeanList(rset, ExamDetail.class);
				}
    
    } catch(SQLException e){
 	   logger.error("SQL Exception when getting all ExamDetail for SchoolUuid"+SchoolAccountUuid+"And Term"+Term+"And Year"+Year);
       logger.error(ExceptionUtils.getStackTrace(e));
       System.out.println(ExceptionUtils.getStackTrace(e));
         }
       return list;

	}

}
