package com.yahoo.petermwenda83.server.servlet.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

public class ExcelUtil {

	private static Logger logger = Logger.getLogger(ExcelUtil.class);
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
	protected ExcelUtil() {
		examcodeArray = new String[] {"c1", "c2", "et", "p1","p2","p3"};
		examcodeList = Arrays.asList(examcodeArray);
	} 
	
	
	/**
	 * Checks that an uploaded results file is in proper order.
	 * 
	 * @param file
	 * @return the feedback of having inspected the file, whether it was proper
	 * @throws IOException 
	 */
	protected String inspectResultFile(File file,String schooluuid,String staffId,RoomDAO roomDAO,SubjectDAO subjectDAO,TeacherSubClassDAO teacherSubClassDAO,StudentDAO studentDAO) throws IOException {
		String feedback = UploadExam.UPLOAD_SUCCESS;
		 // Creating Input Stream 
        FileInputStream myInput =  new FileInputStream(file);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
   		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
   		int totalRow = mySheet.getLastRowNum();
   		
   		DecimalFormat rf = new DecimalFormat("0.0"); 
		rf.setRoundingMode(RoundingMode.HALF_UP);
			
	    DecimalFormat rf2 = new DecimalFormat("0"); 
	    rf2.setRoundingMode(RoundingMode.UP);
	    
	    DecimalFormat rf3 = new DecimalFormat("0"); 
	    rf3.setRoundingMode(RoundingMode.DOWN);
        
        try{
        	
        	int count = 1;
   		
        
   		
   		String subject = "";
   		String classroom = "";
   		String exam = "";
   		
   		String filename = file.getName().replaceAll("_", " "); 
   		
   		String [] parts = filename.split("\\.");
   		subject = parts[0];
   		classroom = parts[1]; 
   		exam = parts[2]; 

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
   		
   		
   	      String examcode = exam;
        	
        	
    		for(int i=0; i<=totalRow; i++){
    			XSSFRow row = mySheet.getRow(i);
    			int totalColumn = row.getLastCellNum();
    			
    			 if(totalColumn >2){
    					return ("Invalid Number of comlumns on line \"" + count);
    				 }
    			 
    			
    			
    			for(int j=0; j<totalColumn; j++){
    			XSSFCell cell = row.getCell((short)j);
    			if(i==0){
    				
    			}else if(i >0){
    				
    				
    				String scorestr =  row.getCell(1)+"";
    				String admnostr = row.getCell(0)+"";
    				
    				double toformat = Double.parseDouble(scorestr);
    				double dblescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(toformat)))));
    			    String formated = rf3.format(dblescore);
    				
    				boolean isNumeric = formated.chars().allMatch(x -> Character.isDigit(x));
    			    if(!isNumeric) {  
			    		return ("Invalid score " + scorestr + " on line \"" + count);
			    	 }
    				
    				
    				
    				String score = (String) scorestr;
    				
    				
    				
    				switch (score){
                    case "null":
                    	return ("Blank score " + score + " on line \"" + count);
                    	
                    case "":
                    	return ("Blank score " + score + " on line \"" + count);
                    default:
                       // System.out.println("Wrong score");
                   break;   
                        
                     }
    				
    				
    			
    			    if(StringUtils.isBlank(score) ||StringUtils.isEmpty(score) || score == null){
    			    	return ("Blank score " + score + " on line \"" + count);
    			    }
    			    
    			   // System.out.println("score ="+score);
    			    
    				
    			   
    				
    				
    				boolean isNumericz = formated.chars().allMatch(x -> Character.isDigit(x));
    			    if(!isNumericz) {  
			    		return ("Invalid score " + formated + " on line \"" + count);
			    	 }
    				
    				 if(StringUtils.equalsIgnoreCase(examcode, "c1") ||
				    		 StringUtils.equalsIgnoreCase(examcode, "c2")){
			    		 double thisscore = Double.parseDouble(scorestr);
				    	 if(dblescore <=0 || dblescore >30) {
				    		 return ("Invalid cat 1 score on line \"" + count);
				    	 }
			    	 }
    				
    				 if(StringUtils.equalsIgnoreCase(examcode, "et")){
			    		 double thisscore = Double.parseDouble(scorestr);
			    		 if(dblescore <=0 || dblescore >70) {
				    		 return ("Invalid end term score on line \"" + count);
				    	 }
			    	 }
			    	 
			    	 if(StringUtils.equalsIgnoreCase(examcode, "p1") ||
					    	  StringUtils.equalsIgnoreCase(examcode, "p2") ||
					    	  StringUtils.equalsIgnoreCase(examcode, "p3")){
			    		 double thisscore = Double.parseDouble(scorestr);
			    		 if(dblescore <=0 || dblescore >100) {
				    		 return ("Invalid paper score on line \"" + count);
				    	 }
			    	 }		
    				
			    	 

			    	 String studentuuid = "";
			    	 Student student = new Student();
			    	 if(admnostr !=null){
				    		student = studentDAO.getStudentObjByadmNo(schooluuid,admnostr);
				    		if(student !=null){
				    			studentuuid = student.getUuid();
				    		}
				    		
				    	}
			    	 
			    	 if(studentDAO.getStudentByuuid(schooluuid,studentuuid)==null) {
			    		 return ("Student with admNo \"" + admnostr + "\" on line \"" + count + "\" was not found in the System");
			    	   }
			          
			    	 
			    	    ClassRoom clss = roomDAO.getroomByRoomName(schooluuid, classroom);
				        TeacherSubClass ts = new TeacherSubClass();  
				        ts.setClassRoomUuid(clss.getUuid());
				        ts.setTeacherUuid(staffId);
				        Subject sub = subjectDAO.getSubjects(subject);
				        ts.setSubjectUuid(sub.getUuid()); 
						
						if(teacherSubClassDAO.getSubjectClass(ts) ==null){
							//return ("Error! Confirm  that Subject \"" + subject +"\" or Class Room \"" + classroom + "\" really belongs to you.");
						}
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
    				
    			}
    			
    			}
    		}
    		
    		
    		count++;
         
            
    }
    catch (Exception e){
    	
    }finally {
    	myInput.close();

    }

   
	    
		return feedback;
	}
	
	

	public void saveResults(File uploadedFile,String stffID, SchoolAccount school, ExamEgineDAO examEgineDAO,
			StudentDAO studentDAO, RoomDAO roomDAO, SubjectDAO subjectDAO,ExamConfigDAO examConfigDAO) throws IOException{
		   
		String subject = "";
   		String classroom = "";
   		String exam = "";
   	    String studentuuid = "";
   	    String formated = "";
   	    ExamConfig  examConfig = examConfigDAO.getExamConfig(school.getUuid());
        String filename = uploadedFile.getName().replaceAll("_", " "); 
   		
   		String [] parts = filename.split("\\.");
   		subject = parts[0];
   		classroom = parts[1]; 
   		exam = parts[2]; 
   		
   		FileInputStream myInput =  new FileInputStream(uploadedFile);
        XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
   		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
   		int totalRow = mySheet.getLastRowNum();
		String scorestr = "";
		String admnostr  = "";
	
		
		try {
			int totalColumn = 0;
		for(int i=0; i<=totalRow; i++){
			XSSFRow row = mySheet.getRow(i);
			if(row !=null){
			  totalColumn = row.getLastCellNum();
			}
		
			for(int j=0; j<totalColumn; j++){
    			XSSFCell cell = row.getCell((short)j);
    			if(i==0){
    				//System.out.println("Headers value: "+cell);
    			}else if(i >0){
    				
				
				admnostr = row.getCell(0)+"";
				scorestr =  row.getCell(1)+"";
				
				DecimalFormat rf = new DecimalFormat("0.0"); 
				rf.setRoundingMode(RoundingMode.HALF_UP);
					
			    DecimalFormat rf2 = new DecimalFormat("0"); 
			    rf2.setRoundingMode(RoundingMode.UP);
			    
			    DecimalFormat rf3 = new DecimalFormat("0"); 
			    rf3.setRoundingMode(RoundingMode.DOWN);
				
				String score = (String) scorestr;
				double toformat = Double.parseDouble(score);
				double dblescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(toformat)))));
			    formated = rf3.format(dblescore);
			   
				
    			 }
    			
    			
			 
			}  
			    if(!StringUtils.equals(formated, "") && !StringUtils.equals(admnostr, "")){ 
			    	//System.out.println("formated : "+formated);
			    	//System.out.println("admnostr : "+admnostr);
			    	   Student student  = new Student();
			           double score = 0;
			           score = Double.parseDouble(formated);
			          // System.out.println("score="+score);
			           
			           ClassRoom room = new ClassRoom();
			           Subject subjct = new Subject();
			    	

					   String admno = StringUtils.trimToEmpty(admnostr);
					  // System.out.println("admno="+admno);
					   
					    if(admno !=null){
						    student = studentDAO.getStudentObjByadmNo(school.getUuid(),admno);
						   }
						    	
			               
			                
			                
			                
						     if(classroom !=null){
						    	 room = roomDAO.getroomByRoomName(school.getUuid(), classroom); 
						     }
						     if(subject !=null){
						    	subjct = subjectDAO.getSubjects(subject);	
						     }
						     
						     if(student !=null){
						    	 studentuuid = student.getUuid();
						    	}
						     
						    // System.out.println("studentuuid="+studentuuid);
						    
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
						       
			    	
			    	
			    	
			    	 
						if(StringUtils.equalsIgnoreCase(exam, "c1")){
							
							CatOne catOne = new CatOne();
				            catOne.setSchoolAccountUuid(school.getUuid());
							catOne.setTeacherUuid(stffID);
							catOne.setClassRoomUuid(classuuid); 
							catOne.setClassesUuid(classesuuid);
							catOne.setSubjectUuid(subjectuuid);
							catOne.setStudentUuid(studentuuid);
							catOne.setCatOne(score); 
							catOne.setTerm(examConfig.getTerm());
							catOne.setYear(examConfig.getYear()); 
						    examEgineDAO.putScore(catOne);	
						  //s  System.out.println("catOne="+catOne);
						}
			            
						if(StringUtils.equalsIgnoreCase(exam, "c2")){
							CatTwo catwo = new CatTwo();
				            catwo.setSchoolAccountUuid(school.getUuid());
				            catwo.setTeacherUuid(stffID);
				            catwo.setClassRoomUuid(classuuid);  
				            catwo.setClassesUuid(classesuuid); 
				            catwo.setSubjectUuid(subjectuuid);
				            catwo.setStudentUuid(studentuuid);
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
					            endterm.setStudentUuid(studentuuid);
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
				            p1.setStudentUuid(studentuuid);
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
				            p2.setStudentUuid(studentuuid);
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
				            p3.setStudentUuid(studentuuid);
				            p3.setPaperThree(score); 
				            p3.setTerm(examConfig.getTerm());
				            p3.setYear(examConfig.getYear()); 
						   examEgineDAO.putScore(p3);
						   
					}
						
						
					 	
				    	
			    }
			    
			
		      }
		
			
			
		} finally {
			if(myInput != null) {
				myInput.close();
			}		   
		}
		
	}

}
