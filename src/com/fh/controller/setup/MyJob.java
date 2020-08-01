package com.fh.controller.setup;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.fh.service.setup.MemberManager;
import com.fh.service.setup.impl.MemberService;
import com.fh.util.PageData;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//普通类从spring容器中拿出service
		WebApplicationContext webctx=ContextLoader.getCurrentWebApplicationContext();
		MemberManager memberService = webctx.getBean("memberService", MemberService.class);
		try {
			memberService.editCheckin(new PageData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
