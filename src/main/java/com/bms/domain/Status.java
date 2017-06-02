package com.bms.domain;

/**
 * @author 540010
 * POJO class to handle status of send mail action
 */
public class Status {

	@Override
	public String toString() {
		return "Status [message=" + message + ", status=" + status + ", getStatus()=" + getStatus() + ", getMessage()="
				+ getMessage() + "]";
	}

	private String message;
	private String status;

	public String getStatus() {
		return status;
	}

	public Status() {
		this.message = "";
	}

	public Status(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}