package com.yb.shiro.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yb.dao.AdminDao;
import com.yb.entity.Admin;
import com.yb.service.ShiroService;
import com.yb.util.ByteSourceUtils;

/**
 *自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {
	@Resource
	private AdminDao adminDao;

	@Resource
	private ShiroService shiroService;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
//		System.out.println("*****************"+principals.getPrimaryPrincipal());
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//simpleAuthorizationInfo.addRole("super");
		simpleAuthorizationInfo.addRole("admin");
		simpleAuthorizationInfo.addStringPermission("product:delete");
		simpleAuthorizationInfo.addStringPermission("product:update");
		//simpleAuthorizationInfo.addStringPermission("product:findAll");
		//用户    角色  权限 -----(资源)  user:add:    /user/add   安装系统
		//queryPermission
		/*List<Permission> list=shiroService.(principals.getPrimaryPrincipal().toString());
		List<Role> roles=shiroService.queryRole(principals.getPrimaryPrincipal().toString());
		if(roles!=null&&roles.size()!=0){
			for (Role role : roles) {
				simpleAuthorizationInfo.addRole(role.getName());
			}
		}
		if(list!=null&&list.size()!=0){
			for (Permission permission : list) {
				simpleAuthorizationInfo.addStringPermission(permission.getName()+":"+true);
			}
		}*/
		return simpleAuthorizationInfo;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
//		System.out.println("用户名====: "+token.getPrincipal());

		Admin admin=adminDao.queryByName(token.getPrincipal().toString());

		if(admin!=null){
			return new SimpleAuthenticationInfo(admin.getName(), admin.getPassword(),
					ByteSourceUtils.bytes(admin.getSalt()),
					this.getName());
		}
		return null;
	}

}
