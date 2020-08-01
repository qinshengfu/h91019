package com.fh.service.setup.impl;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.setup.AddressManager;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * 说明： 地址管理
 * 创建人：
 * 创建时间：2019-10-30
 * @version
 */
@Service("addressService")
public class AddressService implements AddressManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**通过手机号查找数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findByPhone(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AddressMapper.findByPhone", pd);
	}
	
	/**通过会员id获取数据
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public PageData findByMemberId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AddressMapper.findByMemberId", pd);
	}

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("AddressMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("AddressMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("AddressMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AddressMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AddressMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AddressMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("AddressMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

