package com.zzy.tools.dataGenerator;
/**
 * 代表数据库中的一个字段
 * @author Administrator
 *
 */
public class DataField {
	private String name;//字段名
	private String type;//类型
	private int length;//长度,指的是字符长度，而不是字节长度
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
}