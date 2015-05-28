package com.zzy.study.json2bean;

import java.io.Serializable;

/**
 * 竞买
 * @author Administrator
 *
 */
public class ResJydazJM implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String startingPrice;
	private String unit;
	private String bidPrice;
	private String nickName;
	
	public String getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
