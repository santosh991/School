

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
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.exam.PaperOne;
import com.yahoo.petermwenda83.bean.exam.PaperThree;
import com.yahoo.petermwenda83.bean.exam.PaperTwo;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.staff.TeacherSubClass;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamEgineDAO;
import com.yahoo.petermwenda83.persistence.staff.TeacherSubClassDAO;
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
	
	final String FORM1 = "C143978A-E021-4015-BC67-5A00D6C910D1";
	final String FORM2 = "3E22E428-3155-42F5-B73E-66553ED501C9";
	final String FORM3 = "A4BFC2BD-262F-4207-99C8-057D6ADF80C7";
	final String FORM4 = "14E56350-08DA-45CC-97D9-C225AF74A7AD";
	
	final String FORMONE = "FORM 1";
	final String FORMTWO = "FORM 2";
	final String FORMTHREE = "FORM 3";
	final String FORMFOUR = "FORM 4";
	String classesuuid = "";
	
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
	protected String inspectResultFile(File file,String schooluuid,String staffId,RoomDAO roomDAO,SubjectDAO subjectDAO,TeacherSubClassDAO teacherSubClassDAO,StudentDAO studentDAO) {
		String feedback = UploadExam.UPLOAD_SUCCESS;
		int count = 1;
		String[] rowTokens,admNotoken = null,ScoreToken = null;
		
		String subject = "";
		String classroom = "";
		String exam = "";
		
		
		
		
		LineIterator lineIterator =  null;
		try {
			lineIterator = FileUtils.lineIterator(file, "UTF-8");
			
			String filename = file.getName().replaceAll("_", " "); 
			
			String [] parts = filename.split("\\.");
			subject = parts[0];
			classroom = parts[1]; 
			exam = parts[2]; 
		 	//System.out.println("exam="+exam);
		 	
		 // Check to see that only valid exam codes have been provided
			
			 if(subjectDAO.getSubjects(subject) ==null){
		    	  return ("subject code \"" + subject + "\" not found! ");
			    }
				
			  if(roomDAO.getroomByRoomName(schooluuid, classroom) ==null){
				  return ("class code \"" + classroom + "\" not found! ");
				}
		    
		     String code = StringUtils.lowerCase(StringUtils.trimToEmpty(exam) );
		        if(!examcodeList.contains(code)) {
		          return ("Invalid exam code \"" + code.toUpperCase()+"\"");
		    	 }
		    
			
			
		    String line;
		    String examcode = exam;
			while (lineIterator.hasNext()) {
			     line = lineIterator.nextLine();
			     //System.out.println(line);
			     rowTokens = StringUtils.split(line, ',');
			    // System.out.println("rowTokens"+rowTokens);
			     
			     if(rowTokens.length != 2 && line.length() > 0) {
			    	 return ("Invalid format on line \"" + count + "\":   \"" + line+"\"");
			     }
			     
			     if(rowTokens!=null){
			    	 admNotoken = StringUtils.split(rowTokens[0], ';');
				     ScoreToken = StringUtils.split(rowTokens[1], ';'); 
				     
			     }
			    
			     
			     // Check that the scores contain only numbers
			     if(ScoreToken!=null){
			     for(String score : ScoreToken) {
			    	 
			    	 if(!StringUtils.isNumeric(score)) {
			    		 return ("Invalid score on line \"" + count + "\":   \"" + line+"\"");
			    	 }
			    	 
			    	 if(StringUtils.equalsIgnoreCase(examcode, "c1") ||
				    		 StringUtils.equalsIgnoreCase(examcode, "c2")){
			    		 double thisscore = Double.parseDouble(score);
				    	 if(thisscore <=0 || thisscore >30) {
				    		 return ("Invalid cat 1 score on line \"" + count + "\":   \"" + line+"\"");
				    	 }
			    	 }
			    	 
			    	 if(StringUtils.equalsIgnoreCase(examcode, "et")){
			    		 double thisscore = Double.parseDouble(score);
			    		 if(thisscore <=0 || thisscore >70) {
				    		 return ("Invalid end term score on line \"" + count + "\":   \"" + line+"\"");
				    	 }
			    	 }
			    	 
			    	 if(StringUtils.equalsIgnoreCase(examcode, "p1") ||
					    	  StringUtils.equalsIgnoreCase(examcode, "p2") ||
					    	  StringUtils.equalsIgnoreCase(examcode, "p3")){
			    		 double thisscore = Double.parseDouble(score);
			    		 if(thisscore <=0 || thisscore >100) {
				    		 return ("Invalid paper score on line \"" + count + "\":   \"" + line+"\"");
				    	 }
			    	 }		
			    	 
			    	 
			    	 
			       }
			     }//end if(ScoreToken!=null){
			     
			     
			     if(admNotoken!=null){
			     for(String admno : admNotoken) {
			    	 String studentuuid = "";
			    	 Student student = new Student();
			    	 if(admno !=null){
				    		student = studentDAO.getStudentObjByadmNo(schooluuid,admno);
				    		if(student !=null){
				    			studentuuid = student.getUuid();
				    		}
				    		
				    	}
			    	 
			    	 if(studentDAO.getStudentByuuid(schooluuid,studentuuid)==null) {
			    		 return ("Student with admNo \"" + admno + "\" on line \"" + count + "\" was not found in the System");
			    	   }
			          }
			       }
			     
			        ClassRoom clss = roomDAO.getroomByRoomName(schooluuid, classroom);
			        TeacherSubClass ts = new TeacherSubClass();  
			        ts.setClassRoomUuid(clss.getUuid());
			        ts.setTeacherUuid(staffId);
			        Subject sub = subjectDAO.getSubjects(subject);
			        ts.setSubjectUuid(sub.getUuid()); 
					
					if(teacherSubClassDAO.getSubjectClass(ts) ==null){
						return ("Error! Confirm  that Subject \"" + subject +"\" or Class Room \"" + classroom + "\" really belongs to you.");
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
	
	

	public void saveResults(File uploadedFile,String stffID, SchoolAccount school, ExamEgineDAO examEgineDAO,
			StudentDAO studentDAO, RoomDAO roomDAO, SubjectDAO subjectDAO,ExamConfigDAO examConfigDAO){
		   
            LineIterator lineIterator =  null;
            String studentUuid = "";
            String subject = "";
        	String classroom = "";
        	String exam = "";
        	String Scorestr = "";
        	ExamConfig  examConfig = examConfigDAO.getExamConfig(school.getUuid());
           
            String filename = uploadedFile.getName().replaceAll("_", " "); ;
			
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
			    		student = studentDAO.getStudentObjByadmNo(school.getUuid(),admno);
			    	}
			    	
			     }
			     
			     score = 0;				  
                 for(int j=0; j<ScoreToken.length; j++ ) {
                	 Scorestr = ScoreToken[j];
                	 score = Double.parseDouble(Scorestr);
                	 
			     }
			     
                 
                 
			     
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
			     String roomname = room.getRoomName();
			     String subjectuuid = subjct.getUuid();
			     
			     if(StringUtils.contains(roomname, FORMONE) ){ 
                    classesuuid = FORM1;
			     }else if(StringUtils.contains(roomname, FORMTWO)){
			    	 classesuuid = FORM2;
			     }else if(StringUtils.contains(roomname, FORMTHREE)){
			    	 classesuuid = FORM3;
			     }else if(StringUtils.contains(roomname, FORMFOUR)){
			    	 classesuuid = FORM4;
			     }
			       
			   
			     //System.out.println("score="+score);
			    	 
						if(StringUtils.equalsIgnoreCase(exam, "c1")){
							
							CatOne catOne = new CatOne();
				            catOne.setSchoolAccountUuid(school.getUuid());
							catOne.setTeacherUuid(stffID);
							catOne.setClassRoomUuid(classuuid); 
							catOne.setClassesUuid(classesuuid);
							catOne.setSubjectUuid(subjectuuid);
							catOne.setStudentUuid(studentUuid);
							catOne.setCatOne(score); 
							catOne.setTerm(examConfig.getTerm());
							catOne.setYear(examConfig.getYear()); 
						    examEgineDAO.putScore(catOne);	
						   
						}
			            
						if(StringUtils.equalsIgnoreCase(exam, "c2")){
							CatTwo catwo = new CatTwo();
				            catwo.setSchoolAccountUuid(school.getUuid());
				            catwo.setTeacherUuid(stffID);
				            catwo.setClassRoomUuid(classuuid);  
				            catwo.setClassesUuid(classesuuid); 
				            catwo.setSubjectUuid(subjectuuid);
				            catwo.setStudentUuid(studentUuid);
				            catwo.setCatTwo(score); 
				            catwo.setTerm(examConfig.getTerm());
				            catwo.setYear(examConfig.getYear()); 
						    examEgineDAO.putScore(catwo);
						   
						}
						if(StringUtils.equalsIgnoreCase(exam, "et")){
							    EndTerm endterm = new EndTerm();
					            endterm.setSchoolAccountUuid(school.getUuid());
					            endterm.setTeacherUuid(stffID);
					            endterm.setClassRoomUuid(classuuid);  
					            endterm.setClassesUuid(classesuuid); 
					            endterm.setSubjectUuid(subjectuuid);
					            endterm.setStudentUuid(studentUuid);
					            endterm.setEndTerm(score); 
					            endterm.setTerm(examConfig.getTerm());
					            endterm.setYear(examConfig.getYear()); 
							    examEgineDAO.putScore(endterm);
							   
						}
						if(StringUtils.equalsIgnoreCase(exam, "p1")){
							PaperOne p1 = new PaperOne();
				            p1.setSchoolAccountUuid(school.getUuid());
				            p1.setTeacherUuid(stffID);
				            p1.setClassRoomUuid(classuuid);  
				            p1.setClassesUuid(classesuuid); 
				            p1.setSubjectUuid(subjectuuid);
				            p1.setStudentUuid(studentUuid);
				            p1.setPaperOne(score); 
				            p1.setTerm(examConfig.getTerm());
				            p1.setYear(examConfig.getYear()); 
						    examEgineDAO.putScore(p1);
						    
					}
						if(StringUtils.equalsIgnoreCase(exam, "p2")){
							PaperTwo p2 = new PaperTwo();
				            p2.setSchoolAccountUuid(school.getUuid());
				            p2.setTeacherUuid(stffID);
				            p2.setClassRoomUuid(classuuid);
				            p2.setClassesUuid(classesuuid); 
				            p2.setSubjectUuid(subjectuuid);
				            p2.setStudentUuid(studentUuid);
				            p2.setPaperTwo(score); 
				            p2.setTerm(examConfig.getTerm());
				            p2.setYear(examConfig.getYear()); 
						    examEgineDAO.putScore(p2);
						   
					}
						if(StringUtils.equalsIgnoreCase(exam, "p3")){
							PaperThree p3 = new PaperThree();
				            p3.setSchoolAccountUuid(school.getUuid());
				            p3.setTeacherUuid(stffID);
				            p3.setClassRoomUuid(classuuid);  
				            p3.setClassesUuid(classesuuid); 
				            p3.setSubjectUuid(subjectuuid);
				            p3.setStudentUuid(studentUuid);
				            p3.setPaperThree(score); 
				            p3.setTerm(examConfig.getTerm());
				            p3.setYear(examConfig.getYear()); 
						    examEgineDAO.putScore(p3);
						   
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

