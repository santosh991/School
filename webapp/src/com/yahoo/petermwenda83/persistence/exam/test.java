package com.yahoo.petermwenda83.persistence.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import com.yahoo.petermwenda83.bean.exam.Analyzer;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.util.performance.comparator.AnalyzerComparator;
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
		Analyzer a = new Analyzer();
		
		System.out.println("starting .....");
		final String SCHOOL_UUID = "E3CDC578-37BA-4CDB-B150-DAB0409270CD";
		final String FORM_ONE_N = "4DA86139-6A72-4089-8858-6A3A613FDFE6";
		
		
		
		
		pList = perfomanceDAO.getPerfomanceList(SCHOOL_UUID, FORM_ONE_N);
		 //Collections.sort(pList, new PerformanceScoreComparator()); 
	    Collections.sort(pList, new PerformanceScoreComparator().reversed()); 
		pDistinctList = perfomanceDAO.getPerfomanceListDistinct(SCHOOL_UUID, FORM_ONE_N);
	
		
		
		
			
			
		 
		
		
		
		for(Perfomance s : pList){
			student = s.getStudentUuid();
			
			double cat1 = s.getCatOne();
			double cat2 = s.getCatTwo();
			double endterm = s.getEndTerm();
			double totals = cat1+cat2+endterm;
			
		    	if(StringUtils.equals(s.getSubjectUuid(), PHY_UUID)){
		    		//scoreMap.put(student, totals);
		    		a.setStudenId(student); 
		    		a.setPhy(totals); 
			        }
		    	 if(StringUtils.equals(s.getSubjectUuid(), ENG_UUID)){
		    		scoreMap.put(student, totals);
		    		a.setStudenId(student); 
		    		a.setEng(totals); 
		    		
			        }
		    	 if(StringUtils.equals(s.getSubjectUuid(), KISWA_UUID)){
			    	//	scoreMap.put(student, totals);
			    		a.setStudenId(student); 
			    		a.setKis(totals);
				        }
		    	
		    	// System.out.println("analyzer for="+a.getStudenId() +",phy score =" +a.getPhy()  +",eng score =" +a.getEng() +",kis score =" +a.getKis() +",total score =" +a.getTotal()); 
		 		
		    	
		   }
		

		for(Perfomance s : pDistinctList){
			//System.out.println(s.getStudentUuid());
			
				testmap.put(s.getStudentUuid(), scoreMap.get(s.getStudentUuid()));
			
			
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
