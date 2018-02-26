var crossfit = window.crossfit || {};
/*
 * 注册命名空间
 * 使用示例：
 * var myObj = crossfit.namespace("cfer.user");
 * 
 * myObj.myClass = function(){
 * 		this.x = 1;
 *      this.y = 1;
 *      this.test = function(){
 *      	alert(this.x);
 *      }
 * }
 * 
 * 用法：
 * 
 * var obj = new crossfit.cfer.user.myClass();
 * obj.test();
 * 
 * 效果：
 * 输出alert（1）；
 */
crossfit.namespace = function(ns) {
	if (!ns || !ns.length) {
	   return null;
	}
	var levels = ns.split(".");
	var nsobj = crossfit;
	//如果申请的命名空间是在crossfit下的，则必须忽略它，否则就成了crossfit.crossfit了
	for (var i=(levels[0] == "crossfit") ? 1 : 0; i<levels.length; ++i) {
		//如果当前命名空间下不存在，则新建一个关联数组。
		nsobj[levels[i]] = nsobj[levels[i]] || {};
		nsobj = nsobj[levels[i]];
	}
	//返回所申请命名空间的一个引用；
	return nsobj;
};
