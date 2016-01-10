

/*************************************************************
 * Online School Management System                           *
 * Forth Year Project                                        *
 * Maasai Mara University                                    *
 * Bachelor of Science(Computer Science)                     *
 * Year:2015-2016                                            *
 * Name: Njeru Mwenda Peter                                  *
 * ADM NO : BS02/009/2012                                    *
 *                                                           *
 *************************************************************/
package com.yahoo.petermwenda83.persistence.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.CatOne;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 *  Persistence implementation for {@link SchoolPerfomanceDAO}
 *  
 *  Copyright (c) FasTech Solutions Ltd., Dec 02, 2015
 * 
 * @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class PerfomanceDAO extends GenericDAO  implements SchoolPerfomanceDAO {
	
	
	private static PerfomanceDAO perfomanceDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static PerfomanceDAO getInstance(){
		
		if(perfomanceDAO == null){
			perfomanceDAO = new PerfomanceDAO();		
		}
		return perfomanceDAO;
	}
	
	/**
	 * 
	 */
	public PerfomanceDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public PerfomanceDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerfomanceDAO#getPerformance(java.lang.String)
	 */
	@Override
	public List<Perfomance> getPerformance(String schoolAccountUuid,String classRoomUuid,String studentUuid) {
		List<Perfomance> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND studentUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);  
         	   pstmt.setString(3, studentUuid);  
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, Perfomance.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting Perfomance List for student" +studentUuid+ " of school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerfomanceDAO#deletePerfomance(com.yahoo.petermwenda83.bean.exam.Perfomance)
	 */
	@Override
	public boolean deletePerfomance(Perfomance perfomance) {
		boolean success = true;
		ResultSet rset = null;
		try(
				Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Perfomance"
						+ " WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ?;");       

				){

			pstmt.setString(1, perfomance.getSchoolAccountUuid()); 
			pstmt.setString(2, perfomance.getClassRoomUuid()); 
			pstmt.setString(3, perfomance.getStudentUuid()); 
			rset = pstmt.executeQuery();
			while(rset.next()){

				perfomance  = beanProcessor.toBean(rset,CatOne.class);
			}



		}catch(SQLException e){
			logger.error("SQL Exception when deleting  Perfomance: " + perfomance);
			logger.error(ExceptionUtils.getStackTrace(e));

		}

		return success; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerfomanceDAO#getPerfomanceList(java.lang.String)
	 */
	@Override
	public List<Perfomance> getPerfomanceList(String schoolAccountUuid,String classRoomUuid) {
		List<Perfomance> list = new ArrayList<>();
        try (//SELECT DISTINCT studentuuid FROM perfomance WHERE classroomuuid = '4DA86139-6A72-4089-8858-6A3A613FDFE6';
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Perfomance WHERE"
     	         		+ " SchoolAccountUuid = ? AND classRoomUuid = ? ;"); 
        		
        		 PreparedStatement pstmt2 = conn.prepareStatement("SELECT DISTINCT studentuuid FROM perfomance WHERE"
        		 		+ " SchoolAccountUuid = ? AND classRoomUuid = ?;");
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, Perfomance.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting List  of Perfomance for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
      
        return list;
	}
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerfomanceDAO#getPerfomanceListDistinct(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Perfomance> getPerfomanceListDistinct(String schoolAccountUuid, String classRoomUuid) {
		List<Perfomance> list = new ArrayList<>();
        try (
        		 Connection conn = dbutils.getConnection();
        		 PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT studentuuid FROM perfomance WHERE"
        		 		+ " SchoolAccountUuid = ? AND classRoomUuid = ?;");
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, Perfomance.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting DISTINCT StudentUuid List  of Perfomance for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
      
        return list;
	}
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolPerfomanceDAO#getClassPerfomanceList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Perfomance> getClassPerfomanceList(String schoolAccountUuid, String ClassesUuid) {
		List<Perfomance> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Perfomance WHERE SchoolAccountUuid = ?"
     	         		+ " AND ClassesUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, ClassesUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, Perfomance.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting List  of Perfomance for school" + schoolAccountUuid +" and ClassesUuid" +ClassesUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	

}
