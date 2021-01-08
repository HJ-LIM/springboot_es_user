package com.simple.juni.security.domain;

import net.sf.json.JSONObject;

public class SimplePrint {
	public void printJSON(){
		System.out.println(JSONObject.fromObject(this).toString(4));
	}
}
