package com.yahoo.petermwenda83.server.servlet.book;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.book.Book;
import com.yahoo.petermwenda83.persistence.book.BookDAO;

public class UpdateBook extends HttpServlet{
	
	final String BOOK_STATUS_REFERENCE = "Reference";
	final String BPPK_STATUS_SHORTLOAD = "Shortloan";
	
	final String BORROW_STATUS_BORROWED = "Borrowed";
	final String BORROW_STATUS_AVAILABLE = "Available";
	
	final String EMPTY_BOOK_ISBN = "Book ISBN can't be empty";
	final String EMPTY_BOOK_AUTHOR = "Book author can't be empty";
	final String EMPTY_BOOK_PUBLISHER = "Book publisher can't be empty";
	final String EMPTY_BOOK_TITTLE = "Book title can't be empty";
	
	final String ERROR_BOOK_EXIST = "A book with the given ISBN/Book Number already exist";
	final String ERROR_SOMETHING_WENT_WRONG = "Sorry, something went wrong while updating the book";
	final String SUCCESS_BOOK_UPDATED = "The book was successfully updated";
	
	private static BookDAO bookDAO;
	Book book;
	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       
       bookDAO = BookDAO.getInstance();
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String isbn = StringUtils.trimToEmpty(request.getParameter("isbn"));
       String author = StringUtils.trimToEmpty(request.getParameter("author"));
       String publisher = StringUtils.trimToEmpty(request.getParameter("publisher"));
       String title = StringUtils.trimToEmpty(request.getParameter("title"));
       String bookstatus = StringUtils.trimToEmpty(request.getParameter("bookstatus"));
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       
if(StringUtils.isBlank(isbn)){
    	   
       }else if(StringUtils.isBlank(author)){
    	   
       }else if(StringUtils.isBlank(publisher)){
    	   
       }else if(StringUtils.isBlank(title)){
    	   
       }else if(StringUtils.isBlank(bookstatus)){
    	   
       }else if(StringUtils.isBlank(schooluuid)){
    	   
       }else if(bookDAO.getBookByISBN(schooluuid, isbn) !=null){
    	   
       }else{
    	   
    	   
    	   book = new Book();
           book.setISBN(isbn);
           book.setAuthor(author);
           book.setPublisher(publisher);
           book.setTitle(title);
           book.setBookStatus(bookstatus);
           book.setBorrowStatus(BORROW_STATUS_AVAILABLE);
           
           if(bookDAO.updateBook(book)){
        	   
        	   
        	 
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
