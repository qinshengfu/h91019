package com.fh.controller.front;

import com.fh.controller.base.BaseController;
import com.fh.service.record.*;
import com.fh.service.setup.AddressManager;
import com.fh.service.setup.CFProductManager;
import com.fh.service.setup.JFProductManager;
import com.fh.service.setup.MemberManager;
import com.fh.util.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/* 说明：前台页面跳转： 创建时间：2018-09-14
 */
@Controller
@RequestMapping(value = "/front")
public class FrontController extends BaseController {
	
	@Resource(name = "memberService")
	private MemberManager memberService;
	
	@Resource(name = "rechargeService")
	private RechargeManager rechargeService;
	
	@Resource(name = "addressService")
	private AddressManager addressService;
	
	@Resource(name = "cfproductService")
	private CFProductManager cfproductService;
	
	@Resource(name = "jfproductService")
	private JFProductManager jfproductService;
	
	@Resource(name = "withdrawService")
	private WithdrawManager withdrawService;

	@Resource(name = "orderService")
	private OrderManager orderService;
	
	@Resource(name = "bonusService")
	private BonusManager bonusService;
	
	@Resource(name = "commentsService")
	private CommentsManager commentsService;
	
	/**
	 * 去财富商城页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/index");
		return mv;
	}
	
	/**
	 * 去衣服页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/clothes")
	public ModelAndView clothes() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/clothes");
		return mv;
	}
	
	/**
	 * 去奢侈品页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/luxury")
	public ModelAndView luxury() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/luxury");
		return mv;
	}
	
	/**
	 * 去汽车页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/car")
	public ModelAndView car() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/car");
		return mv;
	}
	
	/**
	 * 去床上用品页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bedclothes")
	public ModelAndView bedclothes() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/bedclothes");
		return mv;
	}
	
	/**
	 * 去手机页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/phone")
	public ModelAndView phone() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/phone");
		return mv;
	}
	
	/**
	 * 财富商城商品详情页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shopdetail")
	public ModelAndView shopdetail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = cfproductService.findById(pd);
		mv.addObject("pd", pd);
		mv.setViewName("front/shopdetail");
		return mv;
	}
	
	/**
	 * 下单页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/order1")
	public ModelAndView order1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData addPd = new PageData();
		if("1".equals(pd.getString("mark"))){
			addPd = addressService.findByMemberId(pd);
		}
		if("0".equals(pd.getString("mark"))){
			addPd = addressService.findById(pd);
		}
		PageData cfProduct = cfproductService.findById(pd);
		double price = ((BigDecimal)cfProduct.get("PRICE")).doubleValue();
		pd.put("TOTAL", price * Integer.parseInt(pd.getString("QUANTITY")));
		mv.addObject("pd", pd);
		mv.addObject("addPd",addPd);
		mv.addObject("cfProduct", cfProduct);
		mv.setViewName("front/order1");
		return mv;
	}
	
	/**
	 * 下单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/order1_do")
	public synchronized String order1_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
//        double INITIAL = Double.parseDouble(parameter.getString("INITIAL")); //初始
        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE")); //佣金：积分   参数
//        double DIRECT = Double.parseDouble(parameter.getString("DIRECT"));//直推奖参数
//        double LEADER = Double.parseDouble(parameter.getString("LEADER"));//领导奖参数
//        int SQUAD = Integer.parseInt(parameter.getString("SQUAD"));//小队人数
//        int REVENUE = Integer.parseInt(parameter.getString("REVENUE")); //收益到达倍数（判断是否得分享积分）
//        int COUNT = Integer.parseInt(parameter.getString("COUNT")); //一次性数量
//        int MULTIPLE = Integer.parseInt(parameter.getString("MULTIPLE")); //出局倍数
//        int ONEMULTIPLE = Integer.parseInt(parameter.getString("ONEMULTIPLE")); //一次性出局倍数
		
		PageData addPd = addressService.findById(pd);
		PageData cfProduct = cfproductService.findById(pd);
		PageData member = memberService.findByMember(pd);
		
		pd.put("ORDERNUM", new Date().getTime()-1300000000000L);  //订单号
		pd.put("NAME", cfProduct.getString("NAME")); //商品名称
		pd.put("PRICE", ((BigDecimal)cfProduct.get("PRICE")).doubleValue());//商品价格
		pd.put("IMGPATH", cfProduct.getString("IMGPATH1")); //商品图片
		pd.put("PHONE", addPd.getString("PHONE")); //收货人手机号
		pd.put("RECIPIENT", addPd.getString("RECIPIENT")); //收货人
		pd.put("ADDRESS", addPd.getString("ADDRESS")); //地址
		pd.put("DETAILEDADD", addPd.getString("DETAILEDADD"));//详细地址
		pd.put("SOURCE", "1");//来源
		pd.put("PRODUCT_ID", cfProduct.getString("CFPRODUCT_ID"));
		
		int QUANTITY = Integer.parseInt(pd.getString("QUANTITY"));
		int STOCK = ((BigDecimal)cfProduct.get("STOCK")).intValue();
		double TOTAL = Double.parseDouble(pd.getString("TOTAL"));
		double total = TOTAL*EXCHANGE;
		double INTEGRAL = ((BigDecimal)member.get("INTEGRAL")).doubleValue();
		if(INTEGRAL >= total){ //判断积分是否足够
			if(QUANTITY < STOCK){ //判断库存是否足够
				member.put("INTEGRAL", INTEGRAL - total);
				memberService.edit(member);
				
				pd.put("ORDERTIME", Tools.date2Str(new Date()));//下单时间
				pd.put("PAYMENTTIME", Tools.date2Str(new Date()));//付款时间
				pd.put("DELIVERYTIME", "");//发货时间
				pd.put("RECEIPTTIME", "");//收货时间
				pd.put("STATE", "待发货");//状态
				pd.put("MEMBER_ID", member.getString("MEMBER_ID"));
				pd.put("ORDER_ID", this.get32UUID());
				orderService.save(pd);
				
				cfProduct.put("STOCK", STOCK - QUANTITY);
				cfproductService.edit(cfProduct);
				
				PageData bonusPd1 = new PageData();
				bonusPd1.put("PHONE", member.getString("MEMBER"));
				bonusPd1.put("PRODUCE", member.getString("MEMBER"));
				bonusPd1.put("QUANTITY", total);
				bonusPd1.put("TYPE", "积分消费");
				bonusPd1.put("STATE", "1");
				bonusPd1.put("TIME", Tools.date2Str(new Date()));
				bonusPd1.put("BONUS_ID", this.get32UUID());
				bonusService.save(bonusPd1);
				
				errInfo = "success";
			}else{
				errInfo = "stock"; //库存不足
			}
		}else{
			pd.put("ORDERTIME", Tools.date2Str(new Date()));//下单时间
			pd.put("PAYMENTTIME", "");//付款时间
			pd.put("DELIVERYTIME", "");//发货时间
			pd.put("RECEIPTTIME", "");//收货时间
			pd.put("STATE", "待付款");//状态
			pd.put("MEMBER_ID", member.getString("MEMBER_ID"));
			pd.put("ORDER_ID", this.get32UUID());
			orderService.save(pd);
			errInfo = "money";
		}
		return errInfo;
	}
	
	/**
	 * 下单页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/order2")
	public ModelAndView order2() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData addPd = new PageData();
		if("1".equals(pd.getString("mark"))){
			addPd = addressService.findByMemberId(pd);
		}
		if("0".equals(pd.getString("mark"))){
			addPd = addressService.findById(pd);
		}
		PageData cfProduct = cfproductService.findById(pd);
		double price = ((BigDecimal)cfProduct.get("PRICE")).doubleValue();
		pd.put("TOTAL", price * Integer.parseInt(pd.getString("QUANTITY")));
		mv.addObject("pd", pd);
		mv.addObject("addPd",addPd);
		mv.addObject("cfProduct", cfProduct);
		mv.setViewName("front/order2");
		return mv;
	}
	
	/**
	 * 下单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/order2_do")
	public synchronized String order2_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
//        ServletContext context = webApplicationContext.getServletContext();
//        PageData parameter = (PageData) context.getAttribute("parameter");
//        double INITIAL = Double.parseDouble(parameter.getString("INITIAL")); //初始
//        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE")); //佣金：积分   参数
//        double DIRECT = Double.parseDouble(parameter.getString("DIRECT"));//直推奖参数
//        double LEADER = Double.parseDouble(parameter.getString("LEADER"));//领导奖参数
//        int SQUAD = Integer.parseInt(parameter.getString("SQUAD"));//小队人数
//        int REVENUE = Integer.parseInt(parameter.getString("REVENUE")); //收益到达倍数（判断是否得分享积分）
//        int COUNT = Integer.parseInt(parameter.getString("COUNT")); //一次性数量
//        int MULTIPLE = Integer.parseInt(parameter.getString("MULTIPLE")); //出局倍数
//        int ONEMULTIPLE = Integer.parseInt(parameter.getString("ONEMULTIPLE")); //一次性出局倍数
		
		PageData addPd = addressService.findById(pd);
		PageData cfProduct = cfproductService.findById(pd);
		PageData member = memberService.findByMember(pd);
		
		pd.put("ORDERNUM", new Date().getTime()-1300000000000L);  //订单号
		pd.put("NAME", cfProduct.getString("NAME")); //商品名称
		pd.put("PRICE", ((BigDecimal)cfProduct.get("PRICE")).doubleValue());//商品价格
		pd.put("IMGPATH", cfProduct.getString("IMGPATH1")); //商品图片
		pd.put("PHONE", addPd.getString("PHONE")); //收货人手机号
		pd.put("RECIPIENT", addPd.getString("RECIPIENT")); //收货人
		pd.put("ADDRESS", addPd.getString("ADDRESS")); //地址
		pd.put("DETAILEDADD", addPd.getString("DETAILEDADD"));//详细地址
		pd.put("SOURCE", "1");//来源
		pd.put("PRODUCT_ID", cfProduct.getString("CFPRODUCT_ID"));
		
		int QUANTITY = Integer.parseInt(pd.getString("QUANTITY"));
		int STOCK = ((BigDecimal)cfProduct.get("STOCK")).intValue();
		double TOTAL = Double.parseDouble(pd.getString("TOTAL"));
		
		double WEALTH = ((BigDecimal)member.get("WEALTH")).doubleValue();
		if(WEALTH >= TOTAL){ //判断财富是否足够
			if(QUANTITY < STOCK){ //判断库存是否足够
				member.put("WEALTH", WEALTH - TOTAL);
				memberService.edit(member);
				
				pd.put("ORDERTIME", Tools.date2Str(new Date()));//下单时间
				pd.put("PAYMENTTIME", Tools.date2Str(new Date()));//付款时间
				pd.put("DELIVERYTIME", "");//发货时间
				pd.put("RECEIPTTIME", "");//收货时间
				pd.put("STATE", "待发货");//状态
				pd.put("MEMBER_ID", member.getString("MEMBER_ID"));
				pd.put("ORDER_ID", this.get32UUID());
				orderService.save(pd);
				
				cfProduct.put("STOCK", STOCK - QUANTITY);
				cfproductService.edit(cfProduct);
				
				PageData bonusPd1 = new PageData();
				bonusPd1.put("PHONE", member.getString("MEMBER"));
				bonusPd1.put("PRODUCE", member.getString("MEMBER"));
				bonusPd1.put("QUANTITY", TOTAL);
				bonusPd1.put("TYPE", "财富消费");
				bonusPd1.put("STATE", "1");
				bonusPd1.put("TIME", Tools.date2Str(new Date()));
				bonusPd1.put("BONUS_ID", this.get32UUID());
				bonusService.save(bonusPd1);
				
				errInfo = "success";
			}else{
				errInfo = "stock"; //库存不足
			}
		}else{
			pd.put("ORDERTIME", Tools.date2Str(new Date()));//下单时间
			pd.put("PAYMENTTIME", "");//付款时间
			pd.put("DELIVERYTIME", "");//发货时间
			pd.put("RECEIPTTIME", "");//收货时间
			pd.put("STATE", "待付款");//状态
			pd.put("MEMBER_ID", member.getString("MEMBER_ID"));
			pd.put("ORDER_ID", this.get32UUID());
			orderService.save(pd);
			errInfo = "money";//余额不足
		}
		return errInfo;
	}
	
	/**
	 * 去积分商城页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mallshop")
	public ModelAndView mallshop() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/mallshop");
		return mv;
	}
	
	/**
	 * 去衣服页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/clothes1")
	public ModelAndView clothes1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/clothes1");
		return mv;
	}
	
	/**
	 * 去奢侈品页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/luxury1")
	public ModelAndView luxury1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/luxury1");
		return mv;
	}
	
	/**
	 * 去汽车页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/car1")
	public ModelAndView car1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/car1");
		return mv;
	}
	
	/**
	 * 去床上用品页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bedclothes1")
	public ModelAndView bedclothes1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/bedclothes1");
		return mv;
	}
	
	/**
	 * 去手机页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/phone1")
	public ModelAndView phone1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/phone1");
		return mv;
	}
	
	/**
	 * 积分商城商品详情页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shopdetail1")
	public ModelAndView shopdetail1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = jfproductService.findById(pd);
		mv.addObject("pd", pd);
		mv.setViewName("front/shopdetail1");
		return mv;
	}
	
	/**
	 * 下单页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/order")
	public ModelAndView order() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData addPd = new PageData();
		if("1".equals(pd.getString("mark"))){
			addPd = addressService.findByMemberId(pd);
		}
		if("0".equals(pd.getString("mark"))){
			addPd = addressService.findById(pd);
		}
		PageData jfProduct = jfproductService.findById(pd);
		double price = ((BigDecimal)jfProduct.get("PRICE")).doubleValue();
		pd.put("TOTAL", price * Integer.parseInt(pd.getString("QUANTITY")));
		mv.addObject("pd", pd);
		mv.addObject("addPd",addPd);
		mv.addObject("jfProduct", jfProduct);
		mv.setViewName("front/order");
		return mv;
	}
	
	/**
	 * 下单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/order_do")
	public synchronized String order_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
//        ServletContext context = webApplicationContext.getServletContext();
//        PageData parameter = (PageData) context.getAttribute("parameter");
//        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE")); //佣金：积分   参数
		
		PageData addPd = addressService.findById(pd);
		PageData jfProduct = jfproductService.findById(pd);
		PageData member = memberService.findByMember(pd);
		
		pd.put("ORDERNUM", new Date().getTime()-1300000000000L);  //订单号
		pd.put("NAME", jfProduct.getString("NAME")); //商品名称
		pd.put("PRICE", ((BigDecimal)jfProduct.get("PRICE")).doubleValue());//商品价格
		pd.put("IMGPATH", jfProduct.getString("IMGPATH1")); //商品图片
		pd.put("PHONE", addPd.getString("PHONE")); //收货人手机号
		pd.put("RECIPIENT", addPd.getString("RECIPIENT")); //收货人
		pd.put("ADDRESS", addPd.getString("ADDRESS")); //地址
		pd.put("DETAILEDADD", addPd.getString("DETAILEDADD"));//详细地址
		//pd.put("PRICE", Double.parseDouble(pd.getString("PRICE"))/EXCHANGE);
		pd.put("SOURCE", "0");//来源
		pd.put("PRODUCT_ID", jfProduct.getString("JFPRODUCT_ID"));
		
		int QUANTITY = Integer.parseInt(pd.getString("QUANTITY"));
		int STOCK = ((BigDecimal)jfProduct.get("STOCK")).intValue();
		double TOTAL = Double.parseDouble(pd.getString("TOTAL"));
		double INTEGRAL = ((BigDecimal)member.get("INTEGRAL")).doubleValue();
		if(INTEGRAL >= TOTAL){ //判断积分是否足够
			if(QUANTITY < STOCK){ //判断库存是否足够
				member.put("INTEGRAL", INTEGRAL - TOTAL);
				memberService.edit(member);
				pd.put("TOTAL", TOTAL);
				pd.put("ORDERTIME", Tools.date2Str(new Date()));//下单时间
				pd.put("PAYMENTTIME", Tools.date2Str(new Date()));//付款时间
				pd.put("DELIVERYTIME", "");//发货时间
				pd.put("RECEIPTTIME", "");//收货时间
				pd.put("STATE", "待发货");//状态
				pd.put("MEMBER_ID", member.getString("MEMBER_ID"));
				pd.put("ORDER_ID", this.get32UUID());
				orderService.save(pd);
				
				jfProduct.put("STOCK", STOCK - QUANTITY);
				jfproductService.edit(jfProduct);
				
				//记录
				PageData bonusPd = new PageData();
				bonusPd.put("PHONE", member.getString("MEMBER"));
				bonusPd.put("PRODUCE", member.getString("MEMBER"));
				bonusPd.put("QUANTITY", TOTAL);
				bonusPd.put("TYPE", "积分消费");
				bonusPd.put("STATE", "1");
				bonusPd.put("TIME", Tools.date2Str(new Date()));
				bonusPd.put("BONUS_ID", this.get32UUID());
				bonusService.save(bonusPd);
				
				errInfo = "success";
			}else{
				errInfo = "stock"; //库存不足
			}
		}else{
			pd.put("TOTAL", TOTAL);
			pd.put("ORDERTIME", Tools.date2Str(new Date()));//下单时间
			pd.put("PAYMENTTIME", "");//付款时间
			pd.put("DELIVERYTIME", "");//发货时间
			pd.put("RECEIPTTIME", "");//收货时间
			pd.put("STATE", "待付款");//状态
			pd.put("MEMBER_ID", member.getString("MEMBER_ID"));
			pd.put("ORDER_ID", this.get32UUID());
			orderService.save(pd);
			errInfo = "money";
		}
		return errInfo;
	}
	
	/**
	 * 去个人中心页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mine")
	public ModelAndView mine() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/mine");
		return mv;
	}
	
	/**
	 * 上传头像
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/avatar")
	public String avatar() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData member = memberService.findById(pd);
		member.put("AVATAR", pd.getString("AVATAR"));
		memberService.edit(member);
		return "success";
	}
	
	/**
	 * 签到
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkin")
	public String checkin() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData member = memberService.findById(pd);
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        double SIGNINTEGRAL = Double.parseDouble(parameter.getString("SIGNINTEGRAL"));
        double INTEGRAL = ((BigDecimal)member.get("INTEGRAL")).doubleValue();
        member.put("INTEGRAL", INTEGRAL + SIGNINTEGRAL);
        double JF = ((BigDecimal)member.get("JF")).doubleValue();
        member.put("JF", JF + SIGNINTEGRAL);
        member.put("CHECKIN", "yes");
        memberService.edit(member);
        
        //记录
		PageData bonusPd = new PageData();
		bonusPd.put("PHONE", member.getString("MEMBER"));
		bonusPd.put("PRODUCE", member.getString("MEMBER"));
		bonusPd.put("QUANTITY", SIGNINTEGRAL);
		bonusPd.put("TYPE", "签到");
		bonusPd.put("STATE", "1");
		bonusPd.put("TIME", Tools.date2Str(new Date()));
		bonusPd.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd);
		
		return "success";
	}
	
	/**
	 * 去订单列表页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myorderlist")
	public ModelAndView myorderlist() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		pd = (PageData) session.getAttribute("member");
		pd.remove("STATE");
		
		List<PageData> listAll = orderService.listAll(pd);
		
		mv.addObject("listAll", listAll);
		mv.addObject("pd", pd);
		mv.setViewName("front/myorderlist");
		return mv;
	}
	
	/**
	 * 立即支付
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/Pay")
	public String Pay() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        double INITIAL = Double.parseDouble(parameter.getString("INITIAL")); //初始
        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE")); //佣金：积分   参数
//        double DIRECT = Double.parseDouble(parameter.getString("DIRECT"));//直推奖参数
//        double LEADER = Double.parseDouble(parameter.getString("LEADER"));//领导奖参数
//        int SQUAD = Integer.parseInt(parameter.getString("SQUAD"));//小队人数
//        int REVENUE = Integer.parseInt(parameter.getString("REVENUE")); //收益到达倍数（判断是否得分享积分）
//        int COUNT = Integer.parseInt(parameter.getString("COUNT")); //一次性数量
//        int MULTIPLE = Integer.parseInt(parameter.getString("MULTIPLE")); //出局倍数
//        int ONEMULTIPLE = Integer.parseInt(parameter.getString("ONEMULTIPLE")); //一次性出局倍数
		
		PageData order = orderService.findById(pd);
		PageData member = memberService.findById(order);
		double TOTAL = ((BigDecimal)order.get("TOTAL")).doubleValue();
		int QUANTITY = ((BigDecimal)order.get("QUANTITY")).intValue();
		
		if("1".equals(order.getString("SOURCE"))){  //判断是积分商城的还是财富商城的
			double WEALTH = ((BigDecimal)member.get("WEALTH")).doubleValue();
			if(WEALTH >= TOTAL){
				PageData cfProduct = new PageData();
				cfProduct.put("CFPRODUCT_ID", order.getString("PRODUCT_ID"));
				cfProduct = cfproductService.findById(cfProduct);
				if("1".equals(cfProduct.getString("STATE"))){  //判断是否下架
					int STOCK = ((BigDecimal)cfProduct.get("STOCK")).intValue();
					if(STOCK >= QUANTITY){
						member.put("WEALTH", WEALTH - TOTAL);
						memberService.edit(member);
						
						order.put("PAYMENTTIME", Tools.date2Str(new Date()));
						order.put("STATE", "待发货");
						orderService.edit(order);
						
						cfProduct.put("STOCK", STOCK - QUANTITY);
						cfproductService.edit(cfProduct);
						
						PageData bonusPd1 = new PageData();
						bonusPd1.put("PHONE", member.getString("MEMBER"));
						bonusPd1.put("PRODUCE", member.getString("MEMBER"));
						bonusPd1.put("QUANTITY", TOTAL);
						bonusPd1.put("TYPE", "财富消费");
						bonusPd1.put("STATE", "1");
						bonusPd1.put("TIME", Tools.date2Str(new Date()));
						bonusPd1.put("BONUS_ID", this.get32UUID());
						bonusService.save(bonusPd1);
						
						return "success";
					
					}else{
						//库存不足
						return "stock";
					}
				}else{
					//商品下架
					return "xiajia";
				}
			}else{
				return "wealth";//财富余额不足
			}
		}else{
			double INTEGRAL = ((BigDecimal)member.get("INTEGRAL")).doubleValue();
			if(INITIAL >= TOTAL*EXCHANGE){
				PageData jfProduct = new PageData();
				jfProduct.put("JFPRODUCT_ID", order.getString("PRODUCT_ID"));
				jfProduct = jfproductService.findById(jfProduct);
				if("1".equals(jfProduct.getString("STATE"))){
					int STOCK = ((BigDecimal)jfProduct.get("STOCK")).intValue();
					if(STOCK >= QUANTITY){
						member.put("INTEGRAL", INTEGRAL - TOTAL*EXCHANGE);
						memberService.edit(member);
						
						order.put("PAYMENTTIME", Tools.date2Str(new Date()));
						order.put("STATE", "待发货");
						orderService.edit(order);
						
						jfProduct.put("STOCK", STOCK - QUANTITY);
						jfproductService.edit(jfProduct);
						
						PageData bonusPd1 = new PageData();
						bonusPd1.put("PHONE", member.getString("PHONE"));
						bonusPd1.put("PRODUCE", member.getString("PHONE"));
						bonusPd1.put("QUANTITY", TOTAL*EXCHANGE);
						bonusPd1.put("TYPE", "积分消费");
						bonusPd1.put("STATE", "1");
						bonusPd1.put("TIME", Tools.date2Str(new Date()));
						bonusPd1.put("BONUS_ID", this.get32UUID());
						bonusService.save(bonusPd1);
						
						return "success";
					}else{
						//库存不足
						return "stock";
					}
				}else{
					//商品下架
					return "xiajia";
				}
			}else{
				return "integral"; //积分不足
			}
		}
	}
	
	/**
	 * 确认收货
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/Receipt")
	public String Receipt() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		order.put("STATE", "已完成");
		order.put("RECEIPTTIME", Tools.date2Str(new Date()));
		orderService.edit(order);
		return "success";
	}
	
	/**
	 * 删除订单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delOrder")
	public String delOrder() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		orderService.delete(pd);
		return "success";
	}
	
	/**
	 * 去订单详情页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myorderdetail")
	public ModelAndView myorderdetail() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = orderService.findById(pd);
		mv.addObject("pd", pd);
		mv.setViewName("front/myorderdetail");
		return mv;
	}
	
	/**
	 * 去充值页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/recharge")
	public ModelAndView recharge() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/recharge");
		return mv;
	}
	
	/**
	 * 充值
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/recharge_do")
	public String recharge_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("STATE", "0");
		pd.put("TIME", Tools.date2Str(new Date()));
		pd.put("RECHARGE_ID", this.get32UUID());
		rechargeService.save(pd);
		return "success";
	}
	
	/**
	 * 去提现页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/withdrawal")
	public ModelAndView withdrawal() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/withdrawal");
		return mv;
	}
	
	/**
	 * 提现
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/withdrawal_do")
	public String withdrawal_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        String FEE = parameter.getString("FEE");
        double fee = Double.parseDouble(FEE);
		
		double money = Double.parseDouble(pd.getString("MONEY"));
		PageData member = memberService.findByMember(pd);
		PageData addPd = new PageData();
		if("银行卡".equals(pd.getString("RSTYLE"))){
			addPd = addressService.findByMemberId(member);
			if(addPd == null){
				return "bank";
			}
		}
		
		double COMMISSION = ((BigDecimal)member.get("COMMISSION")).doubleValue();
		if(COMMISSION < money){
			return "money"; // 佣金不足
		}
		double TXMONEY = ((BigDecimal)member.get("TXMONEY")).doubleValue();
		if(TXMONEY < money){
			return "txmoney"; //提现额度不足
		}
		member.put("COMMISSION",COMMISSION - money);
		member.put("TXMONEY", TXMONEY - money);
		memberService.edit(member);
		
		pd.put("ARRIVAL", money - money*fee/100D);
		pd.put("FEE", FEE);
		pd.put("STATE", "0");
		if("银行卡".equals(pd.getString("RSTYLE"))){
			pd.put("ACCOUNT", addPd.getString("CARDNUM"));
		}else{
			pd.put("ACCOUNT", "");
		}
		pd.put("TIME", Tools.date2Str(new Date()));
		pd.put("WITHDRAW_ID", this.get32UUID());
		pd.put("PHONE", member.getString("MEMBER"));
		withdrawService.save(pd);
		
		PageData bonusPd = new PageData();
		bonusPd.put("PHONE", member.getString("MEMBER"));
		bonusPd.put("PRODUCE", member.getString("MEMBER"));
		bonusPd.put("QUANTITY", money);
		bonusPd.put("TYPE", "提现");
		bonusPd.put("STATE", "1");
		bonusPd.put("TIME", Tools.date2Str(new Date()));
		bonusPd.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd);
		
		return "success";
	}
	
	/**
	 * 去兑换页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exchange")
	public ModelAndView exchange() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/exchange");
		return mv;
	}
	
	/**
	 * 兑换
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/exchange_do")
	public String exchange_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE"));
        double INTEGRAL = Double.parseDouble(pd.getString("INTEGRAL"));
		double COMMISSION = INTEGRAL/EXCHANGE;
		PageData member = memberService.findByMember(pd);
		double commission = ((BigDecimal)member.get("COMMISSION")).doubleValue();
		double integral = ((BigDecimal)member.get("INTEGRAL")).doubleValue();
		if(commission < COMMISSION){ //判断佣金是否足够
			return "moneyError";  //佣金不足
		}
		member.put("COMMISSION", commission - COMMISSION);
		member.put("INTEGRAL", integral + INTEGRAL);
		double JF = ((BigDecimal)member.get("JF")).doubleValue();
        member.put("JF", JF + INTEGRAL);
		memberService.edit(member);
		
		//佣金
		PageData bonusPd1 = new PageData();
		bonusPd1.put("PHONE", member.getString("MEMBER"));
		bonusPd1.put("PRODUCE", member.getString("MEMBER"));
		bonusPd1.put("QUANTITY", COMMISSION);
		bonusPd1.put("TYPE", "兑换积分");
		bonusPd1.put("STATE", "1");
		bonusPd1.put("TIME", Tools.date2Str(new Date()));
		bonusPd1.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd1);
		
		//积分
		PageData bonusPd = new PageData();
		bonusPd.put("PHONE", member.getString("MEMBER"));
		bonusPd.put("PRODUCE", member.getString("MEMBER"));
		bonusPd.put("QUANTITY", INTEGRAL);
		bonusPd.put("TYPE", "兑换");
		bonusPd.put("STATE", "1");
		bonusPd.put("TIME", Tools.date2Str(new Date()));
		bonusPd.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd);
        
		return "success";
	}
	
	/**
	 * 去互转页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exchange1")
	public ModelAndView exchange1() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/exchange1");
		return mv;
	}
	
	/**
	 * 积分互转
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/exchange1_do")
	public String exchange1_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData member = memberService.findById(pd);
		PageData pageData = memberService.findByMember(pd);
		if(pageData != null){
			double INTEGRAL = Double.parseDouble(pd.getString("INTEGRAL"));
			double integral = ((BigDecimal)member.get("INTEGRAL")).doubleValue();
			if(integral >= INTEGRAL){
				member.put("INTEGRAL", integral - INTEGRAL);
				memberService.edit(member);
				pageData.put("INTEGRAL", INTEGRAL);
				memberService.editIntegral(pageData);
				
				//转出
				PageData bonusPd1 = new PageData();
				bonusPd1.put("PHONE", member.getString("MEMBER"));
				bonusPd1.put("PRODUCE", pageData.getString("MEMBER"));
				bonusPd1.put("QUANTITY", INTEGRAL);
				bonusPd1.put("TYPE", "转出");
				bonusPd1.put("STATE", "1");
				bonusPd1.put("TIME", Tools.date2Str(new Date()));
				bonusPd1.put("BONUS_ID", this.get32UUID());
				bonusService.save(bonusPd1);
				
				//转入
				PageData bonusPd = new PageData();
				bonusPd.put("PHONE", pageData.getString("MEMBER"));
				bonusPd.put("PRODUCE", member.getString("MEMBER"));
				bonusPd.put("QUANTITY", INTEGRAL);
				bonusPd.put("TYPE", "转入");
				bonusPd.put("STATE", "1");
				bonusPd.put("TIME", Tools.date2Str(new Date()));
				bonusPd.put("BONUS_ID", this.get32UUID());
				bonusService.save(bonusPd);
				
				return "success";
			}else{
				return "moneyError";//积分不足
			}
		}else{
			return "memberError";//转入方不存在
		}
        
	}
	
	/**
	 * 去兑换页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exchange2")
	public ModelAndView exchange2() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/exchange2");
		return mv;
	}
	
	/**
	 * 兑换
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/exchange2_do")
	public String exchange2_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
        double WEALTH = Double.parseDouble(pd.getString("WEALTH"));
		PageData member = memberService.findByMember(pd);
		double commission = ((BigDecimal)member.get("COMMISSION")).doubleValue();
		double YEJI = ((BigDecimal)member.get("YEJI")).doubleValue();
		double YEJINEWS = ((BigDecimal)member.get("YEJINEWS")).doubleValue();
		double wealth = ((BigDecimal)member.get("WEALTH")).doubleValue();
		if(commission < WEALTH){ //判断佣金是否足够
			return "moneyError";  //佣金不足
		}
		member.put("COMMISSION", commission - WEALTH);
		member.put("WEALTH", wealth + WEALTH);
		member.put("YEJINEWS", YEJINEWS + WEALTH);
		member.put("YEJI", YEJI + WEALTH);
		double CF = ((BigDecimal)member.get("CF")).doubleValue();
		member.put("CF", CF + WEALTH);
		memberService.edit(member);
		
		//佣金
		PageData bonusPd2 = new PageData();
		bonusPd2.put("PHONE", member.getString("MEMBER"));
		bonusPd2.put("PRODUCE", member.getString("MEMBER"));
		bonusPd2.put("QUANTITY", WEALTH);
		bonusPd2.put("TYPE", "兑换财富");
		bonusPd2.put("STATE", "1");
		bonusPd2.put("TIME", Tools.date2Str(new Date()));
		bonusPd2.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd2);
		
		//财富
		PageData bonusPd3 = new PageData();
		bonusPd3.put("PHONE", member.getString("MEMBER"));
		bonusPd3.put("PRODUCE", member.getString("MEMBER"));
		bonusPd3.put("QUANTITY", WEALTH);
		bonusPd3.put("TYPE", "佣金兑换");
		bonusPd3.put("STATE", "1");
		bonusPd3.put("TIME", Tools.date2Str(new Date()));
		bonusPd3.put("BONUS_ID", this.get32UUID());
		bonusService.save(bonusPd3);
        
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        double INITIAL = Double.parseDouble(parameter.getString("INITIAL")); //初始
        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE")); //佣金：积分   参数
        double DIRECT = Double.parseDouble(parameter.getString("DIRECT"));//直推奖参数
        double LEADER = Double.parseDouble(parameter.getString("LEADER"));//领导奖参数
        int SQUAD = Integer.parseInt(parameter.getString("SQUAD"));//小队人数
        int REVENUE = Integer.parseInt(parameter.getString("REVENUE")); //收益到达倍数（判断是否得分享积分）
        int COUNT = Integer.parseInt(parameter.getString("COUNT")); //一次性数量
        int MULTIPLE = Integer.parseInt(parameter.getString("MULTIPLE")); //出局倍数
        int ONEMULTIPLE = Integer.parseInt(parameter.getString("ONEMULTIPLE")); //一次性出局倍数
		
        //分享积分
  		String array[] = member.getString("C_PATH").split(",");
  		PageData pageData = new PageData();
  		pageData.put("array", array);
  		if(array.length > 0){
  			List<PageData> allSuperior = memberService.allSuperior(pageData);
  			int index = 0;
  			for(PageData p : allSuperior){
  				if(index == SQUAD || index == allSuperior.size()){
  					break;
  				}
  				int outBei = 0; //出局倍数
  				int DIRECTPUSH = ((BigDecimal)p.get("DIRECTPUSH")).intValue();
  				int LEVEL_NUM = ((BigDecimal)p.get("LEVEL_NUM")).intValue();
  				double CONSUMPTION = ((BigDecimal)p.get("CONSUMPTION")).doubleValue();
  				double INTEGRAL = ((BigDecimal)p.get("INTEGRAL")).doubleValue();
  				if("yes".equals(p.getString("ONETIME"))){
  					outBei = ONEMULTIPLE + DIRECTPUSH/2;
  				}else{
  					outBei = MULTIPLE + DIRECTPUSH/2;
  				}
  				if(CONSUMPTION/(INITIAL*LEVEL_NUM) < outBei){ //判断收益是否出局
  					double OUTMONEY = Double.parseDouble(p.getString("OUTMONEY"));
  					int level = Integer.parseInt(parameter.getString("LEVEL" + LEVEL_NUM));
//  					if("十星".equals(p.getString("MEM_LEVEL"))){ //判断是否最高级
//  						p.put("INTEGRAL", WEALTH*level + INTEGRAL);
//  						p.put("CONSUMPTION", ((BigDecimal)p.get("CONSUMPTION")).doubleValue() + WEALTH*level/EXCHANGE);
//  						p.put("OUTMONEY", Double.parseDouble(p.getString("OUTMONEY")) + WEALTH*level/EXCHANGE);
//  						double JF = ((BigDecimal)p.get("JF")).doubleValue();
//  						p.put("JF", JF + WEALTH*level);
//  						
//  						PageData bonusPd = new PageData();
//  						bonusPd.put("PHONE", p.getString("MEMBER"));
//  						bonusPd.put("PRODUCE", member.getString("MEMBER"));
//  						bonusPd.put("QUANTITY", WEALTH*level);
//  						bonusPd.put("TYPE", "分享积分");
//  						bonusPd.put("STATE", "1");
//  						bonusPd.put("TIME", Tools.date2Str(new Date()));
//  						bonusPd.put("BONUS_ID", this.get32UUID());
//  						bonusService.save(bonusPd);
//  						
//  						index++;
//  					}else{
  						if(OUTMONEY/INITIAL < REVENUE){ //判断是否可以获得分享积分
  							int fenxiang = 0;
  							if((CONSUMPTION + WEALTH*level/EXCHANGE)/(INITIAL*LEVEL_NUM) < outBei){
  								fenxiang = (int) (WEALTH*level);
  							}else{
  								fenxiang = (int) ((outBei*INITIAL*LEVEL_NUM - CONSUMPTION)*EXCHANGE);
  							}
  							p.put("INTEGRAL", fenxiang + INTEGRAL);
  							p.put("CONSUMPTION", ((BigDecimal)p.get("CONSUMPTION")).doubleValue() + fenxiang/EXCHANGE);
  							p.put("OUTMONEY", Double.parseDouble(p.getString("OUTMONEY")) + fenxiang/EXCHANGE);
  							double JF = ((BigDecimal)p.get("JF")).doubleValue();
  							p.put("JF", JF + fenxiang);
  							
  							PageData bonusPd = new PageData();
  							bonusPd.put("PHONE", p.getString("MEMBER"));
  							bonusPd.put("PRODUCE", member.getString("MEMBER"));
  							bonusPd.put("QUANTITY", fenxiang);
  							bonusPd.put("TYPE", "分享积分");
  							bonusPd.put("STATE", "1");
  							bonusPd.put("TIME", Tools.date2Str(new Date()));
  							bonusPd.put("BONUS_ID", this.get32UUID());
  							bonusService.save(bonusPd);
  							
  							index++;
  						}
//  					}
  					memberService.edit(p);
  				}
  			}
  		}
        
		PageData referrer = memberService.findByReferrer(member);
		if(referrer != null){
			int outBei = 0; //出局倍数
			int DIRECTPUSH = ((BigDecimal)referrer.get("DIRECTPUSH")).intValue();
			int LEVEL_NUM = ((BigDecimal)referrer.get("LEVEL_NUM")).intValue();
			double CONSUMPTION = ((BigDecimal)referrer.get("CONSUMPTION")).doubleValue();
			if(!"普通".equals(referrer.getString("MEM_LEVEL"))){
				if("yes".equals(referrer.getString("ONETIME"))){
					outBei = ONEMULTIPLE + DIRECTPUSH/2;
				}else{
					outBei = MULTIPLE + DIRECTPUSH/2;
				}
				//直推奖
				if(CONSUMPTION/(INITIAL*LEVEL_NUM) < outBei){ //判断收益是否出局
					double zhitui = 0;
					if((CONSUMPTION + WEALTH*DIRECT/100D)/(INITIAL*LEVEL_NUM) < outBei){
						zhitui = WEALTH*DIRECT/100D;
					}else{
						zhitui = outBei*INITIAL*LEVEL_NUM - CONSUMPTION;
					}
					referrer.put("COMMISSION", ((BigDecimal)referrer.get("COMMISSION")).doubleValue() + zhitui);
					referrer.put("CONSUMPTION", ((BigDecimal)referrer.get("CONSUMPTION")).doubleValue() + zhitui);
					referrer.put("OUTMONEY", Double.parseDouble(referrer.getString("OUTMONEY")) + zhitui);
					double YJ = ((BigDecimal)referrer.get("YJ")).doubleValue();
					referrer.put("YJ", YJ + zhitui);
					memberService.edit(referrer);
					
					PageData bonusPd = new PageData();
					bonusPd.put("PHONE", referrer.getString("MEMBER"));
					bonusPd.put("PRODUCE", member.getString("MEMBER"));
					bonusPd.put("QUANTITY", zhitui);
					bonusPd.put("TYPE", "直推奖");
					bonusPd.put("STATE", "1");
					bonusPd.put("TIME", Tools.date2Str(new Date()));
					bonusPd.put("BONUS_ID", this.get32UUID());
					bonusService.save(bonusPd);
				}
			}
			//领导奖
			PageData leader = memberService.findByReferrer(referrer);
			if(leader != null){
				if(!"普通".equals(leader.getString("MEM_LEVEL"))){
					outBei = 0; //出局倍数
					DIRECTPUSH = ((BigDecimal)leader.get("DIRECTPUSH")).intValue();
					LEVEL_NUM = ((BigDecimal)leader.get("LEVEL_NUM")).intValue();
					CONSUMPTION = ((BigDecimal)leader.get("CONSUMPTION")).doubleValue();
					if("yes".equals(leader.getString("ONETIME"))){ //获取出局倍数
						outBei = ONEMULTIPLE + DIRECTPUSH/2;
					}else{
						outBei = MULTIPLE + DIRECTPUSH/2;
					}
					if(CONSUMPTION/(INITIAL*LEVEL_NUM) < outBei){ //判断收益是否出局
						double lingdao = 0;
						if((CONSUMPTION + WEALTH*LEADER/100D)/(INITIAL*LEVEL_NUM) < outBei){
							lingdao = WEALTH*LEADER/100D;
						}else{
							lingdao = outBei*INITIAL*LEVEL_NUM - CONSUMPTION;
						}
						leader.put("COMMISSION", ((BigDecimal)leader.get("COMMISSION")).doubleValue() + lingdao);
						leader.put("CONSUMPTION", ((BigDecimal)leader.get("CONSUMPTION")).doubleValue() + lingdao);
						leader.put("OUTMONEY", Double.parseDouble(leader.getString("OUTMONEY")) + lingdao);
						double YJ = ((BigDecimal)leader.get("YJ")).doubleValue();
						leader.put("YJ", YJ + lingdao);
						memberService.edit(leader);
						
						PageData bonusPd = new PageData();
						bonusPd.put("PHONE", leader.getString("MEMBER"));
						bonusPd.put("PRODUCE", member.getString("MEMBER"));
						bonusPd.put("QUANTITY", lingdao);
						bonusPd.put("TYPE", "领导奖");
						bonusPd.put("STATE", "1");
						bonusPd.put("TIME", Tools.date2Str(new Date()));
						bonusPd.put("BONUS_ID", this.get32UUID());
						bonusService.save(bonusPd);
					}
				}
			}
		}
        
		//升级
        double UPGRADE = ((BigDecimal)member.get("UPGRADE")).doubleValue();
        int GIFT = ((BigDecimal)member.get("GIFT")).intValue();
        double TXMONEY = ((BigDecimal)member.get("TXMONEY")).doubleValue();
		if(!"十星".equals(member.getString("MEM_LEVEL"))){
			int LEVEL_NUM = ((BigDecimal)member.get("LEVEL_NUM")).intValue();
			if(WEALTH / INITIAL >= COUNT){ //判断是否达成一次性消费
				if("普通".equals(member.getString("MEM_LEVEL"))){
					referrer.put("DIRECTPUSH", ((BigDecimal)referrer.get("DIRECTPUSH")).intValue() + 1);
				}
				member.put("MEM_LEVEL", "十星");
				member.put("LEVEL_NUM", 10);
				member.put("ONETIME", "yes");
				int gift = (int) ((WEALTH - COUNT*INITIAL)/INITIAL);
				member.put("GIFT", gift + GIFT);
				member.put("UPGRADE", (WEALTH + UPGRADE - COUNT*INITIAL)%INITIAL);
				member.put("TXMONEY", INITIAL*2*10 - INITIAL*2*LEVEL_NUM);
				member.put("OUTMONEY", "0");
			}else{
				int index = (int) ((UPGRADE + WEALTH) / INITIAL + LEVEL_NUM);
				if(index != LEVEL_NUM){
					if("普通".equals(member.getString("MEM_LEVEL"))){
						referrer.put("DIRECTPUSH", ((BigDecimal)referrer.get("DIRECTPUSH")).intValue() + 1);
					}
					member.put("OUTMONEY", "0");
					if(index == 1){
						member.put("MEM_LEVEL", "一星");
						member.put("LEVEL_NUM", 1);
						member.put("TXMONEY", INITIAL*2);
					}else if(index == 2){
						member.put("MEM_LEVEL", "二星");
						member.put("LEVEL_NUM", 2);
						member.put("TXMONEY", INITIAL*2*2 - 2*INITIAL + TXMONEY);
					}else if(index == 3){
						member.put("MEM_LEVEL", "三星");
						member.put("LEVEL_NUM", 3);
						member.put("TXMONEY", INITIAL*2*3 - INITIAL*2*2 + TXMONEY);
					}else if(index == 4){
						member.put("MEM_LEVEL", "四星");
						member.put("LEVEL_NUM", 4);
						member.put("TXMONEY", INITIAL*2*4 - INITIAL*2*3 + TXMONEY);
					}else if(index == 5){
						member.put("MEM_LEVEL", "五星");
						member.put("LEVEL_NUM", 5);
						member.put("TXMONEY", INITIAL*2*5 - INITIAL*2*4 + TXMONEY);
					}else if(index == 6){
						member.put("MEM_LEVEL", "六星");
						member.put("LEVEL_NUM", 6);
						member.put("TXMONEY", INITIAL*2*6 - INITIAL*2*5 + TXMONEY);
					}else if(index == 7){
						member.put("MEM_LEVEL", "七星");
						member.put("LEVEL_NUM", 7);
						member.put("TXMONEY", INITIAL*2*7 - INITIAL*2*6 + TXMONEY);
					}else if(index == 8){
						member.put("MEM_LEVEL", "八星");
						member.put("LEVEL_NUM", 8);
						member.put("TXMONEY", INITIAL*2*8 - INITIAL*2*7 + TXMONEY);
					}else if(index == 9){
						member.put("MEM_LEVEL", "九星");
						member.put("LEVEL_NUM", 9);
						member.put("TXMONEY", INITIAL*2*9 - INITIAL*2*8 + TXMONEY);
					}else if(index >= 10){
						member.put("MEM_LEVEL", "十星");
						member.put("LEVEL_NUM", 10);
						member.put("GIFT", GIFT + index - 10);
						member.put("TXMONEY", INITIAL*2*10 - INITIAL*2*9 + TXMONEY);
					}
				}
			}
			member.put("UPGRADE", (UPGRADE + WEALTH) % INITIAL);
		}else{
			if(WEALTH / INITIAL >= COUNT){ //判断是否达成一次性消费
				member.put("MEM_LEVEL", "十星");
				member.put("LEVEL_NUM", 10);
				member.put("ONETIME", "yes");
				int gift = (int) ((WEALTH - COUNT*INITIAL)/INITIAL);
				member.put("GIFT", gift + GIFT);
				member.put("UPGRADE", (WEALTH + UPGRADE - COUNT*INITIAL)%INITIAL);
				member.put("TXMONEY", TXMONEY + WEALTH*2);
				member.put("OUTMONEY", "0");
			}else{
				int gift = (int) ((UPGRADE + WEALTH) / INITIAL);
				member.put("GIFT", GIFT + gift);
				member.put("UPGRADE", (UPGRADE + WEALTH) % INITIAL);
				if((UPGRADE + WEALTH) / INITIAL > 1){
					member.put("TXMONEY", TXMONEY + WEALTH*2);
					member.put("OUTMONEY", "0");
				}
			}
		}
		memberService.edit(member);
		memberService.edit(referrer);
		
		return "success";
	}
	
	/**
	 * 去奖金明细页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mybonuslist")
	public ModelAndView mybonuslist() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		List<PageData> listAll = bonusService.listAll(member);
		
		mv.addObject("listAll", listAll);
		mv.addObject("pd", pd);
		mv.setViewName("front/mybonuslist");
		return mv;
	}
	
	/**
	 * 去佣金明细页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yj")
	public ModelAndView yj() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		String array[] = {"提现","退回","兑换积分","直推奖","领导奖","兑换财富"};
		member.put("array", array);
		List<PageData> listAll = bonusService.listAll(member);
		
		mv.addObject("listAll", listAll);
		mv.addObject("pd", pd);
		mv.setViewName("front/yj");
		return mv;
	}
	
	/**
	 * 去积分明细页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jf")
	public ModelAndView jf() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		String array[] = {"积分消费","分享积分","兑换","签到","注册","转入","转出"};
		member.put("array", array);
		List<PageData> listAll = bonusService.listAll(member);
		
		mv.addObject("listAll", listAll);
		mv.addObject("pd", pd);
		mv.setViewName("front/jf");
		return mv;
	}
	
	/**
	 * 去财富明细页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cf")
	public ModelAndView cf() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		String array[] = {"财富消费","充值","佣金兑换"};
		member.put("array", array);
		List<PageData> listAll = bonusService.listAll(member);
		
		mv.addObject("listAll", listAll);
		mv.addObject("pd", pd);
		mv.setViewName("front/cf");
		return mv;
	}
	
	/**
	 * 去分享页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sharecode")
	public ModelAndView sharecode() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = (PageData) Jurisdiction.getSession().getAttribute("member");
		mv.addObject("pd", pd);
		mv.setViewName("front/sharecode");
		return mv;
	}
	
	/**
	 *	生成二维码
	 * @throws Exception
	 */
	@RequestMapping(value="/createTwoDimensionCode")
	@ResponseBody
	public Object createTwoDimensionCode(){
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success", encoderImgId = this.get32UUID()+".png"; //encoderImgId此处二维码的图片名
		String encoderContent = pd.getString("encoderContent");				//内容
		if(null == encoderContent){
			errInfo = "error";
		}else{
			try {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + encoderImgId;  //存放路径
				TwoDimensionCode.encoderQRCode(encoderContent, filePath, "png");							//执行生成二维码
			} catch (Exception e) {
				errInfo = "error";
			}
		}
		map.put("result", errInfo);						//返回结果
		map.put("encoderImgId", encoderImgId);			//二维码图片名
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 功能描述：生成二维码
	 * @author Ajie
	 * @date 2020-4-24 15:26:22
	 */
	@RequestMapping(value = "/qr_code")
	public void qrCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
		PageData pd = (PageData) Jurisdiction.getSession().getAttribute("member");
		// 获取请求路径
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		String context = basePath + "release/register?MEMBER=" + pd.get("MEMBER").toString();
		// 通过输出流把邀请码输出到页面
		ServletOutputStream out = response.getOutputStream();
		// 调用工具类
		TwoDimensionCode.encoderQRCode(context, out);
	}
	
