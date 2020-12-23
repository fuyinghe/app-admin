/*! iCheck v1.0.2 by Damir Sultanov, http://git.io/arlzeA, MIT Licensed */
(function(f){function A(a,b,d){var c=a[0],g=/er/.test(d)?_indeterminate:/bl/.test(d)?n:k,e=d==_update?{checked:c[k],disabled:c[n],indeterminate:"true"==a.attr(_indeterminate)||"false"==a.attr(_determinate)}:c[g];if(/^(ch|di|in)/.test(d)&&!e)x(a,g);else if(/^(un|en|de)/.test(d)&&e)q(a,g);else if(d==_update)for(var f in e)e[f]?x(a,f,!0):q(a,f,!0);else if(!b||"toggle"==d){if(!b)a[_callback]("ifClicked");e?c[_type]!==r&&q(a,g):x(a,g)}}function x(a,b,d){var c=a[0],g=a.parent(),e=b==k,u=b==_indeterminate,
v=b==n,s=u?_determinate:e?y:"enabled",F=l(a,s+t(c[_type])),B=l(a,b+t(c[_type]));if(!0!==c[b]){if(!d&&b==k&&c[_type]==r&&c.name){var w=a.closest("form"),p='input[name="'+c.name+'"]',p=w.length?w.find(p):f(p);p.each(function(){this!==c&&f(this).data(m)&&q(f(this),b)})}u?(c[b]=!0,c[k]&&q(a,k,"force")):(d||(c[b]=!0),e&&c[_indeterminate]&&q(a,_indeterminate,!1));D(a,e,b,d)}c[n]&&l(a,_cursor,!0)&&g.find("."+C).css(_cursor,"default");g[_add](B||l(a,b)||"");g.attr("role")&&!u&&g.attr("aria-"+(v?n:k),"true");
g[_remove](F||l(a,s)||"")}function q(a,b,d){var c=a[0],g=a.parent(),e=b==k,f=b==_indeterminate,m=b==n,s=f?_determinate:e?y:"enabled",q=l(a,s+t(c[_type])),r=l(a,b+t(c[_type]));if(!1!==c[b]){if(f||!d||"force"==d)c[b]=!1;D(a,e,s,d)}!c[n]&&l(a,_cursor,!0)&&g.find("."+C).css(_cursor,"pointer");g[_remove](r||l(a,b)||"");g.attr("role")&&!f&&g.attr("aria-"+(m?n:k),"false");g[_add](q||l(a,s)||"")}function E(a,b){if(a.data(m)){a.parent().html(a.attr("style",a.data(m).s||""));if(b)a[_callback](b);a.off(".i").unwrap();
f(_label+'[for="'+a[0].id+'"]').add(a.closest(_label)).off(".i")}}function l(a,b,f){if(a.data(m))return a.data(m).o[b+(f?"":"Class")]}function t(a){return a.charAt(0).toUpperCase()+a.slice(1)}function D(a,b,f,c){if(!c){if(b)a[_callback]("ifToggled");a[_callback]("ifChanged")[_callback]("if"+t(f))}}var m="iCheck",C=m+"-helper",r="radio",k="checked",y="un"+k,n="disabled";_determinate="determinate";_indeterminate="in"+_determinate;_update="update";_type="type";_click="click";_touch="touchbegin.i touchend.i";
_add="addClass";_remove="removeClass";_callback="trigger";_label="label";_cursor="cursor";_mobile=/ipad|iphone|ipod|android|blackberry|windows phone|opera mini|silk/i.test(navigator.userAgent);f.fn[m]=function(a,b){var d='input[type="checkbox"], input[type="'+r+'"]',c=f(),g=function(a){a.each(function(){var a=f(this);c=a.is(d)?c.add(a):c.add(a.find(d))})};if(/^(check|uncheck|toggle|indeterminate|determinate|disable|enable|update|destroy)$/i.test(a))return a=a.toLowerCase(),g(this),c.each(function(){var c=
f(this);"destroy"==a?E(c,"ifDestroyed"):A(c,!0,a);f.isFunction(b)&&b()});if("object"!=typeof a&&a)return this;var e=f.extend({checkedClass:k,disabledClass:n,indeterminateClass:_indeterminate,labelHover:!0},a),l=e.handle,v=e.hoverClass||"hover",s=e.focusClass||"focus",t=e.activeClass||"active",B=!!e.labelHover,w=e.labelHoverClass||"hover",p=(""+e.increaseArea).replace("%","")|0;if("checkbox"==l||l==r)d='input[type="'+l+'"]';-50>p&&(p=-50);g(this);return c.each(function(){var a=f(this);E(a);var c=this,
b=c.id,g=-p+"%",d=100+2*p+"%",d={position:"absolute",top:g,left:g,display:"block",width:d,height:d,margin:0,padding:0,background:"#fff",border:0,opacity:0},g=_mobile?{position:"absolute",visibility:"hidden"}:p?d:{position:"absolute",opacity:0},l="checkbox"==c[_type]?e.checkboxClass||"icheckbox":e.radioClass||"i"+r,z=f(_label+'[for="'+b+'"]').add(a.closest(_label)),u=!!e.aria,y=m+"-"+Math.random().toString(36).substr(2,6),h='<div class="'+l+'" '+(u?'role="'+c[_type]+'" ':"");u&&z.each(function(){h+=
'aria-labelledby="';this.id?h+=this.id:(this.id=y,h+=y);h+='"'});h=a.wrap(h+"/>")[_callback]("ifCreated").parent().append(e.insert);d=f('<ins class="'+C+'"/>').css(d).appendTo(h);a.data(m,{o:e,s:a.attr("style")}).css(g);e.inheritClass&&h[_add](c.className||"");e.inheritID&&b&&h.attr("id",m+"-"+b);"static"==h.css("position")&&h.css("position","relative");A(a,!0,_update);if(z.length)z.on(_click+".i mouseover.i mouseout.i "+_touch,function(b){var d=b[_type],e=f(this);if(!c[n]){if(d==_click){if(f(b.target).is("a"))return;
A(a,!1,!0)}else B&&(/ut|nd/.test(d)?(h[_remove](v),e[_remove](w)):(h[_add](v),e[_add](w)));if(_mobile)b.stopPropagation();else return!1}});a.on(_click+".i focus.i blur.i keyup.i keydown.i keypress.i",function(b){var d=b[_type];b=b.keyCode;if(d==_click)return!1;if("keydown"==d&&32==b)return c[_type]==r&&c[k]||(c[k]?q(a,k):x(a,k)),!1;if("keyup"==d&&c[_type]==r)!c[k]&&x(a,k);else if(/us|ur/.test(d))h["blur"==d?_remove:_add](s)});d.on(_click+" mousedown mouseup mouseover mouseout "+_touch,function(b){var d=
b[_type],e=/wn|up/.test(d)?t:v;if(!c[n]){if(d==_click)A(a,!1,!0);else{if(/wn|er|in/.test(d))h[_add](e);else h[_remove](e+" "+t);if(z.length&&B&&e==v)z[/ut|nd/.test(d)?_remove:_add](w)}if(_mobile)b.stopPropagation();else return!1}})})}})(window.jQuery||window.Zepto);

