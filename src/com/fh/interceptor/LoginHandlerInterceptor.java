package com.fh.interceptor;

import com.fh.entity.system.User;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import org.apache.shiro.session.Session;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
* 类名称：登录过滤，权限验证
* 类描述： 
* @author FH qq313596790[青苔]
* 作者单位： 
* 联系方式：
* 创建时间：2017年11月2日
* @version 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else if (path.matches(Const.NO_DEFAULTERINTERCEPTOR_PATH)){//默认  不用登陆
			return true;
		}else  if(path.matches(Const.NO_MEMUSERINTERCEPTOR_PATH)){//前台登陆才能操作
			/*if(path.matches(Const.GUOLU)){
				return true;
			}*/
			Session session = Jurisdiction.getSession();
			PageData member = (PageData) session.getAttribute("member");
			if(member!=null){
				return true;
			}else{
				response.sendRedirect(request.getContextPath() + "/release/login");
				return false;
			}
		}else{
			User user = (User)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
			if(user!=null){
				path = path.substring(1, path.length());
				boolean b = Jurisdiction.hasJurisdiction(path); //访问权限校验
				if(!b){
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			}else{
				//登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;		
			}
		}
	}
	
}
