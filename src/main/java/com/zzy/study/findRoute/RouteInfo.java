package com.zzy.study.findRoute;


public class RouteInfo {
	public String start;//起点
	public String end; //终点
	public String routeName;//路径名称
	public int distance;//路径距离
	
	public String toString () {
		return "(start:" + this.start + ")(end:" + this.end + ")(routeName:" + this.routeName + ")(distance:" + this.distance + ")";
	}
}
