package com.fh.controller.front;

import com.fh.controller.base.BaseController;
import com.fh.service.record.BonusManager;
import com.fh.service.setup.AddressManager;
import com.fh.service.setup.MemberManager;
import com.fh.util.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/* 说明：前台页面跳转： 创建时间：2018-09-14
 */
@Controller
@RequestMapping(value = "/release")
public class ReleaseController extends BaseController {
	
	@Resource(name = "memberService")
	private MemberManager memberService;
	
	@Resource(name = "bonusService")
	private BonusManager bonusService;

	@Resource(name = "addressService")
	private AddressManager addressService;

	/**
	 * 去登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public ModelAndView login() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/login");
		return mv;
	}
	
	/**
	 * 登录
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/login_do")
	public Object login_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errorInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq313596790fh", "").replaceAll("QQ978336446fh", "").split(",fh,");
		if(null != KEYDATA && KEYDATA.length == 3){
			Session session = Jurisdiction.getSession();
			String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);		//获取session中的验证码
			String code = KEYDATA[2];
			String MEMBER = KEYDATA[0];
			String PWD = KEYDATA[1];
			if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){		//判断登录验证码
				pd.put("MEMBER", MEMBER);
				PWD = new SimpleHash("SHA-1" , PWD , MEMBER , 2).toString();
				pd.put("PWD", PWD);
				pd = memberService.findByMemberAndPwd(pd);
				if(pd != null){ //判断账号密码
					session.setAttribute("member", pd);
					session.removeAttribute(Const.SESSION_SECURITY_CODE);	//清除登录验证码的session
					errorInfo = "success";
				}else{
					errorInfo = "usererror"; //用户名或密码错误
				}
			}else{
				errorInfo = "codeerror"; //验证码错误
			}
		}else{
			errorInfo = "error"; //缺少参数
		}
		return errorInfo;
	}
	
	/**
	 * 去注册页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/register")
	public ModelAndView register() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/register");
		return mv;
	}
	
	/**
	 * 注册
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/register_do")
	public Object register_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errorInfo = "";
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        double REGINTEGRAL = Double.parseDouble(parameter.getString("REGINTEGRAL"));
		
		String phone = pd.getString("PHONE");
		String MEMBER = pd.getString("MEMBER");
		Session session = Jurisdiction.getSession();
		String verifyCode = (String) session.getAttribute("verifyCode" + phone);
		if(verifyCode != null){//判断手机验证码
			PageData findByMember = memberService.findByMember(pd);
			if(findByMember == null){//判断账号是否注册
				String inCode = pd.getString("inCode");
				if(verifyCode.equals(inCode)){//判断手机验证码
					PageData findByReferrer = memberService.findByReferrer(pd);
					if(findByReferrer != null){ //判断推荐码
						String PWD = pd.getString("PWD");
						PWD = new SimpleHash("SHA-1" , PWD , MEMBER , 2).toString();
						pd.put("R_PATH", findByReferrer.getString("R_PATH") + findByReferrer.getString("MEMBER") + ",");//代路径
						pd.put("R_LEVEL", ((BigDecimal)findByReferrer.get("R_LEVEL")).intValue() + 1);//代数
						pd.put("PWD", PWD);//密码
						pd.put("NAME", "hh");
						pd.put("AVATAR", "");//头像
						pd.put("MEM_LEVEL", "普通");//等级
						pd.put("LEVEL_NUM", 0);//等级数
						pd.put("COMMISSION", 0);//佣金
						pd.put("INTEGRAL", REGINTEGRAL);//积分
						pd.put("JF", REGINTEGRAL);
						pd.put("WEALTH", 0);//财富
						pd.put("DIRECTPUSH", 0);//直推人数
						pd.put("CONSUMPTION", 0);//消费总额
						pd.put("UPGRADE", 0);//升级余数
						pd.put("OUTMONEY", "0");//是否出局
						pd.put("ONETIME", "no");//是否一次性消费
						PageData contactPd = new PageData();
						if("0".equals(findByReferrer.getString("LEFT"))){
							contactPd = findByReferrer;
							pd.put("PLACE", ((BigDecimal)contactPd.get("PLACE")).intValue() * 2);//所处位置
							pd.put("AREA", "0"); //所属区域
							contactPd.put("LEFT", "1");
						}else if("0".equals(findByReferrer.getString("RIGHT"))){
							contactPd = findByReferrer;
							pd.put("PLACE", ((BigDecimal)contactPd.get("PLACE")).intValue() * 2 + 1);//所处位置
							pd.put("AREA", "1"); //所属区域
							contactPd.put("RIGHT", "1");
						}else{
							String path = findByReferrer.getString("C_PATH") + findByReferrer.getString("MEMBER");
							PageData contactPath = memberService.contactPath(path);
							String left = contactPath.getString("LEFT"); //左区下级
							int place = ((BigDecimal)contactPath.get("PLACE")).intValue();//所处位置
							contactPd = contactPath;
							if("0".equals(left)){
								pd.put("AREA", "0"); //所属区域
								pd.put("PLACE", place * 2);//所处位置
								contactPd.put("LEFT", "1");
							}else{
								pd.put("AREA", "1"); //所属区域
								pd.put("PLACE", place * 2 + 1);//所处位置
								contactPd.put("RIGHT", "1");
							}
						}
						pd.put("CONTACT", contactPd.getString("MEMBER"));
						pd.put("C_PATH", contactPd.getString("C_PATH") + contactPd.getString("MEMBER") + ",");//接点路径
						pd.put("C_LEVEL", ((BigDecimal)contactPd.get("C_LEVEL")).intValue() + 1);//层数
						pd.put("LEFT", 0);//左区
						pd.put("RIGHT", 0);//右区
						pd.put("TIME", Tools.date2Str(new Date()));//时间
						pd.put("MEMBER_ID", this.get32UUID());//ID
						
						//findByReferrer.put("DIRECTPUSH", ((BigDecimal)findByReferrer.get("DIRECTPUSH")).intValue() + 1);
						
						memberService.save(pd);
						memberService.edit(contactPd);
						memberService.edit(findByReferrer);
						session.removeAttribute("verifyCode" + phone);
						//积分
						PageData bonusPd = new PageData();
						bonusPd.put("PHONE", MEMBER);
						bonusPd.put("PRODUCE", MEMBER);
						bonusPd.put("QUANTITY", REGINTEGRAL);
						bonusPd.put("TYPE", "注册");
						bonusPd.put("STATE", "1");
						bonusPd.put("TIME", Tools.date2Str(new Date()));
						bonusPd.put("BONUS_ID", this.get32UUID());
						bonusService.save(bonusPd);
						
						errorInfo = "success";//成功
					}else{
						errorInfo = "referrer";//推荐码不存在
					}
				}else{
					errorInfo = "code";//手机验证码错误
				}
			}else{
				errorInfo = "memberError";//手机号已注册
			}
		}else{
			errorInfo = "code";//手机验证码错误
		}
		return errorInfo;
	}
	
	/**
	 * 去忘记密码页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forget")
	public ModelAndView forget() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/forget");
		return mv;
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/forget_do")
	public Object forget_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errorInfo = "";
		String phone = pd.getString("PHONE");
		String MEMBER = pd.getString("MEMBER");
		Session session = Jurisdiction.getSession();
		String verifyCode = (String) session.getAttribute("verifyCode" + phone);
		if(verifyCode != null){//判断手机验证码
			// 此处 要修改为 用户名和手机号查询数据
			PageData findByMember = memberService.findByMember(pd);
			if(findByMember != null){//判断账号是否注册
				String inCode = pd.getString("inCode");
				if(verifyCode.equals(inCode)){//判断手机验证码
					String PWD = pd.getString("PWD");
					PWD = new SimpleHash("SHA-1" , PWD , MEMBER , 2).toString();
					findByMember.put("PWD", PWD);//密码
					memberService.editPwd(findByMember);
					session.removeAttribute("verifyCode" + phone);
					errorInfo = "success";//成功
				}else{
					errorInfo = "code";//手机验证码错误
				}
			}else{
				errorInfo = "memberError";//手机号未注册
			}
		}else{
			errorInfo = "code";//手机验证码错误
		}
		return errorInfo;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getPhone")
	public String getPhone() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData findByMember = memberService.findByMember(pd);
		if(findByMember != null){
			return findByMember.getString("PHONE");
		}
		return "memberError";//账号不存在
	}
	
	/**
	 * 发送手机验证码
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/sendCode" , produces = "text/html;charset=UTF-8")
	public String sendCode() throws Exception{
		PageData pd = this.getPageData();
		String phone = pd.getString("PHONE");
/*		String lang = pd.getString("tag");

		if (!"register".equals(lang)) {
			// 此除新增 验证手机号是否注册了
			pd = addressService.findByPhone(pd);
			if (pd == null) {
				return "手机号未注册！";
			}
		}*/

		Session session = Jurisdiction.getSession();
		String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
//		String verifyCode = "666"; //测试备用
		logger.info("手机验证码：" + verifyCode);
		session.setAttribute("verifyCode" + phone, verifyCode);
		//session.setAttribute("codePhone", phone);
		String inputLine = SendSMS.sendSMS(phone,verifyCode);
		return inputLine;
	}
	
}



