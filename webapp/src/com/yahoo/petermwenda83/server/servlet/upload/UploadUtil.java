

package com.yahoo.petermwenda83.server.servlet.upload;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
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

/**
 * Various manipulations on an uploaded  CSV file.
 * <p> 
 * 
 * @author peter
 * 
 */
public class UploadUtil {

	private static Logger logger = Logger.getLogger(UploadUtil.class);
	private String[] examcodeArray;
	private List<String> examcodeList;
	
	
	/**
	 * 
	 */
	protected UploadUtil() {
		examcodeArray = new String[] {"c1", "c2", "et", "p1","p2","p3"};
		examcodeList = Arrays.asList(examcodeArray);
	} 
	
	
	/**
	 * Checks that an uploaded results file is in proper order.
	 * 
	 * @param file
	 * @return the feedback of having inspected the file, whether it was proper
	 */
	protected String inspectResultFile(File file,String schooluuid,RoomDAO roomDAO,SubjectDAO subjectDAO,StudentExamDAO studentExamDAO,StudentDAO studentDAO) {
		String feedback = ResultUpload.UPLOAD_SUCCESS;
		int count = 1;
		
		String subject = "";
		String classroom = "";
		String exam = "";
		
		LineIterator lineIterator =  null;
		try {
			lineIterator = FileUtils.lineIterator(file, "UTF-8");
			String filename = file.getName();
			
			String [] parts = filename.split("\\.");
			subject = parts[0];
			classroom = parts[1]; 
			exam = parts[2]; 
		 	//System.out.println(exam);
		 	
		 // Check to see that only valid exam codes have been provided
		    
		  String code = StringUtils.lowerCase(StringUtils.trimToEmpty(exam) );
		    if(!examcodeList.contains(code)) {
		     return ("Invalid exam code " + code.toUpperCase());
		    	 }
		    
		 	
		  if(roomDAO.getroomByRoomName(schooluuid, classroom) ==null){
			  return ("class code " + classroom + " not found! ");
			}
		  
	      if(subjectDAO.getSubjects(subject) ==null){
	    	  return ("subject code " + subject + " not found! ");
		    }
	      
	      
	      
			String line;
			String[] rowTokens,admNotoken,ScoreToken;
			
			while (lineIterator.hasNext()) {
			     line = lineIterator.nextLine();
			     rowTokens = StringUtils.split(line, ',');
			     
			     if(rowTokens.length != 2 && line.length() > 0) {
			    	 return ("Invalid format on line " + count + ": " + line);
			     }
			     admNotoken = StringUtils.split(rowTokens[0], ';');
			     ScoreToken = StringUtils.split(rowTokens[1], ';');
			     
			     // Check that the scores contain only numbers
			     for(String score : ScoreToken) {
			    	 if(!StringUtils.isNumeric(score)) {
			    		 return ("Invalid score on line " + count + ": " + line);
			    	 }
			     }
			     
			     for(String admno : admNotoken) {
			    	 String studentuuid = "";
			    	 Student student = new Student();
			    	 if(admno !=null){
				    		student = studentDAO.getStudents(admno);
				    		if(student !=null){
				    			studentuuid = student.getUuid();
				    		}
				    		
				    	}
			    	 if(studentExamDAO.getStudentExam(schooluuid, studentuuid)==null) {
			    		 return ("Student with admNo " + admno + " on line " + count + " was not found in Exam Register");
			    	 }
			     }
			     
			     
			     
			     count++;
		    }
			
		} catch (IOException e) {
			logger.error("IOException when inspecting: " + file);
			logger.error(e);
			
		} finally {
			if(lineIterator != null) {
				lineIterator.close();
			}		   
		}
		 
		return feedback;
	}
	
	

