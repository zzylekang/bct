package com.zzy.study.json2bean;

import java.io.Serializable;
import java.util.List;

public class ResJydaz017S implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private String buyerId;
	private String buyerName;
	private String buyerImgPath;
	private String orderInfo;
	private Integer status;
	private String stautsName;
	private String orderTime;
	private String orderVoice;
	private Integer orderVoiceSecs;
	private List<ResJydazImage> image;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerImgPath() {
		return buyerImgPath;
	}
	public void setBuyerImgPath(String buyerImgPath) {
		this.buyerImgPath = buyerImgPath;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStautsName() {
		return stautsName;
	}
	public void setStautsName(String stautsName) {
		this.stautsName = stautsName;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public List<ResJydazImage> getImage() {
		return image;
	}
	public void setImage(List<ResJydazImage> image) {
		this.image = image;
	}
	public Integer getOrderVoiceSecs() {
		return orderVoiceSecs;
	}
	public void setOrderVoiceSecs(Integer orderVoiceSecs) {
		this.orderVoiceSecs = orderVoiceSecs;
	}
	public String getOrderVoice() {
		return orderVoice;
	}
	public void setOrderVoice(String orderVoice) {
		this.orderVoice = orderVoice;
	}

	
}
