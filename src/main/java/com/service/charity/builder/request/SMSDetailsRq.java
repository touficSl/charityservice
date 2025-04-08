package com.service.charity.builder.request;

public class SMSDetailsRq {

	private String recipient;
	private String msgBody;
	private String captchaToken;
	
	private boolean useAccela;
	
	public SMSDetailsRq() {
		super();
	}
	public String getRecipient() {
		return recipient;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getCaptchaToken() {
		return captchaToken;
	}
	public void setCaptchaToken(String captchaToken) {
		this.captchaToken = captchaToken;
	}
	public boolean isUseAccela() {
		return useAccela;
	}
	public void setUseAccela(boolean useAccela) {
		this.useAccela = useAccela;
	}
}
