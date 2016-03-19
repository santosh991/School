package com.yahoo.petermwenda83.server.servlet.book;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.book.StudentBook;
import com.yahoo.petermwenda83.persistence.book.StudentBookDAO;

public class BorrowBook extends HttpServlet{
	
	
	final String EMPTY_BOOK_STATUS = "You can't borrow a reference book";//reference , shortloan
	final String EMPTY_BOOK_BORROW_STATUS = "This book is not available at the moment";//cleared , borrowed
	
	final String BOOK_STATUS_REFERENCE = "Reference";
	final String BPPK_STATUS_SHORTLOAD = "Shortloan";
	
	final String BORROW_STATUS_BORROWED = "Borrowed";
	final String BORROW_STATUS_AVAILABLE = "Available";
	
	final String ERROR_NOT_AVAILABLE = "This book is not available at the moment";

	final String SUCCESS_BOOK_BORROWED = "The book was successfully borrowed";
	final String ERROR_BOOK_NOT_BORROWED = "Something went wrong while borrowing the book";
	
	
	private static StudentBookDAO studentBookDAO;
	StudentBook studentBook;

	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       studentBookDAO = StudentBookDAO.getInstance();
      
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String bookisbn = StringUtils.trimToEmpty(request.getParameter("bookisbn"));
       String studentuuid = StringUtils.trimToEmpty(request.getParameter("studentuuid"));
       
       if(StringUtils.isBlank(bookisbn)){
    	   
       }else if(StringUtils.isBlank(studentuuid)){
    	   
       }else{
    	   
    	   studentBook = new StudentBook();
    	   studentBook.setStudentUuid(studentuuid);
    	   studentBook.setISBN(bookisbn); 
    	   studentBook.setBorrowStatus(BORROW_STATUS_BORROWED);
    	   
    	   if(studentBookDAO.BorrowBook(studentBook)){
    		   
    	   }else{
    		   
    	   }
    	   
    	   
    	   
       }
       
       
       
       
   }
   

   @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
          doPost(request, response);
      }
   

}
