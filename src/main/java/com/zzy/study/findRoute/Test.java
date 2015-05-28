package com.zzy.study.findRoute;

import java.util.ArrayList;
import java.util.List;


public class Test {
	public static List<RouteInfo> arrList = null;//路径基本信息
	//public static List<RouteInfo> oRoute = null;//一个连续的路径链
	public static List<List<RouteInfo>> routeList = new ArrayList<List<RouteInfo>>();//所有可行路径集合
	public static String end;//终点
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		arrList = initRouteInfo();
		List<RouteInfo> oRoute = new ArrayList<RouteInfo>();
		
		//findRoute("a", oRoute);
		end = "b";//终点
		findRouteEnd("a", oRoute);
	}
	
	/**
	 * 根据起始点，寻找路径，不指定终点
	 * @param pRoute
	 */
	public static boolean findRoute(String start, List<RouteInfo> pRoute) {
		RouteInfo ri = null;
		boolean isNew = false;//一个起始节点下出发的路径数 
		boolean ret = false;//是否找到该路径,false：没找到
		List<RouteInfo> lRoute = copyRoute(pRoute);//pRoute的本地拷贝
		
		for (int i = 0; i < arrList.size(); i++) {
			ri = arrList.get(i);
			
			if (!isNew && start.equalsIgnoreCase(ri.start)) {//如果找到该路径，并且为第一条
				pRoute.add(ri);
				
				isNew = true;
				ret = true;
				
				//根据找到的路径，以其终点作为起点继续向下找
				boolean aa = findRoute(ri.end, pRoute);
				
				if (!aa) {//如果没找到，到达路径的终点
					routeList.add(pRoute);
					
					//输出该路径
					printRoute(pRoute);
				}
			} else if (isNew && start.equalsIgnoreCase(ri.start)) {//同一节点下，第一条以外的路径
				List<RouteInfo> oRoute = new ArrayList<RouteInfo>();
				oRoute.addAll(lRoute);//拷贝之前的所有路径
				oRoute.add(ri);
				
				ret = true;
				
				//根据找到的路径，以其终点作为起点继续向下找
				boolean aa = findRoute(ri.end, oRoute);
				
				if (!aa) {//如果没找到，到达路径的终点
					routeList.add(oRoute);
					
					//输出该路径
					printRoute(oRoute);
				}
			}
		}
		
		return ret;
	}
	/**
	 * 根据起始点，寻找路径，指定终点
	 * @param pRoute
	 */
	public static boolean findRouteEnd(String start, List<RouteInfo> pRoute) {
		RouteInfo ri = null;
		boolean isNew = false;//一个起始节点下出发的路径数 
		boolean ret = false;//是否找到该路径,false：没找到
		List<RouteInfo> lRoute = copyRoute(pRoute);//pRoute的本地拷贝
		
		for (int i = 0; i < arrList.size(); i++) {
			ri = arrList.get(i);
			
			if (!isNew && start.equalsIgnoreCase(ri.start)) {//如果找到该路径，并且为第一条
				pRoute.add(ri);
				
				isNew = true;
				ret = true;
				
				//如果到达终点
				if (end.equalsIgnoreCase(ri.end)) {
					routeList.add(pRoute);
					
					//输出该路径
					printRoute(pRoute);
				} else {
					findRoute(ri.end, pRoute);
				}
				
			} else if (isNew && start.equalsIgnoreCase(ri.start)) {//同一节点下，第一条以外的路径
				List<RouteInfo> oRoute = new ArrayList<RouteInfo>();
				oRoute.addAll(lRoute);//拷贝之前的所有路径
				oRoute.add(ri);
				
				ret = true;
				
				//如果到达终点
				if (end.equalsIgnoreCase(ri.end)) {
					routeList.add(oRoute);
					
					//输出该路径
					printRoute(oRoute);
				} else {
					findRoute(ri.end, oRoute);
				}
			}
		}
		
		return ret;
	}
	/**
	 * 将给定路径信息复制一份，返回
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
	 * 初始化路径信息（读文件略）
	 * @return
	 */
	public static List<RouteInfo> initRouteInfo() {
		List<RouteInfo> arrList = new ArrayList<RouteInfo>();
		RouteInfo ri = null;
		//①
		ri = new RouteInfo();
		ri.start = "a";
		ri.end = "b";
		ri.routeName = "L1";
		ri.distance = 30;
		arrList.add(ri);
		
		//②
		ri = new RouteInfo();
		ri.start = "b";
		ri.end = "c";
		ri.routeName = "L7";
		ri.distance = 80;
		arrList.add(ri);
		
		//③
		ri = new RouteInfo();
		ri.start = "c";
		ri.end = "d";
		ri.routeName = "L8";
		ri.distance = 60;
		arrList.add(ri);
		
		//④
		ri = new RouteInfo();
		ri.start = "a";
		ri.end = "e";
		ri.routeName = "L2";
		ri.distance = 50;
		arrList.add(ri);
		
		//⑤
		ri = new RouteInfo();
		ri.start = "e";
		ri.end = "f";
		ri.routeName = "L3";
		ri.distance = 60;
		arrList.add(ri);
		
		//⑥
		ri = new RouteInfo();
		ri.start = "e";
		ri.end = "d";
		ri.routeName = "L4";
		ri.distance = 55;
		arrList.add(ri);
		
		//⑦
		ri = new RouteInfo();
		ri.start = "f";
		ri.end = "d";
		ri.routeName = "L5";
		ri.distance = 30;
		arrList.add(ri);
		
		//⑧
		ri = new RouteInfo();
		ri.start = "b";
		ri.end = "d";
		ri.routeName = "L6";
		ri.distance = 100;
		arrList.add(ri);
		
		
		return arrList;
	}
}
