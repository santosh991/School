/**
 * 
 */
package com.yahoo.petermwenda83.server.servlet.othermoney;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.othermoney.Otherstype;
import com.yahoo.petermwenda83.bean.othermoney.TermOtherMonies;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.othermoney.OtherstypeDAO;
import com.yahoo.petermwenda83.persistence.othermoney.TermOtherMoniesDAO;
import com.yahoo.petermwenda83.server.session.SessionConstants;

/**
 * @author peter
 *
 */
public class NewPayment extends HttpServlet{
	
	
	private static TermOtherMoniesDAO termOtherMoniesDAO;
	private static OtherstypeDAO otherstypeDAO;
	private static ExamConfigDAO examConfigDAO;
	
	Otherstype otherstype;
	TermOtherMonies termOtherMonies;
	ExamConfig examConfig;

	/**  
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       termOtherMoniesDAO = TermOtherMoniesDAO.getInstance();
       otherstypeDAO = OtherstypeDAO.getInstance();
       examConfigDAO = ExamConfigDAO.getInstance();
       
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession(true);
       
       String schooluuid = StringUtils.trimToEmpty(request.getParameter("schooluuid"));
       String type = StringUtils.trimToEmpty(request.getParameter("type"));
       String amount = StringUtils.trimToEmpty(request.getParameter("amount"));
       
       if(StringUtils.isBlank(schooluuid)){
    	   session.setAttribute(SessionConstants.OTHER_MONIES_ADD_ERROR, SessionConstants.OTHER_MONIES_ADD_ERROR); 
    	   
       }else if(StringUtils.isBlank(type)){
    	   session.setAttribute(SessionConstants.OTHER_MONIES_ADD_ERROR, SessionConstants.OTHER_MONIES_ADD_ERROR); 
    	   
       }else if(StringUtils.isBlank(amount)){
    	   session.setAttribute(SessionConstants.OTHER_MONIES_ADD_ERROR, SessionConstants.OTHER_MONIES_ADD_ERROR); 
    	   
       }else{
    	   
    	   examConfig = new ExamConfig();
    	   examConfig = examConfigDAO.getExamConfig(schooluuid);
    	   
    	   otherstype = new Otherstype();
    	   otherstype.setType(type); 
    	   otherstype.setSchoolAccountUuid(schooluuid);
    	   otherstype.setTerm(examConfig.getTerm());
    	   otherstype.setYear(examConfig.getYear()); 
    	   if(otherstypeDAO.putOtherstype(otherstype)){
    		   termOtherMonies = new TermOtherMonies();
    		   termOtherMonies.setSchoolAccountUuid(schooluuid);
    		   termOtherMonies.setOtherstypeUuid(otherstype.getUuid()); 
    		   termOtherMonies.setAmount(Double.parseDouble(amount));
    		   if(termOtherMoniesDAO.putTermOtherMonies(termOtherMonies)){
    			   session.setAttribute(SessionConstants.OTHER_MONIES_ADD_SUCESS, SessionConstants.OTHER_MONIES_ADD_SUCESS); 
    			   
    		   }else{
    			   session.setAttribute(SessionConstants.OTHER_MONIES_ADD_ERROR, SessionConstants.OTHER_MONIES_ADD_ERROR); 
    		   }
    		   
    	   }
    	   
       }
       
       response.sendRedirect("newPayment.jsp");  
       return;
       
        }
   

/**
 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         doPost(request, response);
     }

/**
 * 
 */
private static final long serialVersionUID = 8284355777739195442L;
}
