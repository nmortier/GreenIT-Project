package org.GreenIT.pubVideo.impl;

public class VideoBean {


	private long id;
	private String path;

	
	public VideoBean(long id, String path) {
		this.id = id;
		this.path = path;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}