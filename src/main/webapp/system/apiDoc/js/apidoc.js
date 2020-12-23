var apiurl = {
	menu    : "../../apiMenu/queryApidocMenuList.do",
	list    : "../../apidoc/queryApidocByPid.do",
	view    : "../../apidoc/queryApidocById.do",
	add     : "../../apidoc/saveApidocData.do",
	update  : "../../apidoc/updateApidocData.do",
	delete  : "../../apidoc/deleteApidocDataById.do"
},nice,niceResizeSt,docJson = {},fieldValueCache = {};

//路由
function Router() {
    this.routes = {};
    this.currentUrl = '';
}
Router.prototype.route = function(callback) {
    this.routes = callback || function(){};
};
Router.prototype.refresh = function() {
    this.currentUrl = location.hash.slice(1) || '/';
    this.routes(this.currentUrl);
};
Router.prototype.init = function() {
    window.addEventListener('load', this.refresh.bind(this), false);
    window.addEventListener('hashchange', this.refresh.bind(this), false);
}
window.Router = new Router();
window.Router.init();

Router.route(function(path) {
	var hash;
	var arr = path.split("/");
	switch(arr[1]){
		case "":
			window.location.href="#/default"
		break;
		case "default":
			defaultPage();
			menuManagerClose();
			/*if(!$("canvas")){
				canvas();
			}*/
		break;
		case "view":
			view(arr[2]);
			menuManagerClose();
		break;
		case "add":
			add(arr[2]);
			menuManagerClose();
		break;
		case "edit":
			edit(arr[2]);
			menuManagerClose();
		break;
		case "delete":
			deleteById(arr[2]);
			menuManagerClose();
		break;
		case "menu":
			menuManagerOpen();
		break;
	}
});
function menuManagerOpen(){
	menuManager();
	$(".menu-manager").addClass("open");
	$(".api-doc").addClass("scale");
	$("body").addClass("modal-open");
	$(".menu-manager .close-menu-menager").on("click",function(){
		window.history.back();
		leftMenu();
	})
}
function menuManagerClose(){
	$(".menu-manager").removeClass("open");
	$(".api-doc").removeClass("scale");
	$("body").removeClass("modal-open");
	$("#menu-manager").modelManager("destroy");
	$(".menu-manager .close-menu-menager").off("click");
}

$(function(){
	$(".customFromField,.customGetField").click(function(){
		$.alert("未实现，请配合testApi工具使用。")
	})
	
	//缓存字段对应说明
	if(localStorage.getItem("fieldValueCache")){
		try{
			fieldValueCache = $.strToJson(localStorage.getItem("fieldValueCache"));
		}catch(e){
			fieldValueCache = {};
		}
	}else{
		fieldValueCache = {};
	}
	//缓存字段
	$(".add-doc table.fromDataFieldDesc tbody,.add-doc table.getDataFieldDesc tbody").on("change","textarea.form-control.field-desc",function(){
		$this = $(this);
		$tr = $this.parent().parent();
		fieldValueCache[$tr.find("td:eq(0)").text()] = $this.val();
		fieldValueCache[getLastField($tr.find("td:eq(0)").text())] = $this.val();
		localStorage.setItem("fieldValueCache",$.jsonToStr(fieldValueCache));
	})
	
	
	$("#saveForm").on("click",function(){
		saveForm();
	})
	$("#cancel").on("click",function(){
		closeAdd();
		window.history.back();
	})
	
	//左侧菜单点击事件
	$(document).on("click",".left-menu ul li dl dt",function(){
		var dl = $(this).parent()
		if(!dl.hasClass("loaded") && !dl.hasClass("loading") && !dl.hasClass("open")){
			dl.addClass("loading").addClass("open").find("dd").remove();
			dl.find(".glyphicon-menu-down").toggleClass("glyphicon-menu-up");
			var dd = $("<dd><a href='javascript:;'>加载中...</dd>").appendTo(dl)
			niceResize()
			$.ajax({
				url:apiurl.list,
				data:{pid:dl.attr("data-id")},
				suc:function(data){
					dd.remove();
					dl.removeClass("loading");
					if(data.list.length > 0){
						dl.addClass("loaded");
						$.each(data.list,function(index,obj){
							dl.append('<dd data-id="'+obj.id+'"><a href="#/view/'+obj.id+'">'+obj.name+'</a></dd>')
						})
					}else{
						dl.append('<dd class="none"><a href="javascript:;"><无></a></dd>')
					}
					niceResize()
				},
				hasAlert:true
			})
		}else{
			dl.toggleClass("open");
			dl.find(".glyphicon-menu-down").toggleClass("glyphicon-menu-up");
			niceResize()
		}
	})
	
	
	leftMenu();
	
	autoFormatData("from",$("[name=fromData]"),$("table.fromDataFieldDesc"));
	autoFormatData("get",$("[name=getData]"),$("table.getDataFieldDesc"));
	
	
	
})

