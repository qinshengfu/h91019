package com.fh.util;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.entity.system.MemUser;
import com.fh.util.mail.SimpleMailSender;

/*
 * 验证码
 * */
public class Verification {
	
	/**
	 * 设置邮箱验证码
	 * 
	 * @return
	 * @throws Exception
	 */

	public static String getEmail() throws Exception {
		String str = "0,1,2,3,4,5,6,7,8,9";
		String str2[] = str.split(",");// 将字符串以,分割			
		Random rand = new Random();// 创建Random类的对象rand
		int index = 0;
		String randStr = "";// 创建内容为空字符串对象randStr

		randStr = "";// 清空字符串对象randStr中的值
		for (int i = 0; i < 6; ++i) {
			index = rand.nextInt(str2.length - 1);// 在0到str2.length-1生成一个伪随机数赋值给index
			randStr += str2[index];// 将对应索引的数组与randStr的变量值相连接
		}
		
		
		return randStr;
	}
	
	/**发送邮件
	 * @param toEMAIL 目标邮箱
	 * @param TITLE   标题
	 * @param CONTENT 内容
	 * @param TYPE    发送格式   1：文本格式;2：HTML格式
	 * @throws Exception
	 */
	public static String sendE_mail(String toEMAIL,String TITLE,String CONTENT,String TYPE){
		String strEMAIL = Tools.readTxtFile(Const.EMAIL);		//读取邮件配置
		String info= "ok";
		if(null != strEMAIL && !"".equals(strEMAIL)){
			String strEM[] = strEMAIL.split(",fh,");
			try {
			
				SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3],toEMAIL, TITLE, CONTENT, TYPE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				info = "e_err";//发送失败
			}//调用发送邮件函数

		}else//配置文件出错
			info = "e_null";
		return info;
	}

}
