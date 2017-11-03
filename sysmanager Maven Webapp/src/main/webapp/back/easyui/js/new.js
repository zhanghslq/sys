(function($) { 
	/** 
	* jQuery EasyUI 1.4 --- 功能扩展 
	* 
	* Copyright (c) 2009-2015 RCM 
	* 
	* 新增 validatebox 校验规则 
	* 
	*/
	$.extend($.fn.validatebox.defaults.rules, {  
	    /*必须和某个字段相等*/
	    equalTo: {
	        validator:function(value,param){
	            return $(param[0]).val() == value;
	        },
	        message:'字段不匹配'
	    }           
	}); 
	})(jQuery);