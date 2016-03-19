/**
 * 
 */
package com.yahoo.petermwenda83.persistence.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.book.StudentBook;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class StudentBookDAO extends GenericDAO implements SchoolStudentBookDAO {

	private static StudentBookDAO studentBookDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static StudentBookDAO getInstance(){
		
		if(studentBookDAO == null){
			studentBookDAO = new StudentBookDAO();		
		}
		return studentBookDAO;
	}
	
	/**
	 * 
	 */
	public StudentBookDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public StudentBookDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#getStudentBook(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public StudentBook getStudentBook(String studentUuid, String ISBN, String borrowStatus) {
		StudentBook studentBook = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentBook WHERE studentUuid = ? AND ISBN =? AND borrowStatus =?;");       
        		
        		){
        	
        	 pstmt.setString(1, studentUuid);
        	 pstmt.setString(2, ISBN);
        	 pstmt.setString(3, borrowStatus);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentBook  = beanProcessor.toBean(rset,StudentBook.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting studentBook");
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return studentBook; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#getStudentBookByStudentId(java.lang.String)
	 */
	@Override
	public StudentBook getStudentBookByStudentId(String studentUuid) {
		StudentBook studentBook = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentBook WHERE studentUuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, studentUuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentBook  = beanProcessor.toBean(rset,StudentBook.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting studentBook");
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return studentBook; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#getStudentBookByISBN(java.lang.String)
	 */
	@Override
	public StudentBook getStudentBookByISBN(String ISBN) {
		StudentBook studentBook = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentBook WHERE ISBN = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, ISBN);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentBook  = beanProcessor.toBean(rset,StudentBook.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting studentBook");
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return studentBook; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#getStudentBookByBorrowStatus(java.lang.String)
	 */
	@Override
	public StudentBook getStudentBookByBorrowStatus(String borrowStatus) {
		StudentBook studentBook = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM StudentBook WHERE borrowStatus = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, borrowStatus);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 studentBook  = beanProcessor.toBean(rset,StudentBook.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting studentBook");
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return studentBook; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#putStudentBook(com.yahoo.petermwenda83.bean.book.StudentBook)
	 */
	@Override
	public boolean BorrowBook(StudentBook studentBook) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO StudentBook" 
			        		+"(Uuid,StudentUuid,ISBN,BorrowStatus,BorrowDate,ReturnDate) VALUES (?,?,?,?,?,?);");
		             ){
			   
	            pstmt.setString(1, studentBook.getUuid());
	            pstmt.setString(2, studentBook.getStudentUuid());
	            pstmt.setString(3, studentBook.getISBN());
	            pstmt.setString(4, studentBook.getBorrowStatus());
	            pstmt.setTimestamp(5, new Timestamp(studentBook.getBorrowDate().getTime()));
	            pstmt.setString(6, studentBook.getReturnDate());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
		 logger.error("SQL Exception trying to put studentBook "+studentBook);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#updateStudentBook(com.yahoo.petermwenda83.bean.book.StudentBook)
	 */
	@Override
	public boolean ReturnBook(StudentBook studentBook) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentBook SET BorrowStatus =?,"
			        + "ReturnDate =?  WHERE ISBN = ? AND StudentUuid =? ;");
	               ) {           			 	            
			 
	           
	            pstmt.setString(1, studentBook.getBorrowStatus());
	            pstmt.setString(2, studentBook.getReturnDate());
	            pstmt.setString(3, studentBook.getISBN());
	            pstmt.setString(4, studentBook.getStudentUuid());
	            pstmt.executeUpdate();

		  } catch (SQLException e) {
		    logger.error("SQL Exception when updating studentBook " + studentBook);
		    logger.error(ExceptionUtils.getStackTrace(e));
		    System.out.println(ExceptionUtils.getStackTrace(e));
		    success = false;
		 } 
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#deleteStudentBook(com.yahoo.petermwenda83.bean.book.StudentBook)
	 */
	@Override
	public boolean deleteStudentBook(StudentBook studentBook) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	  PreparedStatement pstmt = conn.prepareStatement("DELETE FROM StudentBook"
	         	      		+ " WHERE ISBN =?;");       
	      		
	      		){
	      	
	      	     pstmt.setString(1, studentBook.getISBN());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting studentBook : " +studentBook);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolStudentBookDAO#StudentBookList()
	 */
	@Override
	public List<StudentBook> StudentBookList() {
		List<StudentBook>  list = null;
		 try(   
		Connection conn = dbutils.getConnection();
		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM StudentBook ;");   
		ResultSet rset = pstmt.executeQuery();
		  ) {
	
         list = beanProcessor.toBeanList(rset, StudentBook.class);

	   } catch(SQLException e){
		  logger.error("SQL Exception when getting all Deposit");
	      logger.error(ExceptionUtils.getStackTrace(e));
	      System.out.println(ExceptionUtils.getStackTrace(e));
	   }
      return list;
	
	}

}
