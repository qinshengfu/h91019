package com.fh.service.setup.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.setup.MemberManager;

/** 
 * 说明： 会员管理
 * 创建人：
 * 创建时间：2019-10-29
 * @version
 */
@Service("memberService")
public class MemberService implements MemberManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 获取接点会员
	 * @return
	 * @throws Exception
	 */
	public PageData getContact(PageData pd) throws Exception{
		return (PageData) dao.findForObject("MemberMapper.getContact", pd);
	}
	
	/**
	 * 获取团队总业绩和总人数和新增业绩
	 * @return
	 * @throws Exception
	 */
	public PageData getTeam(PageData pd) throws Exception{
		return (PageData) dao.findForObject("MemberMapper.getTeam", pd);
	}
	
	/**
	 * 获取市场业绩和人数
	 * @return
	 * @throws Exception
	 */
	public PageData getMarket(PageData pd) throws Exception{
		return (PageData) dao.findForObject("MemberMapper.getMarket", pd);
	}
	
	/**
	 * 根据接点人获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listContact(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("MemberMapper.listContact", pd);
	}
	
	/**
	 * 获取七层接点关系
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listCommunity1(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("MemberMapper.listCommunity1", pd);
	}
	
	/**修改签到记录
	 * @param pd
	 * @throws Exception
	 */
	public void editCheckin(PageData pd)throws Exception{
		dao.update("MemberMapper.editCheckin", pd);
	}
	
	/**获取所有接点上级
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> allSuperior(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("MemberMapper.allSuperior", pd);
	}
	
	/**根据推荐人获取数据
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listReferrer(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("MemberMapper.listReferrer", pd);
	}
	
	/**找出 多代推荐关系
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listTree(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("MemberMapper.listTree", pd);
	}
	
	/**修改积分
	 * @param pd
	 * @throws Exception
	 */
	public void editIntegral(PageData pd)throws Exception{
		dao.update("MemberMapper.editIntegral", pd);
	}
	
	/**修改佣金
	 * @param pd
	 * @throws Exception
	 */
	public void editCommission(PageData pd)throws Exception{
		dao.update("MemberMapper.editCommission", pd);
	}
	
	/**修改密码
	 * @param pd
	 * @throws Exception
	 */
	public void editPwd(PageData pd)throws Exception{
		dao.update("MemberMapper.editPwd", pd);
	}
	
	/**
	 * 小公排找接点人
	 * @return
	 * @throws Exception
	 */
	public PageData contactPath(String path) throws Exception{
		return (PageData) dao.findForObject("MemberMapper.contactPath", path);
	}
	
	/**通过推荐码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByReferrer(PageData pd)throws Exception{
		return (PageData) dao.findForObject("MemberMapper.findByReferrer", pd);
	}
	
	/**通过账号号获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByMember(PageData pd)throws Exception{
		return (PageData) dao.findForObject("MemberMapper.findByMember", pd);
	}
	
	/**通过账号号和密码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByMemberAndPwd(PageData pd)throws Exception{
		return (PageData) dao.findForObject("MemberMapper.findByMemberAndPwd", pd);
	}
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MemberMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MemberMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MemberMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MemberMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

