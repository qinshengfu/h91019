package com.fh.service.setup.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.setup.CFCarouselManager;

/** 
 * 说明： 财富商城轮播图
 * 创建人：
 * 创建时间：2019-10-25
 * @version
 */
@Service("cfcarouselService")
public class CFCarouselService implements CFCarouselManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("CFCarouselMapper.save", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfCarousel = this.listAll(new PageData());//财富商城轮播图
        context.setAttribute("cfCarousel", cfCarousel);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("CFCarouselMapper.delete", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfCarousel = this.listAll(new PageData());//财富商城轮播图
        context.setAttribute("cfCarousel", cfCarousel);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("CFCarouselMapper.edit", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfCarousel = this.listAll(new PageData());//财富商城轮播图
        context.setAttribute("cfCarousel", cfCarousel);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CFCarouselMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CFCarouselMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CFCarouselMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CFCarouselMapper.deleteAll", ArrayDATA_IDS);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfCarousel = this.listAll(new PageData());//财富商城轮播图
        context.setAttribute("cfCarousel", cfCarousel);
	}
	
}

