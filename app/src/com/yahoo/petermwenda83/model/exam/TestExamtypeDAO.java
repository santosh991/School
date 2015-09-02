/**##########################################################
/*************************************************************
 * ##########################################################
 * ##########################################################
 * ### This is My Forth Year Project#########################
 * ####### Maasai Mara University############################
 * ####### Year:2015-2016 ###################################
 * ####### Although this software is open source, No one
 * ###### should assume it ownership and copy paste 
 * ###### the code herein without the owner's approval.
 * ###################################################
 * ##########################################################
 * ##### School Management System ###########################
 * ##### Uses MVC Model, Postgres database, ant for 
 * ##### project management and other technologies.
 * ##### It consist Desktop application and a web
 * #### application all sharing the same DB.
 * ##########################################################
 * 
 */
package com.yahoo.petermwenda83.model.exam;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.contoller.exam.ExamType;
/**
 * @author peter<a href="mailto:mwendapeter72@gmail.com">Peter mwenda</a>
 *
 */
public class TestExamtypeDAO {
	final String databaseName = "allamanodb";
	final String Host = "localhost";
	final String databaseUsername = "allamano";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432;
	
	final String TYPE_UUID = "DAF7EC32-EA25-7D32-8708-2CC132446A2F",//DAF7EC32-EA25-7D32-8708-2CC132446A2F
    		     TYPE_UUID_NEW = "e65ebcd2-31df-47df-96b2-0a104bda136f";
    final String EXAM_TYPE = "CAT",
    		     EXAM_TYPE_NEW = "MAIN",
    		     EXAM_TYPE_UPDATE = "UPADTE" ;
    final String EXAM_TERM = "one",
    		     EXAM_TERM_NEW = "two",
    		     EXAM_TERM_UPDATE = "three" ;
    final String ROOM_UUID = "4DA86139-6A72-4089-8858-6A3A613FDFE6" ,
    		     ROOM_UUID_NEW = "37D3223A-547E-4BA9-BD0C-28F6187BB5D4",
    		     ROOM_UUID_UPDATE = "B6D838A4-9476-428D-9AFF-758BB55FF270";
    final String EXAM_YEAR = "2015",
    		     EXAM_YEAR_UPDATE = "2014" ;
    final String EXAM_DESCRI = " Cat 1",
    		     EXAM_DESCRI_NEW = "Cat 2",
    		     EXAM_DESCRI_UPDATE = "CatUPDATE" ;
    
    final String EXAM_MARKS_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
	final String STU_UUID = "9771e08e-4e87-4fe4-9608-a31952ec10cd";//type uuid DAF7EC32-EA25-7D32-8708-2CC132446A2F
	final String SUB_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446A2E";//D0F7EC32-EA25-7D32-8708-2CC132446A2E
	final String MARKS = "78", MARKS2 = "60",MARKS3 = "93";                                       
	final Date EXAM_DATE = new Date(new Long("1417633242000"));
	
	final String RESULTS_UUID = "204916f3-346d-4297-bdfa-82215820fff5";
	final String RESULTS_TOTALS = "877";
	final String RESULTS_POINTS = "78";
	final String RESULTS_GRADE = "A-";
	final String RESULTS_POSITION = "1";
	final String RESULTS_REMARKS = "Exellent";
	final Date RESULTS_DATE = new Date(new Long("1417633242000"));
	
	final String CAT_UUID = "71f243fb-3826-4911-b1cb-545d9a033578";
	
	final String CAT_RESULTS = "a3c4dbbe-320c-464e-8b18-f6c90987e1d0";
	final String CAT_TOTALS ="500";
	final String CAT_POINTS = "60";
	final String CAT_GRADE = "C-";
	final String CAT_REMARKS = "fair";
	final String OUTOF = "30";
	
	final String CEXAMNO = "EN656",CEXAMNO2 = "EN65773";
	
	final String SUBJECT_UUID ="D0F7EC32-EA25-7D32-8708-2CC132446A2E",
			     SUBJECT_UUID_NEW ="b9bbd718-b32f-4466-ab34-42f544ff900e";
	
	
	
	private ExamtypeDAO store;

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#getExamType(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.getExamType(TYPE_UUID);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getRoomnameUuid(),ROOM_UUID);
		assertEquals(examType.getOutof(),OUTOF);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		assertEquals(examType.getExamno(),CEXAMNO);
		assertEquals(examType.getSubjectUuid(),SUBJECT_UUID);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#getExamTypes(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetExamTypes() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.getExamTypes(CEXAMNO);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getRoomnameUuid(),ROOM_UUID);
		assertEquals(examType.getOutof(),OUTOF);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		assertEquals(examType.getExamno(),CEXAMNO);
		assertEquals(examType.getSubjectUuid(),SUBJECT_UUID);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#get(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType = store.get(EXAM_TYPE, ROOM_UUID, EXAM_DESCRI);
		assertEquals(examType.getUuid(),TYPE_UUID);
		assertEquals(examType.getExamtype(),EXAM_TYPE);
		assertEquals(examType.getTerm(),EXAM_TERM);
		assertEquals(examType.getRoomnameUuid(),ROOM_UUID);
		assertEquals(examType.getOutof(),OUTOF);
		assertEquals(examType.getYear(),EXAM_YEAR);
		assertEquals(examType.getDescription(),EXAM_DESCRI);
		assertEquals(examType.getSubjectUuid(),SUBJECT_UUID);
		//assertEquals(examType.getExamno(),CEXAMNO);
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#putExamType(com.yahoo.petermwenda83.contoller.exam.ExamType)}.
	 */
	@Ignore
	@Test
	public void testPutExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType examType = new ExamType();
		examType.setUuid(TYPE_UUID_NEW);
		examType.setExamtype(EXAM_TYPE_NEW);
		examType.setTerm(EXAM_TERM_NEW);
	    examType.setYear(EXAM_YEAR);
		examType.setRoomnameUuid(ROOM_UUID_NEW);
		examType.setOutof(OUTOF); 
	    examType.setDescription(EXAM_DESCRI_NEW);
	    examType.setExamno(CEXAMNO);
	    examType.setSubjectUuid(SUBJECT_UUID_NEW); 
	    assertTrue(store.putExamType(examType));
		 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#editExamType(com.yahoo.petermwenda83.contoller.exam.ExamType, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testEditExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType type = new ExamType();//
		type.setExamtype(EXAM_TYPE_UPDATE);
		type.setTerm(EXAM_TERM_UPDATE);
		type.setYear(EXAM_YEAR_UPDATE);
		type.setRoomnameUuid(ROOM_UUID_UPDATE);
		type.setOutof(OUTOF); 
		type.setDescription(EXAM_DESCRI_UPDATE);
		type.setExamno(CEXAMNO2);
		type.setSubjectUuid(SUBJECT_UUID_NEW); 
	    assertTrue(store.editExamType(type, TYPE_UUID_NEW)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#editExamType(com.yahoo.petermwenda83.contoller.exam.ExamType, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testDeleteExamType() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		ExamType type = new ExamType();
		type.setStudentUuid(SUBJECT_UUID);
		assertTrue(store.deleteExamType(type));
	}
	/**
	 * Test method for {@link com.yahoo.petermwenda83.model.exam.ExamtypeDAO#getAllExamtype()}.
	 */
	@Ignore
	@Test
	public void testGetAllExamtype() {
		store = new ExamtypeDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<ExamType> list = store.getAllExamtype();	
		for (ExamType l : list) {
			System.out.println(l);
			
		}
	}

}
