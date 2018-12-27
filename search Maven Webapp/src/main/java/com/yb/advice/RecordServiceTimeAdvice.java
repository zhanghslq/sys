package com.yb.advice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.yb.entity.Result;
import com.yb.service.ShiroService;


/**
 * 自定义的通知类  
 * 作用:用来记录业务方法的运行时长
 * @author Administrator
 *
 */
@Component("recordServiceTimeAdvice")
public class RecordServiceTimeAdvice implements MethodInterceptor{
	/**
	 * 环绕通知来判断调用者的IP是否合法，不合法的话，就中断请求，不再继续，可以的话，就继续
	 * 参数: invocation  
	 * 返回值: 当前目标方法的返回值 
	 */
	@Resource
	private ShiroService shiroService;
	@Override
	public Object invoke(MethodInvocation invocation){
		Object[] arguments = invocation.getArguments();
		if(arguments[0]!=null){
			String obj=null;
			obj=shiroService.queryTokenById(arguments[0].toString());
			if(obj!=null){
				if(invocation.getMethod().getName().equals(obj)){
					Object proceed=null;
					try {
						proceed = invocation.proceed();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return proceed;
				}else {
					return new Result(1, "请求权限不足");
				}
			}else{
				return new Result(1, "用户非法");
			}
		}else {
			return new Result(1, "无权访问");
		}
		
	}
}

