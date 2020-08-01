package com.fh.controller.system;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.MemUser;
import com.fh.service.system.AccuntManager;
import com.fh.service.system.MembersManager;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.HttpRequest;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;

/** 
 * 说明：会员管理
 * 创建人：FH Q313596790
 * 创建时间：2019-01-10
 */
@Controller
@RequestMapping(value="/members")
public class MembersController extends BaseController {
	
	String menuUrl = "members/list.do"; //菜单地址(权限用)
	@Resource(name="membersService")
	private MembersManager membersService;
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Members");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEMBERS_ID", this.get32UUID());	//主键
		pd.put("LOGIN_PASSWORD", "");	//登录密码
		pd.put("TRADE_PASSWORD", "");	//二级密码
		pd.put("BANK", "");	//开户银行
		pd.put("BANK_CARD", "");	//银行卡号
		pd.put("REAL_NAME", "");	//真实姓名
		pd.put("ID_CARD", "");	//身份证号
		pd.put("STATUS", "");	//账号状态
		pd.put("REGISTER_TIME", "");	//注册时间
		pd.put("SUPERIOR", "");	//上级
		pd.put("SUPERIOR_F", "");	//所有上级
		pd.put("LEVEL_P", "0");	//会员级别
		pd.put("GOODS_ADDRESS", "");	//收货地址
		pd.put("QINGQIU", "0");	//申请请求
		membersService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/** 确认/拒绝  实名认证  封禁  开放账号
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/apply_for")
	public void apply_for(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Members");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		membersService.edit(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Members");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String LOGIN_PASSWORD = pd.getString("LOGIN_PASSWORD");
		String TRADE_PASSWORD = pd.getString("TRADE_PASSWORD");
		if(LOGIN_PASSWORD!=null){
			LOGIN_PASSWORD = new SimpleHash("SHA-1", LOGIN_PASSWORD).toString(); // 密码加密
			pd.put("LOGIN_PASSWORD", LOGIN_PASSWORD);	//登录密码			
		}
		if(TRADE_PASSWORD!=null){
			TRADE_PASSWORD = new SimpleHash("SHA-1", TRADE_PASSWORD).toString(); // 密码加密
			pd.put("TRADE_PASSWORD", TRADE_PASSWORD);	//登录密码			
		}
		membersService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Members");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		String name = pd.getString("name");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		if(null != name && !"".equals(name)){
			pd.put("name", name.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = membersService.list(page);	//列出Members列表
		mv.setViewName("system/members/members_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list2")
	public ModelAndView list2(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Members");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		page.setPd(pd);
		List<PageData>	varList = membersService.list2(page);	//列出Members列表
		mv.setViewName("system/members/members_list2");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	


	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/members/members_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = membersService.findById(pd);	//根据ID读取
		mv.setViewName("system/members/members_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit_2")
	public ModelAndView goEdit_2()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = membersService.findById(pd);	//根据ID读取
		mv.setViewName("system/members/members_edit_2");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Members");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			membersService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	

	
/*public String recommended(String phone,String mui) throws Exception {
	String wdfs = "";	
	List<MemUser>  list = membersService.getSUPERIOR(phone);
    if("0".equals(mui))
    	mui = "";
	for(int i=0;i<list.size();i++){
		List<MemUser>  t_list = membersService.getSUPERIOR(list.get(i).getSERIAL());
		Account account = accuntService.findByPhone(list.get(i).getSERIAL());
		double number = 0; // 团队业绩
		if(account != null){
			number = account.getTEAM();
		}
		int iumc = accuntService.countNumber("," + list.get(i).getSERIAL() + ",");//团队人数
		String str = "{ name:'"+ list.get(i).getSERIAL() + "(团队业绩:" + number + "  团队人数"+iumc+" /人)',id:"+mui+(i+1);	
		if(t_list!=null&&t_list.size()>0){
			wdfs += str +", alias:'changyong',children:[";
			wdfs += recommended(list.get(i).getSERIAL(),mui+(i+1));		
			wdfs += "]";
		}else{
			wdfs +=	str;	
		}			
	
		if(i==list.size()-1)
			wdfs += "}";
		else
			wdfs += "},";
					
	}
					
		return wdfs;
	}
*/
	
	 /**导出到excel
		 * @param
		 * @throws Exception
		 */
		@RequestMapping(value="/excel")
		public ModelAndView exportExcel() throws Exception{
			logBefore(logger, Jurisdiction.getUsername()+"导出Members到excel");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
			ModelAndView mv = new ModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("手机号");	//1
			titles.add("登录密码");	//2
			titles.add("二级密码");	//3
			titles.add("账号状态");	//4
			titles.add("注册时间");	//5
			titles.add("上级");	//6
			titles.add("所有上级");	//7
			titles.add("会员级别");	//8
			titles.add("微信号");	//9
			titles.add("上级微信号");	//10
			titles.add("注册链接");	//11
			titles.add("注册二维码");	//12
			titles.add("登录链接");	//13
			titles.add("登陆二维码");	//14
			titles.add("直推人数");	//15
			titles.add("平台币");	//16
			dataMap.put("titles", titles);
			List<PageData> varOList = membersService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("PHONE"));	    //1
				vpd.put("var2", varOList.get(i).getString("LOGIN_PASSWORD"));	    //2
				vpd.put("var3", varOList.get(i).getString("TRADE_PASSWORD"));	    //3
				vpd.put("var4", varOList.get(i).getString("STATUS"));	    //4
				vpd.put("var5", varOList.get(i).getString("REGISTER_TIME"));	    //5
				vpd.put("var6", varOList.get(i).getString("SUPERIOR"));	    //6
				vpd.put("var7", varOList.get(i).getString("SUPERIOR_F"));	    //7
				vpd.put("var8", varOList.get(i).get("LEVEL_P").toString());	//8
				vpd.put("var9", varOList.get(i).getString("WEIXIN"));	    //9
				vpd.put("var10", varOList.get(i).getString("WEIXIN_S"));	    //10
				vpd.put("var11", varOList.get(i).getString("REGISTER_LINK"));	    //11
				vpd.put("var12", varOList.get(i).getString("REGISTER_CODE"));	    //12
				vpd.put("var13", varOList.get(i).getString("LOGIN_LINK"));	    //13
				vpd.put("var14", varOList.get(i).getString("LOGIN_CODE"));	    //14
				vpd.put("var15", varOList.get(i).get("ZT_NUMBER").toString());	//15
				vpd.put("var16", varOList.get(i).get("FUND").toString());	//16
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
			return mv;
		}
	
		/**封禁  激活账号
		 * @param
		 * @throws Exception
		 */
		@RequestMapping(value="/banned")
		public ModelAndView banned() throws Exception{	
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();			
			
			membersService.edit(pd);
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
	
	
	
	

	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
