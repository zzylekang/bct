package com.zzy.tools.dataGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataGenerator {
	private String datasource = "file";//数据的来源
	
	private int rows = 5;//默认为五行数据
	private int cols = 3;//文本，数字，日期
	private List<DataField> dataFields;
	
	public DataGenerator(){
		this.dataFields = new ArrayList<DataField>();
		DataField df = new DataField();
		df.setLength(5);
		df.setName("StringField");
		df.setType("String");
		dataFields.add(df);
		
		df = new DataField();
		df.setLength(6);
		df.setName("doubleField");
		df.setType("double");
		dataFields.add(df);
		df = new DataField();
		df.setLength(6);
		
		df.setName("DateField");
		df.setType("Date");
		dataFields.add(df);
	}
	/**
	 * 生成指定行数、列数的数据，
	 * 其中：列的类型为随机生成，文本的长度为6个字符
	 * @param prows
	 * @param pcols
	 */
	public DataGenerator(int prows, int pcols){
		this.rows = prows;
		this.cols = pcols;
		//this.dataFields = this.getRandomColumns();
		setRandomColumns();
	}
	
	public DataGenerator(int prows, List<DataField> pdataFields){//列数以pdataFields中个数为准
		this.rows = prows;
		this.dataFields = pdataFields;
	}
	
	/**
	 * 生成一个ArrayList类型的数据集，其中的每个元素是一个HashMap实例
	 * @return
	 */
	public List<HashMap<String, Object>> getListMapData() {
		List<HashMap<String, Object>> ret = new ArrayList<HashMap<String, Object>>();
		
		for (int i = 0; i < this.rows; i++) {
			ret.add((HashMap<String, Object>) this.getOneRowData());
		}
		
		return ret;
	}
	
	/**
	 * 获得一行数据
	 * @return
	 */
	public Map<String, Object> getOneRowData() {
		Map<String , Object> m = new HashMap<String, Object>();
		DataField df = null;
		for (int i = 0; i < this.dataFields.size(); i++) {
			df = this.dataFields.get(i);
			if ("String".equalsIgnoreCase(df.getType())) {
				m.put(df.getName(), getRandomString(df.getLength()));
			} else if ("double".equalsIgnoreCase(df.getType())) {
				m.put(df.getName(), getRandomDouble(df.getLength()));
			} else if ("Date".equalsIgnoreCase(df.getType())) {
				m.put(df.getName(), getRandomDate(df.getLength()));
			}
		}
		return m;
	}
	
	/**
	 * 获得一个随机的字符串
	 * @return
	 */
	public String getRandomString(int len) {
		String base = "中日美韩乒乓篮球足球张王李赵春夏秋冬男人女人";
		//Random ra = new Random(base.length());
		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < len; i++) {
			ret.append(base.charAt(
					getRandomInt(
							base.length())
							));
		}
		
		return ret.toString();
	}
	/**
	 * 产生一个随机的整数
	 */
	public int getRandomInt(int seed) {
		return (int)Math.floor(seed * Math.random());
	}
	/**
	 * 获得一个随机的浮点型数据
	 * @return
	 */
	public double getRandomDouble(int len) {
		double base = Math.pow(10, len);//获得该长度下的最大整数
		
		return base*Math.random();
	}
	
	/**
	 * 获得一个随机日期
	 */
	public Date getRandomDate(int len) {
		
		// 声明Random对象
		Random rand = new Random();

		// 用给定的模式和默认语言环境的日期格式符号构造SimpleDateFormat。
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 获得当前日期
		Calendar cal = Calendar.getInstance();

		// 设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。
		cal.set(2011, 0, 1);

		// 返回此 Calendar 的时间值，以毫秒为单位。
		long start = cal.getTimeInMillis();

		// 设置日历字段 YEAR、MONTH 和 DAY_OF_MONTH 的值。
		cal.set(2012, 6, 1);

		// 返回此 Calendar 的时间值，以毫秒为单位。
		long end = cal.getTimeInMillis();
		
		//产生随机的日期
		Date d = new Date(start + (long) (rand.nextDouble() * (end - start)));
		
		return d;
	}
	/**
	 * 获得随机列
	 * @return
	 */
	private void setRandomColumns() {
		List<DataField> ret = new ArrayList<DataField>();
		String [] types = {"String", "double", "Date"};
		DataField df = null;
		String type = null;
		for (int i = 0; i < this.cols; i++) {
			df = new DataField();
			type = types[getRandomInt(types.length)];
			df.setLength(6);
			df.setName("field" + i);
			df.setType(type);
			ret.add(df);
		}
		
		this.dataFields = ret;
	}
	/**
	 * 生成指定{类型}，指定{数目}，指定{长度}的列
	 * @return
	 */
	public List<DataField> setRandomColumns(String [] ptype, int pcol, int [] len) {
		List<DataField> ret = new ArrayList<DataField>();
		
		
		return ret;
	}

	public static void main(String[] args){
		DataGenerator dg = new DataGenerator(3, 3);
		List<HashMap<String, Object>> li = dg.getListMapData();
		System.out.print(li);
	}
}
