/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam.cat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.yahoo.petermwenda83.bean.exam.cat.CatDetails;

/**
 * @author peter
 *
 */
public class TestCatDetailsDAO {
	
	final String databaseName = "schooldb";
	final String Host = "localhost";
	final String databaseUsername = "school";
	final String databasePassword = "AllaManO1";
	final int databasePort = 5432; 
	
	private String UUID = "ED080990-148B-48A1-92EA-7A01BAB7CE1D",
			       UUID_NEW="51759E97-722D-437E-B8E1-7F24B943CCBE";

	private String CAT_UUID ="D50E6399-B913-42F2-A5B6-F0D4BAAF9571",
			       CAT_UUID_NEW="34C4244E-5CE0-4D5D-AD85-60E97FDDD80A";
	
	private String ROOM_NAME_UUID ="4DA86139-6A72-4089-8858-6A3A613FDFE6",
			       ROOM_NAME_UUID_NEW="4927E344-0A7C-40A1-AD9B-4A0E4AC305B8";
	
	private String SUBJECT_UUID = "66027e51-b1ad-4b10-8250-63af64d23323",
			      SUBJECT_UUID_NEW="b9bbd718-b32f-4466-ab34-42f544ff900e";
	
	private String YEAR = "2015",
			       YEAR_NEW="2016",
			       YEAR_UPDATE="2017";
	
	private String TERM = "ONE",
			       TERM_NEW="TWO",
			       TERM_UPDATE="THREE";
	
	private int OUTOF = 40,
			    OUTOF_NEW=50,
			    OUTOF_UPDATE=60;
	
	
	private CatDetailsDAO store;
	

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#get(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGet() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatDetails c = new CatDetails();
		c = store.get(CAT_UUID, ROOM_NAME_UUID, SUBJECT_UUID);
		assertEquals(c.getUuid(),UUID);
		assertEquals(c.getCatuuid(),CAT_UUID);
		assertEquals(c.getRoomnameuuid(),ROOM_NAME_UUID);
		assertEquals(c.getSubjectUuid(),SUBJECT_UUID);
		assertEquals(c.getTerm(),TERM);
		assertEquals(c.getYear(),YEAR);
		assertEquals(c.getOutof(),OUTOF);
		
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#put(com.yahoo.petermwenda83.bean.exam.cat.CatDetails)}.
	 */
	@Ignore
	@Test
	public void testPut() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatDetails c = new CatDetails();
		c.setUuid(UUID_NEW);
		c.setCatuuid(CAT_UUID_NEW);
		c.setRoomnameuuid(ROOM_NAME_UUID_NEW);
		c.setSubjectUuid(SUBJECT_UUID_NEW);
		c.setYear(YEAR_NEW);
		c.setTerm(TERM_NEW);
		c.setOutof(OUTOF_NEW);
		assertTrue(store.put(c));
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#edit(com.yahoo.petermwenda83.bean.exam.cat.CatDetails)}.
	 */
	@Ignore
	@Test
	public void testEdit() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatDetails c = new CatDetails();
		c.setCatuuid(CAT_UUID_NEW);
		c.setRoomnameuuid(ROOM_NAME_UUID_NEW);
		c.setSubjectUuid(SUBJECT_UUID_NEW);
		c.setYear(YEAR_UPDATE);
		c.setTerm(TERM_UPDATE);
		c.setOutof(OUTOF_UPDATE);
		assertTrue(store.edit(c) );
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#delete(com.yahoo.petermwenda83.bean.exam.cat.CatDetails)}.
	 */
	@Ignore
	@Test
	public void testDelete() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		CatDetails c = new CatDetails();
		c.setCatuuid(CAT_UUID_NEW);
		c.setRoomnameuuid(ROOM_NAME_UUID_NEW);
		c.setSubjectUuid(SUBJECT_UUID_NEW);
		assertTrue(store.delete(c)); 
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#getAllCatDetails()}.
	 */
	@Ignore
	@Test
	public void testGetAllCatDetails() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatDetails> list = store.getAllCatDetails();	 
		for (CatDetails l : list) {
			System.out.println(l);	
		}
	}

	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#getAllCatDetails(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testGetAllCatDetailsString() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatDetails> list = store.getAllCatDetails(TERM);	 
		for (CatDetails l : list) {
			System.out.println(l);	
		}
	}
	
	/**
	 * Test method for {@link com.yahoo.petermwenda83.persistence.exam.cat.CatDetailsDAO#getAllCatDetails(java.lang.String)}.
	 */
	//@Ignore
	@Test
	public void testGetAllCatDetail() {
		store =  new CatDetailsDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
		List<CatDetails> list = store.getAllCatDetail(CAT_UUID, ROOM_NAME_UUID, SUBJECT_UUID,TERM) ;
		for (CatDetails l : list) {
			System.out.println(l);	
		}
	}

}
