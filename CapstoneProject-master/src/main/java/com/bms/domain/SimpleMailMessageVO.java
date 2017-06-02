package com.bms.domain;

import java.util.Arrays;

/**
 * @author 540010
 * POJO class to handle the the send mail action.
 * It holds to, from, sunject, text and footer of the send mail action
 */
public class SimpleMailMessageVO {

	

	@Override
	public String toString() {
		return "SimpleMailMessageVO [from=" + from + ", to=" + Arrays.toString(to) + ", subject=" + subject + ", text="
				+ text + ", footer=" + footer + ", getTo()=" + Arrays.toString(getTo()) + ", getFrom()=" + getFrom()
				+ ", getSubject()=" + getSubject() + ", getText()=" + getText() + ", getfooter()=" + getfooter() + "]";
	}

	private String from = "BMS@cognizant.com";
	private String[] to;
	private String subject = " Congratulations ";
	private String text;
	private String footer = System.getProperty("line.separator") + System.getProperty("line.separator")
			+ System.getProperty("line.separator") + System.getProperty("line.separator")
			+ "** This is an Auto Generated Mail. Please Do not reply to this mail**";

	public SimpleMailMessageVO() {

	}

	public SimpleMailMessageVO(String[] to, String text) {
		this.to = to;
		this.text = text;
	}

	public String[] getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	public String getfooter() {
		return footer;
	}

}
