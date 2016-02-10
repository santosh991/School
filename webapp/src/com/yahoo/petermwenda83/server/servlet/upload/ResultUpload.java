
package com.yahoo.petermwenda83.server.servlet.upload;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.CatOne;
import com.yahoo.petermwenda83.bean.exam.CatTwo;
import com.yahoo.petermwenda83.bean.exam.EndTerm;
import com.yahoo.petermwenda83.bean.exam.StudentExam;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamEgineDAO;
import com.yahoo.petermwenda83.persistence.exam.StudentExamDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.session.SessionConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class ResultUpload extends HttpServlet {
	  	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8885323460146577309L;
	public final static String UPLOAD_FEEDBACK = "UploadFeedback";
	public final static String UPLOAD_SUCCESS = "You have successfully uploaded your results.";
	
	
	private Logger logger;
	private static StudentDAO studentDAO;
	private static StudentExamDAO studentExamDAO;
	private static ExamEgineDAO examEgineDAO;
	private static RoomDAO roomDAO;
	private static SubjectDAO subjectDAO;
	
	private final String UPLOAD_DIR = "/home/peter/Documents/radom";
	final String DELIMITER = ",";
	File uploadedFile = null;
	final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
	final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	
	final String SUBJECT = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
	final String TEACHER = "F49DB775-4952-4915-B978-9D9F3E36D6E9";
	
	String username = "peter";
	HttpSession session;
	private Cache schoolCache;
	String staffUsername;
	String schooluuid = "";
	
	/**
    *
    * @param config
    * @throws ServletException
    */
   @Override
   public void init(ServletConfig config) throws ServletException {
       super.init(config);
       
       // Create a factory for disk-based file items
       DiskFileItemFactory factory = new DiskFileItemFactory();
       File repository = FileUtils.getTempDirectory();
       factory.setRepository(repository);
       CacheManager.getInstance();
       studentDAO = StudentDAO.getInstance();
       studentExamDAO = StudentExamDAO.getInstance();
       examEgineDAO = ExamEgineDAO.getInstance();
       roomDAO = RoomDAO.getInstance();
       subjectDAO = SubjectDAO.getInstance();
       CacheManager mgr = CacheManager.getInstance();
       schoolCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
   }
	
	
	/**
    * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	*/
	@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		
		session = request.getSession(true);
		boolean isMultpart = ServletFileUpload.isMultipartContent(request);
		
		String schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
		staffUsername = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_USERNAME);
		String stffID = (String) session.getAttribute(SessionConstants.SCHOOL_STAFF_SIGN_IN_ID);
		  SchoolAccount school = new SchoolAccount();
	       Element element;
	       if ((element = schoolCache.get(schoolusername)) != null) {
	    	   school = (SchoolAccount) element.getObjectValue();
	    	   schooluuid = school.getUuid();
	        }
	   
        if(!isMultpart){
    	   session.setAttribute(SessionConstants.FILE_UPLOAD_ERROR, "this servlet only handles file upload request"); 
        }
        else {
    	// Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();  
           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);
       
       // Parse the request              
       try {
		List<FileItem> items = upload.parseRequest(request);
		
		for(FileItem item : items){
			String name = new File(item.getName()).getName();
			
			String [] parts = name.split("\\.");
			String subject = parts[0];
			String classroom = parts[1]; 
			String exam = parts[2]; 
			
			if(!item.isFormField()){	
				 uploadedFile = processUploadedFile(item,subject,classroom,exam); 
				
		          }
						
		       }//end for loop
			
		
		   
		
	        } catch (FileUploadException e) {
	    	logger.error("FileUploadException while getting File Items.");
			logger.error(e);
	         } catch (Exception e) {
							e.printStackTrace();
			}
	   
       
       }//end if is multpart
       
		 session.setAttribute(SessionConstants.FILE_UPLOAD_SUCCESS, UPLOAD_SUCCESS); 
         response.sendRedirect("exam.jsp");
    
	}//end do post
	

	
	
	
	
/**
 * @param item
 * @param subjectname
 * @param roomname
 * @return
 * @throws Exception
 */
