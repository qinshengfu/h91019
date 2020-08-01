package com.fh.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class SendSMS {
	
	private final static String JGID="2132";//机构ID名
	private final static String YHMC="H91019";//帐户名称
	private final static String YHMM="161112";//密码
	private final static String IpAddr="124.172.234.157:8180";//接口服务器IP地址

	public static String sendSMS(String phone,String note) throws Exception {
		Logger logger = Logger.getLogger(SendSMS.class);
		URL url = null;
		String content = "【中首农业】验证码:"+ note +"，用于注册/修改密码，请勿泄漏，谨防被骗。";
		String send_content=URLEncoder.encode(content , "utf-8");//发送内容
		phone = phone.trim();
		url = new URL("http://"+IpAddr+"/service.asmx/SendMessageStr?Id="+JGID+"&Name="+YHMC+"&Psw="+YHMM+"&Message="+send_content+"&Phone="+phone+"&Timestamp=0");
		BufferedReader in = null;
		String inputLine = "";		
		try {
			logger.info("开始发送短信手机号码为 ："+phone);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("网络异常,发送短信失败！");
			inputLine=String.valueOf(-1000);
		}
		logger.info("结束发送短信返回值：  "+inputLine);
		return inputLine;
	}
	
	
	public static void main(String[] args) {
		try {
			sendSMS("13977128507", "663116");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
