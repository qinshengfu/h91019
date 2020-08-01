package com.fh.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fh.controller.setup.MyJob;
import com.fh.service.setup.CFCarouselManager;
import com.fh.service.setup.CFProductManager;
import com.fh.service.setup.JFCarouselManager;
import com.fh.service.setup.JFProductManager;
import com.fh.service.setup.NewsManager;
import com.fh.service.setup.ParameterManager;
import com.fh.service.setup.impl.CFCarouselService;
import com.fh.service.setup.impl.CFProductService;
import com.fh.service.setup.impl.JFCarouselService;
import com.fh.service.setup.impl.JFProductService;
import com.fh.service.setup.impl.NewsService;
import com.fh.service.setup.impl.ParameterService;
import com.fh.util.PageData;
import com.fh.util.QuartzManager;

public class MyContextLitener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{
    			"classpath:spring/ApplicationContext-main.xml",
    			"classpath:spring/ApplicationContext-dataSource.xml",
    			"classpath:spring/ApplicationContext-shiro.xml",
    			"classpath:spring/ApplicationContext-redis.xml"});
		ServletContext context = event.getServletContext();
		ParameterManager parameterService = ac.getBean("parameterService",ParameterService.class);
		JFCarouselManager jfcarouselService = ac.getBean("jfcarouselService",JFCarouselService.class);
		CFCarouselManager cfcarouselService = ac.getBean("cfcarouselService",CFCarouselService.class);
		NewsManager newsService = ac.getBean("newsService", NewsService.class);
		CFProductManager cfproductService = ac.getBean("cfproductService",CFProductService.class);
		JFProductManager jfproductService = ac.getBean("jfproductService",JFProductService.class);
		PageData newsPd = new PageData();
		newsPd.put("NEWS_ID", "ec42ed1965224aa087c3942b529185ca");
		try {
			PageData parameter = parameterService.findById(new PageData());//获取参数
			List<PageData> cfCarousel = cfcarouselService.listAll(new PageData());//财富商城轮播图
			List<PageData> jfCarousel = jfcarouselService.listAll(new PageData());//积分商城轮播图
			List<PageData> cfProduct = cfproductService.listAll(new PageData());//财富商城商品
			List<PageData> jfProduct = jfproductService.listAll(new PageData());//积分商城商品
			newsPd = newsService.findById(newsPd);//滚动公告
			context.setAttribute("parameter", parameter);
			context.setAttribute("cfCarousel", cfCarousel);
			context.setAttribute("jfCarousel", jfCarousel);
			context.setAttribute("newsPd", newsPd);
			context.setAttribute("cfProduct", cfProduct);
			context.setAttribute("jfProduct", jfProduct);
			
			//分类
			PageData pd = new PageData();
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//每天0点执行
		QuartzManager.addJob("清除签到记录", MyJob.class, "0 0 0 * * ?");//0 0 0 * * ?
	}

}
