/**
 * 
 */
package com.yahoo.petermwenda83.persistence.book;

import java.util.List;

import com.yahoo.petermwenda83.bean.book.StudentBook;

/**
 * @author peter
 *
 */
public interface SchoolStudentBookDAO {
	/**
	 * 
	 * @param studentUuid
	 * @return
	 */
	public StudentBook getStudentBook(String studentUuid,String ISBN,String borrowStatus);
	
	/**
	 * 
	 * @param studentUuid
	 * @return
	 */
	public List<StudentBook> getStudentBookByStudentId(String studentUuid);
	/**
	 * 
	 * @param ISBN
	 * @return
	 */
	public StudentBook getStudentBookByISBN(String ISBN);
	/**
	 * 
	 * @param borrowStatus
	 * @return
	 */
	public StudentBook getStudentBookByBorrowStatus(String borrowStatus);
	/**
	 * 
	 * @param studentBook
	 * @return
	 */
	public boolean BorrowBook(StudentBook studentBook);
	/**
	 * 
	 * @param studentBook
	 * @return
	 */
	public boolean ReturnBook(StudentBook studentBook);
	/**
	 * 
	 * @param studentBook
	 * @return
	 */
	public boolean deleteStudentBook(StudentBook studentBook);
	/**
	 * 
	 * @return
	 */
	public List<StudentBook> StudentBookList();

}
