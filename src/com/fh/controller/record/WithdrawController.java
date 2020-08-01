package com.fh.controller.record;

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
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.service.record.BonusManager;
import com.fh.service.record.WithdrawManager;
import com.fh.service.setup.MemberManager;

/** 
 * 说明：提现管理
 * 创建人：
 * 创建时间：2019-11-02
 */
@Controller
@RequestMapping(value="/withdraw")
public class WithdrawController extends BaseController {
	
	String menuUrl = "withdraw/list.do"; //菜单地址(权限用)
	@Resource(name="withdrawService")
	private WithdrawManager withdrawService;
	
	@Resource(name = "memberService")
	private MemberManager memberService;
	
	@Resource(name = "bonusService")
	private BonusManager bonusService;
	
	/**
	 * 确认充值
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/confirmBy")
	public String confirmBy() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = withdrawService.findById(pd);
		pd.put("STATE", "1");
		withdrawService.edit(pd);
		return "success";
	}
	
	/**
	 * 拒绝充值
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/refuse")
	public String refuse() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = withdrawService.findById(pd);
		pd.put("STATE", "2");
		withdrawService.edit(pd);
		double money = ((BigDecimal)pd.get("MONEY")).doubleValue();
		
		PageData member = new PageData();
		member.put("MEMBER", pd.getString("PHONE"));
		member = memberService.findByMember(member);
		member.put("COMMISSION", money);
		//member.put("TXMONEY", money);
		memberService.editCommission(member);
		
		PageData bonusPd = new PageData();
		bonusPd.put("PHONE", member.getString("MEMBER"));
		bonusPd.put("PRODUCE", member.getString("MEMBER"));
		bonusPd.put("QUANTITY", money);
		bonusPd.put("TYPE", "退回");
		bonusPd.put("STATE", "1");
		bonusPd.put("TIME", Tools.date2Str(new Date()));
		bonusPd.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd);
		
		return "success";
	}
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Withdraw");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("WITHDRAW_ID", this.get32UUID());	//主键
		pd.put("STATE", "");	//状态
		pd.put("ARRIVAL", "0");	//实际到账
		withdrawService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Withdraw");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		withdrawService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Withdraw");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		withdrawService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Withdraw");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = withdrawService.list(page);	//列出Withdraw列表
		mv.setViewName("record/withdraw/withdraw_list");
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
		mv.setViewName("record/withdraw/withdraw_edit");
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
		pd = withdrawService.findById(pd);	//根据ID读取
		mv.setViewName("record/withdraw/withdraw_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Withdraw");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			withdrawService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Withdraw到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("提现账号");	//1
		titles.add("提现金额");	//2
		titles.add("提现方式");	//3
		titles.add("真实姓名");	//4
		titles.add("到账账号");	//5
		titles.add("状态");	//6
		titles.add("时间");	//7
		titles.add("实际到账");	//8
		titles.add("手续费");	//9
		dataMap.put("titles", titles);
		List<PageData> varOList = withdrawService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PHONE"));	    //1
			vpd.put("var2", varOList.get(i).get("MONEY").toString());	//2
			vpd.put("var3", varOList.get(i).getString("RSTYLE"));	    //3
			vpd.put("var4", varOList.get(i).getString("NAME"));	    //4
			vpd.put("var5", varOList.get(i).getString("ACCOUNT"));	    //5
			vpd.put("var6", varOList.get(i).getString("STATE"));	    //6
			vpd.put("var7", varOList.get(i).getString("TIME"));	    //7
			vpd.put("var8", varOList.get(i).get("ARRIVAL").toString());	//8
			vpd.put("var9", varOList.get(i).getString("FEE"));	    //9
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
