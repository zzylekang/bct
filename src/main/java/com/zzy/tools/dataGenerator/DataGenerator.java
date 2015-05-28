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
	private String datasource = "file";//���ݵ���Դ
	
	private int rows = 5;//Ĭ��Ϊ��������
	private int cols = 3;//�ı������֣�����
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
	 * ����ָ�����������������ݣ�
	 * ���У��е�����Ϊ������ɣ��ı��ĳ���Ϊ6���ַ�
	 * @param prows
	 * @param pcols
	 */
	public DataGenerator(int prows, int pcols){
		this.rows = prows;
		this.cols = pcols;
		//this.dataFields = this.getRandomColumns();
		setRandomColumns();
	}
	
	public DataGenerator(int prows, List<DataField> pdataFields){//������pdataFields�и���Ϊ׼
		this.rows = prows;
		this.dataFields = pdataFields;
	}
	
	/**
	 * ����һ��ArrayList���͵����ݼ������е�ÿ��Ԫ����һ��HashMapʵ��
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
	 * ���һ������
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
	 * ���һ��������ַ���
	 * @return
	 */
	public String getRandomString(int len) {
		String base = "��������ƹ�����������������Դ����ﶬ����Ů��";
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
	 * ����һ�����������
	 */
	public int getRandomInt(int seed) {
		return (int)Math.floor(seed * Math.random());
	}
	/**
	 * ���һ������ĸ���������
	 * @return
	 */
	public double getRandomDouble(int len) {
		double base = Math.pow(10, len);//��øó����µ��������
		
		return base*Math.random();
	}
	
	/**
	 * ���һ���������
	 */
	public Date getRandomDate(int len) {
		
		// ����Random����
		Random rand = new Random();

		// �ø�����ģʽ��Ĭ�����Ի��������ڸ�ʽ���Ź���SimpleDateFormat��
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// ��õ�ǰ����
		Calendar cal = Calendar.getInstance();

		// ���������ֶ� YEAR��MONTH �� DAY_OF_MONTH ��ֵ��
		cal.set(2011, 0, 1);

		// ���ش� Calendar ��ʱ��ֵ���Ժ���Ϊ��λ��
		long start = cal.getTimeInMillis();

		// ���������ֶ� YEAR��MONTH �� DAY_OF_MONTH ��ֵ��
		cal.set(2012, 6, 1);

		// ���ش� Calendar ��ʱ��ֵ���Ժ���Ϊ��λ��
		long end = cal.getTimeInMillis();
		
		//�������������
		Date d = new Date(start + (long) (rand.nextDouble() * (end - start)));
		
		return d;
	}
	/**
	 * ��������
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
	 * ����ָ��{����}��ָ��{��Ŀ}��ָ��{����}����
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
