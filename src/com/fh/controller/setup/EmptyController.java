package com.fh.controller.setup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.setup.CFProductManager;
import com.fh.service.setup.JFProductManager;
import com.fh.service.setup.MemberManager;
import com.fh.service.setup.impl.EmptyService;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;

/** 
 * 说明：清空数据
 * 创建人：
 * 创建时间：2019-10-06
 */
@Controller
@RequestMapping(value="/empty")
public class EmptyController extends BaseController {
	
	String menuUrl = "empty/list.do"; //菜单地址(权限用)
	
	@Resource(name = "memberService")
	private MemberManager memberService;
	
	@Resource(name = "emptyService")
	private EmptyService emptyService;
	
	@Resource(name="cfproductService")
	private CFProductManager cfproductService;
	
	@Resource(name="jfproductService")
	private JFProductManager jfproductService;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表empty");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("setup/empty/empty");
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}

	/**
	 * 清空数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/empty")
	public String manual(HttpServletRequest request) throws Exception{
		
		List<String> list = new ArrayList<String>();
		list.add("SET_MEMBER");//会员表
		list.add("SET_CFPRODUCT");//财富商城商品表
		list.add("SET_JFPRODUCT");//积分商城商品表
		list.add("SET_ADDRESS");//地址表
		list.add("REC_RECHARGE");//充值表
		list.add("REC_WITHDRAW");//提现表
		list.add("REC_ORDER");//订单表
		list.add("REC_BONUS");//奖金表
		list.add("REC_COMMENTS");//留言表
		
		//保留超级会员
		PageData pd = new PageData();
		pd.put("MEMBER", "admin");
		pd = memberService.findByMember(pd);
		
		if(pd.getString("AVATAR") == null){
			pd.put("AVATAR", "");//头像
		}
		pd.put("COMMISSION", 0);//佣金
		pd.put("INTEGRAL", 0);//积分
		pd.put("JF", 0);
		pd.put("WEALTH", 0);//财富
		pd.put("DIRECTPUSH", 0);//直推人数
		pd.put("CONSUMPTION", 0);//消费总额
		pd.put("UPGRADE", 0);//升级剩余
		pd.put("OUTMONEY", "0");//是否出局
		pd.put("LEFT", "0");//左区
		pd.put("RIGHT", "0");//右区
		pd.put("ONETIME", "no");//是否一次性
		pd.put("CHECKIN", "no");//是否签到
		pd.put("YEJI", 0);  //业绩
		pd.put("YEJINEWS", 0);  //新增业绩
		
		emptyService.empty(list);  //执行清空数据
		memberService.save(pd);	   //执行保留超级会员
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfProduct = cfproductService.listAll(new PageData());//财富商城商品
		List<PageData> jfProduct = jfproductService.listAll(new PageData());//积分商城商品
		context.setAttribute("cfProduct", cfProduct);
		context.setAttribute("jfProduct", jfProduct);
		
		//分类
		PageData pageData = new PageData();
		pd.put("PTYPE", "生鲜");
		List<PageData> clothesList = cfproductService.listAll(pd);
		List<PageData> clothesList1 = jfproductService.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList = cfproductService.listAll(pd);
		List<PageData> luxuryList1 = jfproductService.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList = cfproductService.listAll(pd);
		List<PageData> carList1 = jfproductService.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList = cfproductService.listAll(pd);
		List<PageData> bedclothesList1 = jfproductService.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList = cfproductService.listAll(pd);
		List<PageData> phoneList1 = jfproductService.listAll(pd);
		context.setAttribute("clothesList", clothesList);
		context.setAttribute("luxuryList", luxuryList);
		context.setAttribute("carList", carList);
		context.setAttribute("bedclothesList", bedclothesList);
		context.setAttribute("phoneList", phoneList);
		context.setAttribute("clothesList1", clothesList1);
		context.setAttribute("luxuryList1", luxuryList1);
		context.setAttribute("carList1", carList1);
		context.setAttribute("bedclothesList1", bedclothesList1);
		context.setAttribute("phoneList1", phoneList1);
		
		return "success";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
