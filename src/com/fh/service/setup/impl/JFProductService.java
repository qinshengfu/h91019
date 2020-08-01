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
import com.fh.service.setup.JFProductManager;

/** 
 * 说明： 积分商城商品
 * 创建人：
 * 创建时间：2019-11-02
 * @version
 */
@Service("jfproductService")
public class JFProductService implements JFProductManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("JFProductMapper.save", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> jfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("jfProduct", jfProduct);
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList1 = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList1 = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList1 = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList1 = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList1 = this.listAll(pd);
        context.setAttribute("clothesList1", clothesList1);
		context.setAttribute("luxuryList1", luxuryList1);
		context.setAttribute("carList1", carList1);
		context.setAttribute("bedclothesList1", bedclothesList1);
		context.setAttribute("phoneList1", phoneList1);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("JFProductMapper.delete", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> jfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("jfProduct", jfProduct);
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList1 = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList1 = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList1 = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList1 = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList1 = this.listAll(pd);
        context.setAttribute("clothesList1", clothesList1);
		context.setAttribute("luxuryList1", luxuryList1);
		context.setAttribute("carList1", carList1);
		context.setAttribute("bedclothesList1", bedclothesList1);
		context.setAttribute("phoneList1", phoneList1);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("JFProductMapper.edit", pd);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> jfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("jfProduct", jfProduct);
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList1 = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList1 = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList1 = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList1 = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList1 = this.listAll(pd);
        context.setAttribute("clothesList1", clothesList1);
		context.setAttribute("luxuryList1", luxuryList1);
		context.setAttribute("carList1", carList1);
		context.setAttribute("bedclothesList1", bedclothesList1);
		context.setAttribute("phoneList1", phoneList1);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("JFProductMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("JFProductMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("JFProductMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("JFProductMapper.deleteAll", ArrayDATA_IDS);
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext context = webApplicationContext.getServletContext();
        List<PageData> jfProduct = this.listAll(new PageData());//财富商城商品
        context.setAttribute("jfProduct", jfProduct);
        PageData pd = new PageData();
        pd.put("PTYPE", "生鲜");
		List<PageData> clothesList1 = this.listAll(pd);
		pd.put("PTYPE", "美食");
		List<PageData> luxuryList1 = this.listAll(pd);
		pd.put("PTYPE", "美妆日化");
		List<PageData> carList1 = this.listAll(pd);
		pd.put("PTYPE", "数码家电");
		List<PageData> bedclothesList1 = this.listAll(pd);
		pd.put("PTYPE", "日用百货");
		List<PageData> phoneList1 = this.listAll(pd);
        context.setAttribute("clothesList1", clothesList1);
		context.setAttribute("luxuryList1", luxuryList1);
		context.setAttribute("carList1", carList1);
		context.setAttribute("bedclothesList1", bedclothesList1);
		context.setAttribute("phoneList1", phoneList1);
	}
	
}

