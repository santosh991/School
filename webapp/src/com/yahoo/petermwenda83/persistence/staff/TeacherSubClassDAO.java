/**
 * 
 */
package com.yahoo.petermwenda83.persistence.staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.staff.TeacherSubClass;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.GenericDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;

/**
 * @author peter
 * 
 *
 */
public class TeacherSubClassDAO extends GenericDAO  implements SchoolTeacherSubClassDAO {

	private static TeacherSubClassDAO teacherSubClassDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	/**
	 * 
	 * @return subjectDAO
	 */
	public static TeacherSubClassDAO getInstance(){
		if(teacherSubClassDAO == null){
			teacherSubClassDAO = new TeacherSubClassDAO();		
		}
		return teacherSubClassDAO;
	}
	
	/**
	 * 
	 */
	public TeacherSubClassDAO() {
		super();
	}

	/**
	 * @param databaseName
	 * @param Host
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databasePort
	 */
	public TeacherSubClassDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort){
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolTeacherSubClassDAO#getSubjectClass(java.lang.String)
	 */
	@Override
	public TeacherSubClass getSubjectClass(String teacherUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolTeacherSubClassDAO#putSubjectClass(com.yahoo.petermwenda83.bean.staff.TeacherSubClass)
	 */
	@Override
	public boolean putSubjectClass(TeacherSubClass subClass) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolTeacherSubClassDAO#updateSubjectClass(com.yahoo.petermwenda83.bean.staff.TeacherSubClass)
	 */
	@Override
	public boolean updateSubjectClass(TeacherSubClass subClass) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolTeacherSubClassDAO#deleteSubjectClass(com.yahoo.petermwenda83.bean.staff.TeacherSubClass)
	 */
	@Override
	public boolean deleteSubjectClass(TeacherSubClass subClass) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.persistence.staff.SchoolTeacherSubClassDAO#getSubjectClassList()
	 */
	@Override
	public List<TeacherSubClass> getSubjectClassList() {
		List<TeacherSubClass>  list = null;
		
		 try(   
       		Connection conn = dbutils.getConnection();
       		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM TeacherSubClass;");   
       		ResultSet rset = pstmt.executeQuery();
   		) {
       	
           list = beanProcessor.toBeanList(rset, TeacherSubClass.class);

       } catch(SQLException e){
       	logger.error("SQL Exception when getting all TeacherSubClass");
           logger.error(ExceptionUtils.getStackTrace(e));
       }
     
		
		return list;
	}

}
