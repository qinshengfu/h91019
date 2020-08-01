package com.fh.service.system;

import java.util.List;
import com.fh.entity.Page;
import com.fh.entity.system.Accunt;
import com.fh.util.PageData;

/** 
 * 说明： 资产表接口
 * 创建人：
 * 创建时间：2019-08-20
 * @version
 */
public interface AccuntManager{

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
	
	/**充值
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void recharge(PageData pd)throws Exception;
	
	/**扣款
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deduction(PageData pd)throws Exception;
	
	public Accunt getSERIAL(String SERIAL)throws Exception;

	/**增加会员团队投单量
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void increase(PageData pd)throws Exception;
	
	/**减少会员团队投单量
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void reduce(PageData pd)throws Exception;
	
	/**增加会员直推投单量 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void increase_zt(PageData pd)throws Exception;
	
	/**减少会员直推投单量 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void reduce_zt(PageData pd)throws Exception;
	
	/**每日个人收益清零、修改领取状态 
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void zero_clearing(PageData pd)throws Exception;
}