(function($){
	var _formdata;
	var model = {
		//创建显示表单
		createView : function($container,options){
			this.createLabel(options).appendTo($container)
			this.createDiv(options).addClass("border-bottom value-control").attr("name",options.field).appendTo($container)
		},
		//创建表单
		createForm : function($container,options,purpose){
			if(!purpose){
				purpose = "form"
			}
			this.createLabel(options).appendTo($container)
			var $input;
			var defaultAttr = {
				"name":options.field,
				"class":"form-control",
				"required" : parseInt(options.isRequired) ? "required" : null,
				"placeholder":options.placeholder,
				"value" : options.defaultValue && purpose != "search" ? options.defaultValue : "",
				"data-lbid" : options.lbId || null
			};
			var formType = purpose == "search" ? options.searchType || options.formType : options.formType
			switch(formType){
				case "text":
					if(options.valueType == "int" || options.valueType == "number"){
						defaultAttr.maxvalue = options.maxValue || null;
						defaultAttr.minvalue = options.minValue || null;
					}else{
						defaultAttr.maxsize = (parseInt(options.maxSize)) ? options.maxSize : null;
						defaultAttr.minsize = (parseInt(options.minSize)) ? options.minSize : null;
						if(options.valueType == "only"){
							defaultAttr.url = options.valueConfig.url;
						}
					}
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","valuetype" : options.valueType == "string" ? null : options.valueType}));
					
					break;
				case "radio":
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"radio"}));
					this.createDiv(options,purpose).append($input).appendTo($container);
					if(options.valueConfig && options.valueConfig.list && options.valueConfig.list.length > 0){
						$.each(options.valueConfig.list, function(index,obj) {
							$input.before($("<input>",{"type":"radio","checked":index == 0 ? true : false,"id": ""/*purpose +"-"+ options.field + "-" + index*/,"name":options.field+"-icheck","class":"icheck","value":obj.value})).before($("<label>",{"class":"label-control ml5 mr15 pointer","for":purpose + "-" + options.field + "-"+index}).text(obj.text))
							if(index==0){$input.val(obj.value)}
						});
					}
					return false;
				case "checkbox":
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"checkbox"}));
					this.createDiv(options,purpose).append($input).appendTo($container);
					if(options.valueConfig && options.valueConfig.list && options.valueConfig.list.length > 0){
						$.each(options.valueConfig.list, function(index,obj) {
							$input.before($("<input>",{"type":"checkbox","id":purpose + "-" + options.field + "-"+index,"name":options.field+"-icheck","class":"icheck","value":obj.value})).before($("<label>",{"class":"label-control ml5 mr15 pointer","for":purpose + "-" + options.field + "-"+index}).text(obj.text))
						});
					}
					return false;
				case "select":
					$input = $("<select>",$.extend(defaultAttr,{"type":"text"}));
					if(options.valueConfig && options.valueConfig.list && options.valueConfig.list.length > 0){
						var obj = {
							list:options.valueConfig.list,
							selector:$input,
							valueKey:options.valueConfig.valueKey,
							textKey:options.valueConfig.textKey,
							selector:$input
						}
						purpose == "search" ? obj.defaultOption = {text:"全部",value:""} : obj.defaultOption = {text:options.valueConfig.defaultText || "请选择",value:options.valueConfig.defaultValue || ""};		
						this.createOptions(obj)
					}else if(options.valueConfig && options.valueConfig.url){
						createOptions = this.createOptions;
						$input.attr("data-asyn","asyn").on("refreshData",function(){
							$.ajax({
								url:options.valueConfig.url,
								suc:function(data){
									var obj = {
										list: options.valueConfig.dataField ? data[options.valueConfig.dataField] : data.list,
										valueKey:options.valueConfig.valueKey,
										textKey:options.valueConfig.textKey,
										selector:$input
									}
									purpose == "search" ? obj.defaultOption = {text:"全部",value:""} : obj.defaultOption = {text:options.valueConfig.defaultText || "请选择",value:options.valueConfig.defaultValue || ""};	
									createOptions(obj);
									if($input.attr("data-value")){
										$input.val($input.attr("data-value")).attr("data-value",null)
									}
								},
								err:function(){
									$input.html('<option value="">数据加载失败</option>')
								}
							});
						}).trigger("refreshData");
					}
					break;
				case "textarea":
					defaultAttr.maxsize = (options.maxSize != "0" && options.maxSize != "") ? options.maxSize : null;
					defaultAttr.minsize = (options.minSize != "0" && options.minSize != "") ? options.minSize : null;
					$input = $("<textarea>",$.extend(defaultAttr,{"type":"textarea"}));
					break;
				case "datetime":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control datetimePicker","readonly":"readonly"}));
					break;
				case "date":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control datePicker","readonly":"readonly"}));
					break;
				case "time":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control timePicker","readonly":"readonly"}));
					break;
				case "advSelect":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control advSelect","readonly":"readonly"}));
					break;
				case "linSelect":
					$input = $("<select>",$.extend(defaultAttr,{"type":"text"}));
					break;
				case "editor":
					$input = $("<textarea>",$.extend(defaultAttr,{"type":"editor"}));
			}
			this.createDiv(options,purpose).append($input).appendTo($container)
		},
		//生成分组标签
		createGroupName:function(options){
			return $("<div>",{class:"col-sm-12 border-title mb15"}).append("<h4>"+options.fieldgroupName+"</h4>")
		},
		//生成label
		createLabel:function(options){
			return $("<label>",{"class":"label-control col-sm-1 mb15"}).text(options.title)
		},
		//生成div
		createDiv:function(options,purpose){
			var feedback = "";
			if(purpose == "form" && (options.isRequired == "1" || options.valueType != "string" || options.minSize || options.maxSize)){
				feedback = " has-feedback";
			}
			return $("<div>",{"class":"col-sm-" + (parseInt(options.cols)-1) + " mb15" + feedback})
		},
		//在select中生成options
		createOptions : function(options){
			options = $.extend({
				list: [],
				valueKey: "value",
				textKey:"text",
				defaultOption:{text:"",value:""},
				selector:null
			}, options);
			var $this = options.selector;
			var html = options.defaultOption ? '<option value="'+options.defaultOption.value+'">&lt;'+options.defaultOption.text+'&gt;</option>' : ''
			$.each(options.list, function(index,obj) {
				html += '<option value="'+obj[options.valueKey]+'">'+obj[options.textKey]+'</option>'
			});
			$this.html(html)
		},
		//创建模态框
		createModal:function(options){
			var $modal = $("<div>",{"class":"modal fade "+options.class,"tabindex":"-1","role":"dialog","aria-labelledby":"myLargeModalLabel"})
			var html = '';
			html += '<div class="modal-dialog modal-lg" role="document">';
			html += '<div class="modal-content">';
			html +=	'<div class="modal-header">';
		    html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>';
		    html += '<h4 class="modal-title" id="myLargeModalLabel">'+options.title+'</h4>';
		    html += '</div>';
		    html += '<div class="modal-body form-horizontal">';
		    html += '</div>';
		    html += '<div class="clearfix"></div>';
		    html += '<div class="modal-footer">';
		    html += '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
		    if(options.hasSaveBtn){
		    	html += '<button type="button" class="btn btn-success save">'+(options.saveBtnText || '保存')+'</button>';
		    }
		    html += '</div>';
			html += '</div>';
			html += '</div>';
			$modal.append(html).appendTo($("body"));
			return $modal;
		},
		//提交容器内表单
		sendForm:function(options){
			if(!$.ver(options.container.find(".form-control[required=required],.form-control[valueType]"))){
				return false;
			}
			options.container.sendForm(options)
		},
		//重置容器内表单
		resetForm:function(container,options){
			container.resetForm()
			container.find("input.icheck").each(function(){
				$(this).iCheck("uncheck");
			})
		},
		//回显，表单赋值
		getFormData:function(_url,_data,$container,modelData,bool,viewDataField){
			var newModelData = {};
			var getTextByValueInList = this.getTextByValueInList;
			$.each(modelData, function(index,obj) {
				newModelData[obj.field] = obj;
			});
			$.ajax({
				url:_url,
				data:_data,
				loadingContainer:$container,
				hasAlert:true,
				suc:function(data){
					if(viewDataField){
						data = data[viewDataField]
					}
					if(bool){
						var data2 = {};
						if(data.rows){
							$.each(data.rows, function(index,obj) {
								data2[obj.englishName] = obj.sxValue;
							});
						}
						data = data2;
					}
					$container.find(".form-control").each(function(){
						var $this = $(this);
						var name = $this.attr("name");
						var value = data[name];
						switch($this.attr("formtype")){
							case "radio":
								$container.find("input.icheck[name="+name+"-icheck][value="+value+"]").iCheck('check');
							break;
							case "checkbox":
								$container.find("input.icheck[name="+name+"-icheck]").iCheck('uncheck');
								if(!$.isNull(value)){
									$.each(value.split(","), function(index,val) {
										$container.find("input.icheck[name="+name+"-icheck][value="+val+"]").iCheck('check');
									});
								}
							break;
						};
						if($this.attr("data-asyn") == "asyn"){
							$this.attr("data-value",value);
							$this.trigger("refreshData");
						}else{
							$this.val(value);	
						}
						
					})
					
					$container.find(".value-control").each(function(){
						//$(this).html(data[newModelData[$(this).attr("name")]["mappingField"] || $(this).attr("name")]);
						//先取映射字段
						var text = data[newModelData[$(this).attr("name")]["mappingField"]];
						//其次在模型list中取
						if(!text && newModelData[$(this).attr("name")].valueConfig){
							text = getTextByValueInList(newModelData[$(this).attr("name")].valueConfig.list,data[$(this).attr("name")],newModelData[$(this).attr("name")].valueConfig.valueKey,newModelData[$(this).attr("name")].valueConfig.textKey)
						}
						//最后取真实值
						if(!text){
							text = data[$(this).attr("name")];
						}
						$(this).html(text)
					})
				}
			})
		},
		getTextByValueInList:function(list,value,valueKey,textKey){
			valueKey = valueKey || "value"; 
			textKey = textKey || "text";
			var text = "";
			if(list){
				$.each(list, function(index,obj) {
					if(obj[valueKey] == value){
						text = obj[textKey];
						return;
					}
				});
				return text;
			}else{
				return null;
			}
		},
		//创建表格显示列
		createColumns:function(options){
			var columns = [];
			var getTextByValueInList = this.getTextByValueInList
			$.each(options, function(index,obj) {
				if(parseInt(obj.visible) > 0){
					columns.push({
						"field":obj.mappingField || obj.field,
						"title":obj.title,
						"align":obj.align || "center",
						"halign":obj.halign || "center",
						"visible":parseInt(obj.visible) == 1 ? true : false,
						"sortable":parseInt(obj.sortable) ? true : false,
						"formatter":function(value,rows){
							if(!obj.mappingField && obj.field && obj.valueConfig && $.isArray(obj.valueConfig.list) && obj.valueConfig.list.length > 0){
								return getTextByValueInList(obj.valueConfig.list,value,obj.valueConfig.valueKey,obj.valueConfig.textKey) || value
							}else{
								return value;
							}
						}
					})
				}
			});
			return columns;
		},
		//表单事件
		events : function($container){
			//raido事件
			$container.find("input.icheck[type=radio]").iCheck({
				radioClass: 'iradio_square-green',
		    	increaseArea: '20%'
			}).on('ifChecked', function(event){
				var value = $(this).val()
				var field = $(this).attr("name").replace("-icheck","")
				$container.find("input[name="+field+"]").val(value)
			});
			//checkbox事件
			$container.find("input.icheck[type=checkbox]").iCheck({
				checkboxClass: 'icheckbox_square-green',
		    	increaseArea: '20%'
			}).on('ifChecked', function(event) {
				var value = $(this).val();
				var field = $(this).attr("name").replace("-icheck","");
				var $input = $container.find("input[name="+field+"]");
				var valueArray = $input.val() == '' ? [] : $input.val().split(',');
				valueArray.push(value);
				$input.val(valueArray.toString());
			}).on('ifUnchecked', function(event) {
				var value = $(this).val();
				var field = $(this).attr("name").replace("-icheck","");
				var $input = $container.find("input[name="+field+"]");
				var valueArray = $input.val().split(",");
				valueArray.splice($.inArray(value, valueArray), 1);
				$input.val(valueArray.toString());
			});
			$container.find("input.datetimePicker").datetimepicker({
				format: 'yyyy-mm-dd hh:ii:00',
				language: 'zh-CN',
				autoclose: true
				})
			$container.find("input.datePicker").datetimepicker({
				format: 'yyyy-mm-dd',
				language: 'zh-CN',
				minView: 2,
				autoclose: true
				})
			$container.find("input.timePicker").datetimepicker({
				format: 'hh:ii:00',
				language: 'zh-CN',
				startView: 1,
				minView: 0,
				autoclose: true
				})
			
		},
		//验证事件
		verEvents:function($container){
			var sucGlyphicon = '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>';
			var errGlyphicon = '<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>';
			$container.find(".has-feedback").each(function() {
				$(this).append(sucGlyphicon);
				$(this).append(errGlyphicon);
			});
			$container.find("select[required=required]").on("change",function(){
				$.ver($(this))
			});
			$container.find("input.form-control,textarea.form-control").each(function(){
				var $this = $(this);
				if($this.attr("required") || $this.attr("valuetype") || $this.attr("maxsize") || $this.attr("minsize")){
					$this.on("blur",function(){
						$.ver($(this))
					})
					$this.on("change",function(){
						$.ver($(this))
					})
				}
			});
		},
		//换行策略：创建方法,判断增长策略,列表,容器
		colsWrap:function(createFun,strategy,list,$container){
			var cols = 0;
			var createGroupName = this.createGroupName;
			$.each(list,function(index,obj){
				if(obj.fieldgroupName && !$container.hasClass("search-form")){
					cols = 0;
					$container.append(createGroupName(obj));
				}
				if(strategy(obj)){
					cols += parseInt(obj.cols);
					if(cols>12){
						$container.append($("<div>",{"class":"clearfix"}));
						createFun($container,obj);
						cols = parseInt(obj.cols);
					}else if(cols==12){
						createFun($container,obj);
						if(index != list.length-1){$container.append($("<div>",{"class":"clearfix"}));}
						cols = 0;
					}else{
						createFun($container,obj);
					}
				}
			})
		},
		//接口地址为string类型，即url地址，则ajax请求，为object（list）类型则直接返回该值
		getModel:function(modelApi,loading,callback){
			if(modelApi){
				if(typeof(modelApi) == "string"){
					$.ajax({
						url:modelApi,
						suc:function(data){
							callback(data.model)
						},
						hasAlert:true,
						loadingContainer:loading
					});
				}else if(typeof(modelApi) == "object" && $.isArray(modelApi)){
					callback(modelApi)
				}
			}else{
				return false;
			}
		},
		enterForSearch:function($search,$table){
			//搜索表单回车事件
			$search.off("keyup",".form-control").on("keyup",".form-control",function(e){
				if(e.which == 13){
					$table.bootstrapTable("refresh",{pageNumber:1})
				}
			})
		}
		
	}
	$.model = model;
	//指定容器（模态框）中生成模型表单
	$.fn.modelForm = function(list){
		if($.isArray(list)){
			var $this = $(this)
			model.colsWrap(function($container,obj){
				if(obj.isOnlyread == "2"){
					model.createView($container,obj)
				}else{
					model.createForm($container,obj)
				}
			},function(obj){
				if(!parseInt(obj.isHidden)){return true;}else{return false;}
			},list,$this.find(".modal-body"));
			model.events($this);
			model.verEvents($this);
		}
	}
	//指定容器（模态框）中生成模型显示框架
	$.fn.modelView = function(list){
		if($.isArray(list)){
			var $this = $(this)
			model.colsWrap(function($container,obj){
				model.createView($container,obj)
			},function(obj){
				if(!parseInt(obj.isHidden)){return true;}else{return false;}
			},list,$this.find(".modal-body"));
		}
	}
	//指定容器（模态框）中生成模型表单
	$.fn.modelSearch = function(list){
		var $this = $(this);
		var $search = $("<div>",{"class":"search-form",})
		$search.prependTo($this)
		if($.isArray(list)){
			model.colsWrap(function($c,obj){
				model.createForm($c,obj,"search")
			},function(obj){
				if(!parseInt(obj.isHidden) && parseInt(obj.search) == 2){return true;}else{return false;}
			},list,$search);
		}
		model.events($search);
		return $search;
	}
	$.fn.modelAdvSearch = function(list){
		var $this = $(this);
		var $advSearch = $("<div>",{"class":"advSearch-form"})
		$advSearch.appendTo($this);
		
		if($.isArray(list)){
			model.colsWrap(function($container,obj){
				model.createForm($container,obj,"search")
			},function(obj){
				if(!parseInt(obj.isHidden) && parseInt(obj.search) == 1){return true;}else{return false;}
			},list,$advSearch);
		}
		$advSearch.append($("<div>",{"class":"clearfix"}))
		return $advSearch;
	}
	$.fn.modelTableList = function(obj){
		var $this = $(this);
		var options = obj.options;
		var $table = $("<table>",{"class":"table"});
		$table.appendTo($this);
		//处理显示列
		var column = model.createColumns(obj.modelData)
		if(options.checkbox){
			column.unshift({checkbox: true})
		}
		if(options.oprate){
			//构造删改查按钮
			var oprateHtml = function(value,row,index){
				html = ""
				if(options.viewBtn(row,index)){
					html += '<button type="button" class="btn btn-success btn-xs view"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> '+options.viewBtnText+'</button>';
				}
				if(options.editBtn(row,index)){
					html += ' <button type="button" class="btn btn-info btn-xs edit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> '+options.editBtnText+'</button>'
				}
				if(options.deleteBtn(row,index)){
					html += ' <button type="button" class="btn btn-danger btn-xs delete"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> '+options.deleteBtnText+'</button>'
				}
				html += options.oprateAdd(row,index) || "";
				return html;
			}
			//绑定默认删/改/查事件
			var oprateEvents = {};
			if(options.viewBtn){
				oprateEvents['click .view'] = function(e,value,row,index){
					obj.viewModal.modal("show");
					obj.viewModal.find(".value-control").empty();
					obj.viewModal.find(".modal-footer button.save").hide();
					obj.viewModal.find("h4.modal-title").text(options.viewModalTitle)
					var queryData = {};
					queryData[options["idKey"]] = row[options.idKey];
					model.getFormData(options.viewApi,queryData,obj.viewModal,obj.modelData,options.contentType === "application/json" && options.method === "post",options.viewDataField);
				}
			}
			if(options.editBtn){
				oprateEvents['click .edit'] = function(e,value,row,index){
					obj.formModal.modal("show");
					obj.formModal.resetForm();
					obj.formModal.find(".modal-footer button.save").show();
					obj.formModal.find("h4.modal-title").text(options.editModalTitle)
					_formdata = {}
					_formdata[options["idKey"]] = row[options.idKey]
					model.getFormData(options.editModelViewApi || options.viewApi,_formdata,obj.formModal,obj.modelData,options.contentType === "application/json" && options.method === "post",options.viewDataField);
					obj.formModal.find("button.save").off("click").on("click",function(){
						model.sendForm({
							url:options.editApi,
							method:options.method,
							contentType:options.contentType,
							container:obj.formModal,
							beforeSend:function(data){
								data = $.extend(data,_formdata);
								return data;
							},
							callback:function(data){
								$table.bootstrapTable("refresh")
							}
						})
						
					})
				}
			}
			if(options.deleteBtn){
				oprateEvents['click .delete'] = function(e,value,row,index){
					var queryData = {};
					queryData[options["idKey"]] = row[options.idKey];
					$.confirm("确定删除当前行？",function(){
						$.ajax({
							url:options.deleteApi,
							data:queryData,
							hasAlert:true,
							suc:function(data){
								$.alert("删除成功！","success",function(){
									$table.bootstrapTable("refresh")
								})
							}
						});
					},function(){},false)
				}
			}
			oprateEvents = $.extend(oprateEvents,options.oprateEventsAdd);
			column.push({
					field:"oprate",
					title:"操作",
					align:"center",
					haligh:"center",
					formatter:oprateHtml,
					events:oprateEvents
			})
		}
		$table.createTable({
			method:options.method,
			url:options.listApi,
			data:options.listData,
			dataField:options.listDataField,
			contentType:options.contentType,
			columns:column,
			showRefresh: options.showRefresh,
			showColumns: options.showColumns,
			pageSize:options.pageSize,
			searchFormList:obj.searchForm,
			pagination:obj.pagination,
			cardView:false
		})
		return $table;
	}
	$.fn.modelManager = function(options){
		var $container = $(this);
		$(this).modelManager.options = options;
		if(!($container && $container.length == 1)){return false;}
		//销毁
		if(options === "destroy"){
			$container.trigger("modelManagerDestroy")
			return $container;
		}
		//获得所选
		if(options === "getSelections"){
			return $container.find("table.table").bootstrapTable('getSelections');
		}
		//默认值赋值
		options = $.extend($.fn.modelManager.defaultSetting,options);
		$container.on("modelDataLoaded",function(){
			$container.endLoading();
		}).loading();
		var modelData,addModelData,editModelData,searchModelData,viewModelData,$search,$advSearch,$table,listConfig;
		//创建新增/编辑/查看模态框
		if(options.oprate){
			var $addModal = options.addModelApi ? model.createModal({"class":"addModal","title":"","hasSaveBtn":"1"}) : null;
			var $formModal = model.createModal({"class":"formModal","title":"","hasSaveBtn":"1"});
			var $viewModal = model.createModal({"class":"viewModal","title":"","hasSaveBtn":"0"});
			//add模态框加载模型
			if(options.addModelApi){
				$container.on("addModelDataLoaded",function(){
					$addModal.modelForm(addModelData)
				})
			}
			//edit模态框加载模型
			if(options.editModelApi){
				$container.on("editModelDataLoaded",function(){
					$formModal.modelForm(editModelData)
				})
			}else{
				$container.on("modelDataLoaded",function(){
					$formModal.modelForm(modelData)
				})
			};
			//view模态框加载模型
			if(options.editModelApi){
				$container.on("viewModelDataLoaded",function(){
					$viewModal.modelView(viewModelData)
				})
			}else{
				$container.on("modelDataLoaded",function(){
					$viewModal.modelView(modelData)
				})
			};
		}
		//search模型加载
		if(options.search){
			if(options.searchModelApi){
				$container.on("searchModelDataLoaded",function(){
					$search = $container.modelSearch(searchModelData);
					$container.trigger("searchComplete");
				})
			}else{
				$container.on("modelDataLoaded",function(){
					$search = $container.modelSearch(modelData);
					$container.trigger("searchComplete");	
				})
			};
		}else{
			$container.on("modelDataLoaded",function(){
				$search = $("<div>",{"class":"search-form"});
				$search.appendTo($container)
				$container.trigger("searchComplete");
			})
		}
		
		//主模型加载完毕
		$container.on("modelDataLoaded",function(){
			//创建表格列表
			if(listConfig && (options.listApi || options.listData)){
				$table = $container.modelTableList(listConfig)
				//搜索表单回车事件
				model.enterForSearch($search,$table);
			}else if(options.listApi || options.listData){
				$container.on("listConfigComplete",function(){
					$table = $container.modelTableList(listConfig)
					//搜索表单回车事件
					model.enterForSearch($search,$table);
				})
			}
		});
		
		//搜索加载完毕，附加按钮
		$container.on("searchComplete",function(){
			//列表参数
			listConfig = {
				"options": options,
				"modelData" : modelData,
				"formModal" : $formModal,
				"viewModal" : $viewModal,
				"searchForm" : $search.find(".form-control")
			}
			$container.trigger("listConfigComplete")
			
			
			var $btnGroup = $("<div>",{"class":"col-sm-12 pull-right"});
			if(options.search){
				//查询按钮
				var $searchBtn = $("<button>",{"class":"btn btn-success pull-right searchBtn"}).text(" " + options.searchBtnText).prepend($("<span>",{"class":"glyphicon glyphicon-search"}));

				//查询按钮事件
				$searchBtn.on("click",function(){
					$table.bootstrapTable("refresh",{pageNumber:1})
				});
				
				if(options.advSearchBtnText){
					//创建高级搜索
					var $advSearch = $search.modelAdvSearch(options.searchModelApi ? searchModelData : modelData);
					var $advSearchBtn = $("<button>",{"class":"btn btn-link pull-right"}).text("[" + options.advSearchBtnText + "]");
					//高级查询按钮事件
					$advSearch = $search.find(".advSearch-form");
					$advSearch.css({height:$advSearch.height()}).hide()
					$advSearchBtn.on("click",function(){
						if($advSearch.is(":hidden")){
							$advSearch.slideDown()
						}else{
							$advSearch.slideUp()
							model.resetForm($advSearch)
						}
					});
					$btnGroup.append($advSearchBtn).append($searchBtn);
				}else{
					$btnGroup.append($searchBtn);
				};
			}
			//按钮组容器
			$btnGroup.appendTo($search);
			$search.append($("<div>",{"class":"clearfix"}));
			
			//增加按钮
			var $addBtn;
			if(options.addBtn){
				$addBtn = $("<button>",{"class":"btn btn-info pull-left"}).text(" " + options.addBtnText).prepend($("<span>",{"class":"glyphicon glyphicon-plus"}));
				$btnGroup.append($addBtn)
				$addBtn.on("click",function(){
					var $modal = options.addModelApi ? $addModal : $formModal
					var list = addModelData || modelData;
					model.resetForm($modal);
					$.each(list, function(index,obj) {
						if(obj.defaultValue && $modal.find(".form-control[name="+obj.field+"]")){
							var name = obj.field,value=obj.defaultValue;
							switch(obj.formType){
								case "radio":
									$modal.find("input.icheck[name="+name+"-icheck][value="+value+"]").iCheck('check');
								break;
								case "checkbox":
									$modal.find("input.icheck[name="+name+"-icheck]").iCheck('uncheck');
									if(!$.isNull(value)){
										$.each(value.split(","), function(index,val) {
											$modal.find("input.icheck[name="+name+"-icheck][value="+val+"]").iCheck('check');
										});
									}
								break;
							}
							$modal.find(".form-control[name="+name+"]").val(value);	
						}
						if(obj.valueConfig && (!obj.valueConfig.list || (obj.valueConfig.list &&obj.valueConfig.list.length == 0 )) && obj.valueConfig.url){
							$modal.find(".form-control[name="+obj.field+"]").attr("data-asyn","asyn").attr("data-value",obj.defaultValue || null).trigger("refreshData")
						}
					});
					$modal.find("h4.modal-title").text(options.addModalTitle);
					$modal.modal("show");
					$modal.find("button.save").off("click").on("click",function(){
						model.sendForm({
							url:options.addApi,
							contentType:options.contentType,
							method:options.method,
							container:$modal,
							callback:function(data){
								$table.bootstrapTable("refresh")
								if(options.addSuccessCallback && $.isFunction(options.addSuccessCallback)){
									options.addSuccessCallback($container);
								}
							}
						})
					})
				})
			}
			
			//处理按钮附加list
			if(options.btnAddList && $.isArray(options.btnAddList)){
				$.each(options.btnAddList, function(index,obj) {
					var $btn = $("<button>",{"class":obj.class,"style":obj.css}).text(" "+obj.text);
					if(obj.icon){
						$("<span>",{"class":obj.icon}).prependTo($btn)
					}
					$btn.appendTo($btnGroup);
					$btn.before("&nbsp;").after("&nbsp;");
					$.each(obj.events, function(key,value) {
						$btn.on(key,value)
					});
				});
			}
			$btnGroup.append($("<div>",{"class":"clearfix"}))
		})
		
		
		
		//请求主模型
		model.getModel(options.modelApi,$container,function(data){
			modelData = data;
			$container.trigger("modelDataLoaded");
		})
		//自定义add模型
		model.getModel(options.addModelApi,null,function(data){
			addModelData = data;
			$container.trigger("addModelDataLoaded");
		})
		//自定义edit模型
		model.getModel(options.editModelApi,null,function(data){
			editModelData = data;
			$container.trigger("editModelDataLoaded");
		})
		//自定义view模型
		model.getModel(options.viewModelApi,null,function(data){
			viewModelData = data;
			$container.trigger("viewModelDataLoaded");
		})
		//自定义search模型
		model.getModel(options.searchModelApi,null,function(data){
			searchModelData = data;
			$container.trigger("searchModelDataLoaded");
		})
		
		//销毁事件,暂时替代方案
		$container.on("modelManagerDestroy",function(){
			if($addModal){
				$addModal.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
				$addModal.remove();
			}
			if($formModal){
				$formModal.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
				$formModal.remove();
			}
			if($viewModal){
				$viewModal.remove();
			}
			$container.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
			$container.empty();
			$container.off();
		});
		return $container;
	}
	
	$.fn.modelManager.defaultSetting = {
			method:"post",
			modelApi:"",//主模型、list模型api，必填；
			listApi:"",//数据接口，必填；
			listData:[],
			listDataField:"rows",
			contentType:"application/x-www-form-urlencoded;charset=UTF-8",
			addApi:"",//add接口；
			editApi:"",//edit接口；
			deleteApi:"",//delete接口；
			viewApi:"",//查询单条接口；
			viewDataField:"",
			addModelApi:null, //增加表单模型api,如果为空，则使用主模型
			editModelApi:null, //修改表单模型api,如果为空，则使用主模型
			editModelViewApi:null,//修改表单模型提供数据回显的api，如果为不设置则取viewApi
			viewModelApi:null, //显示模型api，如果为空，则使用主模型
			searchModelApi:null, //搜索表单模型api,如果为空，则使用主模型
			idKey:"id", //查询/删除/修改依赖主键
			checkbox:false,
			showRefresh: true, //表格是否显示刷新按钮
			showColumns: true, //表格是否显示列选择
			pagination : true, //表格是否显示页码
			search:true, //是否开启查询
			searchBtnText:"查询",//查询按钮文字
			advSearchBtnText:"高级查询",//高级查询按钮文字
			addBtn:true,//是否显示增加按钮
			addBtnText:"增加",//增加按钮文字
			addModalTitle:"增加",//增加模态框标题
			
			viewBtn:function(row,index){
				return true;//每行查看按钮显示策略
			},
			viewModalTitle:"查看",//查看模态框标题
			viewBtnText:"查看",//查看按钮文字
			editBtn:function(row,index){
				return true;//每行修改按钮显示策略
			},
			editModalTitle:"编辑",//修改模态框标题
			editBtnText:"编辑",//编辑按钮文字
			deleteBtn:function(row,index){
				return true;//每行删除按钮显示策略
			},
			deleteBtnText:"删除",
			oprate:true,//是否显示操作列
			oprateAdd:function(row,index){
				return "";//操作列附加HTML
			},
			oprateEventsAdd:{
				//操作列附加事件
				/*使用示例：
					"click .class1":function(e,value,row,index){
					
					},
					"click .class1":function(e,value,row,index){
						
					},
				
				*/
			},
			btnAddList:[
				//按钮附加list
				/*使用示例：
				{
					"class":" btn btn-default",
					"text" : "测试附加按钮",
					"icon" : "",
					"css" : "",
					"events":{
						"click" : function(){
							$.alert("123");
						}
					}
				}
				*/
			],
			pageSize:10,
			modelAddArray:[
			/*
				{
					modelType:"add|edit|view",
					modelApi:"",
					viewApi:"",
					saveApi:"",
					idKey:"",
					modalTitle:"",
					btn:function(row,index){
						
					},
					btnText:"",
					btnClass:"",
					btnIcon:""
				}
			*/
			]
			
		}
})(jQuery)
