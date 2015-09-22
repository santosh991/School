/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.exam.Exam;
import com.yahoo.petermwenda83.bean.exam.cat.CatMarks;
import com.yahoo.petermwenda83.bean.exam.cat.CatResults;
import com.yahoo.petermwenda83.bean.exam.results.FinalMark;
import com.yahoo.petermwenda83.bean.exam.results.FinalResult;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;
import com.yahoo.petermwenda83.persistence.exam.result.FinalMarkDAO;
import com.yahoo.petermwenda83.persistence.exam.result.FinalResultDAO;

/**
 * @author peter
 *
 */
public class CatSubMarkDAO extends DBConnectDAO  implements SchoolCatSubMarkDAO {

	private static CatSubMarkDAO catSubMarkDAO;
	
	private static FinalResultDAO finalResultDAO;
	private static CatResultsDAO catResultsDAO;
	private static FinalMarkDAO finalMarkDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * @return examDAO
	 * 
	 */
	public static CatSubMarkDAO getInstance() {
		if(catSubMarkDAO == null){
			catSubMarkDAO = new CatSubMarkDAO();		
			}
		return catSubMarkDAO;
	}
	
	public CatSubMarkDAO() {
		super();
		catResultsDAO = CatResultsDAO.getInstance();
		finalResultDAO = FinalResultDAO .getInstance();
		finalMarkDAO =FinalMarkDAO.getInstance();
		
	}
	

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public CatSubMarkDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
		catResultsDAO = new CatResultsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		finalResultDAO = new FinalResultDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		finalMarkDAO = new FinalMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatSubMarkDAO#getCatMark(java.lang.String, java.lang.String)
	 */
	@Override
	public CatMarks getCatMark(String StudentUuid, String Subjectuuid) {
		CatMarks catMarks = null;
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CatSubjectMark WHERE"
        	      		+ " StudentUuid = ? AND Subjectuuid =?;");       
     		
     		){
    	
     	 pstmt.setString(1, StudentUuid);
     	 pstmt.setString(2, Subjectuuid);
	     rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 catMarks  = beanProcessor.toBean(rset,CatMarks.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting MainMarks with StudentUuid: " + StudentUuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.print(ExceptionUtils.getStackTrace(e));
     }
     
		return catMarks; 
	
	}
	@Override
	public boolean hasCatMark(Exam exam,Double Total) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatSubMarkDAO#addCatMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean addCatMark(Exam exam,Double Percent,Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CatSubjectMark" 
			        		+"(Uuid, Studentuuid,Subjectuuid,Marks,Points,"
			        		+ "Submark,Percent,Grade,Submitdate) VALUES (?,?,?,?,?,?,?,?,?);");
        		){
			  if(exam instanceof CatMarks ){
	            pstmt.setString(1, exam.getUuid());
	            pstmt.setString(2, exam.getStudentUuid());
	            pstmt.setString(3, exam.getSubjectUuid());
	            pstmt.setDouble(4, exam.getMarks());//25
	            pstmt.setDouble(5, Points);//8
	            pstmt.setDouble(6, exam.getSubmark());// 25/40*30=19  /2
	            pstmt.setDouble(7, Percent);//25/40*100=63
	            pstmt.setString(8, exam.getGrade());//B-
	            pstmt.setTimestamp(9,  new Timestamp(exam.getSubmitdate().getTime()));
	            pstmt.executeUpdate();
	            
	            CatResults cr = new CatResults();
	            cr.setStudentUuid(exam.getStudentUuid()); 
	            cr.setRemarks(exam.getRemarks()); 
	            cr.setGrade(exam.getGrade()); 
	            catResultsDAO.addCatMark(cr, Percent, Points);
			  } if(exam instanceof FinalResult ){
	            
	            FinalResult fr = new FinalResult();
	            fr.setUuid(exam.getUuid()); 
	            fr.setStudentUuid(exam.getStudentUuid()); 
	            fr.setGrade(exam.getGrade());//depends on submark
	            fr.setRemarks(exam.getRemarks()); //depends on submark
	           finalResultDAO.addPoints(fr, Points);//Points depend on submark
			  } if(exam instanceof FinalMark ){
	           
	           FinalMark fm = new FinalMark();
	           fm.setUuid(exam.getUuid());
	           fm.setStudentUuid(exam.getStudentUuid());
	           fm.setSubjectUuid(exam.getSubjectUuid()); 
	           fm.setGrade(exam.getGrade()); 
	           double Marks = exam.getSubmark();
	           finalMarkDAO.addMark(fm, Marks); 
	            
	            
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatSubMarkDAO#editCatMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean editCatMark(Exam exam,Double Marks,Double Points) {
		boolean success = true;
		try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE CatSubjectMark SET marks =?, Points =?, "
						+ "submitdate =?"
						+ " WHERE Studentuuid = ? AND Subjectuuid = ?  ;");
        		){
			  if(exam instanceof CatMarks ){
	            pstmt.setDouble(1, Marks);
	            pstmt.setDouble(2, Points);
	            pstmt.setTimestamp(3, new Timestamp(exam.getSubmitdate().getTime()));
	            pstmt.setString(4, exam.getStudentUuid());
	            pstmt.setString(5, exam.getSubjectUuid());
	            pstmt.executeUpdate();
	            
	            CatResults cr = new CatResults();
	            cr.setStudentUuid(exam.getStudentUuid()); 
	            catResultsDAO.addCatMark(cr, Marks, Points);
			  }
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to update Exam: "+exam);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.print(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatSubMarkDAO#deleteCatMark(com.yahoo.petermwenda83.contoller.exam.Exam)
	 */
	@Override
	public boolean deleteCatMark(Exam exam) {
		 boolean success = true; 
         try(
   		  Connection conn = dbutils.getConnection();
     PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CatSubjectMark WHERE"
     		+ " StudentUuid = ? AND SubjectUuid = ?;");       
   		      ){
         	 if(exam instanceof CatMarks ){
   	          pstmt.setString(1,exam.getStudentUuid());
   	          pstmt.setString(2,exam.getSubjectUuid());
	          pstmt.executeUpdate();
         	 }
	     
   }catch(SQLException e){
   	 logger.error("SQL Exception when deletting Exam : " +exam);
        logger.error(ExceptionUtils.getStackTrace(e));
        System.out.println(ExceptionUtils.getStackTrace(e));
        success = false;
        
   }
   
		return success; 
}
	

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.exam.SchoolCatSubMarkDAO#getAllCatSubMark()
	 */
	@Override
	public List<CatMarks> getAllCatSubMark() {
		List<CatMarks> list = null;
		try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM CatSubjectMark ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, CatMarks.class);

	      } catch(SQLException e){
	      	logger.error("SQL Exception when getting all ExamType");
	        logger.error(ExceptionUtils.getStackTrace(e));
	        System.out.println(ExceptionUtils.getStackTrace(e));
	      }
		return list;
	}

	
}
