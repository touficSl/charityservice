package com.service.charity.builder.request;
public class ProjectListRequest {
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
