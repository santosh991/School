/**
 * 
 */
package com.yahoo.petermwenda83.model.principal;

import java.util.List;

import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition;
import com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff;
import com.yahoo.petermwenda83.contoller.staff.teaching.Position;
import com.yahoo.petermwenda83.contoller.staff.teaching.Teacher;

/**
 * @author peter
 *
 */
public class SRegistrationDAO implements StaffRegistrationDAO {

	/**
	 * 
	 */
	public SRegistrationDAO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getTeacher(com.yahoo.petermwenda83.contoller.staff.teaching.Position, java.lang.String)
	 */
	@Override
	public Teacher getTeacher(Position position, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getNTstaff(com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition, java.lang.String)
	 */
	@Override
	public Teacher getNTstaff(NTPosition ntposition, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#putTeacher(com.yahoo.petermwenda83.contoller.staff.teaching.Teacher, com.yahoo.petermwenda83.contoller.staff.teaching.Position, java.lang.String)
	 */
	@Override
	public boolean putTeacher(Teacher teacher, Position position, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#putNTstaff(com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff, com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition, java.lang.String)
	 */
	@Override
	public boolean putNTstaff(NTstaff ntstaff, NTPosition ntposition,
			String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#editTeacher(com.yahoo.petermwenda83.contoller.staff.teaching.Teacher, com.yahoo.petermwenda83.contoller.staff.teaching.Position, java.lang.String)
	 */
	@Override
	public boolean editTeacher(Teacher teacher, Position position, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#editNTstaff(com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff, com.yahoo.petermwenda83.contoller.staff.nonteaching.NTPosition, java.lang.String)
	 */
	@Override
	public boolean editNTstaff(NTstaff ntstaff, NTPosition ntposition,
			String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#deleteTeacher(com.yahoo.petermwenda83.contoller.staff.teaching.Teacher, java.lang.String)
	 */
	@Override
	public boolean deleteTeacher(Teacher teacher, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#deleteNTstaff(com.yahoo.petermwenda83.contoller.staff.nonteaching.NTstaff, java.lang.String)
	 */
	@Override
	public boolean deleteNTstaff(NTstaff ntstaff, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getTeacher()
	 */
	@Override
	public List<Teacher> getTeacher() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.principal.StaffRegistrationDAO#getNTstaff()
	 */
	@Override
	public List<Teacher> getNTstaff() {
		// TODO Auto-generated method stub
		return null;
	}

}
