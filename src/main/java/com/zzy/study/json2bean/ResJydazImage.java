package com.zzy.study.json2bean;

import java.io.Serializable;

/**
 * 图片
 * @author Administrator
 *
 */
public class ResJydazImage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String imagePath;
	private String smallPath;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getSmallPath() {
		return smallPath;
	}
	public void setSmallPath(String smallPath) {
		this.smallPath = smallPath;
	}
	
	
	
	
	
}
