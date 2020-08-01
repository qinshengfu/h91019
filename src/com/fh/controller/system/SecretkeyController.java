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
import com.fh.util.Convert;
import com.fh.util.DateUtil;
import com.fh.util.Etheric;
import com.fh.util.HttpRequest;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Results;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.XstreamUtil;
import com.fh.util.Convert.Unit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fh.service.system.SecretkeyManager;

/** 
 * 说明：会员钱包管理
 * 创建人：
 * 创建时间：2019-06-11
 */
@Controller
@RequestMapping(value="/secretkey")
public class SecretkeyController extends BaseController {
	
	String menuUrl = "secretkey/list.do"; //菜单地址(权限用)
	@Resource(name="secretkeyService")
	private SecretkeyManager secretkeyService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Secretkey");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SECRETKEY_ID", this.get32UUID());	//主键
		pd.put("FILE_CONTENT", "");	//秘钥文件内容
		pd.put("SECRET_PASSWORD", "");	//秘钥密码
		pd.put("PRIVATEKEY", "");	//私钥
		pd.put("PUBLICKEY", "");	//公钥
		pd.put("SECRET_FILE_NAME", "");	//秘钥文件名
		pd.put("SERIAL", "");	//会员编号
		pd.put("CURRENCY", "");	//币种
		pd.put("TRADE_TIME", "");	//最后交易时间
		secretkeyService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/refresh_data")//刷新余额
	public void delete(PrintWriter out) throws Exception{	
		PageData pd = new PageData();
		pd = this.getPageData();
		String us = pd.getString("SECRETKEY_ID");
		if(us==null||"".equals(us)){//刷新所有
			refresh_all();
		}else{//刷新单条数据
			pd = secretkeyService.findById(pd);
			refresh(pd);
		}
		
		
		out.write("success");
		out.close();
	}
	
	private void refresh(PageData pd) {
		// TODO Auto-generated method stub
		String CURRENCY = pd.getString("CURRENCY");
		String mun = "";
		if("ETH".equals(CURRENCY)){
			String url = "module=account&action=balance&address="+pd.getString("ADDRESS")+"&tag=latest&apikey=HFFSRZGHBKH1IZFT28771X9WMBAA7N1V81";
			String lus = HttpRequest.sendGet("https://api.etherscan.io/api", url);
			JSONObject json = JSONObject.fromObject(lus); 
			mun =Convert.fromWei(new BigDecimal(json.getString("result")), Unit.ETHER).toString();		
			System.out.println("余额==>>"+mun);
		}else if("BTC".equals(CURRENCY)){
			String url = "https://api.omniexplorer.info/v1/address/addr/";
			String ssd = HttpRequest.sendPost(url, "addr="+pd.getString("ADDRESS"));
			JSONObject json = JSONObject.fromObject(ssd); 
			JSONArray objarr = json.optJSONArray("balance");
			JSONObject smallobj=null;
			for(int i=0;i<objarr.size();i++){
				smallobj = JSONObject.fromObject(objarr.get(i).toString());
				String symbol = smallobj.getString("symbol");
				if("BTC".equals(symbol)){			
					mun = Convert.fromWei(new BigDecimal(smallobj.getString("value")), Unit.GBTC).toString();
				}
			}
		}else if("BALIC".equals(CURRENCY)){											
			String url = "module=account&action=tokenbalance&contractaddress=0x284da39fec424ad0e1dc5a004b881de309c24a32"
					+"&address="+pd.getString("ADDRESS")+"&tag=latest&apikey=HFFSRZGHBKH1IZFT28771X9WMBAA7N1V81";
			String lus = HttpRequest.sendPost("https://api.etherscan.io/api", url);			
			JSONObject json = JSONObject.fromObject(lus); 
			mun =Convert.fromWei(new BigDecimal(json.getString("result")), Unit.ETHER).toString();		
			System.out.println("余额==>>"+mun);
		}else if("EOS".equals(CURRENCY)){
			
		}else if("USDT".equals(CURRENCY)){
			String url = "https://api.omniexplorer.info/v1/address/addr/";
			String ssd = HttpRequest.sendPost(url, "addr="+pd.getString("ADDRESS"));
			JSONObject json = JSONObject.fromObject(ssd); 
			JSONArray objarr = json.optJSONArray("balance");
			JSONObject smallobj=null;
			for(int i=0;i<objarr.size();i++){
				smallobj = JSONObject.fromObject(objarr.get(i).toString());
				String symbol = smallobj.getString("symbol");
				if("SP31".equals(symbol)){			
					mun = Convert.fromWei(new BigDecimal(smallobj.getString("value")), Unit.GBTC).toString();
				}
			}
			
		}
		try {
			pd.put("BALANCE", mun);
			secretkeyService.edit(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void refresh_all() {
		// TODO Auto-generated method stub
		
	}

	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Secretkey");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String to_add = pd.getString("to_add");//转出地址
		double to_mun = Double.valueOf(pd.getString("to_mun"));//转出金额
		pd = secretkeyService.findById(pd);
		double BALANCE = ((BigDecimal)pd.get("BALANCE")).doubleValue();//账户余额
		String CURRENCY = pd.getString("CURRENCY");//转账币种				
		if(BALANCE>to_mun){
			if("ETH".equals(CURRENCY)){
				transfer_ETH(to_mun,pd,to_add,BALANCE);
			}else if("BTC".equals(CURRENCY)){
				transfer_BTC(to_mun,pd,to_add,BALANCE,"1");
			}else if("EOS".equals(CURRENCY)){
				
			}else if("BALIC".equals(CURRENCY)){
				transfer_YEC(to_mun, pd, to_add, BALANCE);
			}else if("USDT".equals(CURRENCY)){
				transfer_BTC(to_mun,pd,to_add,BALANCE,"SP31");
			}
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	//比特币、USDT交易
	private void transfer_BTC(double to_mun, PageData pd, String to_add, double bALANCE,String propertyid ) {
		// TODO Auto-generated method stub
		String token="bCfCLyvoAlYeDRgcSLMqTeha1edtbRBh";//y验证token
		String url="http://usdt2.bbw-rj.com/index.php/index/usdt/trade/"
				+ "/number/1/token/"+token+"/name/java_"+pd.getString("SERIAL")+"/mytoken/"+pd.getString("SECRET_PASSWORD")
				+"/fromaddress/"+pd.getString("ADDRESS")+"/toaddress/"+to_add+"/propertyid/"+propertyid
				+"/amount/"+to_mun+"/feeaddress/"+to_add;
		
		 String ssd = HttpRequest.sendPost(url, "");
		 if(ssd!=null&&!"".equals(ssd)){
			 JSONObject json = JSONObject.fromObject(ssd); 
			 String hash = json.getString("data");
			 if(hash!=null&&"".equals(hash)){
				 try {
			        	pd.put("HASH", hash);
			        	pd.put("BALANCE",(bALANCE-to_mun)+"");
						secretkeyService.edit(pd);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			 }
		 }
		
	}
	//以太币交易
	private void transfer_ETH(double mun,PageData s_pd,String to_add,double BALANCE) {
		// TODO Auto-generated method stub
		Etheric temp = new Etheric();
		temp.setType("7");		
		temp.setPassword(s_pd.getString("SECRET_PASSWORD"));//加密密码
        temp.setPrivateKey(s_pd.getString("PRIVATEKEY"));//私钥
        temp.setFromAddress(s_pd.getString("ADDRESS"));//转出地址
        temp.setToAddress(to_add);//对方地址
        temp.setData("");//内容
        temp.setValue(mun+"");//转账金额
        temp.setGasPrice("15");//手续费单价
        temp.setGasLimit("90000");//手续费限制  30000起
        
        String xml = XstreamUtil.simpleobject2xml(temp);     
        String ssd = HttpRequest.sendPost("http://eth2.bbw-rj.com:8999//gantPanda//ethernetInterface", "parameter="+xml);
        Results rest = (Results) XstreamUtil.simplexml2object(ssd, new Results());
        String tradingHash = rest.getTradingHash();
		if(!"".equals(tradingHash)&&tradingHash!=null){
	        try {
	        	s_pd.put("HASH", tradingHash);
	        	s_pd.put("BALANCE",(BALANCE-mun)+"");
				secretkeyService.edit(s_pd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	//USDT交易
		private void transfer_USDT(double mun,PageData s_pd,String to_add,double BALANCE) {
			// TODO Auto-generated method stub
			Etheric temp = new Etheric();
			temp.setType("8");		
			temp.setPassword(s_pd.getString("SECRET_PASSWORD"));//加密密码
	        temp.setPrivateKey(s_pd.getString("PRIVATEKEY"));//私钥
	        temp.setFromAddress(s_pd.getString("ADDRESS"));//转出地址
	        temp.setToAddress(to_add);//对方地址
	        temp.setData("");//内容
	        temp.setValue(mun+"");//转账金额
	        temp.setGasPrice("15");//手续费单价
	        temp.setGasLimit("90000");//手续费限制  30000起
	        temp.setContractAddress("0xdAC17F958D2ee523a2206206994597C13D831ec7");
	        						 
	        String xml = XstreamUtil.simpleobject2xml(temp);     
	        String ssd = HttpRequest.sendPost("http://eth2.bbw-rj.com:8999//gantPanda//ethernetInterface", "parameter="+xml);
	        Results rest = (Results) XstreamUtil.simplexml2object(ssd, new Results());
	        String tradingHash = rest.getTradingHash();
			if(!"".equals(tradingHash)&&tradingHash!=null){
		        try {
		        	s_pd.put("HASH", tradingHash);
		        	s_pd.put("BALANCE",(BALANCE-mun)+"");
					secretkeyService.edit(s_pd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		//YEC交易
			private void transfer_YEC(double mun,PageData s_pd,String to_add,double BALANCE) {
					// TODO Auto-generated method stub
					Etheric temp = new Etheric();
					temp.setType("8");		
					temp.setPassword(s_pd.getString("SECRET_PASSWORD"));//加密密码
			        temp.setPrivateKey(s_pd.getString("PRIVATEKEY"));//私钥
			        temp.setFromAddress(s_pd.getString("ADDRESS"));//转出地址
			        temp.setToAddress(to_add);//对方地址
			        temp.setData("");//内容
			        temp.setValue(mun+"");//转账金额
			        temp.setGasPrice("15");//手续费单价
			        temp.setGasLimit("90000");//手续费限制  30000起
			        temp.setContractAddress("0x284da39fec424ad0e1dc5a004b881de309c24a32");
			        						 
			        String xml = XstreamUtil.simpleobject2xml(temp);     
			        String ssd = HttpRequest.sendPost("http://eth2.bbw-rj.com:8999//gantPanda//ethernetInterface", "parameter="+xml);
			        Results rest = (Results) XstreamUtil.simplexml2object(ssd, new Results());
			        String tradingHash = rest.getTradingHash();
					if(!"".equals(tradingHash)&&tradingHash!=null){
				        try {
				        	s_pd.put("HASH", tradingHash);
				        	s_pd.put("BALANCE",(BALANCE-mun)+"");
							secretkeyService.edit(s_pd);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}	
		

	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Secretkey");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String name = pd.getString("name");				//关键词检索条件
		if(null != name && !"".equals(name)){
			pd.put("name", name.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = secretkeyService.list(page);	//列出Secretkey列表
		mv.setViewName("system/secretkey/secretkey_list");
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
		mv.setViewName("system/secretkey/secretkey_edit");
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
		pd = secretkeyService.findById(pd);	//根据ID读取
		mv.setViewName("system/secretkey/secretkey_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Secretkey");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			secretkeyService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Secretkey到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("秘钥文件内容");	//1
		titles.add("秘钥密码");	//2
		titles.add("私钥");	//3
		titles.add("公钥");	//4
		titles.add("秘钥文件名");	//5
		titles.add("会员编号");	//6
		titles.add("币种");	//7
		titles.add("最后交易时间");	//8
		dataMap.put("titles", titles);
		List<PageData> varOList = secretkeyService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("FILE_CONTENT"));	    //1
			vpd.put("var2", varOList.get(i).getString("SECRET_PASSWORD"));	    //2
			vpd.put("var3", varOList.get(i).getString("PRIVATEKEY"));	    //3
			vpd.put("var4", varOList.get(i).getString("PUBLICKEY"));	    //4
			vpd.put("var5", varOList.get(i).getString("SECRET_FILE_NAME"));	    //5
			vpd.put("var6", varOList.get(i).getString("SERIAL"));	    //6
			vpd.put("var7", varOList.get(i).getString("CURRENCY"));	    //7
			vpd.put("var8", varOList.get(i).getString("TRADE_TIME"));	    //8
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
	
	public static void main(String[] args) {
		String url = "https://api.etherscan.io/api?module=account&action=balance&address=0x968fb2677ff1b39C5AA3fa0f561CAe7ba7f57DD5"
		+"&tag=latest&apikey=HFFSRZGHBKH1IZFT28771X9WMBAA7N1V81";
		String lus = HttpRequest.sendGet(url, "");
		JSONObject json = JSONObject.fromObject(lus); 
		String mun =Convert.fromWei(new BigDecimal(json.getString("result")), Unit.ETHER).toString();	
		System.out.println(mun);
	}
}
