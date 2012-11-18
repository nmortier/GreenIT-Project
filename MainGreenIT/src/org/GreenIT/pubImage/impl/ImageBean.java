package org.GreenIT.pubImage.impl;

public class ImageBean {


	private long id;
	private String path;

	
	public ImageBean(long id, String path) {
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