package com.fh.util.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fh.entity.setup.Token;
import com.fh.entity.system.Accunt;
//import com.fh.service.record.impl.Bonus_recordService;
//import com.fh.service.setup.impl.Mining_gradeService;
//import com.fh.service.setup.impl.TokenService;
import com.fh.service.system.impl.AccuntService;
import com.fh.util.HttpRequest;
import com.fh.util.PageData;
import com.mysql.fabric.xmlrpc.base.Array;

import net.sf.json.JSONArray;

public class test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"classpath:spring/ApplicationContext-dataSource.xml", "classpath:spring/ApplicationContext-main.xml",
				"classpath:spring/ApplicationContext-redis.xml" });
//		Bonus_recordService bonus_recordService = context.getBean("bonus_recordService", Bonus_recordService.class);
		try {
			
//			bonus_recordService.bonus_Release();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JSONObject getKsom(JSONObject json,String name){
		JSONObject json_2 = null;
		try {
			 json_2 = new JSONObject(json.getString(name));
		} catch (JSONException e) {
				
		}
		return json_2;
	}
}