	public void saveResults(File uploadedFile,String stffID, SchoolAccount school, ExamEgineDAO examEgineDAO, StudentExamDAO studentExamDAO,
			StudentDAO studentDAO, RoomDAO roomDAO, SubjectDAO subjectDAO){
		   
            LineIterator lineIterator =  null;
            String studentUuid = "";
            String subject = "";
        	String classroom = "";
        	String exam = "";
        	String Scorestr = "";
           
            String filename = uploadedFile.getName();
			
			String [] parts = filename.split("\\.");
			subject = parts[0];
			classroom = parts[1]; 
			exam = parts[2]; 
           
           
           Student student  = new Student();
           double score = 0;
           ClassRoom room = new ClassRoom();
           Subject subjct = new Subject();
           
          
		try {
			lineIterator = FileUtils.lineIterator(uploadedFile, "UTF-8");
			
			String line;
			String[] rowTokens,admNotoken,ScoreToken;
	
			while (lineIterator.hasNext()) {
			     line = lineIterator.nextLine();
			     rowTokens = StringUtils.split(line, ',');
			     
			     admNotoken = StringUtils.split(rowTokens[0], ';');
			     ScoreToken = StringUtils.split(rowTokens[1], ';');
			     
			     for(int j=0; j<admNotoken.length; j++ ) {
			    	String admno = StringUtils.trimToEmpty(admNotoken[j]);
			    	if(admno !=null){
			    		student = studentDAO.getStudents(admno);
			    	}
			    	
			     }
                 for(int j=0; j<ScoreToken.length; j++ ) {
                	 Scorestr = ScoreToken[j];
			     }
			     
                 score = Double.parseDouble(Scorestr);
                 
			     
			     if(classroom !=null){
			    	 room = roomDAO.getroomByRoomName(school.getUuid(), classroom); 
			     }
			     if(subject !=null){
			    	subjct = subjectDAO.getSubjects(subject);	
			     }
			     
			     if(student !=null){
			    	 studentUuid = student.getUuid();
			    	}
			     
			    
			     String classuuid = room.getUuid();
			     String subjectuuid = subjct.getUuid();
			     
			    
			     
			     if((studentExamDAO.getStudentExam(school.getUuid(), studentUuid)) !=null){
			    	 
						if(StringUtils.equalsIgnoreCase(exam, "c1")){
							
							CatOne catOne = new CatOne();
				            catOne.setSchoolAccountUuid(school.getUuid());
							catOne.setTeacherUuid(stffID);
							catOne.setClassRoomUuid(classuuid);  
							catOne.setSubjectUuid(subjectuuid);
							catOne.setStudentUuid(studentUuid);
							catOne.setCatOne(score); 
							catOne.setTerm("1");
							catOne.setYear("2015"); 
						    examEgineDAO.putScore(catOne);	
						}
			            
						if(StringUtils.equalsIgnoreCase(exam, "c2")){
							CatTwo catwo = new CatTwo();
				            catwo.setSchoolAccountUuid(school.getUuid());
				            catwo.setTeacherUuid(stffID);
				            catwo.setClassRoomUuid(classuuid);  
				            catwo.setSubjectUuid(subjectuuid);
				            catwo.setStudentUuid(studentUuid);
				            catwo.setCatTwo(score); 
				            catwo.setTerm("1");
				            catwo.setYear("2015"); 
						    examEgineDAO.putScore(catwo);
						}
						if(StringUtils.equalsIgnoreCase(exam, "et")){
							    EndTerm endterm = new EndTerm();
					            endterm.setSchoolAccountUuid(school.getUuid());
					            endterm.setTeacherUuid(stffID);
					            endterm.setClassRoomUuid(classuuid);  
					            endterm.setSubjectUuid(subjectuuid);
					            endterm.setStudentUuid(studentUuid);
					            endterm.setEndTerm(score); 
					            endterm.setTerm("1");
					            endterm.setYear("2015"); 
							    examEgineDAO.putScore(endterm);
						}
					    
					    
					   
			            
							
					}else{
	                    
						StudentExam studentExam = new StudentExam();
						studentExam.setSchoolAccountUuid(school.getUuid());
						studentExam.setClassRoomUuid(classuuid);
						studentExam.setStudentUuid(studentUuid); 
						if(studentExamDAO.getStudentExam(school.getUuid(), studentUuid) ==null){
							//studentExamDAO.putStudentExam(studentExam);
							
						}
					}
			     
			     
			     
			     
			    
		    }
			
		}catch (IOException e) {
			logger.error("IOException when storing: " + uploadedFile);
			logger.error(e);
			
		} finally {
			if(lineIterator != null) {
				lineIterator.close();
			}		   
		}
		
	}
}

