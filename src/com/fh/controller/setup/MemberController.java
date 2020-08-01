package com.fh.controller.setup;

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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.setup.MemberManager;

/** 
 * 说明：会员管理
 * 创建人：
 * 创建时间：2019-10-29
 */
@Controller
@RequestMapping(value="/member")
public class MemberController extends BaseController {
	
	String menuUrl = "member/list.do"; //菜单地址(权限用)
	@Resource(name="memberService")
	private MemberManager memberService;
	
	/**
	 * 接点图
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/contact")
	public ModelAndView contact() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.getString("MEMBER") == null || "".equals(pd.getString("MEM_NUMBER"))){
			pd.put("MEMBER", "admin");
		}
		PageData top = memberService.findByMember(pd);
		if(top == null){
			pd.put("MEMBER", "admin");
			top = memberService.findByMember(pd);
		}
		int level = ((BigDecimal)top.get("C_LEVEL")).intValue();
		pd.put("level", level+6);
		List<PageData> list = memberService.listCommunity1(pd);
		HashMap<String,List<PageData>> map = new HashMap<String, List<PageData>>();
		for(int i = 0; i < list.size(); i++){  // 接点人一样的装到一起
			PageData pageData = list.get(i);
			if("0".equals(pageData.getString("STATE"))){  // 为激活的跳过
				continue;
			}
			pageData.put("CONTACT", pageData.getString("MEMBER"));
			List<PageData> listContact = memberService.listContact(pageData);
			/*//空位补位
			PageData pageData1 = new PageData();
			pageData1.put("MEM_NUMBER", "点击注册");
			//pageData1.put("CONTACT_PERSON", pageData.getString("MEMBER"));
			if(listContact.size() == 0){
				pageData1.put("AREA", "0");
				listContact.add(pageData1);
				PageData pageData2 = new PageData();
				pageData2.put("MEM_NUMBER", "点击注册");
				pageData2.put("AREA", "1");
				listContact.add(pageData2);
				//System.out.println(listContact);
			}else if(listContact.size() == 1){
				
				PageData pageData2 = listContact.get(0);
				if("0".equals(pageData2.getString("AREA"))){
					pageData1.put("AREA", "1");
					listContact.add(pageData1);
				}else{
					pageData1.put("AREA", "0");
					listContact.add(0, pageData1);
				}
			}*/
			map.put(pageData.getString("MEMBER"), listContact);
		}
		mv.setViewName("setup/member/community1");
		mv.addObject("map", map);
		mv.addObject("top", top);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEMBER_ID", this.get32UUID());	//主键
		pd.put("R_PATH", "");	//代路径
		pd.put("R_LEVEL", "0");	//代数
		pd.put("AVATAR", "");	//头像
		pd.put("MEM_LEVEL", "");	//等级
		pd.put("LEVEL_NUM", "0");	//等级数
		pd.put("DIRECTPUSH", "0");	//直推人数
		pd.put("CONSUMPTION", "0");	//消费总额
		pd.put("UPGRADE", "0");	//升级余数
		pd.put("OUTMONEY", "");	//是否出局
		pd.put("CONTACT", "");	//接点人
		pd.put("C_PATH", "");	//层路径
		pd.put("C_LEVEL", "0");	//层数
		pd.put("PLACE", "0");	//所处位置
		pd.put("LEFT", "");	//左区
		pd.put("RIGHT", "");	//右区
		pd.put("ONETIME", "");	//是否一次性消费
		pd.put("TIME", Tools.date2Str(new Date()));  //时间
		memberService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		memberService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		memberService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Member");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = memberService.list(page);	//列出Member列表
		mv.setViewName("setup/member/member_list");
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
		mv.setViewName("setup/member/member_edit");
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
		pd = memberService.findById(pd);	//根据ID读取
		mv.setViewName("setup/member/member_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Member");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			memberService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Member到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("用户名");	//1
		titles.add("手机号");	//2
		titles.add("登录密码");	//3
		titles.add("推荐码");	//4
		titles.add("代路径");	//5
		titles.add("代数");	//6
		titles.add("头像");	//7
		titles.add("等级");	//8
		titles.add("等级数");	//9
		titles.add("佣金");	//10
		titles.add("积分");	//11
		titles.add("财富");	//12
		titles.add("直推人数");	//13
		titles.add("消费总额");	//14
		titles.add("升级余数");	//15
		titles.add("是否出局");	//16
		titles.add("接点人");	//17
		titles.add("层路径");	//18
		titles.add("层数");	//19
		titles.add("所处位置");	//20
		titles.add("左区");	//21
		titles.add("右区");	//22
		titles.add("是否一次性消费");	//23
		titles.add("注册时间");	//24
		dataMap.put("titles", titles);
		List<PageData> varOList = memberService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("PHONE"));	    //2
			vpd.put("var3", varOList.get(i).getString("PWD"));	    //3
			vpd.put("var4", varOList.get(i).getString("REFERRER"));	    //4
			vpd.put("var5", varOList.get(i).getString("R_PATH"));	    //5
			vpd.put("var6", varOList.get(i).get("R_LEVEL").toString());	//6
			vpd.put("var7", varOList.get(i).getString("AVATAR"));	    //7
			vpd.put("var8", varOList.get(i).getString("MEM_LEVEL"));	    //8
			vpd.put("var9", varOList.get(i).get("LEVEL_NUM").toString());	//9
			vpd.put("var10", varOList.get(i).get("COMMISSION").toString());	//10
			vpd.put("var11", varOList.get(i).get("INTEGRAL").toString());	//11
			vpd.put("var12", varOList.get(i).get("WEALTH").toString());	//12
			vpd.put("var13", varOList.get(i).get("DIRECTPUSH").toString());	//13
			vpd.put("var14", varOList.get(i).get("CONSUMPTION").toString());	//14
			vpd.put("var15", varOList.get(i).get("UPGRADE").toString());	//15
			vpd.put("var16", varOList.get(i).getString("OUTMONEY"));	    //16
			vpd.put("var17", varOList.get(i).getString("CONTACT"));	    //17
			vpd.put("var18", varOList.get(i).getString("C_PATH"));	    //18
			vpd.put("var19", varOList.get(i).get("C_LEVEL").toString());	//19
			vpd.put("var20", varOList.get(i).get("PLACE").toString());	//20
			vpd.put("var21", varOList.get(i).getString("LEFT"));	    //21
			vpd.put("var22", varOList.get(i).getString("RIGHT"));	    //22
			vpd.put("var23", varOList.get(i).getString("ONETIME"));	    //23
			vpd.put("var24", varOList.get(i).getString("TIME"));	    //24
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
