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
public class Cat1DAO extends DBConnectDAO implements SchoolCat1DAO {
	
	private static Cat1DAO cat1DAO;
	private Logger logger = Logger.getLogger(this.getClass());
	public static Cat1DAO getInstance(){
		if(cat1DAO == null){
			cat1DAO = new Cat1DAO();
		}
		return cat1DAO;
	}

	/**
	 * 
	 */
	public Cat1DAO() {
		super();
	}
 
	public Cat1DAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolCat1DAO#hasCat1(com.yahoo.petermwenda83.bean.exam.result.Perfomance, double)
	 */
	
	public boolean hasCat1(Perfomance perfomance) {
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
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolCat1DAO#putCat1(com.yahoo.petermwenda83.bean.exam.result.Perfomance, double)
	 */
	@Override
	public boolean putCat1(Perfomance perfomance, double cat1) {
		boolean success = true;
		if(hasCat1(perfomance)) {
			try( 
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Perfomance " +
							"SET Cat1 =? WHERE StudentUuid = ? AND ExamDetailUuid =?;");				
					) {
				    
				    pstmt.setDouble(1, cat1);
				    pstmt.setString(2, perfomance.getStudentUuid());
		            pstmt.setString(3, perfomance.getExamDetailUuid());
					pstmt.executeUpdate();
				
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by updating the Performance of '" + perfomance +
						" of " + cat1+"'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
			
			
		} else { 
			try(	
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Perfomance(Studentuuid,"
							+ "ExamDetailUuid,Cat1,SysUser,SubmitDate) VALUES(?,?,?,?,?);");				
					) {
				    
				   
				    pstmt.setString(1, perfomance.getStudentUuid());
		            pstmt.setString(2, perfomance.getExamDetailUuid());
		            pstmt.setDouble(3, cat1);
		            pstmt.setString(4, perfomance.getSysUser());
		            pstmt.setTimestamp(5, new Timestamp(perfomance.getSubmitDate().getTime()));	
					pstmt.executeUpdate();		
						
										
			} catch(SQLException e) {
				logger.error("SQLException while adding by creating the Performance of '" + perfomance +
						"' of " + cat1 + "'.");
				logger.error(ExceptionUtils.getStackTrace(e));
				 System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
						
		
		return success;
	}

	
}
