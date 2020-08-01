package com.fh.service.system.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.Accunt;
import com.fh.util.PageData;
import com.fh.service.system.AccuntManager;

/** 
 * 说明： 资产表
 * 创建人：
 * 创建时间：2019-08-20
 * @version
 */
@Service("accuntService")
public class AccuntService implements AccuntManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("AccuntMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("AccuntMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("AccuntMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AccuntMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AccuntMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AccuntMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("AccuntMapper.deleteAll", ArrayDATA_IDS);
	}

	/**充值
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void recharge(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.recharge", pd);
	}

	/**扣款
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deduction(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.deduction", pd);
	}

	@Override
	public Accunt getSERIAL(String SERIAL) throws Exception {
		// TODO Auto-generated method stub
		return (Accunt)dao.findForObject("AccuntMapper.getSERIAL", SERIAL);
	}

	@Override
	public void increase(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.increase", pd);
	}

	@Override
	public void reduce(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.reduce", pd);
	}

	@Override
	public void increase_zt(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.increase_zt", pd);
	}

	@Override
	public void reduce_zt(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.reduce_zt", pd);
	}

	@Override
	public void zero_clearing(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("AccuntMapper.zero_clearing", pd);
	}
	
}

