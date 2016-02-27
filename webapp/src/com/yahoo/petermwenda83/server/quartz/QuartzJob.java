package com.yahoo.petermwenda83.server.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job{

	public QuartzJob() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		StartBackup();
	}

	private void StartBackup() {
		String user = System.getProperty("user.name");
		String backup = "/home/"+user+"/svn/School/trunk/webapp/bin/backup.sh";
		
		ProcessBuilder pb = new ProcessBuilder(backup,"arg","arg");
		try {
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line = null;
			
			while((line=br.readLine())!=null){
				System.out.println(line);
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}

}
