

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
import com.yahoo.petermwenda83.bean.exam.CatTwo;
import com.yahoo.petermwenda83.bean.exam.Common;
import com.yahoo.petermwenda83.bean.exam.EndTerm;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.persistence.GenericDAO;


/**
 *  Persistence implementation for {@link SchoolExamEngineDAO}
 *  
 *  Copyright (c) FasTech Solutions Ltd., Dec 02, 2015
 * 
 * @author <a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class ExamEgineDAO extends GenericDAO implements SchoolExamEngineDAO {
	
	private static ExamEgineDAO examEgineDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static ExamEgineDAO getInstance(){
		
		if(examEgineDAO == null){ 
			examEgineDAO = new ExamEgineDAO();		
		}
		return examEgineDAO;
	}
	
	/**
	 * 
	 */
	public ExamEgineDAO() {
		super();
	}
	
	/**
	 * 
	 */
	public ExamEgineDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getCatOne(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Common getCommon(String schoolAccountUuid, String classRoomUuid, String studentUuid,String subjectUuid) {
		Common common = new Common();
		ResultSet rset = null;
		try(
				Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Perfomance"
						+ " WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ?  AND SubjectUuid = ?;");       

				){

			pstmt.setString(1, schoolAccountUuid); 
			pstmt.setString(2, classRoomUuid); 
			pstmt.setString(3, studentUuid); 
			pstmt.setString(4, subjectUuid); 
			rset = pstmt.executeQuery();
			while(rset.next()){

				common  = beanProcessor.toBean(rset,Common.class);
			}



		}catch(SQLException e){
			logger.error("SQL Exception when getting Common: " + common);
			logger.error(ExceptionUtils.getStackTrace(e));

		}

		return common; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#hasCatOne(com.yahoo.petermwenda83.bean.exam.CatOne)
	 */
	@Override
	public boolean Checker(String schoolAccountUuid,String classRoomUuid,String studentUuid,String subjectUuid) {
		boolean studentexist = false;
		String school = "";
		String classroom = "";
		String student = "";
		String subject = "";
		
		ResultSet rset = null;
		try(    Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,classRoomUuid,StudentUuid,SubjectUuid FROM Perfomance "
	        			+ "WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ?  AND SubjectUuid = ?;");
      		){

	            pstmt.setString(1, schoolAccountUuid);
	            pstmt.setString(2, classRoomUuid);
	            pstmt.setString(3, studentUuid);
	            pstmt.setString(4, subjectUuid);
	            rset = pstmt.executeQuery();
	            
	            if(rset.next()){
					school = rset.getString("SchoolAccountUuid");
					classroom = rset.getString("classRoomUuid");
					student = rset.getString("StudentUuid");
					subject = rset.getString("SubjectUuid");
					
					studentexist = (school != schoolAccountUuid&&
							        classroom != classRoomUuid && 
							        student != studentUuid && 
							        subject != subjectUuid) ? true : false;
					
				}
			
			
		  }
		     catch(SQLException e){
			 logger.error("SQL Exception while getting score for  Perfomance: ");
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
            
		 }
		

		return studentexist;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#putCatOne(com.yahoo.petermwenda83.bean.exam.CatOne)
	 */
	@Override
	public boolean putScore(Perfomance perfomance) {
		boolean success = true;
		if(!Checker(perfomance.getSchoolAccountUuid(),perfomance.getClassRoomUuid(),perfomance.getStudentUuid(),perfomance.getSubjectUuid())) {
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,CatOne,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,CatTwo,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmt3 = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,EndTerm,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?);");
				){

			if(perfomance instanceof CatOne) {
			    pstmt.setString(1, perfomance.getSchoolAccountUuid());
			    pstmt.setString(2, perfomance.getStudentUuid());
			    pstmt.setString(3, perfomance.getSubjectUuid());
	            pstmt.setString(4, perfomance.getClassRoomUuid());
	            pstmt.setDouble(5, perfomance.getCatOne());
	            pstmt.setString(6, perfomance.getTeacherUuid());
	            pstmt.setString(7, perfomance.getTerm());
	            pstmt.setString(8, perfomance.getYear());
			    pstmt.executeUpdate();
			}
			else if(perfomance instanceof CatTwo) {
			    pstmt2.setString(1, perfomance.getSchoolAccountUuid());
			    pstmt2.setString(2, perfomance.getStudentUuid());
			    pstmt2.setString(3, perfomance.getSubjectUuid());
	            pstmt2.setString(4, perfomance.getClassRoomUuid());
	            pstmt2.setDouble(5, perfomance.getCatTwo());
	            pstmt2.setString(6, perfomance.getTeacherUuid());
	            pstmt2.setString(7, perfomance.getTerm());
	            pstmt2.setString(8, perfomance.getYear());
			    pstmt2.executeUpdate();
			}
			else if(perfomance instanceof EndTerm) {
			    pstmt3.setString(1, perfomance.getSchoolAccountUuid());
			    pstmt3.setString(2, perfomance.getStudentUuid());
			    pstmt3.setString(3, perfomance.getSubjectUuid());
	            pstmt3.setString(4, perfomance.getClassRoomUuid());
	            pstmt3.setDouble(5, perfomance.getEndTerm());
	            pstmt3.setString(6, perfomance.getTeacherUuid());
	            pstmt3.setString(7, perfomance.getTerm());
	            pstmt3.setString(8, perfomance.getYear());
			    pstmt3.executeUpdate();
			}
			
			
			
			
			
		

		}catch(SQLException e){
			logger.error("SQL Exception trying to put Perfomance" +perfomance);
			logger.error(ExceptionUtils.getStackTrace(e)); 
			System.out.println(ExceptionUtils.getStackTrace(e));
			success = false;
		}	
	
		
		
		} else { 
			
			      try(
					Connection conn = dbutils.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("UPDATE Perfomance SET CatOne =?,TeacherUuid =?,Term =?,Year =?" 
							+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
			    	PreparedStatement pstmt2 = conn.prepareStatement("UPDATE Perfomance SET CatTwo =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
			        PreparedStatement pstmt3 = conn.prepareStatement("UPDATE Perfomance SET EndTerm =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");			  
					) {
				if(perfomance instanceof CatOne) {
					
					    pstmt.setDouble(1, perfomance.getCatOne());
					    pstmt.setString(2, perfomance.getTeacherUuid());
					    pstmt.setString(3, perfomance.getTerm());
			            pstmt.setString(4, perfomance.getYear());
					    pstmt.setString(5, perfomance.getSchoolAccountUuid());
			            pstmt.setString(6, perfomance.getClassRoomUuid());
			            pstmt.setString(7, perfomance.getStudentUuid());
			            pstmt.setString(8, perfomance.getSubjectUuid());
					    pstmt.executeUpdate();
				}	
				else if(perfomance instanceof CatTwo) {
					
					    pstmt2.setDouble(1, perfomance.getCatTwo());
					    pstmt2.setString(2, perfomance.getTeacherUuid());
					    pstmt2.setString(3, perfomance.getTerm());
			            pstmt2.setString(4, perfomance.getYear());
					    pstmt2.setString(5, perfomance.getSchoolAccountUuid());
			            pstmt2.setString(6, perfomance.getClassRoomUuid());
			            pstmt2.setString(7, perfomance.getStudentUuid());
			            pstmt2.setString(8, perfomance.getSubjectUuid());
					    pstmt2.executeUpdate();
			   }	
               else if(perfomance instanceof EndTerm) {
					
					    pstmt3.setDouble(1, perfomance.getEndTerm());
					    pstmt3.setString(2, perfomance.getTeacherUuid());
					    pstmt3.setString(3, perfomance.getTerm());
			            pstmt3.setString(4, perfomance.getYear());
					    pstmt3.setString(5, perfomance.getSchoolAccountUuid());
			            pstmt3.setString(6, perfomance.getClassRoomUuid());
			            pstmt3.setString(7, perfomance.getStudentUuid());
			            pstmt3.setString(8, perfomance.getSubjectUuid());
					    pstmt3.executeUpdate();
			   }	
				
				
				
			
										
			} catch(SQLException e) {
				logger.error("SQL Exception trying to update Perfomance" +perfomance);
				logger.error(ExceptionUtils.getStackTrace(e));
				System.out.println(ExceptionUtils.getStackTrace(e));
				success = false;				
			} 
		}
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getcatoneList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatOne> getcatoneList(String schoolAccountUuid, String classRoomUuid) {
		List<CatOne> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,classRoomUuid,CatOne,Term,Year FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, CatOne.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting List  of CatOne for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getcatwoList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CatTwo> getcatwoList(String schoolAccountUuid, String classRoomUuid) {
		List<CatTwo> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,classRoomUuid,CatTwo,Term,Year FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, CatTwo.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting List CatTwo for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getendtermList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<EndTerm> getendtermList(String schoolAccountUuid, String classRoomUuid) {
		List<EndTerm> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,classRoomUuid,EndTerm,Term,Year FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, EndTerm.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting EndTerm List for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

}
