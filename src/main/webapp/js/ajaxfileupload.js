/*
封装针对 file/uploadFile2.do上传接口的的方法
1，上传初始化，绑定上传事件
	$.uploadInit({
		//默认值
		fileListContainer : $(".file-list"), //上传成功文件列表容器
		descDir : "temp",                    //上传目录
		url : "../../file/uploadFile2.do",   //上传接口地址
		fileElementId:"upload"               //上传域input[type=file]的ID（必须唯一）
	})

2，获得新上传文件列表json，用于附加到表单数据中一起发送到保存接口，例如formData = $.extend(formData,$.getNewFileList($(".file-list")))
	$.getNewFileList(fileListContainer)      //fileListContainer:上传成功文件列表容器，例如：$(".file-list")
	返回值：json类型
	{
		relativePath:"1,2,3,4",
		newName:"1,2,3,4",
		oldName:"a,b,c,d"
	}
3,载入已上传文件列表（用于修改页面，每个文件后只有删除按钮）
	$.getOldFileList(fileListContainer,fileListArray,function(attrId){})
	//fileListContainer:上传成功文件列表容器，例如：$(".file-list")
	//fileListArray:传入文件列表，jsonlist格式，key值有attrId,newName,oldName-relativePath
	//callback(attrId):有删除动作回调函数，每当有删除动作，将会返回当前被删除文件attrId

4,载入已上传文件列表（用于查看页面，每个文件后只有下载按钮）
	$.getDownloadList(fileListContainer,fileListArray,descDir)
	//fileListContainer:上传成功文件列表容器，例如：$(".file-list")
	//fileListArray:传入文件列表，jsonlist格式，key值有attrId,newName,oldName-relativePath
	//descDir:和上传目录保持一致
*/

