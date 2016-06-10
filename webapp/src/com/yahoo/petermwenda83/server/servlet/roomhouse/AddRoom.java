/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.roomhouse;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/** 
 * @author peter
 *
 */
public class AddRoom extends HttpServlet{
	
	
	
	final String EMPTY_ROOM = "Class room name can't be empty";
	final String ERROR_EMPTY_SCH_ID = "Something went wrong";
	final String ERROR_ROOM_EXIST = "A room with the given name exist";
	final String ERROR_ROOM_NAME_INVALID = "Class-room name must have length of 8 characters e.g FORM 1 N (space is a character)";//FORM 1 N   8 char
	final String ERROR_ROOM_ADD = "Error occured while adding Class room";
	final String SUCCESS_ROOM_ADD = "Classroom added successfully";
	
	private static RoomDAO roomDAO;
	ClassRoom classRoom;

	/**    
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       roomDAO = RoomDAO.getInstance();
      
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

	   HttpSession session = request.getSession(true);
	   
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String roomname = StringUtils.trimToEmpty(request.getParameter("roomname"));
       
       if(StringUtils.isBlank(schooluuid)){
    	   session.setAttribute(SessionConstants.ROOM_REG_ERROR, ERROR_EMPTY_SCH_ID); 
    	   
       }else if(StringUtils.isBlank(roomname)){
    	   session.setAttribute(SessionConstants.ROOM_REG_ERROR, EMPTY_ROOM); 
    	   
       }else if(!lengthValid(roomname)){
	 	   session.setAttribute(SessionConstants.ROOM_REG_ERROR, ERROR_ROOM_NAME_INVALID); 
		   
	   }else if(roomDAO.getroomByRoomName(schooluuid, roomname) !=null){
    	   session.setAttribute(SessionConstants.ROOM_REG_ERROR, ERROR_ROOM_EXIST); 
    	   
       }else{
    	   
    	    classRoom = new ClassRoom();
    	    classRoom.setSchoolAccountUuid(schooluuid);
    	    classRoom.setRoomName(roomname.toUpperCase());
    	    
    	    if(roomDAO.putroom(classRoom)){
    	    	session.setAttribute(SessionConstants.ROOM_REG_SUCCESS, SUCCESS_ROOM_ADD); 
    	    }else{
    	    	session.setAttribute(SessionConstants.ROOM_REG_ERROR, ERROR_ROOM_ADD); 
    	    }
    	   
       }
       response.sendRedirect("addnewClass.jsp");  
	   return;
       
   }
   
   /**
	 * @param mystring
	 * @return
	 */
	private static boolean lengthValid(String mystring) {
		boolean isvalid = true;
		int length = 0;
		length = mystring.length();
		if(length<8 ||length>8){
			isvalid = false;
		}
		return isvalid;
	}
	
   

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }

/**
 * 
 */
private static final long serialVersionUID = -3760207702778606378L;

}
