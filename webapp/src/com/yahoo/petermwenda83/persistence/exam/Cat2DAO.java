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
public class Cat2DAO extends DBConnectDAO implements SchoolCat2DAO {
	
	private static Cat2DAO cat2DAO;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static Cat2DAO getInstance(){
		if(cat2DAO == null){
			cat2DAO = new Cat2DAO();
		}
		return cat2DAO;
	}

	/**
	 * 
	 */
	public Cat2DAO() {
		super();
	}
	
	public Cat2DAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolCat2DAO#hasCat2(com.yahoo.petermwenda83.bean.exam.result.Perfomance)
	 */
	@Override
	public boolean hasCat2(Perfomance perfomance) {
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
			 logger.error("SQL Exception Trying to check whether Student Cat2 mark: "+perfomance);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             found = false;
		 }
		 
		
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolCat2DAO#putCat2(com.yahoo.petermwenda83.bean.exam.result.Perfomance, double)
	 */
	@Override
	public boolean putCat2(Perfomance perfomance, double cat2) {
		boolean success = true;
		if(hasCat2(perfomance)) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Perfomance " +
							"SET Cat2 =? WHERE StudentUuid = ? AND ExamDetailUuid =?;");				
					) {
				    
				    pstmt.setDouble(1, cat2);
				    pstmt.setString(2, perfomance.getStudentUuid());
		            pstmt.setString(3, perfomance.getExamDetailUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the Performance of '" + perfomance +
						" of " + cat2+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Perfomance(Studentuuid,"
							+ "ExamDetailUuid,Cat2,SysUser,SubmitDate) VALUES(?,?,?,?,?);");				
					) {
				    
				   
				    pstmt.setString(1, perfomance.getStudentUuid());
		            pstmt.setString(2, perfomance.getExamDetailUuid());
		            pstmt.setDouble(3, cat2);
		            pstmt.setString(4, perfomance.getSysUser());
		            pstmt.setTimestamp(5, new Timestamp(perfomance.getSubmitDate().getTime()));	
					pstmt.executeUpdate();		
						
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the Performance of '" + perfomance +
						"' of " + cat2 + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

}