function leftMenu(){
	$.ajax({
		url:apiurl.menu,
		suc:function(data){
			$(".left-menu ul").empty();
			if($.isArray(data.apiMenuList) && data.apiMenuList.length>0){
				$.each(data.apiMenuList,function(index,obj){
					if(obj.pid == "0"){
						$(".left-menu ul").append('<li data-id="'+obj.sid+'"><h3>'+obj.name+'</h3></li>')
					}else{
						$(".left-menu ul li[data-id="+obj.pid+"]").append('<dl data-id="'+obj.sid+'"><dt><span class="name">'+obj.name+'</span><span class="glyphicon glyphicon-menu-down"></span><a class="add" href="#/add/'+obj.sid+'"><span class="glyphicon glyphicon-plus"></span></a></dt></dl>')
					}
				})
				nice = $(".left-menu").niceScroll({
					horizrailenabled: false
				})
			}
		},
		hasAlert:true,
		loadingContainer:$("body")
	})
}
function niceResize(timeout){
	if(timeout == null){
		timeout = 300
	}
	niceResizeSt = setTimeout(function(){
		nice.resize();
	},timeout)
}


function saveForm(){
	if($.trim($(".add-doc input[name=apiName]").val()) == ""){
		swal('','接口名称不能为空','error')
		return false;
	}
	updateDocJson()
	var _url = docJson.id ? apiurl.update : apiurl.add;
	$.ajax({
		type:"post",
		url:_url,
		data:docJson,
		success:function(data){
			if(data.id){
				$(".left-menu").find("dl[data-id="+docJson.pid+"]").append('<dd data-id="'+data.id+'"><a href="#/view/'+data.id+'">'+docJson.name+'</a></dd>').find("dd.none").remove();
			}else{
				$(".left-menu").find("dd[data-id="+docJson.id+"] a").html(docJson.name)
			}
			swal({
				title:"",
				text:"保存成功",
				type:"success",
			},function(isConfirm){
				if(isConfirm){
					window.location.hash = "/view/"+(data.id || docJson.id)	
				}
			})
		},
		hasAlert:true,
		loadingContainer:$("body")
	})
}

function updateDocJson(){
	docJson.name = $(".add-doc [name=apiName]").val();
	docJson.url = $(".add-doc [name=apiUrl]").val()
	var config = {};
	$(".add-doc .form-group").not(".table-container,.form-group-lg").find("input,select,textarea").each(function(){
		config[$(this).attr("name")] = $(this).val();
	})
	config.fromDataDesc = [];
	config.getDataDesc = [];
	$(".add-doc table.fromDataFieldDesc tbody tr").each(function(){
		var row = [];
		row.push($(this).find("td:eq(0)").text());
		row.push($(this).find("td:eq(1) input[name=isRequired]").is(":checked") ? "1" : "0");
		row.push($(this).find("td:eq(2) textarea").val());
		fieldValueCache[getLastField($(this).find("td:eq(0)").text())] = $(this).find("td:eq(2) textarea").val()
		fieldValueCache[$(this).find("td:eq(0)").text()] = $(this).find("td:eq(2) textarea").val()
		config.fromDataDesc.push(row);
	})
	$(".add-doc table.getDataFieldDesc tbody tr").each(function(){
		var row = [];
		row.push($(this).find("td:eq(0)").text());
		row.push($(this).find("td:eq(1) textarea").val());
		fieldValueCache[getLastField($(this).find("td:eq(0)").text())] = $(this).find("td:eq(1) textarea").val()
		fieldValueCache[$(this).find("td:eq(0)").text()] = $(this).find("td:eq(1) textarea").val()
		config.getDataDesc.push(row);
	})
	docJson.config = $.jsonToStr(config);
	localStorage.setItem("fieldValueCache",$.jsonToStr(fieldValueCache))
}
function getLastField(str){
	var arr = str.split(".")
	return arr[arr.length - 1]
}

