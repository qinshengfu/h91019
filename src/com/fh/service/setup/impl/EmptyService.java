package com.fh.service.setup.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;

/** 
 * 说明： 清空数据
 * 创建人：
 * 创建时间：2019-08-27
 * @version
 */
@Service("emptyService")
public class EmptyService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 清空数据
	 * @param pd
	 * @throws Exception
	 */
	public void empty(List<String> list) throws Exception{
		dao.update("EmptyMapper.empty", list);
	}
	
}

