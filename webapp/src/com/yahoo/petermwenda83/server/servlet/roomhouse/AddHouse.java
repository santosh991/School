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

import com.yahoo.petermwenda83.bean.student.House;
import com.yahoo.petermwenda83.persistence.student.HouseDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/**
 * @author peter
 *
 */
public class AddHouse extends HttpServlet{
	
	
	final String EMPTY_HOUSE = "House name can't be empty";
	final String ERROR_EMPTY_SCH_ID = "Something went wrong";
	final String ERROR_HOUSE_EXIST = "A room with the given name exist";
	final String ERROR_HOUSE_ADD = "Error occured while adding house";
	final String SUCCESS_HOUSE_ADD = "House added successfully";
	
	
	private static HouseDAO houseDAO;
	House house;


	/**    
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       houseDAO = HouseDAO.getInstance();
   }
   
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

	   HttpSession session = request.getSession(true);
	   
	   String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String housename = StringUtils.trimToEmpty(request.getParameter("housename"));
       
       if(StringUtils.isBlank(schooluuid)){
    	   session.setAttribute(SessionConstants.HOUSE_REG_ERROR, ERROR_EMPTY_SCH_ID); 
    	   
       }else if(StringUtils.isBlank(housename)){
    	   session.setAttribute(SessionConstants.HOUSE_REG_ERROR, EMPTY_HOUSE); 
    	   
       }else{
    	   
    	    house = new House();
    	    house.setSchoolAccountUuid(schooluuid);
    	    house.setHouseName(housename.toUpperCase());
    	    
    	    if(houseDAO.putHouse(house)){
    	    	session.setAttribute(SessionConstants.HOUSE_REG_SUCCESS, SUCCESS_HOUSE_ADD); 
    	    }else{
    	    	session.setAttribute(SessionConstants.HOUSE_REG_ERROR, ERROR_HOUSE_ADD); 
    	    }
    	  
       }
       response.sendRedirect("addnewHouse.jsp");  
	   return;
       
   }
   

@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }

/**
 * 
 */
private static final long serialVersionUID = 568354307812304203L;
}
