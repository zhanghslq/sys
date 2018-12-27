package com.yb.shiro.realm;

import java.util.List;

import javax.annotation.Resource;

import com.yb.service.StationService;
import org.apache.shiro.SecurityUtils;
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
import com.yb.entity.Permission;
import com.yb.entity.Role;
import com.yb.service.ShiroService;
import com.yb.util.ByteSourceUtils;

/**
 *自定义realm
 * @author Administrator
 */
public class CustomerRealm extends AuthorizingRealm {
	@Resource
	private AdminDao adminDao;

	@Resource
	private ShiroService shiroService;

	@Resource
	private StationService stationService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		/**
		 * 授权的时候首先得到的就是用户名称，然后利用用户名进行查询，现在需要根据用户名进行查授权的油站
		 *
		 *
		 * 然后把授权的油站添加到资源里面，当做资源（相当于 假的资源），为了功能
		 */
		List<Permission> list=shiroService.queryPermission(principals.getPrimaryPrincipal().toString());
		List<Role> roles=shiroService.queryRole(principals.getPrimaryPrincipal().toString());
		List<String> stationId = stationService.getStationId(principals.getPrimaryPrincipal().toString());
		if(stationId!=null){
			if(stationId.size()==0){

			}else {
				for (String id : stationId) {
					simpleAuthorizationInfo.addStringPermission(id);
				}
			}
		}

		if(roles!=null&&roles.size()!=0){
			for (Role role : roles) {
				if(role!=null){
					simpleAuthorizationInfo.addRole(role.getName());
				}
			}
		}
		if(list!=null&&list.size()!=0){
			for (Permission permission : list) {
				if(permission!=null){
					simpleAuthorizationInfo.addStringPermission(permission.getName());
				}
			}
		}
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		Admin admin=adminDao.queryByName(token.getPrincipal().toString());

		if(admin!=null){
			return new SimpleAuthenticationInfo(admin.getName(), admin.getPassword(),
					ByteSourceUtils.bytes(admin.getSalt()),
					this.getName());
		}
		return null;
	}

}
