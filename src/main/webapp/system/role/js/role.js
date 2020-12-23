$(function(){
	$("#role-manager").modelManager({
		modelApi:[
			{"field":"mc","mappingField":"","title":"角色名称","defaultValue":"","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"text","isHidden":"0","cols":"4","visible":"1","isOnlyread":"0","sortable":"0","search":"2","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},
			{"field":"jsDm","mappingField":"","title":"角色代码","defaultValue":"","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"text","isHidden":"0","cols":"4","visible":"1","isOnlyread":"0","sortable":"0","search":"0","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},
			{"field":"xh","mappingField":"","title":"序号","defaultValue":"0","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"text","isHidden":"0","cols":"4","visible":"1","isOnlyread":"0","sortable":"0","search":"0","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},
			{"field":"zt","mappingField":"","title":"状态","defaultValue":"1","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"select","isHidden":"0","cols":"4","visible":"1","isOnlyread":"0","sortable":"0","search":"0","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[{"value":"1","text":"使用中"},{"value":"0","text":"未使用"}],},"lbId":"T_"}
		],
		listApi:"../../js/queryJSListPage.do",
		addApi:"../../js/saveJS.do",
		deleteApi:"../../js/deleteJs.do",
		editApi:"../../js/updateJS.do",
		viewApi:"../../js/queryJSListPage.do",
		viewDataField:"rows",
		idKey:"jsId",
		viewBtn:false,
		oprateAdd:function(){
			var html = ' <button class="btn btn-success btn-xs menu-config"><span class="glyphicon glyphicon-menu-hamburger"></span> 配菜单</button>';
			html += ' <button class="btn btn-primary btn-xs user-list"><span class="glyphicon glyphicon-th-list"></span> 用户列表</button>';
			return html;
		},
		oprateEventsAdd:{
			"click .menu-config":function(e,value,row,index){
				menuConfig(row.jsId)
			},
			"click .user-list":function(e,value,row,index){
				userList(row.jsId,row.mc)
			},
		},
		advSearchBtnText:""
	})
})

