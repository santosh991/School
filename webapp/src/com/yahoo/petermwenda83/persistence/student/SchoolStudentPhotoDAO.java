/**
 * 
 */
package com.yahoo.petermwenda83.persistence.student;

import java.util.List;

import com.yahoo.petermwenda83.bean.student.StudentPhoto;

/**
 * @author peter
 *
 */
public interface SchoolStudentPhotoDAO {
	/**
	 * 
	 * @param StudentUuid
	 * @return Student Photo
	 */
	public StudentPhoto getPhoto(String StudentUuid);
	/**
	 * 
	 * @param photo
	 * @return Whether student Photo was put successfully 
	 */
	public boolean putPhoto(StudentPhoto photo);
	 /**
	  * 
	  * @param photo
	  * @return Whether student Photo was edited successfully 
	  */
	public boolean editPhoto(StudentPhoto photo);
	  /**
	   * 
	   * @param photo
	   * @return Whether student Photo was deleted successfully 
	   */
	public boolean deletePhoto(StudentPhoto photo);
	   /**
	    * 
	    * @return {@link List} List of all Student Photos
	    */
	public List<StudentPhoto> getAllPhotos();

}
