package org.GreenIT.stats.impl;

public class StatsBean {

	private long id, mediaCount, textCount, imageCount, videoCount;
	private String nameCampagne;
	
	public StatsBean(long id, String nameCampagne, long mediaCount, long textCount, long imageCount,
			long videoCount) {
		super();
		this.id = id;
		this.mediaCount = mediaCount;
		this.textCount = textCount;
		this.imageCount = imageCount;
		this.videoCount = videoCount;
		this.nameCampagne = nameCampagne;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMediaCount() {
		return mediaCount;
	}
	public void setMediaCount(long mediaCount) {
		this.mediaCount = mediaCount;
	}
	public long getTextCount() {
		return textCount;
	}
	public void setTextCount(long textCount) {
		this.textCount = textCount;
	}
	public long getImageCount() {
		return imageCount;
	}
	public void setImageCount(long imageCount) {
		this.imageCount = imageCount;
	}
	public long getVideoCount() {
		return videoCount;
	}
	public void setVideoCount(long videoCount) {
		this.videoCount = videoCount;
	}
	public String getNameCampagne() {
		return nameCampagne;
	}
	public void setNameCampagne(String nameCampagne) {
		this.nameCampagne = nameCampagne;
	}
}
