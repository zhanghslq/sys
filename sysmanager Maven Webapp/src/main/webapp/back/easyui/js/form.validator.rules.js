   /**
    * 
 	* 扩展默认验证规则
 	*
 	*/
$.extend($.fn.validatebox.defaults.rules,{
	//用户名
	uname:{
		validator:function(value,param){
			 return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
		},
		message:"用户名只能包含字母,数字,下划线,空格!!!"
	},
	//密码
	pwd:{
		validator:function(value,param){
			return /^[a-zA-Z0-9_ ]{1,}$/.test(value);					
		},
		message:"密码只能是字母,数字,下划线,空格!!!",
	},
	//确认密码
	cofirmPwd:{
		validator:function(value,param){
			return value == $(param[0]).val();					
		},
		message:"两次输入的密码不一致!!!"
	},
	//邮箱的验证
	email:{
		validator:function(value){
			return /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value);
		},
		message:"邮箱的格式不正确!!!"
	},
	//验证有手机号
	phone:{
		validator:function(value){
			return 	/^1(3|4|5|7|8)\d{9}$/.test(value);				
		},
		message:"手机号不正确!!!"
	},
	//银行卡号
	bankNumber:{
		validator:function(value){
			return /^(\d{16}|\d{19})$/.test(value);
		},
		message:"银行卡号输入错误!!!"
	},
	//qq号
	qq:{
		validator:function(value){
			return /^[1-9]\d{4,10}$/.test(value);
		},
		message:"qq号码不正确!!!"
	},
	//保留两位小数
	yue:{
		validator:function(value){
			return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
		},
		message:"请保留两位小数!!!"
	},
	//身份证号
	idCode:{
		validator:function(value,param){
		  return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
		},
		message:"请输入正确的身份证号!!!"
	},
	//电话号码
	 tel:{
		validator:function(value,param){
		  return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
		},
		message:"电话号码不正确!!!"
	},
	//数字
	 number: {
		validator: function (value, param) {
			return /^[0-9]+.?[0-9]*$/.test(value);
		},
		message: "只能输入数字!!!"
	},
	//中文
	CHS:{
		validator: function (value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message: "只能输入中文!!!"
	},
	//邮编
	zipCode:{
		validator: function (value, param) {
			return /^[0-9]\d{5}$/.test(value);
		},
		message: "邮政编码输入错误!!!"
	},
	//英文
	ens:{// 验证英语
		validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
		},
		message : "只能输入英文!!!"
	},
	//ip地址
	ip:{
		validator : function(value) {
		    return /\d+\.\d+\.\d+\.\d+/.test(value);
		},
		message : "IP地址不正确!!!"
	},
	//图片结尾
	image:{
		validator:function(value){
			return /.*(.jpg|.png|.gif)$/.test(value);
		},
		message:"青选择以图片结尾的文件!!!"
	}
});