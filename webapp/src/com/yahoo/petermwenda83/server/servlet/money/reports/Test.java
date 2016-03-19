package com.yahoo.petermwenda83.server.servlet.money.reports;

import org.apache.commons.lang3.StringUtils;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String [] classes = {"FORM 1 N","FORM 1 S","FORM 2 N","FORM 3 N","FORM 3 S","FORM 4 N","FORM 3 S","FORM 1 N","FORM 1 S","FORM 2 N","FORM 3 N","FORM 4 N","FORM 3 S"};
		
		int count = 0;
		int countf1 = 0;
		int countf2 = 0;
		int countf3 = 0;
		for(int i=0;i<classes.length;i++){
			
			
			if(StringUtils.containsIgnoreCase(classes[i], "form 1")){
				countf1++;
				computeF1(countf1);
			}
			
			if(StringUtils.containsIgnoreCase(classes[i], "form 2")){
				countf2++;
				computeF2(countf2);
			}
			
			if(StringUtils.containsIgnoreCase(classes[i], "form 3")){
				countf3++;
				computeF3(countf3);
				
			}
			count++;
		}
		
		System.out.println("(total classes " +count +")\nmean f1=" +computeF1(countf1)+  " form ones' "+countf1 +",\nmean f2=" + computeF2(countf2)+ " form twos' " + countf2 + ", \nmean f3=" +computeF3(countf3)+" form threes' " + countf3 +",");
	}

	private static double computeF3(double count) {
		double mean = 30;
		
		return mean/count;
	}

	private static double computeF2(double count) {
		double mean = 40;
		
		return mean/count;
	}

	private static double computeF1(double count) {
		double mean = 50;
		
		return mean/count;
	}

}