function resetAddForm(){
	$(".add-doc .form-control").val("")
	$(".add-doc tbody").empty()
}
function add(pid){
	resetAddForm();
	if(docJson.id){
		delete docJson.id;
	}
	docJson.pid = pid;
	openAdd();
}
function deleteById(id){
	$.ajax({
		type:"post",
		url:apiurl.delete,
		data:{"id":id},
		suc:function(data){
			$(".left-menu").find("li dl dd[data-id="+id+"]").remove();
			swal("","删除成功！","success")
			window.location.hash = "";
		},
		hasAlert:true,
		loadingContainer:$("body")
	});
	
}
function defaultPage(){
	closeView();
	closeAdd();
}
function openAdd(){
	$("body,.add-doc").addClass("open")
}
function closeAdd(){
	resetAddForm();
	$("body,.add-doc").removeClass("open")
}
function openView(){
	closeAdd();
	resetView();
	$("body,.view-doc").addClass("open")
}
function closeView(){
	$("body,.view-doc").removeClass("open")
}
function resetView(){
	$(".view-doc .value,.view-doc .fromDataFieldDesc tbody,.view-doc .getDataFieldDesc tbody").empty();
}
function edit(id){
	openAdd();
	resetAddForm();
	$.ajax({
		url:apiurl.view,
		data:{"id":id},
		suc:function(data){
			docJson = data.apidoc
			docJson.config = $.strToJson(docJson.config)
			console.dir(docJson);
			$(".add-doc .form-control").each(function(){
				var key = $(this).attr("name")
				$(this).val(docJson[key] || docJson.config[key])
			})
			$(".add-doc [name=apiName]").val(docJson.name);
			
			$.each(docJson.config.fromDataDesc,function(index,obj){
				var html = "";
				html += '<tr>';
				html += '<td>'+obj[0]+'</td>';
				html += '<td><input name="isRequired" type="checkbox" '+(obj[1] == "1" ? 'checked="checked"' : '')+' value="是"></td>';
				html += '<td><textarea class="form-control field-desc">'+obj[2]+'</textarea></td>';
				html += '<td><button class="btn btn-danger delete-field" data-field="'+obj[0]+'">删除</button></td>'
				html += '</tr>';
				$(".add-doc .fromDataFieldDesc tbody").append(html)
			})
			$.each(docJson.config.getDataDesc,function(index,obj){
				var html = "";
				html += '<tr>';
				html += '<td>'+obj[0]+'</td>';
				html += '<td><textarea class="form-control field-desc">'+obj[1]+'</textarea></td>';
				html += '<td><button class="btn btn-danger delete-field" data-field="'+obj[0]+'">删除</button></td>'
				html += '</tr>';
				$(".add-doc .getDataFieldDesc tbody").append(html)
			})
			$(".add-doc [name=getData],.add-doc [name=fromData]").each(function(){
				$(this).focus();
			})
			$("#saveForm").focus();
		},
		hasAlert:true,
		loadingContainer:$("body")
	});
	
}
function view(id){
	closeAdd();
	openView();
	$(".view-doc a.edit-doc").attr("href","#/edit/"+id)
	$(".view-doc a.delete-doc").attr("href","#/delete/"+id)
	$.ajax({
		url:apiurl.view,
		data:{"id":id},
		suc:function(data){
			docJson = data.apidoc;
			docJson.config = $.strToJson(data.apidoc.config);
			$(".view-doc .value").each(function(){
				var key = $(this).attr("name");
				$(this).html(docJson[key] || docJson.config[key]);
			})
			
			$.each(docJson.config.fromDataDesc,function(index,obj){
				var html = "";
				html += '<tr>';
				html += '<td>'+obj[0]+'</td>';
				html += '<td>'+(obj[1] == "1" ? "是" : "否")+'</td>';
				html += '<td>'+obj[2]+'</td>';
				html += '</tr>';
				$(".view-doc .fromDataFieldDesc tbody").append(html)
			})
			$.each(docJson.config.getDataDesc,function(index,obj){
				var html = "";
				html += '<tr>';
				html += '<td>'+obj[0]+'</td>';
				html += '<td>'+obj[1]+'</td>';
				html += '</tr>';
				$(".view-doc .getDataFieldDesc tbody").append(html)
			})
			
			
		},
		hasAlert:true,
		loadingContainer:$("body")
		
	})
}
function autoFormatData(type,$textarea,$table){
	var oldDataStr,jsonData;
	$table.on("click",".delete-field",function(){
		var field = $(this).attr("data-field").split(".");
		var n = field.length;
		if(n == 1){
			if(arrayField(field[0]) != false){
				try{
					delete jsonData[arrayField(field[0])]
				}catch(e){
						
				}
				$(this).parents("tr").siblings("tr").each(function(index){
					if($(this).find("td:eq(0)").text().indexOf(field[0]+".") == 0){
						$(this).remove()
					}
				})
			}else{
				try{
					delete jsonData[field[0]];
				}catch(e){
						
				}
			}
			oldDataStr = JSON.stringify(jsonData, null, 2);
			$textarea.val(oldDataStr);
			
		}else if(n == 2){
			if(arrayField(field[0]) != false){
				for(n = 0; n<jsonData[arrayField(field[0])].length; n++){
					try{
						delete jsonData[arrayField(field[0])][n][field[1]];
					}catch(e){
						
					}
					oldDataStr = JSON.stringify(jsonData, null, 2);
					$textarea.val(oldDataStr);
				}
			}else{
				try{
					delete jsonData[field[0]][field[1]];
				}catch(e){
					
				}
				oldDataStr = JSON.stringify(jsonData, null, 2);
				$textarea.val(oldDataStr);
			}
		}else{
			swal("","删除多级参数自动修改源码未实现,请自行在源码中修改,并选择不生成","warning");
		}
		$(this).parents("tr").remove();
	})
	function arrayField(str){
		if(str.indexOf("[]") > 0){
			return str.replace("[]","");
		}else{
			return false;
		}
	}
	$textarea.on("focus",function(){
		if($.isNull($(this).val())){
			oldDataStr = "";
			return false;
		};
		try{
			jsonData = $.strToJson($(this).val());
		}catch(e){
			jsonData = "";
			return false;
		}
		oldDataStr = JSON.stringify(jsonData, null, 2);
	})
	$textarea.on("blur",function(){
		var $ta = $(this)
		if($.isNull($(this).val())){
			return false;
		}else if(oldDataStr != "" && oldDataStr != $(this).val()){
			
			swal({
				title:"是否重新生成参数说明表格",
				text:"检测到您修改了数据源码，如果重新生成表格，表格中的数据将会清空，不生成择保留修改",
				type:"info",
				showConfirmButton:true,
				showCancelButton:true,
				confirmButtonText:"确定重新生成",
				cancelButtonText:"不生成",
				closeOnConfirm:false,
			},function(isConfirm){
				if(isConfirm){
					autoCreateTabel($ta)
				}
			})
		}else{
			autoCreateTabel($ta)
		}
		
	})
	
	function autoCreateTabel($ta){
		try{
			jsonData = $.strToJson($ta.val());
		}catch(e){
			swal("","请求数据非json类型","error");
			jsonData = "";
			return false;
		}
		if(typeof(jsonData) == "object" && oldDataStr != JSON.stringify(jsonData, null, 2)){
			$table.find("tbody").empty();
			//简化json，去除无效数据
			jsonData = simplifyObject(jsonData)
			
			$.each(jsonData, function(key,value) {
				if(value != null){
					var html = objToHTML(key,value,type);
					$table.find("tbody").append(html);
				}else{
					delete jsonData[key];
				}
			});
			oldDataStr = JSON.stringify(jsonData, null, 2);
			$textarea.val(oldDataStr);	
		}
		swal.close();
	}
	function simplifyObject(obj){
		if(typeof obj == "object" && obj != null){
			if($.isArray(obj)){
				return [simplifyObject(obj[0])]
			}else{
				$.each(obj,function(key,value){
					if(value == null){
						delete obj[key]
					}else{
						obj[key] = simplifyObject(obj[key])
					}
				})
				return obj;
			}
		}else{
			return obj;
		}
	}
	function objToHTML(key,value,type){
		var html = "";
		if($.isArray(value)){
			key = key+"[]";
		}
		html += '<tr data-field="'+key+'">';
		html += '<td>'+key+'</td>';
		if(type == "from"){
			html += '<td class="text-center"><input name="isRequired" type="checkbox" value="是"></td>';
		}
		html += '<td><textarea class="form-control field-desc">'+valueByField(key)+'</textarea></td>';
		html += '<td><button class="btn btn-danger delete-field" data-field="'+key+'">删除</button></td>';
		html += '</tr>';
		if($.isArray(value) && value[0] && typeof(value[0]) == "object"){
			$.each(value[0], function(key2,value2){
				if(value2 != null){
					html += objToHTML(key+"."+key2,value2,type);
				}
			})
		}else if(value != null && !$.isArray(value) && typeof(value) == "object"){
			$.each(value, function(key2,value2){
				html += objToHTML(key+"."+key2,value2,type);
			})
		}
		return html;
	}
	function valueByField(field){
		var valueList = {
			errcode:"错误码",
			errmsg:"错误信息描述",
			pageNo:"页码",
			pageSize:"每页条数",
			totalCount:"总条数",
			totalPage:"总页数"
		}
		valueList = $.extend(valueList,fieldValueCache);
		return valueList[field] || valueList[getLastField(field)] || "";
	}
}


