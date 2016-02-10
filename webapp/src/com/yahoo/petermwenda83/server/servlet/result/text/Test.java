package com.yahoo.petermwenda83.server.servlet.result.text;

import com.yahoo.petermwenda83.server.util.magic.MiddleNumberFor3;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double no1 = 60,no2 =70 ,no3 = 70;
		double big = 0;
		MiddleNumberFor3 middle = new MiddleNumberFor3();
		
		
		
		
		System.out.println("\n");
		System.out.println("my numbers are "+no1+" , "+no2+" , "+no3);
		System.out.println("\n");

		big = Math.max( (Math.max(no1, no2)), Math.max(Math.max(no1, no2), no3));
		//middle = ((no1>no2) ? (no1>no3) ? no3 : no1 : (no2>no3) ? no3 : no2);
		
		
		//System.out.println("big = "+big); 
		System.out.println("middle = "+middle.ComputeMiddle(no1, no2, no3)); 
		//System.out.println("sum = "+(middle+big)); 
		
	}

	private static double MiddleCompute(double no1, double no2, double no3) {
		double middle = 0;
		
		if(no1>no2&&no1>no3){
			if(no2>no3){
				middle = no2;
			}else{middle = no3;}
		}
		if(no2>no1&&no2>no3){
				if(no1>no3){
					middle = no1;
				}else{middle = no3;}	
		}
		if(no3>no1&&no3>no2){
			if(no1>no2){
				middle = no1;
			}else{middle = no2;}	
	   }
		
		if(no1==no2 || no1==no3){
			middle = no1;
		}else if(no2==no1 || no2==no3){
			middle = no2;
		}else if(no3==no1 || no3==no2){
			middle = no3;
		}
		
		
		
		return middle;
	}

}
