/**
 * 
 */
package com.yahoo.petermwenda83.bean.student;

import com.yahoo.petermwenda83.bean.AllBean;

/**
 * @author peter
 *
 */
public class StudentPhoto extends AllBean{
	
	private String StudentUuid;
	private byte[] Image;

	/**
	 * 
	 */
	public StudentPhoto() {
		StudentUuid = "";
		Image = new byte[2048]; 
		
	}
	

	/**
	 * @return the studentUuid
	 */
	public String getStudentUuid() {
		return StudentUuid;
	}


	/**
	 * @param studentUuid the studentUuid to set
	 */
	public void setStudentUuid(String studentUuid) {
		StudentUuid = studentUuid;
	}


	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return Image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		Image = image;
	}


	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("StudentPhoto [getUuid()");
		builder.append(getUuid());
		builder.append(", StudentUuid =");
		builder.append(StudentUuid);
		builder.append(",Image =");
		builder.append(Image);
		builder.append("]");
		return builder.toString(); 
		}
}
