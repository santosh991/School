/**
 * 
 */
package com.yahoo.petermwenda83.persistence.student;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.student.StudentPhoto;

/**
 * @author peter
 *
 */
public class TestStudentPhotoDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	private StudentPhotoDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentPhotoDAO#getPhoto(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetPhoto() {
		store = new StudentPhotoDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentPhotoDAO#putPhoto(com.yahoo.petermwenda83.bean.student.StudentPhoto)}.
	 */
	@Ignore
	@Test
	public void testPutPhoto() {
		store = new StudentPhotoDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentPhotoDAO#editPhoto(com.yahoo.petermwenda83.bean.student.StudentPhoto)}.
	 */
	@Ignore
	@Test
	public void testEditPhoto() {
		store = new StudentPhotoDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentPhotoDAO#deletePhoto(com.yahoo.petermwenda83.bean.student.StudentPhoto)}.
	 */
	@Ignore
	@Test
	public void testDeletePhoto() {
		store = new StudentPhotoDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.student.StudentPhotoDAO#getAllPhotos()}.
	 */
	//@Ignore
	@Test
	public void testGetAllPhotos() {
		store = new StudentPhotoDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<StudentPhoto> list = store.getAllPhotos();	
		for (StudentPhoto p : list) {
			System.out.println(p);
		}
	}

}
