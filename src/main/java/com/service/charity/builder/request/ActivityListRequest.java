package com.service.charity.builder.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.service.charity.config.SanitizedStringDeserializer;

public class ActivityListRequest {
    @JsonDeserialize(using = SanitizedStringDeserializer.class)
    private String title;
    private int fromrow;
    private int torow;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getFromrow() {
		return fromrow;
	}
	public void setFromrow(int fromrow) {
		this.fromrow = fromrow;
	}
	public int getTorow() {
		return torow;
	}
	public void setTorow(int torow) {
		this.torow = torow;
	}
}