	/**
	 * 我的团队
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myteam")
	public ModelAndView myteam() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        int SQUAD = Integer.parseInt(parameter.getString("SQUAD"));//小队人数
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		String MEMBER = pd.getString("MEMBER");
		PageData top = null;
		if(MEMBER == null){
			top = member;
			pd.put("MEMBER", member.getString("MEMBER"));
		}else{
			top = memberService.findByMember(pd);
		}
		int level = ((BigDecimal)top.get("R_LEVEL")).intValue();
		pd.put("level", level+6);
		List<PageData> list = memberService.listTree(pd);
		HashMap<String,List<PageData>> map = new HashMap<String, List<PageData>>();
		for(int i = 0; i < list.size(); i++){  // 推荐人一样的装到一起
			PageData pageData = list.get(i);
			pageData.put("REFERRER", pageData.getString("MEMBER"));
			List<PageData> listReferrer = memberService.listReferrer(pageData);
			map.put(pageData.getString("MEMBER"), listReferrer);
		}
		
		PageData market = memberService.getMarket(pd);
		pd.put("C_PATH", member.getString("C_PATH") + member.getString("MEMBER"));
		pd.put("C_LEVEL", ((BigDecimal)member.get("C_LEVEL")).intValue() + SQUAD);
		PageData team = memberService.getTeam(pd);
		
		mv.addObject("team", team);
		mv.addObject("market", market);
		mv.addObject("top", top);
		mv.addObject("map", map);
		mv.addObject("pd", pd);
		mv.setViewName("front/tree");
		return mv;
	}
	
	/**
	 * 实名认证页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/verified")
	public ModelAndView verified() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		PageData addPd = addressService.findByMemberId(member);
		
		mv.addObject("addPd", addPd);
		mv.addObject("pd", pd);
		mv.setViewName("front/verified");
		return mv;
	}
	
	/**
	 * 实名认证
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/verified_do")
	public String verified_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData member = memberService.findById(pd);
		PageData findByMemberId = addressService.findByMemberId(member);
		
		if(findByMemberId != null){
			pd.put("ADDRESS_ID", findByMemberId.getString("ADDRESS_ID"));
			addressService.edit(pd);
		}else{
			pd.put("DEFAULT", "yes");
			pd.put("STATE", "1");
			pd.put("TIME", Tools.date2Str(new Date()));
			pd.put("ADDRESS_ID", this.get32UUID());
			addressService.save(pd);
		}
		return "success";
	}
	
//	/**
//	 * 去我的地址页
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/myadresslist")
//	public ModelAndView myadresslist() throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		
//		Session session = Jurisdiction.getSession();
//		PageData member = (PageData) session.getAttribute("member");
//		List<PageData> listAll = addressService.listAll(member);
//		
//		mv.addObject("listAll", listAll);
//		mv.addObject("pd", pd);
//		mv.setViewName("front/myadresslist");
//		return mv;
//	}
//	
//	/**
//	 * 去添加地址页
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/myadress")
//	public ModelAndView myadress() throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		mv.addObject("pd", pd);
//		mv.setViewName("front/myadress");
//		return mv;
//	}
//	
//	/**
//	 * 添加地址
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping("/myadress_do")
//	public String myadress_do() throws Exception{
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		pd.put("DEFAULT", "yes");
//		pd.put("ADDRESS_ID", this.get32UUID());
//		PageData pd1 = addressService.findByMemberId(pd);
//		if(pd1 != null){
//			pd1.put("DEFAULT", "no");
//			addressService.edit(pd1);
//		}
//		addressService.save(pd);
//		return "success";
//	}
//	
//	/**
//	 * 默认地址
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping("/editDefault")
//	public String editDefault() throws Exception{
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		pd = addressService.findById(pd);
//		pd.put("DEFAULT", "yes");
//		PageData pd1 = addressService.findByMemberId(pd);
//		if(pd1 != null){
//			pd1.put("DEFAULT", "no");
//			addressService.edit(pd1);
//		}
//		addressService.edit(pd);
//		return "success";
//	}
//	
//	/**
//	 * 删除地址
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping("/del")
//	public String del() throws Exception{
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		addressService.delete(pd);
//		return "success";
//	}
	
	/**
	 * 去修改密码页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatepsw")
	public ModelAndView updatepsw() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.addObject("pd", pd);
		mv.setViewName("front/updatepsw");
		return mv;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updatepsw_do")
	public String updatepsw_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String phone = pd.getString("PHONE");
		String MEMBER = pd.getString("MEMBER");
		Session session = Jurisdiction.getSession();
		String verifyCode = (String) session.getAttribute("verifyCode" + phone);
		if(verifyCode != null){  //判断短信验证码
			// 判断验证码是否正确
			String inCode = pd.getString("inCode");
			if (!verifyCode.equalsIgnoreCase(inCode)) {
				return "code";
			}
			session.removeAttribute("verifyCode" + phone);
			PageData member = memberService.findByMember(pd);
			String pwd = member.getString("PWD");
			String oldPwd = pd.getString("oldPwd");
			oldPwd = new SimpleHash("SHA-1" , oldPwd , MEMBER , 2).toString();
			String newPwd = pd.getString("newPwd");
			newPwd = new SimpleHash("SHA-1" , newPwd , MEMBER , 2).toString();
			if(pwd.equals(oldPwd)){
				member.put("PWD", newPwd);
				memberService.editPwd(member);
				session.removeAttribute("verifyCode" + phone);
				errInfo = "success";
			}else{
				errInfo = "oldPwd";  //旧密码错误
			}
		}else{
			errInfo = "code"; //短信验证码错误
		}
		return errInfo;
	}
	
	/**
	 * 去赠送星级页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gift")
	public ModelAndView gift() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		member = memberService.findById(member);
		session.setAttribute("member", member);
		
		mv.addObject("pd", pd);
		mv.setViewName("front/gift");
		return mv;
	}
	
	/**
	 * 赠送星级
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/gift_do")
	public String gift_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        double INITIAL = Double.parseDouble(parameter.getString("INITIAL")); //初始
		
		PageData member = memberService.findById(pd);
		PageData pageData = memberService.findByMember(pd);
		if(pageData != null){
			int GIFT = ((BigDecimal)member.get("GIFT")).intValue();
			int gift = Integer.parseInt(pd.getString("GIFT"));
			if(GIFT >= gift){
				member.put("GIFT", GIFT - gift);
				memberService.edit(member);
				
				int LEVEL_NUM = ((BigDecimal)pageData.get("LEVEL_NUM")).intValue();
				int index = LEVEL_NUM + gift;
				/*if(index == 1){
					pageData.put("MEM_LEVEL", "一星");
					pageData.put("LEVEL_NUM", 1);
				}else if(index == 2){
					pageData.put("MEM_LEVEL", "二星");
					pageData.put("LEVEL_NUM", 2);
				}else if(index == 3){
					pageData.put("MEM_LEVEL", "三星");
					pageData.put("LEVEL_NUM", 3);
				}else if(index == 4){
					pageData.put("MEM_LEVEL", "四星");
					pageData.put("LEVEL_NUM", 4);
				}else if(index == 5){
					pageData.put("MEM_LEVEL", "五星");
					pageData.put("LEVEL_NUM", 5);
				}else if(index == 6){
					pageData.put("MEM_LEVEL", "六星");
					pageData.put("LEVEL_NUM", 6);
				}else if(index == 7){
					pageData.put("MEM_LEVEL", "七星");
					pageData.put("LEVEL_NUM", 7);
				}else if(index == 8){
					pageData.put("MEM_LEVEL", "八星");
					pageData.put("LEVEL_NUM", 8);
				}else if(index == 9){
					pageData.put("MEM_LEVEL", "九星");
					pageData.put("LEVEL_NUM", 9);
				}else if(index >= 10){
					pageData.put("MEM_LEVEL", "十星");
					pageData.put("LEVEL_NUM", 10);
					int GIFT1 = ((BigDecimal)pageData.get("GIFT")).intValue();
					pageData.put("GIFT", GIFT1 + index - 10);
				}*/
				double TXMONEY = ((BigDecimal)pageData.get("TXMONEY")).doubleValue();
				
