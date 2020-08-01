package com.fh.service.system;

import java.util.List;
import com.fh.entity.Page;
import com.fh.entity.system.MemUser;
import com.fh.util.PageData;

/** 
 * 说明： 会员管理接口
 * 创建人：FH Q313596790
 * 创建时间：2019-01-10
 * @version
 */
public interface MembersManager{

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
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list2(Page page)throws Exception;
	
	
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
	
	/**通过手机号获取数据
	 * @param pd
	 * @throws Exception
	 */
	public MemUser getPhone(String PHONE)throws Exception;
	
	
	/**申请代理商
	 * @param pd
	 * @throws Exception
	 */
	public void apply(PageData pd)throws Exception;
	
	
	/**申请列表
	 * @param page
	 * @throws Exception
	 */
	public List<MemUser> apply_list(Page page)throws Exception;
	
	/**申请列表
	 * @param page
	 * @throws Exception
	 */
	public List<MemUser> prepare(Page page)throws Exception;
	
	//更改代理商
	public void edit_agent(String PHONE)throws Exception;
	
	public MemUser getPhone_d(String PHONE) throws Exception;
	
	//取 直推下级数据  
	public List<MemUser> getSUPERIOR(String SUPERIOR)throws Exception;
	
	//取  代理商下属数据  
	public List<MemUser> getAGENT(String AGENT)throws Exception;
	

	
	public String getSERIAL()throws Exception;
	
	/**所有下级
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> syxj(String SERIAL)throws Exception;
	
	/**获取总数
	 * @param pd
	 * @throws Exception
	 */
	public int getTotal()throws Exception;
	
	/**获取下级激活数量
	 * @param pd
	 * @throws Exception
	 */
	public int confirm_num(String SERIAL)throws Exception;
}

