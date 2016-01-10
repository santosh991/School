package com.yahoo.petermwenda83.persistence.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.util.performance.comparator.PerformanceScoreComparator;

public class test {
	
	static final String databaseName = "schooldb";
	static final String Host = "localhost";
	static final String databaseUsername = "school";
	static final String databasePassword = "AllaManO1";
	static final int databasePort = 5432;
	
	static String student = "";
	
	private static PerfomanceDAO perfomanceDAO = new PerfomanceDAO(databaseName, Host, databaseUsername, databasePassword, databasePort);
	
	private static Map<String,Double> scoreMap = new LinkedHashMap<String,Double>();
	private static Map<String,Double> testmap = new LinkedHashMap<String,Double>(); 
	
	private static List<Perfomance> testList = new ArrayList<Perfomance>();
	private static List<Perfomance> pList = new ArrayList<Perfomance>();
	private static List<Perfomance> pDistinctList = new ArrayList<Perfomance>();
	
	final static String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
	final static String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
    final static String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";
	
	public test() {
		perfomanceDAO = PerfomanceDAO.getInstance();
		
	}

	public static void main(String[] args) {
		
		
		System.out.println("starting .....");
		final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
		final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
		
		
		
		
		pList = perfomanceDAO.getPerfomanceList(SCHOOL_UUID, FORM_ONE_N);
		 //Collections.sort(pList, new PerformanceScoreComparator()); 
	    Collections.sort(pList, new PerformanceScoreComparator().reversed()); 
	   // System.out.println(pList); 
		 
	    
		pDistinctList = perfomanceDAO.getPerfomanceListDistinct(SCHOOL_UUID, FORM_ONE_N);
	
		
		
		
			
			
		
		List<Perfomance> list = new ArrayList<>();
		double grandscore = 0;
		double engscore = 0;
		double kisscore = 0;
		double physcore = 0;
		
		for(Perfomance s : pDistinctList){
			list = perfomanceDAO.getPerformance(SCHOOL_UUID, FORM_ONE_N, s.getStudentUuid());
			
			// System.out.println(list); 
			for(Perfomance pp : list){
				
				double cat1 = pp.getCatOne();
				double cat2 = pp.getCatTwo();
				double endterm = pp.getEndTerm();
				double total = (cat1+cat2)/2 +endterm;
				grandscore +=total;
				
				//System.out.println(pp); 
				 
				if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){
					engscore = total;
					Double x = Double.parseDouble("")/3;
					//System.out.println(total); 
				}
		    	if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){
		    		physcore = total;
		    		//System.out.println(total); 
			        }
		    	 if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){
		    		 kisscore = total;
		    		// System.out.println(total); 
				        }
				
		    	//System.out.println(engscore+physcore+kisscore); 
		    	
				
				
		    	
				
			}
			
			
			 testmap.put(s.getStudentUuid(), grandscore);
			 
			 
			// System.out.println(grandscore); 
			 grandscore = 0;
			
			 //System.out.println(testmap); 
			 
			 
			 
			 
			 
				
			
			
			}
			
	   	 System.out.println("\n"); 
	    
		
		 
		 
		 ArrayList<?> as = new ArrayList(testmap.entrySet());
			Collections.sort(as,new Comparator(){
				public int compare(Object o1,Object o2){
					Map.Entry e1 = (Map.Entry)o1;
					Map.Entry e2 = (Map.Entry)o2;
					Double f = (Double)e1.getValue();
					Double s = (Double)e2.getValue();
					return s.compareTo(f);
				}
			});
		
			for(Object o : as){
				 String items = String.valueOf(o);
				 String [] item = items.split("=");
				 String uuid = item[0];
				 String score = item[1];
				 System.out.println(uuid +" score ="+score);
				 
				 
			}
			
	}
			
	   

}


/**
 *  Collections.sort(pDistinctList,new Comparator<Perfomance>(){
			public int compare(Perfomance o1, Perfomance o2) {
				return Integer.compare(pList.indexOf(o1), pList.indexOf(o2)); 
			}	
		    });
		*/