private File processUploadedFile(FileItem item, String subjectname, String roomname,String examtype) throws Exception {
	
	File file = null;
	String name = new File(item.getName()).getName();
	item.write(new File(UPLOAD_DIR + File.separator +username+name));
	
	String scvfile = "/home/peter/Documents/radom/"+username+name;
	BufferedReader br = null;
	String line = "";
	String classroomuuid = "";
	String subjectuuid = "";
	Student student;
	ClassRoom room;
	Subject subjct;
	
	try{
		br = new BufferedReader(new FileReader(scvfile));
		
		while((line = br.readLine()) !=null){
			String [] content = line.split(DELIMITER);
			
			String admno = content[0];
			Double score = Double.parseDouble(content[1]);
	        
			
			if(roomDAO.getroomByRoomName(SCHOOL_UUID, roomname) !=null){
				room = roomDAO.getroomByRoomName(SCHOOL_UUID, roomname);
			    classroomuuid = room.getUuid();
			}
			   if(subjectDAO.getSubjects(subjectname) !=null){
				  subjct = subjectDAO.getSubjects(subjectname);
				  subjectuuid = subjct.getUuid();
			       } 
			
			if(studentDAO.getStudents(schooluuid,admno) == null){
				
			}else{
				//check validity
				InspectToken(admno,score);	
				
				student = studentDAO.getStudents(schooluuid,admno);
				String studentUuid = student.getUuid();
		
				if((studentExamDAO.getStudentExam(SCHOOL_UUID, studentUuid)) !=null){
		            
					if(StringUtils.equalsIgnoreCase(examtype, "c1")){
						CatOne catOne = new CatOne();
			            catOne.setSchoolAccountUuid(SCHOOL_UUID);
						catOne.setTeacherUuid(TEACHER);
						catOne.setClassRoomUuid(classroomuuid);  
						catOne.setSubjectUuid(subjectuuid);
						catOne.setStudentUuid(studentUuid);
						catOne.setCatOne(score); 
						catOne.setTerm("1");
						catOne.setYear("2015"); 
					    examEgineDAO.putScore(catOne);	
					}
		            
					if(StringUtils.equalsIgnoreCase(examtype, "c2")){
						CatTwo catwo = new CatTwo();
			            catwo.setSchoolAccountUuid(SCHOOL_UUID);
			            catwo.setTeacherUuid(TEACHER);
			            catwo.setClassRoomUuid(classroomuuid);  
			            catwo.setSubjectUuid(subjectuuid);
			            catwo.setStudentUuid(studentUuid);
			            catwo.setCatTwo(score); 
			            catwo.setTerm("1");
			            catwo.setYear("2015"); 
					    examEgineDAO.putScore(catwo);
					}
					if(StringUtils.equalsIgnoreCase(examtype, "et")){
						    EndTerm endterm = new EndTerm();
				            endterm.setSchoolAccountUuid(SCHOOL_UUID);
				            endterm.setTeacherUuid(TEACHER);
				            endterm.setClassRoomUuid(classroomuuid);  
				            endterm.setSubjectUuid(subjectuuid);
				            endterm.setStudentUuid(studentUuid);
				            endterm.setEndTerm(score); 
				            endterm.setTerm("1");
				            endterm.setYear("2015"); 
						    examEgineDAO.putScore(endterm);
					}
				    
				    
				   
		            
						
				}else{
                    
					StudentExam studentExam = new StudentExam();
					studentExam.setSchoolAccountUuid(SCHOOL_UUID);
					studentExam.setClassRoomUuid(FORM_ONE_N);
					studentExam.setStudentUuid(studentUuid); 
					if(studentExamDAO.getStudentExam(SCHOOL_UUID, studentUuid) ==null){
						studentExamDAO.putStudentExam(studentExam);
						
					}
					
				}
				
			}
			
		}	
		
	}catch(FileNotFoundException e){
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	}finally{
		if(br !=null){
			try{
				br.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
		return file;
	}


private String InspectToken(String admno, Double score) {
	String feedback = ResultUpload.UPLOAD_SUCCESS;
	if(studentDAO.getStudents(schooluuid,admno) == null){
		return " Admossion Number " +admno+" Not found" ;
	}if(score <= 0){
		return " score " +score+" Not allowed" ;
	}
	
	return feedback;
}


/**
 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doPost(request, response);
   }
}
