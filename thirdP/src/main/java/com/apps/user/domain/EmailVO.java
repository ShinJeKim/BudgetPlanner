package com.apps.user.domain;

public class EmailVO {
	 
    private String subject;
    private String content;
    private String receiver;
     
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
 
	public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    public EmailVO() {
    	
    }
    
    public EmailVO(String subject, String content, String receiver) {
		super();
		this.subject = subject;
		this.content = content;
		this.receiver = receiver;
	}
    
    
	@Override
	public String toString() {
		return "EmailVO [subject=" + subject + ", content=" + content + ", receiver=" + receiver + "]";
	}
	
	
}
