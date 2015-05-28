package com.zzy.study.findRoute;

import java.util.ArrayList;
import java.util.List;


public class Test {
	public static List<RouteInfo> arrList = null;//·��������Ϣ
	//public static List<RouteInfo> oRoute = null;//һ��������·����
	public static List<List<RouteInfo>> routeList = new ArrayList<List<RouteInfo>>();//���п���·������
	public static String end;//�յ�
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		arrList = initRouteInfo();
		List<RouteInfo> oRoute = new ArrayList<RouteInfo>();
		
		//findRoute("a", oRoute);
		end = "b";//�յ�
		findRouteEnd("a", oRoute);
	}
	
	/**
	 * ������ʼ�㣬Ѱ��·������ָ���յ�
	 * @param pRoute
	 */
	public static boolean findRoute(String start, List<RouteInfo> pRoute) {
		RouteInfo ri = null;
		boolean isNew = false;//һ����ʼ�ڵ��³�����·���� 
		boolean ret = false;//�Ƿ��ҵ���·��,false��û�ҵ�
		List<RouteInfo> lRoute = copyRoute(pRoute);//pRoute�ı��ؿ���
		
		for (int i = 0; i < arrList.size(); i++) {
			ri = arrList.get(i);
			
			if (!isNew && start.equalsIgnoreCase(ri.start)) {//����ҵ���·��������Ϊ��һ��
				pRoute.add(ri);
				
				isNew = true;
				ret = true;
				
				//�����ҵ���·���������յ���Ϊ������������
				boolean aa = findRoute(ri.end, pRoute);
				
				if (!aa) {//���û�ҵ�������·�����յ�
					routeList.add(pRoute);
					
					//�����·��
					printRoute(pRoute);
				}
			} else if (isNew && start.equalsIgnoreCase(ri.start)) {//ͬһ�ڵ��£���һ�������·��
				List<RouteInfo> oRoute = new ArrayList<RouteInfo>();
				oRoute.addAll(lRoute);//����֮ǰ������·��
				oRoute.add(ri);
				
				ret = true;
				
				//�����ҵ���·���������յ���Ϊ������������
				boolean aa = findRoute(ri.end, oRoute);
				
				if (!aa) {//���û�ҵ�������·�����յ�
					routeList.add(oRoute);
					
					//�����·��
					printRoute(oRoute);
				}
			}
		}
		
		return ret;
	}
	/**
	 * ������ʼ�㣬Ѱ��·����ָ���յ�
	 * @param pRoute
	 */
	public static boolean findRouteEnd(String start, List<RouteInfo> pRoute) {
		RouteInfo ri = null;
		boolean isNew = false;//һ����ʼ�ڵ��³�����·���� 
		boolean ret = false;//�Ƿ��ҵ���·��,false��û�ҵ�
		List<RouteInfo> lRoute = copyRoute(pRoute);//pRoute�ı��ؿ���
		
		for (int i = 0; i < arrList.size(); i++) {
			ri = arrList.get(i);
			
			if (!isNew && start.equalsIgnoreCase(ri.start)) {//����ҵ���·��������Ϊ��һ��
				pRoute.add(ri);
				
				isNew = true;
				ret = true;
				
				//��������յ�
				if (end.equalsIgnoreCase(ri.end)) {
					routeList.add(pRoute);
					
					//�����·��
					printRoute(pRoute);
				} else {
					findRoute(ri.end, pRoute);
				}
				
			} else if (isNew && start.equalsIgnoreCase(ri.start)) {//ͬһ�ڵ��£���һ�������·��
				List<RouteInfo> oRoute = new ArrayList<RouteInfo>();
				oRoute.addAll(lRoute);//����֮ǰ������·��
				oRoute.add(ri);
				
				ret = true;
				
				//��������յ�
				if (end.equalsIgnoreCase(ri.end)) {
					routeList.add(oRoute);
					
					//�����·��
					printRoute(oRoute);
				} else {
					findRoute(ri.end, oRoute);
				}
			}
		}
		
		return ret;
	}
	/**
	 * ������·����Ϣ����һ�ݣ�����
	 */
	public static List<RouteInfo> copyRoute(List<RouteInfo> pRoute) {
		List<RouteInfo> ret = new ArrayList<RouteInfo>();
		RouteInfo ri = null;
		RouteInfo rinew = null;
		for (int i = 0; i < pRoute.size(); i++) {
			ri = pRoute.get(i);
			rinew = new RouteInfo();
			rinew.start = ri.start;
			rinew.end = ri.end;
			rinew.routeName = ri.routeName;
			rinew.distance = ri.distance;
			
			ret.add(rinew);
		}
		
		return ret;
				
	}
	public static void printRoute(List<RouteInfo> pRoute) {
		RouteInfo ri = null;
		System.out.println("-----------route pirnt start---------------");
		for (int i = 0; i < pRoute.size(); i++) {
			ri = pRoute.get(i);
			System.out.println(ri.toString());
		}
		System.out.println("-----------route pirnt end---------------");
		System.out.println("");
	}
	/**
	 * ��ʼ��·����Ϣ�����ļ��ԣ�
	 * @return
	 */
	public static List<RouteInfo> initRouteInfo() {
		List<RouteInfo> arrList = new ArrayList<RouteInfo>();
		RouteInfo ri = null;
		//��
		ri = new RouteInfo();
		ri.start = "a";
		ri.end = "b";
		ri.routeName = "L1";
		ri.distance = 30;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "b";
		ri.end = "c";
		ri.routeName = "L7";
		ri.distance = 80;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "c";
		ri.end = "d";
		ri.routeName = "L8";
		ri.distance = 60;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "a";
		ri.end = "e";
		ri.routeName = "L2";
		ri.distance = 50;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "e";
		ri.end = "f";
		ri.routeName = "L3";
		ri.distance = 60;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "e";
		ri.end = "d";
		ri.routeName = "L4";
		ri.distance = 55;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "f";
		ri.end = "d";
		ri.routeName = "L5";
		ri.distance = 30;
		arrList.add(ri);
		
		//��
		ri = new RouteInfo();
		ri.start = "b";
		ri.end = "d";
		ri.routeName = "L6";
		ri.distance = 100;
		arrList.add(ri);
		
		
		return arrList;
	}
}
