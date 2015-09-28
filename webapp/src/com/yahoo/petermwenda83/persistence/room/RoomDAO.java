/**
 * 
 */
package com.yahoo.petermwenda83.persistence.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.room.ClassRoom;
import com.yahoo.petermwenda83.persistence.DBConnectDAO;

/**
 * @author peter
 *
 */
public class RoomDAO extends DBConnectDAO implements SchoolRoomDAO {
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

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.room.SchoolRoomDAO#getroom(java.lang.String)
	 */
	@Override
	public ClassRoom getroom(String Uuid) {
		ClassRoom room = new ClassRoom();
        ResultSet rset = null;
     try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ClassRoom WHERE Uuid = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, Uuid);
	         rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 room  = beanProcessor.toBean(rset,ClassRoom.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting ExamType with uuid: " + Uuid);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return room; 
	}
	
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.room.SchoolRoomDAO#get(java.lang.String)
	 */
	@Override
	public ClassRoom get(String Room,String RoomName) {
		ClassRoom room = new ClassRoom();
        ResultSet rset = null;
       try(
     		 Connection conn = dbutils.getConnection();
        	      PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ClassRoom WHERE Room=? AND RoomName = ?;");       
     		
     		){
     	
     	 pstmt.setString(1, Room);
     	 pstmt.setString(2, RoomName);
	     rset = pstmt.executeQuery();
	     while(rset.next()){
	
	    	 room  = beanProcessor.toBean(rset,ClassRoom.class);
	   }
     	
     	
     	
     }catch(SQLException e){
     	 logger.error("SQL Exception when getting ExamType with RoomName: " + Room+RoomName);
          logger.error(ExceptionUtils.getStackTrace(e));
          System.out.println(ExceptionUtils.getStackTrace(e));
     }
     
		return room; 
	}
	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.room.SchoolRoomDAO#putroom(com.yahoo.petermwenda83.contoller.room.ClassRoom)
	 */
	@Override
	public boolean putroom(ClassRoom room) {
		boolean success = true; 
		  
		 try(   Connection conn = dbutils.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ClassRoom" 
			        		+"(Uuid,SchoolAccountUuid,Room,Roomname) VALUES (?,?,?,?);");
      		){
			   
	            pstmt.setString(1, room.getUuid());
	            pstmt.setString(2, room.getSchoolAccountUuid());
	            pstmt.setString(3, room.getRoom());
	            pstmt.setString(4, room.getRoomName());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to put ClassRoom: "+room);
           logger.error(ExceptionUtils.getStackTrace(e)); 
           System.out.println(ExceptionUtils.getStackTrace(e));
           success = false;
		 }
		
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.room.SchoolRoomDAO#editroom(com.yahoo.petermwenda83.contoller.room.ClassRoom, java.lang.String)
	 */
	@Override
	public boolean editroom(ClassRoom room) {
		 boolean success = true; 
		 try(   Connection conn = dbutils.getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("UPDATE ClassRoom SET Room =?,RoomName =?,SchoolAccountUuid =? "
			+ " WHERE Uuid = ? ;");
        		){
	            pstmt.setString(1, room.getRoom());
	            pstmt.setString(2, room.getRoomName());
	            pstmt.setString(3, room.getSchoolAccountUuid());
	            pstmt.setString(4, room.getUuid());
	            pstmt.executeUpdate();
			 
		 }catch(SQLException e){
			 logger.error("SQL Exception trying to update ClassRoom: "+room);
             logger.error(ExceptionUtils.getStackTrace(e)); 
             System.out.println(ExceptionUtils.getStackTrace(e));
             success = false;
		 }
		
		return success;
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.room.SchoolRoomDAO#deleteroom(com.yahoo.petermwenda83.contoller.room.ClassRoom, java.lang.String)
	 */
	@Override
	public boolean deleteroom(ClassRoom room) {
		 boolean success = true; 
	      try(
	      		  Connection conn = dbutils.getConnection();
	         	      PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ClassRoom WHERE"
	         	      		+ " Room = ? AND RoomName =?;");       
	      		
	      		){
	      	
	      	 pstmt.setString(1, room.getRoom());
	      	 pstmt.setString(2, room.getRoomName());
		     pstmt.executeUpdate();
		     
	      }catch(SQLException e){
	      	 logger.error("SQL Exception when deletting ClassRoom: " +room);
	           logger.error(ExceptionUtils.getStackTrace(e));
	           System.out.println(ExceptionUtils.getStackTrace(e));
	           success = false;
	           
	      }
	      
			return success; 
	}

	/* (non-Javadoc)
	 * @see com.yahoo.petermwenda83.model.room.SchoolRoomDAO#getAllRooms()
	 */
	@Override
	public List<ClassRoom> getAllRooms() {
		  List<ClassRoom> list =new  ArrayList<>(); 
		  try(   
	      		Connection conn = dbutils.getConnection();
	      		PreparedStatement  pstmt = conn.prepareStatement("SELECT * FROM ClassRoom ;");   
	      		ResultSet rset = pstmt.executeQuery();
	  		) {
	      	
	          list = beanProcessor.toBeanList(rset, ClassRoom.class);

	      } catch(SQLException e){
	      	  logger.error("SQL Exception when getting all ClassRoom");
	          logger.error(ExceptionUtils.getStackTrace(e));
	          System.out.println(ExceptionUtils.getStackTrace(e));
	      }
	   
		return list;
	}

	

}