function menuConfig(jsId){
	var $menuConfig = $("#menu-config");
	var menuModel = [
		{"field":"pId","mappingField":"sjMc","title":"父级菜单","defaultValue":"","isRequired":"0","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"select","isHidden":"0","cols":"5","visible":"1","isOnlyread":"0","sortable":"0","search":"2","searchType":"","placeholder":"","valueConfig":{"url":"../../gn/findChildGNByKey.do?gnId=-1","valueKey":"gnId","textKey":"mc","dataField":"rows","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},
		{"field":"mc","mappingField":"","title":"菜单名称","defaultValue":"","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"text","isHidden":"0","cols":"5","visible":"1","isOnlyread":"0","sortable":"0","search":"2","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},
	];
	$menuConfig.modal("show");
	$menuConfig.find(".unSelected-menu").modelManager("destroy").modelManager({
		modelApi:menuModel,
		listApi:"../../js/findUnSelectGNByJsId.do?jsId="+jsId,
		idKey:"gnId",
		addBtn:false,
		viewBtn:false,
		editBtn:false,
		deleteBtn:false,
		oprateAdd:function(value,row,index){
			return '<button class="btn btn-xs btn-success add-menu"><span class="glyphicon glyphicon-arrow-right"></span> 添加</button>';
		},
		oprateEventsAdd:{
			"click .add-menu":function(e,value,row,index){
				$.ajax({
					url:"../../js/addUnSelectMenu.do",
					data:{
						"jsId":jsId,
						"gnId":row.gnId
					},
					suc:function(data){
						$menuConfig.find(".unSelected-menu").modelManager("refreshList");
						$menuConfig.find(".selected-menu").modelManager("refreshList");
					},
					hasAlert:true,
					loadingContainer:$menuConfig.find(".modal-body")
				});
			}
		},
		advSearchBtnText:"",
		onModelLoaded:function(obj){
			obj.searchForm.find("label.label-control").removeClass("col-sm-1").addClass("col-sm-2")
		},
		formatter:{
			"pId":function(value,row,index){
				if(row.pId == "-1"){
					return "<一级菜单>"
				}else{
					return row.sjMc
				}
				
			}
		},
	});
	$menuConfig.find(".selected-menu").modelManager("destroy").modelManager({
		modelApi:menuModel,
		listApi:"../../js/findSelectGNByJsId.do?jsId="+jsId,
		idKey:"gnId",
		addBtn:false,
		viewBtn:false,
		editBtn:false,
		deleteBtn:false,
		advSearchBtnText:"",
		oprateAdd:function(value,row,index){
			return '<button class="btn btn-xs btn-danger delete-menu"><span class="glyphicon glyphicon-arrow-left"></span> 删除</button>';
		},
		oprateEventsAdd:{
			"click .delete-menu":function(e,value,row,index){
				$.ajax({
					url:"../../js/deleteSelectMenu.do",
					data:{
						"jsId":jsId,
						"gnId":row.gnId
					},
					suc:function(data){
						$menuConfig.find(".unSelected-menu").modelManager("refreshList");
						$menuConfig.find(".selected-menu").modelManager("refreshList");
					},
					hasAlert:true,
					loadingContainer:$menuConfig.find(".modal-body")
				});
			}
		},
		onModelLoaded:function(obj){
			obj.searchForm.find("label.label-control").removeClass("col-sm-1").addClass("col-sm-2")
		},
		formatter:{
			"pId":function(value,row,index){
				if(row.pId == "-1"){
					return "<一级菜单>"
				}else{
					return row.sjMc
				}
				
			}
		},
	})
}
function userList(jsId,mc){
	var $userList = $("#user-list");
	$userList.find("h4.modal-title").html("["+mc+"]用户列表")
	$userList.modal("show")
	var userModel = [
		//{"field":"yhId","mappingField":"","title":"用户ID","defaultValue":"","isRequired":"0","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"text","isHidden":"1","cols":"4","visible":"0","isOnlyread":"0","sortable":"0","search":"0","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","dataField":"rows","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},
		{"field":"xm","mappingField":"","title":"用户名称","defaultValue":"","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"text","isHidden":"0","cols":"4","visible":"1","isOnlyread":"0","sortable":"0","search":"2","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[],"nextSelectField":"","nextSelectUrl":"","nextSelectQueryKey":"id"},"lbId":"T_"},				
		{"field":"zt","mappingField":"","title":"状态","defaultValue":"1","isRequired":"1","valueType":"","maxValue":"","minValue":"","maxSize":"","minSize":"","formType":"select","isHidden":"0","cols":"4","visible":"1","isOnlyread":"0","sortable":"0","search":"0","searchType":"","placeholder":"","valueConfig":{"url":"","valueKey":"value","textKey":"text","enable":0,"list":[{"value":"1","text":"使用中"},{"value":"0","text":"未使用"}],},"lbId":"T_"}
	];
	$userList.find(".modal-body").modelManager("destroy").modelManager({
		modelApi:userModel,
		listApi:"../../js/findSelectdeYhByJsId.do?jsId="+jsId,
		deleteApi:"../../js/deleteSelectedRole.do?jsId="+jsId,
		idKey:"yhId",
		addBtn:false,
		viewBtn:false,
		editBtn:false,
		advSearchBtnText:false,
		btnAddList:[
			{
				"class":" btn btn-info",
				"text" : "增加用户",
				"icon" : "glyphicon glyphicon-plus",
				"css" : "",
				"events":{
					"click" : function(){
						userPickerLoaded(function(){
							userPicker(function(info){
								$.ajax({
									url:"../../js/addUnSelectRole.do",
									data:{
										"yhId":info.yhId,
										"jsId":jsId
									},
									suc:function(data){
										$.alert(data.errmsg,"success");
										$("#user-manager").modelManager("refreshList")
									},
									complete:function(){
										$("body").addClass("modal-open")
									},
									hasAlert:true,
									loadingContainer:$("body")
								})
								
							},jsId)
						})
					}
				}
			}
		],
		
		
	})
}
function userPickerLoaded(callback){
	if(window.$rolePicker){
		callback();		
	}else{
		$.ajax({
			url:"../user/user-picker.html",
			success:function(data){
				$("body").append(data);
				callback();
			}
		});
	}
}
