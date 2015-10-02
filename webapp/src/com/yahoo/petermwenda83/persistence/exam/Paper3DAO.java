/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.result.Perfomance;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class Paper3DAO extends DBConnectDAO implements SchoolPaper3DAO {
	private static Paper3DAO Paper3DAO;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static Paper3DAO getInstance(){
		if(Paper3DAO == null){
			Paper3DAO = new Paper3DAO();
		}
		return Paper3DAO;
	
     }
	/**
	 * 
	 */
	public Paper3DAO() {
		super();
	}
	
	public Paper3DAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
		

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPaper3DAO#hasPaper3(com.yahoo.petermwenda83.bean.exam.result.Perfomance)
	 */
	@Override
	public boolean hasPaper3(Perfomance perfomance) {
		boolean found = false;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT StudentUuid,ExamDetailUuid FROM Perfomance "
	        			+ "WHERE StudentUuid = ? AND ExamDetailUuid =? ;");
      		){
			 
	            pstmt.setString(1, perfomance.getStudentUuid());
	            pstmt.setString(2, perfomance.getExamDetailUuid());
	            try(
						ResultSet rset = pstmt.executeQuery();
						
						) {
					
					if(rset.next()) {
						String stuUuid = rset.getString("StudentUuid");
						String examdUuid = rset.getString("ExamDetailUuid");
								if(  StringUtils.equals(stuUuid, perfomance.getStudentUuid()) &&
										StringUtils.equals(examdUuid, perfomance.getExamDetailUuid())){
									found = true;
								}
					} 
				}
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception Trying to check whether Student: "+perfomance);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             found = false;
		 }
		 
		
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPaper3DAO#putPaper3(com.yahoo.petermwenda83.bean.exam.result.Perfomance, double)
	 */
	@Override
	public boolean putPaper3(Perfomance perfomance, double Paper3) {
		boolean success = true;
		if(hasPaper3(perfomance)) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Perfomance " +
							"SET Paper3 =? WHERE StudentUuid = ? AND ExamDetailUuid =?;");				
					) {
				    
				    pstmt.setDouble(1, Paper3);
				    pstmt.setString(2, perfomance.getStudentUuid());
		            pstmt.setString(3, perfomance.getExamDetailUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the Performance of '" + perfomance +
						" of " + Paper3+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Perfomance(Studentuuid,"
							+ "ExamDetailUuid,Paper3,SysUser,SubmitDate) VALUES(?,?,?,?,?);");				
					) {
				    
				   
				    pstmt.setString(1, perfomance.getStudentUuid());
		            pstmt.setString(2, perfomance.getExamDetailUuid());
		            pstmt.setDouble(3, Paper3);
		            pstmt.setString(4, perfomance.getSysUser());
		            pstmt.setTimestamp(5, new Timestamp(perfomance.getSubmitDate().getTime()));	
					pstmt.executeUpdate();		
						
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the Performance of '" + perfomance +
						"' of " + Paper3 + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

}
