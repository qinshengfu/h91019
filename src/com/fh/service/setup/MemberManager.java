package com.fh.service.setup;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 会员管理接口
 * 创建人：
 * 创建时间：2019-10-29
 * @version
 */
public interface MemberManager{

	/**
	 * 获取接点会员
	 * @return
	 * @throws Exception
	 */
	public PageData getContact(PageData pd) throws Exception;
	
	/**
	 * 获取团队总业绩和总人数和新增业绩
	 * @return
	 * @throws Exception
	 */
	public PageData getTeam(PageData pd) throws Exception;
	
	/**
	 * 获取市场业绩和人数
	 * @return
	 * @throws Exception
	 */
	public PageData getMarket(PageData pd) throws Exception;
	
	/**
	 * 根据接点人获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listContact(PageData pd) throws Exception; 
	
	/**
	 * 获取七层接点关系
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listCommunity1(PageData pd) throws Exception;
	
	/**修改签到记录
	 * @param pd
	 * @throws Exception
	 */
	public void editCheckin(PageData pd)throws Exception;
	
	/**获取所有接点上级
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> allSuperior(PageData pd)throws Exception;
	
	/**根据推荐人获取数据
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> listReferrer(PageData pd)throws Exception;
	
	/**找出 多代推荐关系
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> listTree(PageData pd)throws Exception;
	
	/**修改积分
	 * @param pd
	 * @throws Exception
	 */
	public void editIntegral(PageData pd)throws Exception;
	
	/**修改佣金
	 * @param pd
	 * @throws Exception
	 */
	public void editCommission(PageData pd)throws Exception;
	
	/**修改密码
	 * @param pd
	 * @throws Exception
	 */
	public void editPwd(PageData pd)throws Exception;
	
	/**
	 * 小公排找接点人
	 * @return
	 * @throws Exception
	 */
	public PageData contactPath(String path) throws Exception;
	
	/**通过推荐码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByReferrer(PageData pd)throws Exception;
	
	/**通过账号号获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByMember(PageData pd)throws Exception;
	
	/**通过账号和密码获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByMemberAndPwd(PageData pd)throws Exception;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
}

