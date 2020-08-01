package com.fh.service.system.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.MemUser;
import com.fh.service.system.MembersManager;
import com.fh.util.PageData;

/** 
 * 说明： 会员管理
 * 创建人：FH 
 * 创建时间：2019-01-10
 * @version
 */
@Service("membersService")
public class MembersService implements MembersManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MembersMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MembersMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MembersMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MembersMapper.datalistPage", page);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list2(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MembersMapper.datalistPage2", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MembersMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MembersMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MembersMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public MemUser getPhone(String PHONE) throws Exception {
		// TODO Auto-generated method stub
		return (MemUser)dao.findForObject("MembersMapper.getPhone", PHONE);
	}
	
	@Override
	public MemUser getPhone_d(String PHONE) throws Exception {
		// TODO Auto-generated method stub
		return (MemUser)dao.findForObject("MembersMapper.getPhone_d", PHONE);
	}

	@Override
	public void apply(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("MembersMapper.apply", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemUser> apply_list(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<MemUser>)dao.findForList("MembersMapper.datalistPage2", page);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemUser> prepare(Page page) throws Exception {
		// TODO Auto-generated method stub
		return (List<MemUser>)dao.findForList("MembersMapper.datalistPage3", page);
	}

	@Override
	public void edit_agent(String PHONE) throws Exception {
		// TODO Auto-generated method stub
		dao.update("MembersMapper.edit_agent", PHONE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemUser> getSUPERIOR(String SUPERIOR) throws Exception {
		// TODO Auto-generated method stub
		return (List<MemUser>)dao.findForList("MembersMapper.getSUPERIOR", SUPERIOR);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemUser> getAGENT(String AGENT) throws Exception {
		// TODO Auto-generated method stub
		return (List<MemUser>)dao.findForList("MembersMapper.getAGENT", AGENT);
	}
	
	
	@Override
	public String getSERIAL() throws Exception {
		// TODO Auto-generated method stub
		return (String) dao.findForObject("MembersMapper.getSERIAL", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> syxj(String SERIAL) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("MembersMapper.syxj", SERIAL);	
	}

	@Override
	public int getTotal() throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.findForObject("MembersMapper.getTotal", null);
	}

	@Override
	public int confirm_num(String SERIAL) throws Exception {
		// TODO Auto-generated method stub
		return (Integer) dao.findForObject("MembersMapper.confirm_num", SERIAL);
	}
}

