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
import com.fh.service.setup.CFProductManager;

/** 
 * 说明： 财富商城商品
 * 创建人：
 * 创建时间：2019-10-30
 * @version
 */
@Service("cfproductService")
public class CFProductService implements CFProductManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("CFProductMapper.save", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("cfProduct", cfProduct);
        //分类
		pd.put("PTYPE", "生鲜");
		List<PageData> clothesList = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList = this.listAll(pd);
        context.setAttribute("clothesList", clothesList);
		context.setAttribute("luxuryList", luxuryList);
		context.setAttribute("carList", carList);
		context.setAttribute("bedclothesList", bedclothesList);
		context.setAttribute("phoneList", phoneList);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("CFProductMapper.delete", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("cfProduct", cfProduct);
        //分类
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList = this.listAll(pd);
        context.setAttribute("clothesList", clothesList);
		context.setAttribute("luxuryList", luxuryList);
		context.setAttribute("carList", carList);
		context.setAttribute("bedclothesList", bedclothesList);
		context.setAttribute("phoneList", phoneList);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("CFProductMapper.edit", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("cfProduct", cfProduct);
        //分类
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList = this.listAll(pd);
        context.setAttribute("clothesList", clothesList);
		context.setAttribute("luxuryList", luxuryList);
		context.setAttribute("carList", carList);
		context.setAttribute("bedclothesList", bedclothesList);
		context.setAttribute("phoneList", phoneList);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CFProductMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CFProductMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CFProductMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CFProductMapper.deleteAll", ArrayDATA_IDS);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> cfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("cfProduct", cfProduct);
        //分类
        PageData pd = new PageData();
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList = this.listAll(pd);
        context.setAttribute("clothesList", clothesList);
		context.setAttribute("luxuryList", luxuryList);
		context.setAttribute("carList", carList);
		context.setAttribute("bedclothesList", bedclothesList);
		context.setAttribute("phoneList", phoneList);
	}
	
}

