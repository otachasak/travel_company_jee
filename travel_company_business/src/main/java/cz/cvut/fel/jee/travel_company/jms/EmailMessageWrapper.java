package cz.cvut.fel.jee.travel_company.jms;

import java.io.Serializable;

public class EmailMessageWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String emailAddress;
	private String subject;
	private String message;
	
	public EmailMessageWrapper(String emailAddress, String subject,
			String message) {
		super();
		this.emailAddress = emailAddress;
		this.subject = subject;
		this.message = message;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
