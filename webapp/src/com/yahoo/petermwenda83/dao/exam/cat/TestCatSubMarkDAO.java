/**
 * 
 */
package com.yahoo.petermwenda83.dao.exam.cat;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.cat.CatMarks;
import com.yahoo.petermwenda83.bean.exam.results.FinalMark;
import com.yahoo.petermwenda83.bean.exam.results.FinalResult;

/**
 * @author peter
 *
 */
public class TestCatSubMarkDAO {
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	//FOR CAT SUBJECT MARKS
    
    final String CAT_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
    
    final String CAT_STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd",
			     CAT_STU_UUID_NEW="A5151A2D-3D63-4092-BC67-AC23A2ED2606";
			                 
    
    final String CAT_SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E",
		          CAT_SUB_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
		        


	final Double CAT_MARK = 28.0,
		         CAT_MARK_NEW = 28.1,
		         CAT_MARK_UPDATE = 28.2 ;


	final Double CAT_SUBMARK =34.0, 
                 CAT_SUBMARK_NEW = 34.1,
                 CAT_SUBMARK_UPDATE = 34.2;  
	
	
	final Double CAT_PERCENT = 88.0,
	             CAT_PERCENT_NEW =88.1,
	             CAT_PERCENT_UPDATE = 88.2;
	
	 final double CAT_POINTS = 60.0,
		          CAT_POINTS_NEW = 60.1,
		          CAT_POINTS_UPDATE = 60.2; 

	
	 final String CAT_GRADE = "A-",
		          CAT_GRADE_NEW = "NEWA-",
		          CAT_GRADE_UPDATE = "UPDATEA-";
	
	
	 final Date CAT_DATE = new Date(new Long("1417633242000"));
	 final Date CAT_DATE_NEW = new Date(new Long("1417633242000"));
	 final Date CAT_DATE_UPDATE = new Date(new Long("1417633242000"));
	
	 private CatSubMarkDAO store;


	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatSubMarkDAO#getCatMark(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetCatMark() {
		store = new CatSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
		CatMarks catMarks = new CatMarks();
		catMarks = store.getCatMark(CAT_STU_UUID, CAT_SUB_UUID);
		assertEquals(catMarks.getUuid(),CAT_UUID);
		assertEquals(catMarks.getStudentUuid(),CAT_STU_UUID);
		assertEquals(catMarks.getSubjectUuid(),CAT_SUB_UUID);
		assertEquals(catMarks.getMarks(),CAT_MARK,0);
		assertEquals(catMarks.getSubmark(),CAT_SUBMARK,0);
		assertEquals(catMarks.getPercent(),CAT_PERCENT,0);
		//assertEquals(catMarks.getSubmitdate(),CAT_DATE);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatSubMarkDAO#hasCatMark(com.yahoo.petermwenda83.bean.exam.Exam, java.lang.Double)}.
	 */
	@Ignore
	@Test
	// NOT IMPLEMENTED
	public void testHasCatMark() {
		store = new CatSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatMarks catm = new CatMarks();
		catm.setStudentUuid(CAT_STU_UUID); 
		catm.setSubjectUuid(CAT_SUB_UUID); 
		assertTrue(store.hasCatMark(catm, CAT_MARK));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatSubMarkDAO#addCatMark(com.yahoo.petermwenda83.bean.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	//@Ignore
	@Test
	public void testAddCatMark() {
		store = new CatSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatMarks catm = new CatMarks();
		catm.setStudentUuid(CAT_STU_UUID);
		catm.setSubjectUuid(CAT_SUB_UUID); 
		catm.setSubmark(CAT_SUBMARK);
		catm.setPercent(CAT_PERCENT); 
		catm.setRemarks("remarks");
		catm.setGrade(CAT_GRADE); 
		catm.setSubmitdate(CAT_DATE);
		assertTrue(store.addCatMark(catm, CAT_MARK_NEW, CAT_POINTS_NEW));
		

		 FinalResult fr = new FinalResult();
		 fr.setStudentUuid(CAT_STU_UUID);
		 fr.setSubjectUuid(CAT_SUB_UUID); 
		 fr.setGrade("A+");
		 fr.setRemarks("Spledid"); 
		 assertTrue(store.addCatMark(fr, CAT_MARK, CAT_POINTS));
		 
		 FinalMark fm = new FinalMark();
		 fm.setStudentUuid(CAT_STU_UUID);
		 fm.setSubjectUuid(CAT_SUB_UUID); 
		 fm.setSubmark(45); 
		 fm.setGrade("A+"); 
		 assertTrue(store.addCatMark(fm, CAT_MARK, CAT_POINTS));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatSubMarkDAO#editCatMark(com.yahoo.petermwenda83.bean.exam.Exam, java.lang.Double, java.lang.Double)}.
	 */
	@Ignore
	@Test
	public void testEditCatMark() {
		store = new CatSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		
        CatMarks catm = new CatMarks();
		
		catm.setStudentUuid(CAT_STU_UUID);
		catm.setSubjectUuid(CAT_SUB_UUID);
		catm.setMarks(CAT_MARK_NEW);
		catm.setSubmark(CAT_SUBMARK_NEW);
		catm.setPercent(CAT_PERCENT_NEW); 
		catm.setGrade(CAT_GRADE_NEW); 
		catm.setSubmitdate(CAT_DATE_NEW);
		assertTrue(store.editCatMark(catm, CAT_MARK_NEW, CAT_POINTS_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatSubMarkDAO#deleteCatMark(com.yahoo.petermwenda83.bean.exam.Exam)}.
	 */
	@Ignore
	@Test
	public void testDeleteCatMark() {
		store = new CatSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatMarks catm = new CatMarks();
		catm.setStudentUuid(CAT_STU_UUID);
		catm.setSubjectUuid(CAT_SUB_UUID); 
		assertTrue(store.deleteCatMark(catm)); 
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.dao.exam.cat.CatSubMarkDAO#getAllCatSubMark()}.
	 */
	@Ignore
	@Test
	public void testGetAllCatSubMark() {
		store = new CatSubMarkDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatMarks> list = store.getAllCatSubMark();	
		for (CatMarks l : list) {
			System.out.println(l);
			
		}
		
	}

}