				PageData referrer = memberService.findByReferrer(pageData);
				if("普通".equals(pageData.getString("MEM_LEVEL"))){
					referrer.put("DIRECTPUSH", ((BigDecimal)referrer.get("DIRECTPUSH")).intValue() + 1);
					memberService.edit(referrer);
				}
				
				if(index == 1){
					pageData.put("MEM_LEVEL", "一星");
					pageData.put("LEVEL_NUM", 1);
					pageData.put("TXMONEY", INITIAL*2);
					pageData.put("OUTMONEY", "0");
				}else if(index == 2){
					pageData.put("MEM_LEVEL", "二星");
					pageData.put("LEVEL_NUM", 2);
					pageData.put("TXMONEY", INITIAL*2*2 - 2*INITIAL + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 3){
					pageData.put("MEM_LEVEL", "三星");
					pageData.put("LEVEL_NUM", 3);
					pageData.put("TXMONEY", INITIAL*2*3 - INITIAL*2*2 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 4){
					pageData.put("MEM_LEVEL", "四星");
					pageData.put("LEVEL_NUM", 4);
					pageData.put("TXMONEY", INITIAL*2*4 - INITIAL*2*3 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 5){
					pageData.put("MEM_LEVEL", "五星");
					pageData.put("LEVEL_NUM", 5);
					pageData.put("TXMONEY", INITIAL*2*5 - INITIAL*2*4 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 6){
					pageData.put("MEM_LEVEL", "六星");
					pageData.put("LEVEL_NUM", 6);
					pageData.put("TXMONEY", INITIAL*2*6 - INITIAL*2*5 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 7){
					pageData.put("MEM_LEVEL", "七星");
					pageData.put("LEVEL_NUM", 7);
					pageData.put("TXMONEY", INITIAL*2*7 - INITIAL*2*6 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 8){
					pageData.put("MEM_LEVEL", "八星");
					pageData.put("LEVEL_NUM", 8);
					pageData.put("TXMONEY", INITIAL*2*8 - INITIAL*2*7 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index == 9){
					pageData.put("MEM_LEVEL", "九星");
					pageData.put("LEVEL_NUM", 9);
					pageData.put("TXMONEY", INITIAL*2*9 - INITIAL*2*8 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}else if(index >= 10){
					pageData.put("MEM_LEVEL", "十星");
					pageData.put("LEVEL_NUM", 10);
					int GIFT1 = ((BigDecimal)pageData.get("GIFT")).intValue();
					pageData.put("GIFT", GIFT1 + index - 10);
					pageData.put("TXMONEY", INITIAL*2*10 - INITIAL*2*9 + TXMONEY);
					pageData.put("OUTMONEY", "0");
				}
				memberService.edit(pageData);
				
				return "success";
			}else{
				return "giftError";
			}
		}else{
			return "memberError";
		}
	}
	
	/**
	 * 去留言页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/liuyan")
	public ModelAndView liuyan() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Session session = Jurisdiction.getSession();
		PageData member = (PageData) session.getAttribute("member");
		List<PageData> listAll = commentsService.listAll(member);
		
		mv.addObject("listAll", listAll);
		mv.addObject("pd", pd);
		mv.setViewName("front/liuyan");
		return mv;
	}
	
	/**
	 * 留言
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/liuyan_do")
	public String liuyan_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MARK", "1");
		pd.put("STATE", "0");
		pd.put("IMG", "no");
		pd.put("TIME", Tools.date2Str(new Date()));
		pd.put("COMMENTS_ID", this.get32UUID());
		commentsService.save(pd);
		return "success";
	}
	
	/**
	 * 留言
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/liuyan1_do")
	public String liuyan1_do() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MARK", "1");
		pd.put("STATE", "0");
		pd.put("IMG", "yes");
		pd.put("CONTENT", pd.getString("CONTENT1"));
		pd.put("TIME", Tools.date2Str(new Date()));
		pd.put("COMMENTS_ID", this.get32UUID());
		commentsService.save(pd);
		return "success";
	}
	
	/**
	 * 退出登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public ModelAndView logout() throws Exception{
		ModelAndView mv = this.getModelAndView();
		Session session = Jurisdiction.getSession();
		session.removeAttribute("mem");
		mv.addObject("msg", "success");
		mv.setViewName("front/login");
		return mv;
	}
	
	  /**
     * 上传图片
     */
    @ResponseBody
    @RequestMapping(value="/upload")
    public Object uploadImage( HttpServletRequest request) {
    	
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(
                request.getServletContext());
        String path = null;
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();

            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                mFile.getName();
                if (mFile != null) {
                    path = FileUtil.saveFile(mFile,request,"adminImg");
                    path = path.substring(path.indexOf("adminImg"),path.length());
                }
            }
        }
        //System.out.println("图片路径==>>"+path);
        return path;
    }
	
}