jQuery.extend({
	uploadInit:function(options){
		options = $.extend({
			fileListContainer : $(".file-list"),
			descDir : "temp",
			url : "../../file/uploadFile2.do",
			fileElementId:"upload",
			callback:function(){},
			beforeUpload:function(){},
			uploaded:function(){},
			maxSize:20971520,
			type:"file" //图片模式img
		},options);
		$("#"+options.fileElementId).off("change");
		$("#"+options.fileElementId).val("");
		$("#"+options.fileElementId).on("change",function(){
			options.beforeUpload()
			$.startUpload(options)
		})
		/*
		if(options.type == "img"){
			$(document).off("click",".upload-pic img")
			$(document).on("click",".upload-pic img",function(){
				var $img = $(this);
				var $li = $img.parent();
				var $viewImg = $li.parent().siblings(".view-pic");
				if($li.hasClass("hover-pic")){
					$li.removeClass("hover-pic");
					if($viewImg.find("img").length > 0){
						$viewImg.find("img").remove();
					}
				}else{
					$li.siblings(".hover-pic").removeClass("hover-pic")
					$li.addClass("hover-pic")
					if($viewImg.find("img").length > 0){
						$viewImg.find("img").attr("src",$img.attr("src"))
					}else{
						$viewImg.append('<img src="'+$img.attr("src")+'">')
					}
				}
				
			})
		}
		*/
		
		
	},
	startUpload : function(options) {
		options = $.extend({
			fileListContainer : $(".file-list"),
			descDir : "temp",
			url : "../../file/uploadFile2.do",
			fileElementId:"upload",
			callback:function(){},
			beforeSend:function(){},
			complete:function(){}
		},options);
		var ajaxsetting = {
			url: options.url, //用于文件上传的服务器端请求地址
			type: 'post',
			secureuri: false, //是否需要安全协议，一般设置为false
			fileElementId: options.fileElementId, //文件上传域的ID
			dataType: 'json', //返回值类型 一般设置为json
			data: {
				"descDir": options.descDir
			},
			success: function(data, status) //服务器成功响应处理函数
			{
				if(data.code == 200) {
					options.fileListContainer.append('<li class="new-file" data-relativePath="' + data.relativePath + '" data-newName="' + data.newName + '" data-oldName="' + data.oldName + '" ><span>' + data.oldName + '</span> <a href="javascript:;" onclick="$(this).parent().remove()">删除</a></li>')
				} else {
					swal("上传失败", data.message, "error")
				}
				$.uploadInit(options)
				options.callback()
			},
			error: function(data, status, e) //服务器响应失败处理函数
			{
				swal("上传失败", "接口访问失败", "error")
				$.uploadInit(options)
			}
		}
		if($.isIE10plus()){
			//利用html5 FormData对象上传
			var uploadfile = document.getElementById(options.fileElementId).files[0]
			if(uploadfile && uploadfile.size >= options.maxSize){
				swal("上传失败", "请上传小于"+$.renderSize(options.maxSize)+"的文件(当前文件:"+$.renderSize(uploadfile.size)+")", "error")
				return false
			}
			
			var uploading = "";
			
			var formData = new FormData()
			formData.append("descDir",options.descDir)
			formData.append("file",uploadfile)
			ajaxsetting.data = formData
			delete ajaxsetting.secureuri
			delete ajaxsetting.fileElementId
			ajaxsetting.processData=false
  			ajaxsetting.contentType=false 
			var onprogress = function(evt) {
				var loaded = evt.loaded;
				var tot = evt.total;
				var per = Math.floor(100 * loaded / tot);
				uploading.find(".uploading").css({width:per+"%"})
				uploading.find(".uploading-percent").html(per+"%")
			}
			ajaxsetting.xhr = function() {
				var xhr = $.ajaxSettings.xhr();
				if(onprogress && xhr.upload) {
					xhr.upload.addEventListener("progress", onprogress, false);
					return xhr;
				}
			}
			ajaxsetting.beforeSend = function(){
				var uploadingContainer = '<div class="uploading-container" style="display:inline-block;width:100px;padding:1px;border:1px solid #17a68a"><div class="uploading" style="background:#17a68a;height:10px; width:0%;"></div></div>'
				uploading = $('<li class="uploading-file">'+uploadfile.name+'('+$.renderSize(uploadfile.size)+') '+uploadingContainer+' <span class="uploading-percent"></span> <a class="delete-request" href="javascript:;">删除</a></li>')
				uploading.appendTo(options.fileListContainer);
				uploading.on("click",".delete-request",function(){
					$(this).parent().remove();
					uploadRequest.abort()
				})
				$.uploadInit(options)
			}
			ajaxsetting.success = function(data){
				uploading.hide();
				if(data.code == 200) {
					if(options.type == "img"){
						var viewUrl = "../../file/showImg2.do"+"?newName="+data.newName+"&relativePath="+data.relativePath+"&descDir="+options.descDir
						uploading.after('<li class="new-file upload-pic" data-relativePath="' + data.relativePath + '" data-newName="' + data.newName + '" data-oldName="' + data.oldName + '" ><img src="'+viewUrl+'"> <p><a href="javascript:;" onclick="$(this).parent().parent().remove()">删除</a></p></li>')
					}else{
						uploading.after('<li class="new-file" data-relativePath="' + data.relativePath + '" data-newName="' + data.newName + '" data-oldName="' + data.oldName + '" ><span>' + data.oldName + '</span> <a href="javascript:;" onclick="$(this).parent().remove()">删除</a></li>')
					}
					
				} else {
					swal("上传失败", data.message, "error")
				}
				uploading.remove();
				options.callback(data)
			}
			
			var uploadRequest = $.ajax(ajaxsetting)
			
		}else{
			$.ajaxFileUpload(ajaxsetting)
		}
		
		return false;
	},
	renderSize : function(value){
	    if(null==value||value==''){
	        return "0 Bytes";
	    }
	    var unitArr = new Array("Bytes","KB","MB","GB","TB","PB","EB","ZB","YB");
	    var index=0;
	    var srcsize = parseFloat(value);
	    index=Math.floor(Math.log(srcsize)/Math.log(1024));
	    var size =srcsize/Math.pow(1024,index);
	    size=size.toFixed(2);//保留的小数位数
	    return size+unitArr[index];
	},
	getNewFileList:function(fileListContainer){
		var arr1 = []
		var arr2 = []
		var arr3 = []
		fileListContainer.find("li.new-file").each(function(){
			arr1.push($(this).attr("data-relativePath"))
			arr2.push($(this).attr("data-newName"))
			arr3.push($(this).attr("data-oldName"))
		})
		var obj = {
			relativePath:arr1.join(","),
			newName:arr2.join(","),
			oldName:arr3.join(",")
		}
		return obj;
	},
	getOldFileList:function(fileListContainer,fileListArray,callback,type){
		fileListContainer.empty();
		fileListContainer.off("click");
		fileListContainer.on("click",".delete",function(){
			callback($(this).parent().attr("data-attrId"))
			$(this).parent().remove();
		});
		
		if($.isArray(fileListArray) && fileListArray.length > 0){
			$.each(fileListArray, function(index,data) {
				if(type == "img"){
					var viewUrl = "../../file/showImg2.do"+"?attrId=" + data.attrId + "&descDir=" + data.relativePath.split("/")[0]
					var $li = $('<li class="old-file upload-pic" data-attrId="'+data.attrId+'" data-relativePath="' + data.relativePath + '" data-newName="' + data.newName + '" data-oldName="' + data.oldName + '" ><img alt="'+data.oldName+'" title="'+data.addTime+'" src="'+viewUrl+'"> <p class="delete"><a href="javascript:;" onclick="">删除</a></p></li>')
					$li.appendTo(fileListContainer);
					$li.find("img").on("click",function(){
						var $img = $(this);
						var $li = $img.parent();
						var $viewImg = $li.parent().siblings(".view-pic");
						if($li.hasClass("hover-pic")){
							$li.removeClass("hover-pic");
							if($viewImg.find("img").length > 0){
								$viewImg.find("img").remove();
							}
						}else{
							$li.siblings(".hover-pic").removeClass("hover-pic")
							$li.addClass("hover-pic")
							if($viewImg.find("img").length > 0){
								$viewImg.find("img").attr("src",$img.attr("src"))
							}else{
								$viewImg.append('<img src="'+$img.attr("src")+'">')
							}
						}
						
					})
					//fileListContainer.append('<li class="old-file upload-pic" data-attrId="'+data.attrId+'" data-relativePath="' + data.relativePath + '" data-newName="' + data.newName + '" data-oldName="' + data.oldName + '" ><img src="'+viewUrl+'"> <p class="delete"><a href="javascript:;" onclick="">删除</a></p></li>')
					
					
				
				}else{
					fileListContainer.append('<li class="old-file" data-attrId="'+data.attrId+'" data-relativePath="' + data.relativePath + '" data-newName="' + data.newName + '" data-oldName="' + data.oldName + '" ><span>' + data.oldName + '</span> <a class="delete" href="javascript:;" onclick="">删除</a></li>')
				}
			});
		}
	},
	getDownloadList:function(fileListContainer,fileListArray,descDir,type){
		fileListContainer.empty();
		var downloadApi = "../../file/downloadFile2.do"
		if($.isArray(fileListArray) && fileListArray.length > 0){
			$.each(fileListArray, function(index,data) {
				if(type == "img"){
					var viewUrl = "../../file/showImg2.do"+"?attrId=" + data.attrId + "&descDir=" + descDir
					//fileListContainer.append('<li class="upload-pic" data-attrId="'+data.attrId+'" data-newName="' + data.newName + '"><a class="download" href="'+viewUrl+'" target="_blank"><img src="'+viewUrl+'"></a></li>')
					var $li = $('<li class="upload-pic" data-attrId="'+data.attrId+'" data-newName="' + data.newName + '"><a class="download" href="'+viewUrl+'" target="_blank"><img alt="'+data.oldName+'" title="'+data.addTime+'" src="'+viewUrl+'"></a></li>')
					
					if(data.addTime){
						var time = new Date(data.addTime)
						var m = time.getMonth() + 1;
						var d = time.getDate();
						var h = time.getHours();
						var mm = time.getMinutes();
						$li.append("<p class='show'><a>"+m+"-"+d+" "+h+":"+mm+"</a></p>")
					}
					
					$li.appendTo(fileListContainer);
					
					
					$li.find("a").on("click",function(){
						
						var $img = $(this).find("img");
						var $li = $(this).parent();
						var $viewImg = $li.parent().siblings(".view-pic");
						if($viewImg){
							if($li.hasClass("hover-pic")){
								$li.removeClass("hover-pic");
								if($viewImg.find("img").length > 0){
									$viewImg.find("img").remove();
								}
							}else{
								$li.siblings(".hover-pic").removeClass("hover-pic")
								$li.addClass("hover-pic")
								if($viewImg.find("img").length > 0){
									$viewImg.find("img").attr("src",$img.attr("src"))
								}else{
									$viewImg.append('<img src="'+$img.attr("src")+'">')
								}
							}
							return false;
						}
					
						
						
						
					})
				
				}else{
					var downloadUrl = downloadApi+"?attrId="+data.attrId+"&newName="+data.newName+"&descDir="+descDir
					fileListContainer.append('<li class="download-file" data-attrId="'+data.attrId+'" data-newName="' + data.newName + '"><span>' + data.oldName + '</span> <a class="download" href="'+downloadUrl+'" target="_blank">下载</a></li>')
				}
			});
		}
	},
	
	isIE:function(){
		return /msie/.test(navigator.userAgent.toLowerCase());
	},
	isIE10plus:function(){
		var userAgent = navigator.userAgent.toLowerCase();
		return (/msie 10/.test(userAgent) || /msie 11/.test(userAgent) || !/msie/.test(userAgent));
	},
	
    handleError: function (s, xhr, status, e) {
        // If a local callback was specified, fire it  
        if (s.error) {
            s.error.call(s.context || s, xhr, status, e);
        }

        // Fire the global callback  
        if (s.global) {
            (s.context ? jQuery(s.context) : jQuery.event).trigger("ajaxError", [xhr, s, e]);
        }
    },
    createUploadIframe: function (id, uri) {

        var frameId = 'jUploadFrame' + id;

        if (window.ActiveXObject) {
            if ($.isIE) {
                io = document.createElement('iframe');
                io.id = frameId;
                io.name = frameId;
            }
            else
            {

                var io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
                if (typeof uri == 'boolean') {
                    io.src = 'javascript:false';
                }
                else if (typeof uri == 'string') {
                    io.src = uri;
                }
            }
        }
        else {
            var io = document.createElement('iframe');
            io.id = frameId;
            io.name = frameId;
        }
        io.style.position = 'absolute';
        io.style.top = '-1000px';
        io.style.left = '-1000px';

        document.body.appendChild(io);

        return io;
    },
    ajaxUpload: function (s, xml) {
        //if((fromFiles.nodeType&&!((fileList=fromFiles.files)&&fileList[0].name)))  

        var uid = new Date().getTime(), idIO = 'jUploadFrame' + uid, _this = this;
        var jIO = $('<iframe name="' + idIO + '" id="' + idIO + '" style="display:none">').appendTo('body');
        var jForm = $('<form action="' + s.url + '" target="' + idIO + '" method="post" enctype="multipart/form-data" style="display:none"></form>').appendTo('body');
        var oldElement = $('#' + s.fileElementId);
        var newElement = $(oldElement).clone();
        $(oldElement).attr('id', 'jUploadFile' + uid);
        $(oldElement).before(newElement);
        $(oldElement).appendTo(jForm);

        this.remove = function () {
            if (_this !== null) {
                jNewFile.before(jOldFile).remove();
                jIO.remove(); jForm.remove();
                _this = null;
            }
        }
        this.onLoad = function () {

            var data = $(jIO[0].contentWindow.document.body).text();

            try {

                if (data != undefined) {
                    data = eval('(' + data + ')');
                    try {

                        if (s.success)
                            s.success(data, status);

                        // Fire the global callback  
                        if (s.global)
                            jQuery.event.trigger("ajaxSuccess", [xml, s]);
                        if (s.complete)
                            s.complete(data, status);
                        xml = null;
                    } catch (e) {

                        status = "error";
                        jQuery.handleError(s, xml, status, e);
                    }

                    // The request was completed  
                    if (s.global)
                        jQuery.event.trigger("ajaxComplete", [xml, s]);
                    // Handle the global AJAX counter  
                    if (s.global && ! --jQuery.active)
                        jQuery.event.trigger("ajaxStop");

                    // Process result  

                }
            } catch (ex) {
                alert(ex.message);
            };
        }
        this.start = function () { jForm.submit(); jIO.load(_this.onLoad); };
        return this;

    },
    createUploadForm: function (id, url, fileElementId, data) {
        //create form     
        var formId = 'jUploadForm' + id;
        var fileId = 'jUploadFile' + id;
        var form = jQuery('<form  action="' + url + '" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');
        if (data) {
            for (var i in data) {
                jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
            }
        }

        var oldElement = jQuery('#' + fileElementId);
        var newElement = jQuery(oldElement).clone();
        jQuery(oldElement).attr('id', fileId);
        jQuery(oldElement).before(newElement);
        jQuery(oldElement).appendTo(form);

        //set attributes  
        jQuery(form).css('position', 'absolute');
        jQuery(form).css('top', '-1200px');
        jQuery(form).css('left', '-1200px');
        jQuery(form).appendTo('body');
        return form;
    },
    ajaxFileUpload: function (s) {
        // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout      
        // Create the request object  
        var xml = {};
        s = jQuery.extend({}, jQuery.ajaxSettings, s);
        if (window.ActiveXObject) {
            var upload = new jQuery.ajaxUpload(s, xml);
            upload.start();

        } else {
            var id = new Date().getTime();
            var form = jQuery.createUploadForm(id, s.url, s.fileElementId, (typeof (s.data) == 'undefined' ? false : s.data));
            var io = jQuery.createUploadIframe(id, s.secureuri);
            var frameId = 'jUploadFrame' + id;
            var formId = 'jUploadForm' + id;
            // Watch for a new set of requests  
            if (s.global && !jQuery.active++) {
                jQuery.event.trigger("ajaxStart");
            }
            var requestDone = false;

            if (s.global)
                jQuery.event.trigger("ajaxSend", [xml, s]);
            // Wait for a response to come back  
            var uploadCallback = function (isTimeout) {
                var io = document.getElementById(frameId);

                try {
                    if (io.contentWindow) {
                        xml.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
                        xml.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument : io.contentWindow.document;

                    } else if (io.contentDocument) {
                        xml.responseText = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
                        xml.responseXML = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument : io.contentDocument.document;
                    }
                } catch (e) {
                    jQuery.handleError(s, xml, null, e);
                }
                if (xml || isTimeout == "timeout") {
                    requestDone = true;
                    var status;
                    try {
                        status = isTimeout != "timeout" ? "success" : "error";
                        // Make sure that the request was successful or notmodified  
                        if (status != "error") {
                            // process the data (runs the xml through httpData regardless of callback)  
                            var data = jQuery.uploadHttpData(xml, s.dataType);
                            // If a local callback was specified, fire it and pass it the data  

                            if (s.success)
                                s.success(data, status);

                            // Fire the global callback  
                            if (s.global)
                                jQuery.event.trigger("ajaxSuccess", [xml, s]);
                            if (s.complete)
                                s.complete(data, status);

                        } else
                            jQuery.handleError(s, xml, status);
                    } catch (e) {
                        status = "error";
                        jQuery.handleError(s, xml, status, e);
                    }

                    // The request was completed  
                    if (s.global)
                        jQuery.event.trigger("ajaxComplete", [xml, s]);
                    // Handle the global AJAX counter  
                    if (s.global && ! --jQuery.active)
                        jQuery.event.trigger("ajaxStop");

                    // Process result  
                    jQuery(io).unbind();

                    setTimeout(function () {
                        try {
                            jQuery(io).remove();
                            jQuery(form).remove();

                        } catch (e) {
                            jQuery.handleError(s, xml, null, e);
                        }

                    }, 100);

                    xml = null;

                }
            };
            // Timeout checker  
            if (s.timeout > 0) {
                setTimeout(function () {
                    // Check to see if the request is still happening  
                    if (!requestDone) uploadCallback("timeout");
                }, s.timeout);
            }

            try {

                var form = jQuery('#' + formId);
                jQuery(form).attr('action', s.url);
                jQuery(form).attr('method', 'POST');
                jQuery(form).attr('target', frameId);

                if (form.encoding) {
                    jQuery(form).attr('encoding', 'multipart/form-data');
                }
                else {
                    jQuery(form).attr('enctype', 'multipart/form-data');
                }


                jQuery(form).submit();

            } catch (e) {
                jQuery.handleError(s, xml, null, e);
            }

            jQuery('#' + frameId).load(uploadCallback);
            return { abort: function () { } };

        }
    },

    uploadHttpData: function (r, type) {

        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
        // If the type is "script", eval it in global context  
        if (type == "script")
            jQuery.globalEval(data);
        // Get the JavaScript object, if JSON is used.  
        if (type == "json") {

            //eval("data = " + $(data).html());//默认方式，在ie下不好使
            //data = jQuery.parseJSON(jQuery(data).text());//第一次修改后，ie7、8、9、10可用，ie11不可用
            try {
                data = jQuery.parseJSON(data); //第二次修改，ie全兼容
            } catch (e) {
                data = jQuery.parseJSON(jQuery(data).text());
            }

        }
        // evaluate scripts within html  
        if (type == "html")
            jQuery("<div>").html(data).evalScripts();

        return data;
    }
});