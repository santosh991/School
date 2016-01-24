

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
import com.yahoo.petermwenda83.bean.exam.PaperOne;
import com.yahoo.petermwenda83.bean.exam.PaperThree;
import com.yahoo.petermwenda83.bean.exam.PaperTwo;
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
				
				PreparedStatement pstmtCatOne = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,ClassesUuid,CatOne,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmtCatTwo = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,ClassesUuid,CatTwo,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmtEndTerm = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,ClassesUuid,EndTerm,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmtPaperOne = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,ClassesUuid,PaperOne,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmtPaperTwo  = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,ClassesUuid,PaperTwo,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?,?);");
				
				PreparedStatement pstmtPaperThree  = conn.prepareStatement("INSERT INTO Perfomance"
						+"(SchoolAccountUuid,StudentUuid,SubjectUuid,classRoomUuid,ClassesUuid,PaperThree,TeacherUuid,Term,Year) VALUES (?,?,?,?,?,?,?,?,?);");
				){

			if(perfomance instanceof CatOne) {
				pstmtCatOne.setString(1, perfomance.getSchoolAccountUuid());
				pstmtCatOne.setString(2, perfomance.getStudentUuid());
				pstmtCatOne.setString(3, perfomance.getSubjectUuid());
				pstmtCatOne.setString(4, perfomance.getClassRoomUuid());
				pstmtCatOne.setString(5, perfomance.getClassesUuid());
				pstmtCatOne.setDouble(6, perfomance.getCatOne());
				pstmtCatOne.setString(7, perfomance.getTeacherUuid());
				pstmtCatOne.setString(8, perfomance.getTerm());
				pstmtCatOne.setString(9, perfomance.getYear());
				pstmtCatOne.executeUpdate();
			}
			else if(perfomance instanceof CatTwo) {
				pstmtCatTwo.setString(1, perfomance.getSchoolAccountUuid());
				pstmtCatTwo.setString(2, perfomance.getStudentUuid());
				pstmtCatTwo.setString(3, perfomance.getSubjectUuid());
				pstmtCatTwo.setString(4, perfomance.getClassRoomUuid());
				pstmtCatTwo.setString(5, perfomance.getClassesUuid());
				pstmtCatTwo.setDouble(6, perfomance.getCatTwo());
				pstmtCatTwo.setString(7, perfomance.getTeacherUuid());
				pstmtCatTwo.setString(8, perfomance.getTerm());
				pstmtCatTwo.setString(9, perfomance.getYear());
				pstmtCatTwo.executeUpdate();
			}
			else if(perfomance instanceof EndTerm) {
				pstmtEndTerm.setString(1, perfomance.getSchoolAccountUuid());
				pstmtEndTerm.setString(2, perfomance.getStudentUuid());
			    pstmtEndTerm.setString(3, perfomance.getSubjectUuid());
			    pstmtEndTerm.setString(4, perfomance.getClassRoomUuid());
			    pstmtEndTerm.setString(5, perfomance.getClassesUuid());
			    pstmtEndTerm.setDouble(6, perfomance.getEndTerm());
			    pstmtEndTerm.setString(7, perfomance.getTeacherUuid());
			    pstmtEndTerm.setString(8, perfomance.getTerm());
			    pstmtEndTerm.setString(9, perfomance.getYear());
			    pstmtEndTerm.executeUpdate();
			}
			else if(perfomance instanceof PaperOne) { 
				pstmtPaperOne.setString(1, perfomance.getSchoolAccountUuid());
				pstmtPaperOne.setString(2, perfomance.getStudentUuid());
				pstmtPaperOne.setString(3, perfomance.getSubjectUuid());
				pstmtPaperOne.setString(4, perfomance.getClassRoomUuid());
				pstmtPaperOne.setString(5, perfomance.getClassesUuid());
				pstmtPaperOne.setDouble(6, perfomance.getPaperOne());
				pstmtPaperOne.setString(7, perfomance.getTeacherUuid());
				pstmtPaperOne.setString(8, perfomance.getTerm());
				pstmtPaperOne.setString(9, perfomance.getYear());
				pstmtPaperOne.executeUpdate();
			}
			else if(perfomance instanceof PaperTwo) { 
				pstmtPaperTwo.setString(1, perfomance.getSchoolAccountUuid());
				pstmtPaperTwo.setString(2, perfomance.getStudentUuid());
				pstmtPaperTwo.setString(3, perfomance.getSubjectUuid());
				pstmtPaperTwo.setString(4, perfomance.getClassRoomUuid());
				pstmtPaperTwo.setString(5, perfomance.getClassesUuid());
				pstmtPaperTwo.setDouble(6, perfomance.getPaperTwo());
				pstmtPaperTwo.setString(7, perfomance.getTeacherUuid());
				pstmtPaperTwo.setString(8, perfomance.getTerm());
				pstmtPaperTwo.setString(9, perfomance.getYear());
				pstmtPaperTwo.executeUpdate();
			}
			else if(perfomance instanceof PaperThree) { 
				pstmtPaperThree.setString(1, perfomance.getSchoolAccountUuid());
				pstmtPaperThree.setString(2, perfomance.getStudentUuid());
				pstmtPaperThree.setString(3, perfomance.getSubjectUuid());
				pstmtPaperThree.setString(4, perfomance.getClassRoomUuid());
				pstmtPaperThree.setString(5, perfomance.getClassesUuid());
				pstmtPaperThree.setDouble(6, perfomance.getPaperThree());
				pstmtPaperThree.setString(7, perfomance.getTeacherUuid());
				pstmtPaperThree.setString(8, perfomance.getTerm());
				pstmtPaperThree.setString(9, perfomance.getYear());
				pstmtPaperThree.executeUpdate();
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
					PreparedStatement pstmtCatOne = conn.prepareStatement("UPDATE Perfomance SET CatOne =?,TeacherUuid =?,Term =?,Year =?" 
							+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
			    	PreparedStatement pstmtCatTwo = conn.prepareStatement("UPDATE Perfomance SET CatTwo =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
			        PreparedStatement pstmtEndTerm = conn.prepareStatement("UPDATE Perfomance SET EndTerm =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
			       PreparedStatement pstmtPaperOne = conn.prepareStatement("UPDATE Perfomance SET PaperOne =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");
			    		  
			       PreparedStatement pstmtPaperTwo = conn.prepareStatement("UPDATE Perfomance SET PaperTwo =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
			       PreparedStatement pstmtPaperThree = conn.prepareStatement("UPDATE Perfomance SET PaperThree =?,TeacherUuid =?,Term =?,Year =?" 
									+"WHERE SchoolAccountUuid = ? AND classRoomUuid = ? AND StudentUuid = ? AND SubjectUuid = ?  ; ");	
			    		  
					) {
				if(perfomance instanceof CatOne) {
					
					pstmtCatOne.setDouble(1, perfomance.getCatOne());
					pstmtCatOne.setString(2, perfomance.getTeacherUuid());
					pstmtCatOne.setString(3, perfomance.getTerm());
					pstmtCatOne.setString(4, perfomance.getYear());
					pstmtCatOne.setString(5, perfomance.getSchoolAccountUuid());
					pstmtCatOne.setString(6, perfomance.getClassRoomUuid());
					pstmtCatOne.setString(7, perfomance.getStudentUuid());
					pstmtCatOne.setString(8, perfomance.getSubjectUuid());
					pstmtCatOne.executeUpdate();
				}	
				else if(perfomance instanceof CatTwo) {
					
					pstmtCatTwo.setDouble(1, perfomance.getCatTwo());
					pstmtCatTwo.setString(2, perfomance.getTeacherUuid());
					pstmtCatTwo.setString(3, perfomance.getTerm());
					pstmtCatTwo.setString(4, perfomance.getYear());
					pstmtCatTwo.setString(5, perfomance.getSchoolAccountUuid());
					pstmtCatTwo.setString(6, perfomance.getClassRoomUuid());
					pstmtCatTwo.setString(7, perfomance.getStudentUuid());
					pstmtCatTwo.setString(8, perfomance.getSubjectUuid());
					pstmtCatTwo.executeUpdate();
			   }	
               else if(perfomance instanceof EndTerm) {
					
            	   pstmtEndTerm.setDouble(1, perfomance.getEndTerm());
            	   pstmtEndTerm.setString(2, perfomance.getTeacherUuid());
            	   pstmtEndTerm.setString(3, perfomance.getTerm());
            	   pstmtEndTerm.setString(4, perfomance.getYear());
            	   pstmtEndTerm.setString(5, perfomance.getSchoolAccountUuid());
            	   pstmtEndTerm.setString(6, perfomance.getClassRoomUuid());
            	   pstmtEndTerm.setString(7, perfomance.getStudentUuid());
            	   pstmtEndTerm.setString(8, perfomance.getSubjectUuid());
            	   pstmtEndTerm.executeUpdate();
			   }	
               else if(perfomance instanceof PaperOne) {
					
            	   pstmtPaperOne.setDouble(1, perfomance.getPaperOne());
            	   pstmtPaperOne.setString(2, perfomance.getTeacherUuid());
            	   pstmtPaperOne.setString(3, perfomance.getTerm());
            	   pstmtPaperOne.setString(4, perfomance.getYear());
            	   pstmtPaperOne.setString(5, perfomance.getSchoolAccountUuid());
            	   pstmtPaperOne.setString(6, perfomance.getClassRoomUuid());
            	   pstmtPaperOne.setString(7, perfomance.getStudentUuid());
            	   pstmtPaperOne.setString(8, perfomance.getSubjectUuid());
            	   pstmtPaperOne.executeUpdate();
			   }	
				
               else if(perfomance instanceof PaperTwo) {
					
            	   pstmtPaperTwo.setDouble(1, perfomance.getPaperTwo());
            	   pstmtPaperTwo.setString(2, perfomance.getTeacherUuid());
            	   pstmtPaperTwo.setString(3, perfomance.getTerm());
            	   pstmtPaperTwo.setString(4, perfomance.getYear());
            	   pstmtPaperTwo.setString(5, perfomance.getSchoolAccountUuid());
            	   pstmtPaperTwo.setString(6, perfomance.getClassRoomUuid());
            	   pstmtPaperTwo.setString(7, perfomance.getStudentUuid());
            	   pstmtPaperTwo.setString(8, perfomance.getSubjectUuid());
            	   pstmtPaperTwo.executeUpdate();
			   }
				
               else if(perfomance instanceof PaperThree) {
					
            	   pstmtPaperThree.setDouble(1, perfomance.getPaperThree());
            	   pstmtPaperThree.setString(2, perfomance.getTeacherUuid());
            	   pstmtPaperThree.setString(3, perfomance.getTerm());
            	   pstmtPaperThree.setString(4, perfomance.getYear());
            	   pstmtPaperThree.setString(5, perfomance.getSchoolAccountUuid());
            	   pstmtPaperThree.setString(6, perfomance.getClassRoomUuid());
            	   pstmtPaperThree.setString(7, perfomance.getStudentUuid());
            	   pstmtPaperThree.setString(8, perfomance.getSubjectUuid());
            	   pstmtPaperThree.executeUpdate();
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

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getPaperOneList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<PaperOne> getPaperOneList(String schoolAccountUuid, String classRoomUuid) {
		List<PaperOne> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,classRoomUuid,PaperOne,Term,Year FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, PaperOne.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting PaperOne List for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getPaperTwoList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<PaperTwo> getPaperTwoList(String schoolAccountUuid, String classRoomUuid) {
		List<PaperTwo> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,classRoomUuid,PaperTwo,Term,Year FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, PaperTwo.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting PaperTwo List for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.exam.SchoolExamEngineDAO#getpaperThreeList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<PaperThree> getpaperThreeList(String schoolAccountUuid, String classRoomUuid) {
		List<PaperThree> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT SchoolAccountUuid,TeacherUuid,StudentUuid,SubjectUuid,classRoomUuid,PaperThree,Term,Year FROM Perfomance WHERE SchoolAccountUuid = ? AND classRoomUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, schoolAccountUuid);      
         	   pstmt.setString(2, classRoomUuid);      
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, PaperThree.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting PaperThree List for school" + schoolAccountUuid +" and classroom" +classRoomUuid); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

}
