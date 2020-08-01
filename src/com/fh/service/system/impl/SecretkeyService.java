package com.fh.service.system.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.Secretkey;
import com.fh.util.PageData;
import com.fh.service.system.SecretkeyManager;

/** 
 * 说明： 会员钱包管理
 * 创建人：
 * 创建时间：2019-06-11
 * @version
 */
@Service("secretkeyService")
public class SecretkeyService implements SecretkeyManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("SecretkeyMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("SecretkeyMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("SecretkeyMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("SecretkeyMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SecretkeyMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SecretkeyMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("SecretkeyMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public Secretkey findSERIAL(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Secretkey)dao.findForObject("SecretkeyMapper.findSERIAL", pd);
	}

	@Override
	public void edit2(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("SecretkeyMapper.edit2", pd);
	}
	
}

