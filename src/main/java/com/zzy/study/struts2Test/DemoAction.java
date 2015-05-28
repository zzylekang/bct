package com.zzy.study.struts2Test;

import com.zzy.dev.project.base.web.action.Struts2BaseAction;

public class DemoAction extends Struts2BaseAction {
	public String demo() {
		System.out.println("[test.struts2Test.DemoAction]demo");
		return SUCCESS;
	}
}
