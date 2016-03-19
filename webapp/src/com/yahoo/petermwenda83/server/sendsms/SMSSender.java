package com.yahoo.petermwenda83.server.sendsms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * This Java program is a client that sends requests to the Tawi SMS Gateway
 * HTTP API.
 * <p>
 * This has been tested on JDK 1.7.
 * <p>
 * Copyright (c) Tawi Commercial Services Ltd., July 2014
 *
 */
public class SMSSender {
	/**
	 * @param args
	 */
	public static void main(String[ ] args) {
		String apiUrl = "http://sms.tawi.mobi/sendsms";
		try {
			System.out.println("Response for a sendsms request: " +
					getResponse(apiUrl + "?" +
							"username=" + URLEncoder.encode("michael", "UTF-8") +
							"&password=" + URLEncoder.encode("mypassword", "UTF-8") +
							"&source=1987" +
							"&destination=254728123456" +"&message=" + URLEncoder.encode("Hello Test SMS!", "UTF-8") +
							"&network=safaricom"));

		} catch(UnsupportedEncodingException e) {
			System.err.println("UnsupportedEncodingException while trying to send SMS.");
			e.printStackTrace( );


		}
	}

	/**
	 *
	 * @param urlStr
	 * @return String
	 */
	private static String getResponse(String urlStr) {
		URLConnection conn;
		URL url;
		BufferedReader reader;
		String response = "";
		try {
			url = new URL(urlStr);
			conn = url.openConnection( );
			conn.setDoInput(true);
			conn.setDoOutput(true);
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			response = reader.readLine();
			reader.close( );
		} catch(MalformedURLException e) {
			System.err.println("MalformedURLException exception");
			e.printStackTrace( );
		} catch(IOException e) {
			System.err.println("IOException exception");
			e.printStackTrace( );
		}


		return response;

	}

}
