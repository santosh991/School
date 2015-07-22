/**
 * <h1>
 */
package com.yahoo.petermwenda83.view.curriculum;

import java.util.ArrayList;
import java.util.List;

import com.yahoo.petermwenda83.contoller.student.Subject;
import com.yahoo.petermwenda83.model.DButils;
import com.yahoo.petermwenda83.model.curriculum.SubjectDAO;


/**
 * @author peter
 *
 */
public class SubjectView extends DButils {
		
    private static SubjectDAO subDAO;	 
    static  List<Subject> subList = new ArrayList<>();
	
	
	 /**
	 * Contractor
	 */
	protected SubjectView(){
		 super();
		 subDAO = SubjectDAO.getInstance();
		
	 }
	
	/**
	 * @return
	 */
	public static String Subjectz(){
		String sub = "";
   	 try{
   		
   		 subList = subDAO.getAllSubjects();
   	    	for(Subject s : subList){
   	    	System.out.println(s.getSubjectname());	
   	    	}
   		 
   	 }catch(Exception e){
   		 e.printStackTrace();
   	 }
		return sub;
	}
	
	
	 
    /**
     * @param args
     */
    public static void main(String [] args){  
   
   
    	System.out.println(Subjectz());	
    	
    }
	 
}
