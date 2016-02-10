package com.yahoo.petermwenda83.server.servlet.export;

import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class ExportExcel extends HttpServlet{

		
	 /**
	 * 
	 */
	
	 private String schoolusername = "";
	 private String classroomuuidToken = "";
	 private final String SPREADSHEET_NAME = "Export.xlsx";
	 private DbFileUtils dbFileUtils;
	 private Cache schoolaccountCache;
		 /** 
		 *    
		 * @param config
		 * @throws ServletException
		 */
	 public void init(ServletConfig config) throws ServletException {
		 super.init(config);
		 CacheManager mgr = CacheManager.getInstance();
		 schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
		 dbFileUtils = DbFileUtils.getInstance();
    }
	 
	 
	 /**
	    *
	    * @param request
	    * @param response
	    * @throws ServletException, IOException
	    */
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
		   
		   ServletOutputStream out = response.getOutputStream();
		   response.setContentType("application/zip");
	       response.setHeader("Cache-Control", "cache, must-revalidate");
	       response.setHeader("Pragma", "public");
	       
	       classroomuuidToken = StringUtils.trimToEmpty(request.getParameter("classroomuuid"));
	       
		   SchoolAccount school = new SchoolAccount();
		   HttpSession session = request.getSession(false);
		   
		   if(session !=null){
		   schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
		
		   
		      }
		   net.sf.ehcache.Element element;
		   
		   element = schoolaccountCache.get(schoolusername);
		   if(element !=null){
		   school = (SchoolAccount) element.getObjectValue();
	 
		   }
		   String fileName;
		   
		   fileName = new StringBuffer(school.getUsername()).append(" ")
	                .append(StringUtils.trimToEmpty(school.getUsername()))
	                .append(" ")
	                .append(SPREADSHEET_NAME)
	                .toString();

	        response.setHeader("Content-Disposition", "attachment; filename=\""
	                + StringUtils.replace(fileName, ".xlsx", ".zip") + "\"");
	        

	        File excelFile = new File(FileUtils.getTempDirectoryPath() + File.separator + fileName);
	        File csvFile = new File(StringUtils.replace(excelFile.getCanonicalPath(), ".xlsx", ".csv"));
	        File zippedFile = new File(StringUtils.replace(excelFile.getCanonicalPath(), ".xlsx", ".zip"));
		   
	     // These are to determine whether or not we have created a CSV & Excel file on disk
	        boolean successCSVFile = true, successExcelFile = true;		        
	        	successCSVFile = dbFileUtils.sqlResultToCSV(getExportTopupsSqlQuery(school), csvFile.toString(), '|');
	            
	        	if(successCSVFile) {
	        		successExcelFile = AllExportUtil.createExcelExport(csvFile.toString(), "|", excelFile.toString()); 
	        	}    	        	
	        			
	       
	        
	        if(successExcelFile) { // If we successfully created the MS Excel File on disk  
	        	// Zip the Excel file
	        	List<File> filesToZip = new ArrayList<>();
	        	filesToZip.add(excelFile);
	        	ZipUtil.compressFiles(filesToZip, zippedFile.toString());
	        	
	        	// Push the file to the request
	            FileInputStream input = FileUtils.openInputStream(zippedFile);
	            IOUtils.copy(input, out);
	        }
	        
	        out.close();
	        
	        FileUtils.deleteQuietly(excelFile);
	        FileUtils.deleteQuietly(csvFile);
	        FileUtils.deleteQuietly(zippedFile);
		   
		  
       }



/**
 * Gets the String that will be used to export all the topup activity of an 
 * account holder.
 * <p>
 * Note that it is tied to the design of the database.
 * 
 * @param account
 * @return the SQL query to be used
 */
private String getExportTopupsSqlQuery(SchoolAccount school) {
	StringBuffer query = new StringBuffer("SELECT student.firstname,student.lastname,student.surname, ")
			.append("studenthouse.housename,studentparent.fathername,studentparent.fatherphone,")
			.append("studentparent.mothername,studentparent.motherphone,studentprimary.schoolname,")
			.append("studentprimary.index,studentprimary.kcpeyear,studentprimary.kcpemark FROM student")
			.append("INNER JOIN studenthouse ON student.uuid=studenthouse.studentuuid INNER JOIN")
			.append("studentparent ON student.uuid=studentparent.studentuuid INNER JOIN studentprimary"
					+ " ON student.uuid=studentprimary.studentuuid WHERE student.classroomuuid = '")
			.append(classroomuuidToken+"'")
			.append("AND student.schoolaccountuuid = '")
			.append(school.getUuid())
			.append("';");
	
	return query.toString();
}




/**
*
* @param request
* @param response
* @throws ServletException, IOException
*/
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
doPost(request, response);
}

private static final long serialVersionUID = -3776557694529951828L;

}
