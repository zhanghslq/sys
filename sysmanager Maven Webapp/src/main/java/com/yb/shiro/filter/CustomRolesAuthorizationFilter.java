package com.yb.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 自定义角色Filter 关系为  只要有一个角色满足即可
 * @author Administrator
 *
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String roles[] = (String [])mappedValue;
		
		if(roles==null || roles.length==0){
			return true;
		}
		
		for (String role : roles) {
			if(subject.hasRole(role)){
				return true;
			}
		}
		return false;
	}
	
}