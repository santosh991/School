/**
 * 
 */
package com.yahoo.petermwenda83.persistence.classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.Exam;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.GenericDAO;

/**
 * @author peter
 *
 */
public class RoomDAO extends GenericDAO implements SchoolRoomDAO {
	
	private static RoomDAO roomDAO;
	private Logger logger = Logger.getLogger(this.getClass());
	private BeanProcessor beanProcessor = new BeanProcessor();
	
	public static RoomDAO getInstance(){
		
		if(roomDAO == null){
			roomDAO = new RoomDAO();		
		}
		return roomDAO;
	}
	
	/**
	 * 
	 */
	public RoomDAO() { 
		super();
	}
	
	/**
	 * 
	 */
	public RoomDAO(String databaseName, String Host, String databaseUsername, String databasePassword, int databasePort) {
		super(databaseName, Host, databaseUsername, databasePassword, databasePort);
	}

	@Override
	public ClassRoom getroom(String SchoolAccountUuid, String Uuid) {
		ClassRoom classRoom = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ClassRoom WHERE SchoolAccountUuid = ? AND Uuid =? ;");       
        		
        		){
        	
        	 pstmt.setString(1, SchoolAccountUuid);
        	 pstmt.setString(2, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 classRoom  = beanProcessor.toBean(rset,ClassRoom.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting classRoom with Uuid: " + Uuid);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
        //System.out.println(classRoom);
		return classRoom; 
	}

	@Override
	public ClassRoom getroomByRoomName(String SchoolAccountUuid, String RoomName) {
		ClassRoom classRoom = null;
        ResultSet rset = null;
        try(
        		  Connection conn = dbutils.getConnection();
           	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ClassRoom WHERE SchoolAccountUuid = ? AND RoomName =? ;");       
        		
        		){
        	
        	 pstmt.setString(1, SchoolAccountUuid);
        	 pstmt.setString(2, RoomName);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 classRoom  = beanProcessor.toBean(rset,ClassRoom.class);
	   }
        	
        	
        	
        }catch(SQLException e){
        	 logger.error("SQL Exception when getting classRoom with RoomName: " + RoomName);
             logger.error(ExceptionUtils.getStackTrace(e));
             System.out.println(ExceptionUtils.getStackTrace(e));
        }
        //System.out.println(classRoom);
		return classRoom; 
	}

	@Override
	public boolean putroom(ClassRoom room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateroom(ClassRoom room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteroom(ClassRoom room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClassRoom> getAllRooms(String SchoolAccountUuid) {
		List<ClassRoom> list = new ArrayList<>();

        try (
        		 Connection conn = dbutils.getConnection();
     	         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ClassRoom WHERE SchoolAccountUuid = ?;");    		   
     	   ) {
         	   pstmt.setString(1, SchoolAccountUuid);           
         	   try( ResultSet rset = pstmt.executeQuery();){
     	       
     	       list = beanProcessor.toBeanList(rset, ClassRoom.class);
         	   }
        } catch (SQLException e) {
            logger.error("SQLException when getting ClassRoom List of a school with schoolAccountUuid " + SchoolAccountUuid ); 
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return list;
	}

	
	
	

}