function menuManager(){
	var modelData = [
	  {
	    "field": "pid",
	    "mappingField": "pName",
	    "title": "上级菜单",
	    "defaultValue": "",
	    "isRequired": "1",
	    "valueType": "",
	    "maxValue": "",
	    "minValue": "",
	    "maxSize": "",
	    "minSize": "",
	    "formType": "select",
	    "isHidden": "0",
	    "cols": "6",
	    "visible": "1",
	    "isOnlyread": "0",
	    "sortable": "0",
	    "search": "2",
	    "searchType": "",
	    "placeholder": "",
	    "valueConfig": {
	      "url": "../../apiMenu/queryApidocMenuList.do?pid=0",
	      "valueKey": "sid",
	      "textKey": "name",
	      "dataField":"apiMenuList",
	      "defaultText":"一级菜单",
	      "defaultValue":"0",
	      "enable": 0,
	      "list": [],
	      "nextSelectField": "",
	      "nextSelectUrl": "",
	      "nextSelectQueryKey": "id"
	    }
	  },
	  {
	    "field": "name",
	    "mappingField": "",
	    "title": "菜单名称",
	    "defaultValue": "",
	    "isRequired": "1",
	    "valueType": "",
	    "maxValue": "",
	    "minValue": "",
	    "maxSize": "",
	    "minSize": "",
	    "formType": "text",
	    "isHidden": "0",
	    "cols": "6",
	    "visible": "1",
	    "isOnlyread": "0",
	    "sortable": "0",
	    "search": "2",
	    "searchType": "",
	    "placeholder": "",
	    "valueConfig": {
	      "url": "",
	      "valueKey": "value",
	      "textKey": "text",
	      "enable": 0,
	      "list": [],
	      "nextSelectField": "",
	      "nextSelectUrl": "",
	      "nextSelectQueryKey": "id"
	    }
	  }
	];
	$("#menu-manager").modelManager({
		modelApi:modelData,
		idKey:"sid",
		listDataField:"apiMenuList",
		viewDataField:"apiMenu",
		listApi:"../../apiMenu/queryApidocMenuListPage.do",
		viewApi:"../../apiMenu/queryApidocMenuById.do",
		addApi:"../../apiMenu/saveApidocMenuData.do",
		deleteApi:"../../apiMenu/deleteApidocMenuDataById.do",
		editApi:"../../apiMenu/updateApidocMenuData.do",
		advSearchBtnText:"",
		addSuccessCallback:function($container){
			$container.find(".search-form .form-control[data-asyn=asyn]").trigger("refreshData");
		}
	});
}