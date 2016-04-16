/**
 * 
 */
package com.yahoo.petermwenda83.persistence.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.book.Book;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/** 
 * @author peter
 *
 */
public class BookDAO extends GenericDAO implements SchoolBookDAO {

	private static BookDAO bookDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static BookDAO getInstance(){
		
		if(bookDAO == null){
			bookDAO = new BookDAO();		
		}
		return bookDAO;
	}
	
	/**
	 * 
	 */
	public BookDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public BookDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}


	/**
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolBookDAO#getBookByISBN(java.lang.String, java.lang.String)
	 */
	@Override
	public Book getBookByISBN(String schoolAccountUuid, String ISBN) {
		Book book = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Books WHERE schoolAccountUuid = ? AND ISBN = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
        	 pstmt.setString(2, ISBN);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 book  = beanProcessor.toBean(rset,Book.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting Book");
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return book; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolBookDAO#getBookByUUID(java.lang.String, java.lang.String)
	 */
	@Override
	public Book getBookByUUID(String schoolAccountUuid, String Uuid) {
		Book book = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Books WHERE schoolAccountUuid = ? AND Uuid = ?;");       
        		
        		){
        	
        	 pstmt.setString(1, schoolAccountUuid);
        	 pstmt.setString(2, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 book  = beanProcessor.toBean(rset,Book.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting Book");
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
		return book; 
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolBookDAO#putBook(com.yahoo.petermwenda83.bean.book.Book)
	 */
	@Override
	public boolean putBook(Book book) {
		boolean success = true;
		
		  try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Books" 
			        		+"(Uuid,SchoolAccountUuid,ISBN,Author,Publisher,Title,BookStatus,BorrowStatus) VALUES (?,?,?,?,?,?,?,?);");
		             ){
			   
	            pstmt.setString(1, book.getUuid());
	            pstmt.setString(2, book.getSchoolAccountUuid());
	            pstmt.setString(3, book.getISBN());
	            pstmt.setString(4, book.getAuthor());
	            pstmt.setString(5, book.getPublisher());
	            pstmt.setString(6, book.getTitle());
	            pstmt.setString(7, book.getBookStatus());
	            pstmt.setString(8, book.getBorrowStatus());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
		   logger.error("SQL Exception trying to put book "+book);
         logger.error(ExceptionUtils.getStackTrace(e)); 
         System.out.println(ExceptionUtils.getStackTrace(e));
         success = false;
		 }
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolBookDAO#updateBook(com.yahoo.petermwenda83.bean.book.Book)
	 */
	@Override
	public boolean updateBook(Book book) {
		boolean success = true;
		
		  try (  Connection conn = dbutils.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement("UPDATE Books SET Author = ? ,Publisher =?,"
			        + "Title =? ,BookStatus =? ,BorrowStatus =? WHERE Uuid = ? AND SchoolAccountUuid =? AND ISBN =? ;");
	               ) {           			 	            
			 
	          
	            pstmt.setString(1, book.getAuthor());
	            pstmt.setString(2, book.getPublisher());
	            pstmt.setString(3, book.getTitle());
	            pstmt.setString(4, book.getBookStatus());
	            pstmt.setString(5, book.getBorrowStatus());
	            pstmt.setString(6, book.getUuid());
	            pstmt.setString(7, book.getSchoolAccountUuid());
	            pstmt.setString(8, book.getISBN());
	            pstmt.executeUpdate();

		  } catch (SQLException e) {
		    logger.error("SQL Exception when updating book " + book);
		    logger.error(ExceptionUtils.getStackTrace(e));
		    System.out.println(ExceptionUtils.getStackTrace(e));
		    success = false;
		 } 
		
		return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolBookDAO#deleteBook(com.yahoo.petermwenda83.bean.book.Book)
	 */
	@Override
	public boolean deleteBook(Book book) {
		boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	  PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Books"
	         	      		+ " WHERE ISBN =?;");       
	      		
	      		){
	      	
	      	     pstmt.setString(1, book.getISBN());
		         pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	   logger.error("SQL Exception when deletting book : " +book);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success;
	}

	/**
	 * @see com.yahoo.petermwenda83.persistence.book.SchoolBookDAO#getBookList(java.lang.String)
	 */
	@Override
	public List<Book> getBookList(String schoolAccountUuid) {
		List<Book> bookList = new ArrayList<>();
		try(
				Connection conn = dbutils.getConnection();
				PreparedStatement psmt= conn.prepareStatement("SELECT * FROM Books WHERE "
						+ "schoolAccountUuid = ?;");
				) {
			psmt.setString(1, schoolAccountUuid);
			try(ResultSet rset = psmt.executeQuery();){
			
				bookList = beanProcessor.toBeanList(rset, Book.class);
			}
		} catch (SQLException e) {
			logger.error("SQLException when trying to get a Student List for school"+schoolAccountUuid);
            logger.error(ExceptionUtils.getStackTrace(e));
            System.out.println(ExceptionUtils.getStackTrace(e)); 
	    }
		
		return bookList;
	}

}
