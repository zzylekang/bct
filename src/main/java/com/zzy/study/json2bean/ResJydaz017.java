package com.zzy.study.json2bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResJydaz017 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sellFrom;
	private String offerId;
	private String productDesc;
	private String releaseTime;
	private String sourceType;
	private String voice;
	private List<ResJydazImage> image;
	private ResJydazJM jmInfo;
	private List<ResJydaz017S> order;
	private Integer voiceSecs;
	
	public String getSellFrom() {
		return sellFrom;
	}
	public void setSellFrom(String sellFrom) {
		this.sellFrom = sellFrom;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public List<ResJydazImage> getImage() {
		return image;
	}
	public void setImage(List<ResJydazImage> image) {
		this.image = image;
	}
	public ResJydazJM getJmInfo() {
		return jmInfo;
	}
	public void setJmInfo(ResJydazJM jmInfo) {
		this.jmInfo = jmInfo;
	}
	public List<ResJydaz017S> getOrder() {
		return order;
	}
	public void setOrder(List<ResJydaz017S> order) {
		this.order = order;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public Integer getVoiceSecs() {
		return voiceSecs;
	}
	public void setVoiceSecs(Integer voiceSecs) {
		this.voiceSecs = voiceSecs;
	}

}
