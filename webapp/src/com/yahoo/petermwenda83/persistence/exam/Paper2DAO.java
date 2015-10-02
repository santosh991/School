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
public class Paper2DAO extends DBConnectDAO implements SchoolPaper2DAO {
	private static Paper2DAO Paper2DAO;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static Paper2DAO getInstance(){
		if(Paper2DAO == null){
			Paper2DAO = new Paper2DAO();
		}
		return Paper2DAO;
	
     }
	/**
	 * 
	 */
	public Paper2DAO() {
		super();
	}
	
	public Paper2DAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
		

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPaper2DAO#hasPaper2(com.yahoo.petermwenda83.bean.exam.result.Perfomance)
	 */
	@Override
	public boolean hasPaper2(Perfomance perfomance) {
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
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPaper2DAO#putPaper2(com.yahoo.petermwenda83.bean.exam.result.Perfomance, double)
	 */
	@Override
	public boolean putPaper2(Perfomance perfomance, double Paper2) {
		boolean success = true;
		if(hasPaper2(perfomance)) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Perfomance " +
							"SET Paper2 =? WHERE StudentUuid = ? AND ExamDetailUuid =?;");				
					) {
				    
				    pstmt.setDouble(1, Paper2);
				    pstmt.setString(2, perfomance.getStudentUuid());
		            pstmt.setString(3, perfomance.getExamDetailUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the Performance of '" + perfomance +
						" of " + Paper2+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Perfomance(Studentuuid,"
							+ "ExamDetailUuid,Paper2,SysUser,SubmitDate) VALUES(?,?,?,?,?);");				
					) {
				    
				   
				    pstmt.setString(1, perfomance.getStudentUuid());
		            pstmt.setString(2, perfomance.getExamDetailUuid());
		            pstmt.setDouble(3, Paper2);
		            pstmt.setString(4, perfomance.getSysUser());
		            pstmt.setTimestamp(5, new Timestamp(perfomance.getSubmitDate().getTime()));	
					pstmt.executeUpdate();		
						
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the Performance of '" + perfomance +
						"' of " + Paper2 + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

}
