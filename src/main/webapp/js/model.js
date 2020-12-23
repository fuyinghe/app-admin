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
/* bootstrap-switch - v3.3.4 ，https://bttstrp.github.io/bootstrap-switch*/
(function(a,b){if('function'==typeof define&&define.amd)define(['jquery'],b);else if('undefined'!=typeof exports)b(require('jquery'));else{b(a.jquery),a.bootstrapSwitch={exports:{}}.exports}})(this,function(a){'use strict';function c(j,k){if(!(j instanceof k))throw new TypeError('Cannot call a class as a function')}var d=function(j){return j&&j.__esModule?j:{default:j}}(a),e=Object.assign||function(j){for(var l,k=1;k<arguments.length;k++)for(var m in l=arguments[k],l)Object.prototype.hasOwnProperty.call(l,m)&&(j[m]=l[m]);return j},f=function(){function j(k,l){for(var n,m=0;m<l.length;m++)n=l[m],n.enumerable=n.enumerable||!1,n.configurable=!0,'value'in n&&(n.writable=!0),Object.defineProperty(k,n.key,n)}return function(k,l,m){return l&&j(k.prototype,l),m&&j(k,m),k}}(),g=d.default||window.jQuery||window.$,h=function(){function j(k){var l=this,m=1<arguments.length&&void 0!==arguments[1]?arguments[1]:{};c(this,j),this.$element=g(k),this.options=g.extend({},g.fn.bootstrapSwitch.defaults,this._getElementOptions(),m),this.prevOptions={},this.$wrapper=g('<div>',{class:function(){var o=[];return o.push(l.options.state?'on':'off'),l.options.size&&o.push(l.options.size),l.options.disabled&&o.push('disabled'),l.options.readonly&&o.push('readonly'),l.options.indeterminate&&o.push('indeterminate'),l.options.inverse&&o.push('inverse'),l.$element.attr('id')&&o.push('id-'+l.$element.attr('id')),o.map(l._getClass.bind(l)).concat([l.options.baseClass],l._getClasses(l.options.wrapperClass)).join(' ')}}),this.$container=g('<div>',{class:this._getClass('container')}),this.$on=g('<span>',{html:this.options.onText,class:this._getClass('handle-on')+' '+this._getClass(this.options.onColor)}),this.$off=g('<span>',{html:this.options.offText,class:this._getClass('handle-off')+' '+this._getClass(this.options.offColor)}),this.$label=g('<span>',{html:this.options.labelText,class:this._getClass('label')}),this.$element.on('init.bootstrapSwitch',this.options.onInit.bind(this,k)),this.$element.on('switchChange.bootstrapSwitch',function(){for(var n=arguments.length,o=Array(n),p=0;p<n;p++)o[p]=arguments[p];!1===l.options.onSwitchChange.apply(k,o)&&(l.$element.is(':radio')?g('[name="'+l.$element.attr('name')+'"]').trigger('previousState.bootstrapSwitch',!0):l.$element.trigger('previousState.bootstrapSwitch',!0))}),this.$container=this.$element.wrap(this.$container).parent(),this.$wrapper=this.$container.wrap(this.$wrapper).parent(),this.$element.before(this.options.inverse?this.$off:this.$on).before(this.$label).before(this.options.inverse?this.$on:this.$off),this.options.indeterminate&&this.$element.prop('indeterminate',!0),this._init(),this._elementHandlers(),this._handleHandlers(),this._labelHandlers(),this._formHandler(),this._externalLabelHandler(),this.$element.trigger('init.bootstrapSwitch',this.options.state)}return f(j,[{key:'setPrevOptions',value:function(){this.prevOptions=e({},this.options)}},{key:'state',value:function(l,m){return'undefined'==typeof l?this.options.state:this.options.disabled||this.options.readonly||this.options.state&&!this.options.radioAllOff&&this.$element.is(':radio')?this.$element:(this.$element.is(':radio')?g('[name="'+this.$element.attr('name')+'"]').trigger('setPreviousOptions.bootstrapSwitch'):this.$element.trigger('setPreviousOptions.bootstrapSwitch'),this.options.indeterminate&&this.indeterminate(!1),this.$element.prop('checked',!!l).trigger('change.bootstrapSwitch',m),this.$element)}},{key:'toggleState',value:function(l){return this.options.disabled||this.options.readonly?this.$element:this.options.indeterminate?(this.indeterminate(!1),this.state(!0)):this.$element.prop('checked',!this.options.state).trigger('change.bootstrapSwitch',l)}},{key:'size',value:function(l){return'undefined'==typeof l?this.options.size:(null!=this.options.size&&this.$wrapper.removeClass(this._getClass(this.options.size)),l&&this.$wrapper.addClass(this._getClass(l)),this._width(),this._containerPosition(),this.options.size=l,this.$element)}},{key:'animate',value:function(l){return'undefined'==typeof l?this.options.animate:this.options.animate===!!l?this.$element:this.toggleAnimate()}},{key:'toggleAnimate',value:function(){return this.options.animate=!this.options.animate,this.$wrapper.toggleClass(this._getClass('animate')),this.$element}},{key:'disabled',value:function(l){return'undefined'==typeof l?this.options.disabled:this.options.disabled===!!l?this.$element:this.toggleDisabled()}},{key:'toggleDisabled',value:function(){return this.options.disabled=!this.options.disabled,this.$element.prop('disabled',this.options.disabled),this.$wrapper.toggleClass(this._getClass('disabled')),this.$element}},{key:'readonly',value:function(l){return'undefined'==typeof l?this.options.readonly:this.options.readonly===!!l?this.$element:this.toggleReadonly()}},{key:'toggleReadonly',value:function(){return this.options.readonly=!this.options.readonly,this.$element.prop('readonly',this.options.readonly),this.$wrapper.toggleClass(this._getClass('readonly')),this.$element}},{key:'indeterminate',value:function(l){return'undefined'==typeof l?this.options.indeterminate:this.options.indeterminate===!!l?this.$element:this.toggleIndeterminate()}},{key:'toggleIndeterminate',value:function(){return this.options.indeterminate=!this.options.indeterminate,this.$element.prop('indeterminate',this.options.indeterminate),this.$wrapper.toggleClass(this._getClass('indeterminate')),this._containerPosition(),this.$element}},{key:'inverse',value:function(l){return'undefined'==typeof l?this.options.inverse:this.options.inverse===!!l?this.$element:this.toggleInverse()}},{key:'toggleInverse',value:function(){this.$wrapper.toggleClass(this._getClass('inverse'));var l=this.$on.clone(!0),m=this.$off.clone(!0);return this.$on.replaceWith(m),this.$off.replaceWith(l),this.$on=m,this.$off=l,this.options.inverse=!this.options.inverse,this.$element}},{key:'onColor',value:function(l){return'undefined'==typeof l?this.options.onColor:(this.options.onColor&&this.$on.removeClass(this._getClass(this.options.onColor)),this.$on.addClass(this._getClass(l)),this.options.onColor=l,this.$element)}},{key:'offColor',value:function(l){return'undefined'==typeof l?this.options.offColor:(this.options.offColor&&this.$off.removeClass(this._getClass(this.options.offColor)),this.$off.addClass(this._getClass(l)),this.options.offColor=l,this.$element)}},{key:'onText',value:function(l){return'undefined'==typeof l?this.options.onText:(this.$on.html(l),this._width(),this._containerPosition(),this.options.onText=l,this.$element)}},{key:'offText',value:function(l){return'undefined'==typeof l?this.options.offText:(this.$off.html(l),this._width(),this._containerPosition(),this.options.offText=l,this.$element)}},{key:'labelText',value:function(l){return'undefined'==typeof l?this.options.labelText:(this.$label.html(l),this._width(),this.options.labelText=l,this.$element)}},{key:'handleWidth',value:function(l){return'undefined'==typeof l?this.options.handleWidth:(this.options.handleWidth=l,this._width(),this._containerPosition(),this.$element)}},{key:'labelWidth',value:function(l){return'undefined'==typeof l?this.options.labelWidth:(this.options.labelWidth=l,this._width(),this._containerPosition(),this.$element)}},{key:'baseClass',value:function(){return this.options.baseClass}},{key:'wrapperClass',value:function(l){return'undefined'==typeof l?this.options.wrapperClass:(l||(l=g.fn.bootstrapSwitch.defaults.wrapperClass),this.$wrapper.removeClass(this._getClasses(this.options.wrapperClass).join(' ')),this.$wrapper.addClass(this._getClasses(l).join(' ')),this.options.wrapperClass=l,this.$element)}},{key:'radioAllOff',value:function(l){if('undefined'==typeof l)return this.options.radioAllOff;var m=!!l;return this.options.radioAllOff===m?this.$element:(this.options.radioAllOff=m,this.$element)}},{key:'onInit',value:function(l){return'undefined'==typeof l?this.options.onInit:(l||(l=g.fn.bootstrapSwitch.defaults.onInit),this.options.onInit=l,this.$element)}},{key:'onSwitchChange',value:function(l){return'undefined'==typeof l?this.options.onSwitchChange:(l||(l=g.fn.bootstrapSwitch.defaults.onSwitchChange),this.options.onSwitchChange=l,this.$element)}},{key:'destroy',value:function(){var l=this.$element.closest('form');return l.length&&l.off('reset.bootstrapSwitch').removeData('bootstrap-switch'),this.$container.children().not(this.$element).remove(),this.$element.unwrap().unwrap().off('.bootstrapSwitch').removeData('bootstrap-switch'),this.$element}},{key:'_getElementOptions',value:function(){return{state:this.$element.is(':checked'),size:this.$element.data('size'),animate:this.$element.data('animate'),disabled:this.$element.is(':disabled'),readonly:this.$element.is('[readonly]'),indeterminate:this.$element.data('indeterminate'),inverse:this.$element.data('inverse'),radioAllOff:this.$element.data('radio-all-off'),onColor:this.$element.data('on-color'),offColor:this.$element.data('off-color'),onText:this.$element.data('on-text'),offText:this.$element.data('off-text'),labelText:this.$element.data('label-text'),handleWidth:this.$element.data('handle-width'),labelWidth:this.$element.data('label-width'),baseClass:this.$element.data('base-class'),wrapperClass:this.$element.data('wrapper-class')}}},{key:'_width',value:function(){var l=this,m=this.$on.add(this.$off).add(this.$label).css('width',''),n='auto'===this.options.handleWidth?Math.round(Math.max(this.$on.width(),this.$off.width())):this.options.handleWidth;return m.width(n),this.$label.width(function(o,p){return'auto'===l.options.labelWidth?p<n?n:p:l.options.labelWidth}),this._handleWidth=this.$on.outerWidth(),this._labelWidth=this.$label.outerWidth(),this.$container.width(2*this._handleWidth+this._labelWidth),this.$wrapper.width(this._handleWidth+this._labelWidth)}},{key:'_containerPosition',value:function(){var l=this,m=0<arguments.length&&void 0!==arguments[0]?arguments[0]:this.options.state,n=arguments[1];this.$container.css('margin-left',function(){var o=[0,'-'+l._handleWidth+'px'];return l.options.indeterminate?'-'+l._handleWidth/2+'px':m?l.options.inverse?o[1]:o[0]:l.options.inverse?o[0]:o[1]})}},{key:'_init',value:function(){var l=this,m=function(){l.setPrevOptions(),l._width(),l._containerPosition(),setTimeout(function(){if(l.options.animate)return l.$wrapper.addClass(l._getClass('animate'))},50)};if(this.$wrapper.is(':visible'))return void m();var n=window.setInterval(function(){if(l.$wrapper.is(':visible'))return m(),window.clearInterval(n)},50)}},{key:'_elementHandlers',value:function(){var l=this;return this.$element.on({'setPreviousOptions.bootstrapSwitch':this.setPrevOptions.bind(this),'previousState.bootstrapSwitch':function(){l.options=l.prevOptions,l.options.indeterminate&&l.$wrapper.addClass(l._getClass('indeterminate')),l.$element.prop('checked',l.options.state).trigger('change.bootstrapSwitch',!0)},'change.bootstrapSwitch':function(n,o){n.preventDefault(),n.stopImmediatePropagation();var p=l.$element.is(':checked');l._containerPosition(p),p===l.options.state||(l.options.state=p,l.$wrapper.toggleClass(l._getClass('off')).toggleClass(l._getClass('on')),!o&&(l.$element.is(':radio')&&g('[name="'+l.$element.attr('name')+'"]').not(l.$element).prop('checked',!1).trigger('change.bootstrapSwitch',!0),l.$element.trigger('switchChange.bootstrapSwitch',[p])))},'focus.bootstrapSwitch':function(n){n.preventDefault(),l.$wrapper.addClass(l._getClass('focused'))},'blur.bootstrapSwitch':function(n){n.preventDefault(),l.$wrapper.removeClass(l._getClass('focused'))},'keydown.bootstrapSwitch':function(n){!n.which||l.options.disabled||l.options.readonly||(37===n.which||39===n.which)&&(n.preventDefault(),n.stopImmediatePropagation(),l.state(39===n.which))}})}},{key:'_handleHandlers',value:function(){var l=this;return this.$on.on('click.bootstrapSwitch',function(m){return m.preventDefault(),m.stopPropagation(),l.state(!1),l.$element.trigger('focus.bootstrapSwitch')}),this.$off.on('click.bootstrapSwitch',function(m){return m.preventDefault(),m.stopPropagation(),l.state(!0),l.$element.trigger('focus.bootstrapSwitch')})}},{key:'_labelHandlers',value:function(){var l=this;this.$label.on({click:function(o){o.stopPropagation()},'mousedown.bootstrapSwitch touchstart.bootstrapSwitch':function(o){l._dragStart||l.options.disabled||l.options.readonly||(o.preventDefault(),o.stopPropagation(),l._dragStart=(o.pageX||o.originalEvent.touches[0].pageX)-parseInt(l.$container.css('margin-left'),10),l.options.animate&&l.$wrapper.removeClass(l._getClass('animate')),l.$element.trigger('focus.bootstrapSwitch'))},'mousemove.bootstrapSwitch touchmove.bootstrapSwitch':function(o){if(null!=l._dragStart){var p=(o.pageX||o.originalEvent.touches[0].pageX)-l._dragStart;o.preventDefault(),p<-l._handleWidth||0<p||(l._dragEnd=p,l.$container.css('margin-left',l._dragEnd+'px'))}},'mouseup.bootstrapSwitch touchend.bootstrapSwitch':function(o){if(l._dragStart){if(o.preventDefault(),l.options.animate&&l.$wrapper.addClass(l._getClass('animate')),l._dragEnd){var p=l._dragEnd>-(l._handleWidth/2);l._dragEnd=!1,l.state(l.options.inverse?!p:p)}else l.state(!l.options.state);l._dragStart=!1}},'mouseleave.bootstrapSwitch':function(){l.$label.trigger('mouseup.bootstrapSwitch')}})}},{key:'_externalLabelHandler',value:function(){var l=this,m=this.$element.closest('label');m.on('click',function(n){n.preventDefault(),n.stopImmediatePropagation(),n.target===m[0]&&l.toggleState()})}},{key:'_formHandler',value:function(){var l=this.$element.closest('form');l.data('bootstrap-switch')||l.on('reset.bootstrapSwitch',function(){window.setTimeout(function(){l.find('input').filter(function(){return g(this).data('bootstrap-switch')}).each(function(){return g(this).bootstrapSwitch('state',this.checked)})},1)}).data('bootstrap-switch',!0)}},{key:'_getClass',value:function(l){return this.options.baseClass+'-'+l}},{key:'_getClasses',value:function(l){return g.isArray(l)?l.map(this._getClass.bind(this)):[this._getClass(l)]}}]),j}();g.fn.bootstrapSwitch=function(j){for(var l=arguments.length,m=Array(1<l?l-1:0),n=1;n<l;n++)m[n-1]=arguments[n];return Array.prototype.reduce.call(this,function(o,p){var q=g(p),r=q.data('bootstrap-switch'),s=r||new h(p,j);return r||q.data('bootstrap-switch',s),'string'==typeof j?s[j].apply(s,m):o},this)},g.fn.bootstrapSwitch.Constructor=h,g.fn.bootstrapSwitch.defaults={state:!0,size:null,animate:!0,disabled:!1,readonly:!1,indeterminate:!1,inverse:!1,radioAllOff:!1,onColor:'primary',offColor:'default',onText:'ON',offText:'OFF',labelText:'&nbsp',handleWidth:'auto',labelWidth:'auto',baseClass:'bootstrap-switch',wrapperClass:'wrapper',onInit:function(){},onSwitchChange:function(){}}});
/*
 * model.class.js
 * Git : https://gitee.com/caodiankun/model.git
 * Author :  Caodiankun<Diankun20080@126.com>
 */
(function($){
	/*集成webkit内核,ie10+浏览器上传类*/
	var uploadClass = {
		uploadInit:function($div,options){
			options = $.extend({
				callback:function(){},
				beforeUpload:function(){},
				uploadConfirm:"",
				uploaded:function(){},
				maxSize:20971520,//上传单个文件大小，单位：字节
				maxCount:10,//该字段最大上传数量
				type:"file", 
				data:{},
				fileField:"upfile"
			},options);
			$div.each(function(){
				var $this = $(this);
				if(options.type == "img"){
					var $fileList = $("<div>",{"class":"file-list img-file-list"});
					var $input = $("<input>",{type:"file",name:options.fileField,"class":"file-upload","accept":"image/gif,image/jpeg,image/png,image/jpg,image/bmp","multiple":true});
					var $btn = $($.isIE() ? "<div>" : "<button>",{"class":"btn btn-default btn-upload img-upload mb15"});
					$btn.html('<span class="glyphicon glyphicon-plus"></span>').append($input);
					$this.append($fileList.append($btn));
				}else if(options.type == "file"){
					var $fileList = $("<div>",{"class":"file-list","style":"line-height:30px"});
					var $input = $("<input>",{type:"file",name:options.fileField,"class":"file-upload","multiple":true});
					var $btn = $($.isIE() ? "<div>" : "<button>",{"class":"btn btn-upload btn-default"}).append($input).append('<span class="glyphicon glyphicon-open"></span> 上传');
					$this.append($btn).append($fileList);
				}
				$btn.off("checkCount").on("checkCount",function(){
					if(options.maxCount > 0 && $fileList.find(".upload-file").length >= options.maxCount){
						$(this).hide();
					}else{
						$(this).show();
					}
				})
				uploadClass.uploadEvents($.extend({},options,{
					fileListContainer:$fileList,
					fileElement:$input,
				}))
			})
		},
		//按钮事件绑定
		uploadEvents:function(options){
			options.fileElement.off("change").val('').on("change",function(){
				options.beforeUpload(options)
				if(options.uploadConfirm){
					$.confirm(options.uploadConfirm,
						function(){uploadClass.startUpload(options)},
						function(){uploadClass.uploadInit(options)}
					)
				}else{
					uploadClass.startUpload(options)
				}
			})
		},
		//开始上传
		startUpload : function(options) {
			var ajaxsetting = {
				url: options.url,
				type: 'post',
				secureuri: false,
				fileElement:options.fileElement,
				dataType: 'json',
				data:options.data,
				error: function(data, status, e) {
					swal("上传失败", "接口访问失败", "error")
					uploadClass.uploadEvents(options)
				},
				success: function(data){
					uploading.hide();
					if(data.errcode == "0") {
						uploadClass.addUploadFile(data,options.type,options.fileListContainer,"form");
						
					} else {
						swal("上传失败", data.message, "error");
					}
					uploadClass.uploadEvents(options);
					uploading.remove();
					options.callback(data);
				}
			},uploadfile = options.fileElement[0].files[0],uploading,uploadRequest;
			if(uploadfile && options.maxSize && uploadfile.size >= options.maxSize){
				swal("上传失败", "请上传小于"+uploadClass.renderSize(options.maxSize)+"的文件(当前文件:"+uploadClass.renderSize(uploadfile.size)+")", "error");
				return false;
			}
			if($.isWhichIEAndPlus(10)){
				//利用html5 FormData对象上传
				var formData = new FormData(),progressBar;
				$.each(options.data,function(key,value){formData.append(key,value)});
				formData.append(options.fileField,uploadfile)
				ajaxsetting.data = formData;
				delete ajaxsetting.secureuri;
				ajaxsetting.processData=false;
	  			ajaxsetting.contentType=false;
				var onprogress = function(evt) {
					var loaded = evt.loaded;
					var tot = evt.total;
					var per = Math.floor(100 * loaded / tot);
					progressBar.css({width:per+"%"}).attr("aria-valuenow",per).html(per+"%");
				}
				ajaxsetting.xhr = function() {
					var xhr = $.ajaxSettings.xhr();
					if(onprogress && xhr.upload) {
						xhr.upload.addEventListener("progress", onprogress, false);
						return xhr;
					}
				}
				ajaxsetting.beforeSend = function(){
					if(options.type == "file"){
						progressBar	= $("<div>",{"class":"progress-bar progress-bar-success","role":"progressbar","aria-valuenow":"0","aria-valuemin":"0","aria-valuemax":"100","style":"width:0%;"}).text("0%");
						var uploadingContainer = $('<div>',{"class":"progress mr15","style":"display:inline-block;width:120px;margin-bottom:0px;"}).append(progressBar);
						uploading = $('<li>',{"class":"uploading-file list-unstyled"}).append($("<span>",{"class":"glyphicon glyphicon-refresh mr15"}));
						uploading.append($("<span>",{"class":"file-name mr15"}).html(uploadfile.name)).append($("<span>",{"class":"file-size mr15"}).html('('+uploadClass.renderSize(uploadfile.size)+')'));
						uploading.appendTo(options.fileListContainer);
						uploading.append(uploadingContainer).append($("<a>",{"class":"delete-request","href":"javascript:;"}).text("删除").on("click",function(){
							uploading.remove();
							uploadRequest.abort();
						}));
					}else if(options.type == "img"){
						progressBar	= $("<div>",{"class":"progress-bar progress-bar-success","role":"progressbar","aria-valuenow":"0","aria-valuemin":"0","aria-valuemax":"100","style":"width:0%;"}).text("0%");
						var uploadingContainer = $('<div>',{"class":"progress","style":"display:inline-block;width:120px;height:120px;padding:50px 0px;margin-bottom:0px;"}).append(progressBar);
						uploading = $('<li>',{"class":"uploading-file uploading-img-file list-unstyled pull-left mr5"}).append(uploadingContainer);
						options.fileListContainer.find("button.img-upload").before(uploading);
					}
					uploadClass.uploadEvents(options);
				}
				uploadRequest = $.ajax(ajaxsetting);
			}else{
				if(window.uploadClassForIE){
					uploadClassForIE.ajaxFileUpload(ajaxsetting);
				}else{
					console.error("请引入uploadClassForIE.js");
				}
			}
			return false;
		},
		addUploadFile:function(data,fileType,fileListContainer,mode){
			if(fileType == "img"){
				//var $viewImg = $("<div>",{"class":"view-img"})
				var $li = $("<li>",{"class":"upload-file upload-img-file pull-left pos-rel list-unstyled mr5","data-id":data.attachId}).append($("<img>",{"class":"img-thumbnail","src":data.url}));
				if(mode == "form"){
					$li.append($("<a>",{"class":"delete-upload","href":"javascript:;"}).text("删除").on("click",function(){
						var $viewImg = fileListContainer.siblings("div.view-img"),$img = $(this).siblings("img")
						if($viewImg.length > 0 && $viewImg.find("img").length > 0 && $viewImg.find("img").attr("src") == $img.attr("src")){
							$viewImg.empty();
						}
						$li.remove();
						fileListContainer.find(".btn-upload").trigger("checkCount");
					}))
					fileListContainer.find(".img-upload").before($li);
					fileListContainer.find(".btn-upload").trigger("checkCount");
				}else{
					fileListContainer.append($li);
				}
				$li.on("click","img.img-thumbnail",function(){
					var $container = mode=="form" ? fileListContainer.parent() : fileListContainer,$viewImg;
					if($container.find("div.view-img").length > 0){
						$viewImg = $container.find("div.view-img");
					}else{
						$viewImg = $("<div>",{"class":"view-img mt5 mb5"}).appendTo($container).before($("<div>",{"class":"clearfix"}));
					}
					if($viewImg.children("img").length > 0 && $viewImg.children("img:eq(0)").attr("src") == $(this).attr("src")){
						$viewImg.empty().hide();
					}else{
						$viewImg.empty().show().append($(this).clone());
					}
				})
				
			}else if(fileType == "file"){
				var $li = $("<li>",{"class":"upload-file list-unstyled","data-id":data.attachId}).append($("<span>",{"class":"glyphicon glyphicon-paperclip mr15"})).append($("<span>",{"class":"file-name mr15"}).text(data.originalName))
				if(mode == "form"){
					$li.append($("<span>",{"class":"file-size mr15"}).text("("+this.renderSize(data.len || data.size)+")")).append($("<a>",{"class":"mr15","target":"_blank","href":data.downloadUrl}).text("下载")).append($("<a>",{"href":"javascript:;"}).text("删除").on("click",function(){
						$li.remove();
						fileListContainer.siblings(".btn-upload").trigger("checkCount");
					}))
					fileListContainer.append($li);
					fileListContainer.siblings(".btn-upload").trigger("checkCount");
				}else{
					$li.append($("<span>",{"class":"file-size mr15"}).text("("+this.renderSize(data.len || data.size)+")")).append($("<a>",{"href":data.downloadUrl,"target":"_blank"}).text("下载"));
					fileListContainer.append($li);
				}
			}
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
		}
	}
	/*upload.class end*/
	
	var _formdata;
	var model = {
		//默认单个模型配置
		defaultModel:{"field":"","mappingField":"","title":"","defaultValue":"","isRequired":0,"valueType":"","maxValue":"","minValue":"","maxSize":0,"minSize":0,"formType":"text","isHidden":0,"cols":4,"wrap":0,"visible":0,"isOnlyread":0,"sortable":0,"search":0,"searchType":"","placeholder":"","valueConfig":{}},
		//设置模型默认值
		setDefaultModel:function(list){
			var newList = [];
			$.each(list,function(index,obj){
				newList[index] = $.extend({},model.defaultModel,obj);
			})
			return newList;
		},
		customSetting:{},
		//modelManager默认配置信息
		defaultSetting:{
			method:"post",
			modelApi:"",//主模型、list模型api，必填
			listApi:"",//列表数据接口，必填
			listData:[],//列表数据
			listDataField:"rows",//列表接口中列表字段
			contentType:"application/x-www-form-urlencoded;charset=UTF-8",
			addApi:"",//add接口；
			editApi:"",//edit接口；
			deleteApi:"",//delete接口；
			viewApi:"",//查询单条接口；
			viewDataField:"",//查询接口中数据object字段
			addModelApi:null, //增加表单模型api,如果为空，则使用主模型
			editModelApi:null, //修改表单模型api,如果为空，则使用主模型
			editModelViewApi:null,//修改表单模型提供数据回显的api，如果为不设置则取viewApi
			editOnTable:false,//是否可以在表格上双击编辑非readonly字段
			viewModelApi:null, //显示模型api，如果为空，则使用主模型
			searchModelApi:null, //搜索表单模型api,如果为空，则使用主模型
			idKey:"id", //查询/删除/修改依赖主键
			checkbox:false,//表格是否显示选择列
			showRefresh: true, //表格是否显示刷新按钮
			showColumns: true, //表格是否显示列选择
			pagination : true, //表格是否显示页码
			simpleSort : true, //表格是否简单排序模式
			formatter:{
				/*
				"[field]":function(row,value,index){}
				*/
			}, //重定义表格内容
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
			examineBtn:function(row,index){
				return false;
			},
			examineBtnText:"审核",
			examineModalTitle:"审核",
			examineModelApi:null,
			examineApi:"",
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
			btnGroupInline:false,
			pageSize:10,
			pageList:[10],
			extendData:{},
			onListLoaded:function(obj){
				//列表加载完成回调函数
			},
			editOnTable:false,//是否可以在表格上双击修改，
			addOntable:false,//增加是否为在表格上增加的模式，
			editOnTableApi:"",//在表格上增加/修改的保存接口地址，
			onViewDataLoaded:function(obj){},//view操作回调函数
			onEditDataLoaded:function(obj){},//edit操作回调函数
			onExamineDataLoaded:function(obj){},//examine操作回调函数
		},
		random:0,
		cache:{},
		//创建显示表单
		createView : function($container,options){
			this.createLabel(options).appendTo($container)
			if(options.formType == "switch"){
				var $div = this.createDiv(options).addClass("switch switch-view").append($("<input>",{"type":"checkbox","name":options.field})).appendTo($container);
				$div.find("input").attr("data-onvalue",options.valueConfig.onValue || "1").bootstrapSwitch({
					onText:options.valueConfig.onText || "&nbsp;&nbsp;&nbsp;&nbsp;",  
			        offText:options.valueConfig.offText || "&nbsp;&nbsp;&nbsp;&nbsp;", 
			        size:"small",
			        onColor:"success",
				})
			}else{
				this.createDiv(options).addClass("border-bottom value-control").attr("name",options.field).appendTo($container);
			}
			
		},
		//创建表单
		createForm : function($container,options,purpose){
			if(!purpose){
				purpose = "form";
			}
			this.createLabel(options).appendTo($container);
			var $input = this.createInput($container,options,purpose);
			if($input){
				this.createDiv(options,purpose).append($input).appendTo($container)
			}
		},
		createInput : function($container,options,purpose){
			var $input;
			var defaultAttr = {
				"name":options.field,
				"class":"form-control",
				"readonly" : parseInt(options.isOnlyread) ? "readonly" : null,
				"required" : parseInt(options.isRequired) && purpose != "search" ? "required" : null,
				"placeholder":options.placeholder,
				"value" : purpose == "search" ? (options.searchDefaultValue || "") : (options.defaultValue || "")
			};
			if(options.other){
				$.each(options.other,function(key,value){
					defaultAttr["data-"+key.toLowerCase()] = value;
				});
			};
			var formType = purpose == "search" ? options.searchType || options.formType : options.formType;
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
				case "password":
					defaultAttr.maxsize = (parseInt(options.maxSize)) ? options.maxSize : null;
					defaultAttr.minsize = (parseInt(options.minSize)) ? options.minSize : null;
					$input = $("<input>",$.extend(defaultAttr,{"type":"password"}));
					break;
				case "radio":
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"radio"}));
					this.createDiv(options,purpose).append($input).appendTo($container);
					if(options.valueConfig && options.valueConfig.list && options.valueConfig.list.length > 0){
						$.each(options.valueConfig.list, function(index,obj) {
							$input.before($("<input>",{"type":"radio","checked":index == 0 ? true : false,"id": ""/*purpose +"-"+ options.field + "-" + index*/,"name":options.field+"-icheck","class":"icheck prevent","value":obj.value})).before($("<label>",{"class":"label-control ml5 mr15 pointer","for":purpose + "-" + options.field + "-"+index}).text(obj.text));
							if(index==0){$input.val(obj.value)};
						});
					}
					return false;
				case "checkbox":
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"checkbox"}));
					this.createDiv(options,purpose).append($input).appendTo($container);
					if(options.valueConfig && options.valueConfig.list && options.valueConfig.list.length > 0){
						$.each(options.valueConfig.list, function(index,obj) {
							$input.before($("<input>",{"type":"checkbox","id":purpose + "-" + options.field + "-"+index,"name":options.field+"-icheck","class":"icheck prevent","value":obj.value})).before($("<label>",{"class":"label-control ml5 mr15 pointer","for":purpose + "-" + options.field + "-"+index}).text(obj.text));
						});
					}
					return false;
				case "yesorno":
					$input = $("<select>",$.extend(defaultAttr,{"type":"select"}));
					var obj = {
						list:[{"text":"是","value":"1"},{"text":"否","value":"0"}],
						selector:$input,
						valueKey:"value",
						textKey:"text"
					};
					purpose == "search" ? obj.defaultOption = {text:"全部",value:""} : obj.defaultOption = {text:options.valueConfig.defaultText || "请选择",value:options.valueConfig.defaultValue || ""};		
					this.createOptions(obj);
					break;
				case "switch":
					var config = $.extend({
						onText:"&nbsp;&nbsp;&nbsp;&nbsp;",
						offText:"&nbsp;&nbsp;&nbsp;&nbsp;",
						onValue:"1",
						offValue:"0",
						size:"small",
				        onColor:"success",
					},options.valueConfig||{});
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"switch"}));
					var $input2 = $("<input>",{"type":"checkbox","class":"prevent"}).appendTo(this.createDiv(options,purpose).append($input).appendTo($container));
					$input2.bootstrapSwitch({
						onText:config.onText,  
				        offText:config.offText, 
				        size:config.size,
				        onColor:config.onColor,
					}).on('switchChange.bootstrapSwitch', function (event,state) {
		                if(state == true){  
				            $input.val(config.onValue);
				        }else{ 
				            $input.val(config.offValue);
				        }
		            });
		            $input.on("modelFormChange",function(){
		            	if($(this).val() == config.onValue){
		            		$input2.bootstrapSwitch("state",true);
		            	}else{
		            		$input2.bootstrapSwitch("state",false);
		            		$(this).val(config.offValue);
		            	}
		            });
					return false;
				case "advSelect":
					var random = this.random;
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"advSelect"}));
					this.createDiv(options,purpose).append($input).appendTo($container);
					var $input2 = $("<input>",{"type":"input","readonly":"readonly","data-name":options.field,"name":options.mappingField || (options.field+"-advSelect"),"class":"form-control advSelect bgfff pointer"});
					$input.before($input2);
					var $emptyBtn = $("<span>",{"class":"glyphicon glyphicon-remove form-control-feedback remove-value hidden text-danger"}).on("click",function(){
						$input.val("");
						$input2.val("");
						$(this).trigger("check");
					}).on("check",function(){
						if($input.val() || $input2.val()){
							$(this).removeClass("hidden");
						}else{
							$(this).addClass("hidden");
						}
					});
					if(purpose == "search"){
						$input.after($emptyBtn);
					}
					var list;
					if(options.valueConfig && options.valueConfig.list && $.isArray(options.valueConfig.list) && options.valueConfig.list.length > 0){
						list = options.valueConfig.list;
					}else if(options.valueConfig && options.valueConfig.url){
						$.ajax({
							url:options.valueConfig.url,
							suc:function(data){
								list = options.valueConfig.dataField ? data[options.valueConfig.dataField] : data["list"];
								$input2.trigger("listLoaded");
							}
						});	
					}
					if(list){
						createPicker();
					}else{
						$input2.on("listLoaded",function(){
							createPicker();
						})
					}
					function createPicker(){
						var setting = {
							"id":purpose + "-" + options.field + "-ztree-"+random,
							"list":list,
							"idKey":options.valueConfig.valueKey || "value",
							"textKey":options.valueConfig.textKey || "text",
							"pIdKey":options.valueConfig.pIdKey || "pId",
						}
						setting["condition"] = function(obj){
							if(obj[options.valueConfig.conditionKey || "condition"]){
								return true;
							}else{
								return false;
							}
						}
						if(options.maxSize != "1" && options.maxSize != 1){
							setting["enable"] = true;
							setting["onCheck"] = function(personCheckedArr, $simpleInput){
								var valueList = [];
								var textList = [];
								if($.isArray(personCheckedArr) && personCheckedArr.length > 0) {
									$.each(personCheckedArr, function(index, obj) {
										valueList.push(obj[options.valueConfig.valueKey || "value"]);
										textList.push(obj[options.valueConfig.textKey || "text"]);
									})
								}
								$simpleInput.val(textList.join(","));
								$input.val(valueList.join(","));
								$emptyBtn.trigger("check");
							}
						}else{
							setting["enable"] = false;
							setting["onClick"] = function(e, treeId, treeNode) {
								$input2.val(treeNode[options.valueConfig.textKey] || "text");
								$input.val(treeNode[options.valueConfig.valueKey || "value"]);
								$emptyBtn.trigger("check");
							}
						}
						$input2.ztreePicker(setting);
					};
					return false;
				case "select":
					$input = $("<select>",$.extend(defaultAttr,{"type":"select","data-lastselectfield":options.valueConfig.lastSelectField || null}));
					createOptions = this.createOptions;
					if(options.valueConfig && options.valueConfig.list && options.valueConfig.list.length > 0){
						var obj = {
							list:options.valueConfig.list,
							valueKey:options.valueConfig.valueKey || "value",
							textKey:options.valueConfig.textKey || "text",
							selector:$input
						}
						purpose == "search" ? obj.defaultOption = {text:"全部",value:""} : obj.defaultOption = {text:options.valueConfig.defaultText || "请选择",value:options.valueConfig.defaultValue || ""};		
						createOptions(obj);
					}else if(options.valueConfig && options.valueConfig.url){
						//联动select字段
						var lastSelectField = options.valueConfig ? options.valueConfig.lastSelectField : null;
						$input.attr("data-asyn","asyn").on("refreshData",function(){
							var queryData = {};
							if(lastSelectField){
								queryData[options.valueConfig.lastFieldQueryKey || lastSelectField] = $input.attr("data-lastfieldvalue");
							};
							if(!lastSelectField || $input.attr("data-lastfieldvalue")){
								$.ajax({
									url:options.valueConfig.url,
									data:queryData,
									suc:function(data){
										var obj = {
											list: options.valueConfig.dataField ? data[options.valueConfig.dataField] : data["list"],
											valueKey:options.valueConfig.valueKey || "value",
											textKey:options.valueConfig.textKey || "text",
											selector:$input
										}
										purpose == "search" ? obj.defaultOption = {text:"全部",value:""} : obj.defaultOption = {text:options.valueConfig.defaultText || "请选择",value:options.valueConfig.defaultValue || ""};	
										createOptions(obj);
										if($input.attr("data-value")){
											$input.val($input.attr("data-value")).attr("data-value",null);
										}
										$input.attr("data-lastfieldvalue",null).trigger("modelFormChange");
									},
									err:function(){
										$input.html('<option value="">数据加载失败</option>');
									}
								});
							}
						})
						
						if(lastSelectField){
							$input.on("resetData",function(){
								$input.html(purpose == "search" ? '<option value="">&lt;全部&gt;</option>' : '<option value="">&lt;请选择&gt;</option>').trigger("change");
							}).trigger("resetData");
							
						}else{
							if(purpose == "search"){
								$container.on("listDataLoaded",function(){
									$input.trigger("refreshData");
									$container.off("listDataLoaded");
								})
							}
						}
					}
					break;
				case "textarea":
					defaultAttr.maxlength = (options.maxSize != 0 && options.maxSize != "0" && options.maxSize != "") ? options.maxSize : null;
					defaultAttr.minsize = (options.maxSize != 0 && options.minSize != "0" && options.minSize != "") ? options.minSize : null;
					$input = $("<textarea>",$.extend(defaultAttr,{"type":"textarea"}));
					break;
				case "datetime":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control datetimePicker"}));
					break;
				case "date":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control datePicker"}));
					break;
				case "time":
					$input = $("<input>",$.extend(defaultAttr,{"type":"text","class":"form-control timePicker"}));
					break;
				case "dateBetween":
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","class":"form-control","data-formtype":"dateBetween"}));
					this.createDiv(options,purpose).append($("<div>",{"class":"row pos-rel"}).append($input)).appendTo($container);
					var $startDate = $("<input>",{"type":"text","value":"","name":options.field + "-start","class":"form-control prevent datePicker start","placeholder":"开始日期"});
					var $endDate = $("<input>",{"type":"text","value":"","name":options.field + "-end","class":"form-control prevent datePicker end","placeholder":"结束日期"});
					$input.before($("<div>",{"class":"col-sm-6"}).append($startDate)).before($("<div>",{"class":"col-sm-6"}).append($endDate)).before($("<div>",{"class":"text-center pos-abs","style":"width:10px;left:50%;margin-left:-5px;line-height:34px;"}).text("-"));
					
					$startDate.on("change",function(){
						$endDate.datetimepicker('setStartDate',$startDate.val() || null);
						$input.val($startDate.val() || $endDate.val() ? ($startDate.val() || "*") + "," + ($endDate.val() || "*") : "");
					})
					$endDate.on("change",function(){
						 $startDate.datetimepicker('setEndDate',$endDate.val() || null);
						 $input.val($startDate.val() || $endDate.val() ? ($startDate.val() || "*") + "," + ($endDate.val() || "*") : "");
					});
					return false;
				case "datetimeBetween":
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","class":"form-control","data-formtype":"datetimeBetween"}));
					this.createDiv(options,purpose).append($("<div>",{"class":"row pos-rel"}).append($input)).appendTo($container);
					var $startDate = $("<input>",{"type":"text","value":"","name":options.field + "-start","class":"form-control prevent datetimePicker start","placeholder":"开始日期"});
					var $endDate = $("<input>",{"type":"text","value":"","name":options.field + "-end","class":"form-control prevent datetimePicker end","placeholder":"结束日期"});
					$input.before($("<div>",{"class":"col-sm-6"}).append($startDate)).before($("<div>",{"class":"col-sm-6"}).append($endDate)).before($("<div>",{"class":"text-center pos-abs","style":"width:10px;left:50%;margin-left:-5px;line-height:34px;"}).text("-"));
					
					$startDate.on("change",function(){
						$endDate.datetimepicker('setStartDate',$startDate.val() || null);
						$input.val($startDate.val() || $endDate.val() ? ($startDate.val() || "*") + "," + ($endDate.val() || "*") : "");
					})
					$endDate.on("change",function(){
						 $startDate.datetimepicker('setEndDate',$endDate.val() || null);
						 $input.val($startDate.val() || $endDate.val() ? ($startDate.val() || "*") + "," + ($endDate.val() || "*") : "");
					});
					return false;
				case "editor":
					var editorID = purpose+"-"+options.field+"-editor-"+this.random+"-"+parseInt(Math.random() * 1000);
					$input = $("<input>",$.extend(defaultAttr,{"type":"hidden","formtype":"editor","for":editorID}));
					$editor = $("<script>",{"type":"text/plain","class":"umeditor","style":"width:100%; height:240px;","id":editorID}).html(defaultAttr.value);
					this.createDiv(options,purpose).append($input).append($editor).appendTo($container);
					$input[0].um = UM.getEditor(editorID,{imageUrl:options.valueConfig.url || ""});
					$input.on("resetForm",function(){
						$(this).empty();
						this.um.setContent("");
					}).on("resetEditorWidth",function(){
						this.um.setWidth($(this).parent().width());
						$(this).off("resetEditorWidth");
					});
					return false;
				case "file":
					var $div = this.createDiv(options,purpose).appendTo($container);
					$div.addClass("file-upload-container");
					uploadClass.uploadInit($div,{
						url:options.valueConfig.url,
						type:"file",
						maxCount: options.maxSize != null ? parseInt(options.maxSize) : null, //最大数量
						maxSize: options.maxValue != null ? parseInt(options.maxValue) : null //上传文件最大大小
					})
					return false;
				case "img":
					var $div = this.createDiv(options,purpose).appendTo($container);
					$div.addClass("file-upload-container");
					uploadClass.uploadInit($div,{
						url:options.valueConfig.url,
						type:"img",
						maxCount: options.maxSize != null ? parseInt(options.maxSize) : null, //最大数量
						maxSize: options.maxValue != null ? parseInt(options.maxValue) : null //上传文件最大大小
					})
					return false;
			}
			return $input
		},
		
		getUEditor:function(callback){
			
		},
		//生成分组标签
		createGroupName:function(options){
			return $("<div>",{class:"col-sm-12 border-title mb15"}).append("<h4>" + (options.fieldgroupName || options.groupName) + "</h4>");
		},
		//生成label
		createLabel:function(options){
			//return $("<div>",{"class":"label-control text-middle col-sm-"+(options.labelCols || 1)+" mb15","data-field":options.field}).text(options.title)
			var $table = $("<table>",{"height":"100%"}).append($("<tr>").append($("<td>",{"height":"100%","valign":"middle"}).text(options.title)));
			return $("<div>",{"class":"label-control text-middle col-sm-"+(options.labelCols || 1)+" mb15","data-field":options.field}).append($table);
		},
		//生成div
		createDiv:function(options,purpose){
			var feedback = "";
			if(purpose == "form" && (options.isRequired == "1" || options.valueType != "string" || options.minSize || options.maxSize)){
				feedback = " has-feedback";
			}
			if(purpose == "search" && (options.formType == "advSelect")){
				feedback = " has-feedback";
			}
			var $div = $("<div>",{"class":"col-sm-" + (parseInt(options.cols)-(options.labelCols || 1)) + " mb15" + feedback,"data-field":options.field});
			if(options.other){
				$.each(options.other,function(key,value){
					$div.attr("data-"+key.toLowerCase(),value);
				})
			}
			return $div;
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
			var html = options.defaultOption ? '<option value="'+options.defaultOption.value+'">&lt;'+options.defaultOption.text+'&gt;</option>' : '';
			$.each(options.list, function(index,obj) {
				html += '<option value="'+obj[options.valueKey]+'">'+obj[options.textKey]+'</option>';
			});
			$this.html(html);
		},
		//创建模态框
		createModal:function(options){
			var $modal = $("<div>",{"class":"modal fade "+options.class,"tabindex":"-1","role":"dialog","aria-labelledby":"myLargeModalLabel"});
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
			//处理editor
			options.container.find("input[formtype=editor]").each(function(){
				$(this).val(this.um.getContent());
			})
			options.container.sendForm(options);
		},
		//重置容器内表单
		resetForm:function(container,options){
			container.find("input.icheck").each(function(){
				$(this).iCheck("uncheck");
			})
			container.find("select.form-control").each(function(){
				if($(this).attr("data-lastselectfield")){
					$(this).trigger("resetData");
				}
			})
			container.find("input[formtype=editor]").trigger("resetForm");
			container.find("input.advSelect").attr("data-ztree","0");
			container.resetForm();
			$(document).trigger("mousedown");
		},
		//回显，表单赋值
		getFormData:function(options){	
			options = $.extend({
				url:"",//地址或数据
				data:{},//请求数据
				dataField:"",//数据字段
				container:null,//容器
				model:[],//模型
				special:false,//特殊数据模式
				callback:function(){}//回调函数
			},options);
			
			if(typeof(options.url) == "string"){
				$.ajax({
					url:options.url,
					data:options.data,
					loadingContainer:$("body"),
					hasAlert:true,
					suc:function(data){
						if(options.dataField){
							data = data[options.dataField];
							if($.isArray(data) && data[0]){
								data = data[0];
							}
						}
						if(options.special){
							var data2 = {};
							if(data[options.dataField || "rows"]){
								$.each(data.rows, function(index,obj) {
									if(obj.sxValue){
										data2[obj.englishName] = obj.sxValue;
									}
								});
							}
							data = data2;
						}
						model.assignment(data,options.container,options.model,"",options.callback);
					}
				})
			}else if(options.url && typeof(options.url) == "object"){
				model.assignment(options.url,options.container,options.model,"",options.callback);
			}
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
		assignment:function(viewData,$container,modelData,selector,callback){
			selector = selector || ".form-control";
			var newModelData = {};
			var getTextByValueInList = model.getTextByValueInList;
			$.each(modelData, function(index,obj) {
				newModelData[obj.field] = obj;
				if((obj["formType"] === "file" || obj["formType"] === "img") && viewData[obj["field"]] && viewData[obj["field"]].length > 0){
					var attach = viewData[obj["mappingField"] || obj["field"]];
					var $form = $container.find(".file-upload-container[data-field="+obj["field"]+"]");
					var $view = $container.find(".value-control[name="+obj["field"]+"]");
					if($.isArray(attach)){
						getAttach(attach);
					}else if(typeof(attach) == "string"){
						$.ajax({
							url:obj["valueConfig"]["url"].replace("upload.do","queryAttrList.do"),
							data:{
								"attachId":attach
							},
							suc:function(data){
								getAttach(data.rows);
							}
						});
					}
					function getAttach(attach){
						$.each(attach, function(index,data) {
							if($form){
								uploadClass.addUploadFile(data,obj["formType"],$form.find(".file-list"),"form");
							}
							if($view){
								uploadClass.addUploadFile(data,obj["formType"],$view,"view");
							}
						});
					}
				}
			});
			$container.find("button.btn-upload").trigger("checkCount");
			$container.find(selector).each(function(){
				var $this = $(this);
				var name = $this.attr("name");
				var value = viewData[name];
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
					case "editor":
						if(value){
							$this[0].um.setContent(value);
							$this.empty();
						}
					break;
				};
				if($this.attr("valuetype") == "only" && value){
					$this.attr("originalvalue",value)
				}else{
					$this.removeAttr("originalvalue")
				}
				if($this.attr("data-asyn") == "asyn"){
					if($this.attr("data-lastselectfield")){
						//如果是联动菜单，
						$this.attr("data-lastfieldvalue",viewData[$this.attr("data-lastselectfield")])
					}
					$this.attr("data-value",value);
					$this.trigger("refreshData");
				}else{
					if($(this).hasClass("advSelect") && value){
						if(typeof(value) == "string"){
							$(this).val(value);
						}else if($.isArray(value)){
							var textKey = newModelData[$(this).attr("data-name")]["valueConfig"]["textKey"] || "text",textList = [];
							$.each(value, function(index,obj) {
								textList.push(obj[textKey]);
							});
							$(this).val(textList.toString());
							$this.attr("data-ztree","0").trigger("modelFormChange");
						}
					}else{
						$this.val(value);
						$this.trigger("modelFormChange");
					}
				}
			})
			$container.find(".value-control").each(function(){
				var text;
				if(!newModelData[$(this).attr("name")]){
					text = viewData[$(this).attr("name")];
					$(this).html(text);
					return;
				}
				//先取映射字段
				if(newModelData[$(this).attr("name")]["mappingField"]){
					text = viewData[newModelData[$(this).attr("name")]["mappingField"]]
				}
				if(text && $.isArray(text)){
					var textKey = newModelData[$(this).attr("name")]["valueConfig"]["textKey"] || "text",textList = [];
					$.each(text, function(index,obj) {
						textList.push(obj[textKey]);
					});	
					text = textList.toString();
				}
				//判断是否yesorno类型
				if(newModelData[$(this).attr("name")]["formType"] == "yesorno"){
					text = viewData[$(this).attr("name")] == "1" ? "是" : "否";
				}
				//其次在模型list中取
				if(!text && newModelData[$(this).attr("name")].valueConfig && newModelData[$(this).attr("name")].valueConfig.list){
					text = getTextByValueInList(newModelData[$(this).attr("name")].valueConfig.list,viewData[$(this).attr("name")],newModelData[$(this).attr("name")].valueConfig.valueKey,newModelData[$(this).attr("name")].valueConfig.textKey);
				}
				//最后取真实值
				if(!text){
					text = newModelData[$(this).attr("name")]["mappingField"] ? "-" : viewData[$(this).attr("name")];
				}
				if((typeof(text) === "string" || typeof(text) === "number") && $.inArray(newModelData[$(this).attr("name")]["formType"],["file","img"]) < 0 ){
					$(this).html(text);
				}
			})
			$container.find(".switch-view input").each(function(){
				$(this).bootstrapSwitch('readonly', false);
				if(viewData[$(this).attr("name")] == $(this).attr("data-onvalue")){
					$(this).bootstrapSwitch("state",true);
				}else{
					$(this).bootstrapSwitch("state",false);
				}
				$(this).bootstrapSwitch('readonly', true);
			})
			if(callback && $.isFunction(callback)){
				callback({
					container:$container,
					model:modelData,
					data:viewData,
				})
			}
		},
		//创建表格显示列
		createColumns:function(options,formatter){
			var columns = [];
			var model = this;
			var getTextByValueInList = this.getTextByValueInList;
			var idKey = this.customSetting.idKey,editOnTable=this.customSetting.editOnTable;
			$.each(options, function(index,obj) {
				if(parseInt(obj.visible) > 0){
					columns.push({
						"field":obj.field,
						"title":obj.title,
						"align":obj.align || (obj.valueConfig ? obj.valueConfig.align : null) || "center",
						"halign":obj.halign ||  (obj.valueConfig ? obj.valueConfig.halign : null) || "center",
						"width":obj.width || (obj.valueConfig ? obj.valueConfig.width : null) || undefined,
						"visible":parseInt(obj.visible) == 1 ? true : false,
						"sortable":parseInt(obj.sortable) ? true : false,
						"formatter":formatter[obj.field] ? function(value,rows){return '<div class="editOnTable">'+formatter[obj.field](value,rows)+'</div>'} : function(value,rows){return '<div class="editOnTable">'+(function(value,rows){
							if(obj.formType == "yesorno"){
								return value == "1" ? "是" : "否";
							}else if(obj.formType == "switch"){
								return '<input type="checkbox" data-field="'+obj.field+'" data-id="'+rows[idKey]+'" data-ontext="'+(obj.valueConfig.onText||"&nbsp;&nbsp;&nbsp;&nbsp;")+'" data-onvalue="'+(obj.valueConfig.onValue||"1")+'" data-offtext="'+(obj.valueConfig.offText||"&nbsp;&nbsp;&nbsp;&nbsp;")+'" data-offvalue="'+(obj.valueConfig.offValue||"0")+'" class="switch" '+(value == (obj.valueConfig.onValue || "1") ? "checked" : "")+' >';
							}else if(obj.mappingField){
								return rows[obj.mappingField] || '-';
							}else if(!obj.mappingField && obj.field && obj.valueConfig && $.isArray(obj.valueConfig.list) && obj.valueConfig.list.length > 0){
								return getTextByValueInList(obj.valueConfig.list,value,obj.valueConfig.valueKey,obj.valueConfig.textKey) || value || "-";
							}else if(typeof(value) == "number"){
								return value
							}else{
								return value || "-"
							}
						})(value,rows)+'</div>'},
						"events":editOnTable&&!parseInt(obj.isOnlyread) ? {
							"dblclick .editOnTable":function(e,value,row,index){
								$(this).parents("tr").attr("data-id",row[model.customSetting.idKey])
								var $tableView = $(e.target).hide();
								var $tableForm = $("<div>",{"class":"row form-horizontal"}).append(model.createDiv($.extend(obj,{labelCols:1,cols:13}),"form").removeClass("mb15").addClass("editOnTableForm"));
								$tableView.after($tableForm)
								
								var $input = model.createInput($tableForm.find(".editOnTableForm"),obj);
								if($input){$tableForm.find(".editOnTableForm").append($input);$input[0].focus();};
								$tableForm.find(".mb15").removeClass("mb15");
								model.events($tableForm);
								model.verEvents($tableForm.parent());
								model.assignment(row,$tableForm,obj);
								if(!model.customSetting.table.hasClass("edit-status")){
									model.enterEditMode(model.customSetting.table,model.customSetting.container.find(".fixed-table-toolbar"))
								}
							}
						} : undefined
					})
				}
			});
			return columns;
		},
		enterEditMode:function($table,$toolbar){
			var model = this;
			var $btnContainer = $("<div>",{"class":"columns columns-left btn-list pull-left"}).prependTo($toolbar);
			$toolbar.find(".columns.btn-group").hide();
			var $saveBtn = $("<button>",{"class":"btn btn-success save"}).html(" 保存").prepend($("<span>",{"class":"glyphicon glyphicon-floppy-disk"})).appendTo($btnContainer).on("click",function(){
				if(!$.ver($table.find(".form-control[required=required],.form-control[valueType]"))){
					return false;
				}
				var list = [];
				$table.find("tbody tr").each(function(){
					if($(this).find(".editOnTableForm").length > 0){
						var obj = $(this).sendForm();
						obj[model.customSetting.idKey] = $(this).attr("data-id") || null;
						list.push(obj)
					}
				})
				$.ajax({
					type:"post",
					url:model.customSetting.editOnTableApi || model.customSetting.editApi,
					contentType:"application/json",
					data:JSON.stringify(list),
					suc:function(data){
						$.alert(data.errmsg,"success",function(){
							$table.bootstrapTable("refresh");
							model.exitEditMode($table,$toolbar);
						})
					},
					hasAlert:true,
					loadingContainer:$("body")
				});
			});
			var $cancelBtn =  $("<button>",{"class":"btn btn-danger cancel ml15"}).html(" 取消").prepend($("<span>",{"class":"glyphicon glyphicon-remove"})).appendTo($btnContainer).on("click",function(){
				model.exitEditMode($table,$toolbar)
			});
			$table.addClass("edit-status");
			return $saveBtn
		},
		exitEditMode:function($table,$toolbar){
			$table.removeClass("edit-status").find("tr.new-tr").remove();
			$table.find(".editOnTable").each(function(){
				$(this).siblings(".row.form-horizontal").remove();
				$(this).show();
			})
			$toolbar.find(".columns.btn-list").remove();
			$toolbar.find(".columns.btn-group").show();
		},
		//表单事件
		events : function($container){
			//raido事件
			$container.find("input.icheck[type=radio]").iCheck({
				radioClass: 'iradio_square-green',
		    	increaseArea: '20%'
			}).on('ifChecked', function(event){
				var value = $(this).val();
				var field = $(this).attr("name").replace("-icheck","");
				$container.find("input[name="+field+"]").val(value).trigger("modelFormChange");
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
			}).on("changeDate",function(a,b){
				$(this).trigger("blur");
			})
			$container.find("input.datePicker").datetimepicker({
				format: 'yyyy-mm-dd',
				language: 'zh-CN',
				minView: 2,
				autoclose: true
			}).on("changeDate",function(a,b){
				$(this).trigger("blur");
			})
			$container.find("input.timePicker").datetimepicker({
				format: 'hh:ii:00',
				language: 'zh-CN',
				startView: 1,
				minView: 0,
				autoclose: true
			}).on("changeDate",function(a,b){
				$(this).trigger("blur");
			})
			//联动select事件
			$container.find("select.form-control").each(function(){
				var lastSelectField = $(this).attr("data-lastselectfield")
				$(this).on("change",function(){
					$(this).trigger("modelFormChange");
				})
				if(lastSelectField){
					var $input = $(this);
					$container.on("change","select[name="+lastSelectField+"]",function(){
						if($(this).val()){
							$input.attr("data-lastfieldvalue",$(this).val());
							$input.trigger("refreshData");
						}else{
							$input.trigger("resetData");
						}
					})
				}
			});
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
					if($(this).is("select")){
						$this.on("change",function(){
							$.ver($(this));
						})
					}else{
						$this.on("blur",function(){
							$.ver($(this));
						})
					}
					
				}
			});
		},
		//换行策略：创建方法,判断增长策略,列表,容器
		colsWrap:function(createFun,strategy,list,$container){
			var cols = 0;
			var createGroupName = this.createGroupName;
			$.each(list,function(index,obj){
				if((obj.fieldgroupName || obj.groupName) && !$container.hasClass("search-form") && !$container.hasClass("advSearch-form")){
					cols = 0;
					$container.append(createGroupName(obj));
				}
				if(obj.wrap){
					cols = 0;
					$container.append($("<div>",{"class":"clearfix"}));
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
				/*if(index == list.length-1){
					$container.append($("<div>",{"class":"clearfix"}));
				}*/
			})
		},
		//接口地址为string类型，即url地址，则ajax请求，为object（list）类型则直接返回该值
		getModel:function(modelApi,loading,callback){
			if(modelApi){
				var setDefaultModel = this.setDefaultModel;
				if(typeof(modelApi) == "string"){
					$.ajax({
						url:modelApi,
						suc:function(data){
							callback(setDefaultModel(data.model));
						},
						hasAlert:true,
						loadingContainer:loading
					});
				}else if(typeof(modelApi) == "object" && $.isArray(modelApi)){
					callback(setDefaultModel(modelApi));
				}
			}else{
				return false;
			}
		},
		enterForSearch:function($search,$table){
			//搜索表单回车事件
			$search.off("keyup",".form-control").on("keyup",".form-control",function(e){
				if(e.which == 13){
					$table.bootstrapTable("refresh",{pageNumber:1});
				}
			})
		},
		onModalShown:function($modal){
			$modal.find("input[formtype=editor]").trigger("resetEditorWidth");
		},
		//主函数
		modelManager:function($container,options){
			options = $.extend({container:$container},this.defaultSetting,options);
			this.customSetting = options;
			$container.on("modelDataLoaded",function(){
				$container.endLoading();
			}).loading();
			var model = this,modelData,addModelData,editModelData,searchModelData,viewModelData,$search,$advSearch,$btnGroup,$table,listConfig;
			model.random = options.random;
			//创建新增/编辑/查看模态框
			if(options.oprate){
				var $addModal = (options.addModelApi || options.editModelApi) ? model.createModal({"class":"addModal","title":"","hasSaveBtn":"1"}) : null;
				var $formModal = (options.editBtn || options.addBtn) ? model.createModal({"class":"formModal","title":"","hasSaveBtn":"1"}) : null;
				var $viewModal = options.viewBtn ? model.createModal({"class":"viewModal","title":"","hasSaveBtn":"0"}) : null;
				//add模态框加载模型
				if(options.addModelApi || options.editModelApi){
					if(options.addModelApi){
						$container.on("addModelDataLoaded",function(){
							model.modelForm($addModal.find(".modal-body"),addModelData);
						})
					}else{
						$container.on("modelDataLoaded",function(){
							model.modelForm($addModal.find(".modal-body"),modelData);
						})
					}
					
					$addModal.on("shown.bs.modal",function(){
						model.onModalShown($(this));
					})
				}
				//edit模态框加载模型
				if($formModal){
					if(options.editModelApi){
						$container.on("editModelDataLoaded",function(){
							model.modelForm($formModal.find(".modal-body"),editModelData);
						})
					}else{
						$container.on("modelDataLoaded",function(){
							model.modelForm($formModal.find(".modal-body"),modelData);
						})
					};

					$formModal.on("shown.bs.modal",function(){
						model.onModalShown($(this));
					})
				}
				//view模态框加载模型
				if($viewModal){
					if(options.viewModelApi){
						$container.on("viewModelDataLoaded",function(){
							model.modelView($viewModal.find(".modal-body"),viewModelData);
						})
					}else{
						$container.on("modelDataLoaded",function(){
							model.modelView($viewModal.find(".modal-body"),modelData);
						})
					};
				}
			}else if(options.addBtn){
				var $addModal = model.createModal({"class":"addModal","title":"","hasSaveBtn":"1"});
				if(options.addModelApi){
					$container.on("addModelDataLoaded",function(){
						model.modelForm($addModal.find(".modal-body"),addModelData);
					})
				}else{
					$container.on("modelDataLoaded",function(){
						model.modelForm($addModal.find(".modal-body"),modelData);
					})
				};
			}
			//search模型加载
			if(options.search){
				if(options.searchModelApi){
					$container.on("searchModelDataLoaded",function(){
						$search = model.modelSearch($container,searchModelData);
						$container.trigger("searchComplete");
					})
				}else{
					$container.on("modelDataLoaded",function(){
						$search = model.modelSearch($container,modelData);
						$container.trigger("searchComplete");	
					})
				};
			}else{
				$container.on("modelDataLoaded",function(){
					$search = $("<div>",{"class":"search-form row form-horizontal"});
					$search.appendTo($container);
					$container.trigger("searchComplete");
				})
			}
			
			//搜索加载完毕，附加按钮
			$container.on("searchComplete",function(){
				if(options.btnGroupInline){
					$btnGroup = $("<div>",{"class":"text-right pr15 pull-right"});
				}else{
					$btnGroup = $("<div>",{"class":"col-sm-12 pull-right"});
				}
				
				if(options.search){
					//查询按钮
					var $searchBtn = $("<button>",{"class":"btn btn-success ml15 pull-right searchBtn"}).text(" " + options.searchBtnText).prepend($("<span>",{"class":"glyphicon glyphicon-search"}));
	
					//查询按钮事件
					$searchBtn.on("click",function(){
						$table.bootstrapTable("refresh",{pageNumber:1});
					});
					
					if(options.advSearchBtnText){
						//创建高级搜索
						var $advSearch = model.modelAdvSearch($search,options.searchModelApi ? searchModelData : modelData);
						var $advSearchBtn = $("<button>",{"class":"btn btn-link pull-right"}).text("[" + options.advSearchBtnText + "]");
						//高级查询按钮事件
						$advSearch = $search.find(".advSearch-form");
						$advSearch.css({height:$advSearch.height()}).hide();
						$advSearchBtn.on("click",function(){
							if($advSearch.is(":hidden")){
								$advSearch.slideDown();
							}else{
								$advSearch.slideUp();
								model.resetForm($advSearch);
							}
						});
						//处理高级查询异步select
						var advSearchRefreshAsyn = function(){
							$advSearch.find("select[data-asyn=asyn]").trigger("refreshData");
							$advSearchBtn.off("click",advSearchRefreshAsyn);
						}
						$advSearchBtn.on("click",advSearchRefreshAsyn);
						
						$btnGroup.append($advSearchBtn).append($searchBtn);
					}else{
						$btnGroup.append($searchBtn);
					};
					model.events($search);
					
				}
				
				//列表参数
				if(modelData){
					$container.trigger("listConfigComplete");
				}else{
					$container.on("modelDataLoaded",function(){
						$container.trigger("listConfigComplete");
					})
				}
				
				//按钮组容器
				$btnGroup.appendTo($search);
				$search.append($("<div>",{"class":"clearfix"}));
				
				//增加按钮
				var $addBtn;
				if(options.addBtn){
					$addBtn = $("<button>",{"class":"btn btn-info pull-left"}).text(" " + options.addBtnText).prepend($("<span>",{"class":"glyphicon glyphicon-plus"}));
					$btnGroup.append($addBtn)
					if(options.addOnTable){
						if(modelData){
							model.addOnTabelEvent($addBtn,$table,$container,modelData)
						}else{
							$container.on("modelDataLoaded",function(){
								model.addOnTabelEvent($addBtn,$table,$container,modelData)
							})
						}
					}else{
						$addBtn.on("click",function(){
							var $modal = $addModal || $formModal;
							var list = addModelData || modelData;
							var defaultViewData = {};
							model.resetForm($modal);
							$.each(list,function(index,obj){
								defaultViewData[obj.field] = obj.defaultValue || "";
							})
							model.getFormData({
								url:defaultViewData,
								container:$modal.find(".modal-body"),
								model:list,
								special:options.contentType === "application/json" && options.method === "post",
							})
							
							$modal.find("h4.modal-title").text(options.addModalTitle);
							$modal.modal("show");
							$modal.find("button.save").off("click").on("click",function(){
								model.sendForm({
									url:options.addApi,
									contentType:options.contentType,
									method:options.method,
									container:$modal,
									loadingContainer:$("body"),
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
				}
				
				//处理按钮附加list
				if(options.btnAddList && $.isArray(options.btnAddList)){
					$.each(options.btnAddList, function(index,obj) {
						var $btn = $("<button>",{"class":obj.class,"style":obj.css}).text(" "+obj.text);
						if(obj.icon){
							$("<span>",{"class":obj.icon}).prependTo($btn);
						}
						$btn.appendTo($btnGroup);
						$btn.before("&nbsp;").after("&nbsp;");
						$.each(obj.events, function(key,value) {
							$btn.on(key,value);
						});
					});
				}
				$btnGroup.append($("<div>",{"class":"clearfix"}));
			})
			
			//创建表格列表
			if(options.listApi || options.listData){
				$container.on("listConfigComplete",function(){
					listConfig = {
						"options": options,
						"modelData" : modelData,
						"addModelData" : addModelData,
						"editModelData" : editModelData,
						"viewModelData" : viewModelData,
						"formModal" : $formModal,
						"addModal" : $addModal,
						"viewModal" : $viewModal,
						"searchForm" : $search
					}
					$table = model.modelTableList($container,listConfig);
					//搜索表单回车事件
					model.enterForSearch($search,$table);
				})
			}
			
			
			$.each(["Search","Add","Edit","View",""],function(index,value){
				var str = value=="" ? "m" : value.toLowerCase()+"M";
				$container.on(str+"odelDataLoaded",function(){
					if(options["on"+value+"ModelLoaded"] && $.isFunction(options["on"+value+"ModelLoaded"])){
						options["on"+value+"ModelLoaded"]({
							"formModal" : $formModal,
							"addModal" : $addModal,
							"viewModal" : $viewModal,
							"searchForm": $search,
							"btnGroup":$btnGroup,
							"table":$table,
							"modelData" : modelData,
							"addModelData":addModelData,
							"editModelData":editModelData,
							"viewModelData":viewModelData,
							"searchModelData":searchModelData,
						});
					}
				})
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
			
			//请求主模型
			model.getModel(options.modelApi,$container,function(data){
				modelData = data;
				$container.trigger("modelDataLoaded");
			})
			
			//销毁事件,暂时替代方案
			$container.on("modelManagerDestroy",function(){
				if($addModal){
					$addModal.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
					$addModal.find("input[formtype=editor]").each(function(index){
						this.um.destroy();
					})
					$addModal.remove();
				}
				if($formModal){
					$formModal.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
					$formModal.find("input[formtype=editor]").each(function(index){
						this.um.destroy();
					})
					$formModal.remove();
				}
				if($viewModal){
					$viewModal.remove();
				}
			});
			
		},
		//增加按钮绑定addOnTableMode事件
		addOnTabelEvent:function($addBtn,$table,$container,modelData){
			var model = this,getModel = function(field){
				var model;
				$.each(modelData,function(index,obj){
					if(obj.field == field){model = obj;}
				})
				return model;
			};
			$addBtn.on("click",function(){
				if(!$table.hasClass("edit-status")){
					model.enterEditMode($table,$container.find(".fixed-table-toolbar"));
				}
				var $tr = $("<tr>",{"class":"new-tr"}).appendTo($table.find("tbody"));
				$.each($table.find("thead th"), function() {
					if($(this).attr("data-field") != "oprate"){
						var $td = $("<td>").appendTo($tr),field = $(this).attr("data-field");
						var obj = getModel(field)
						var $tableForm = $("<div>",{"class":"row form-horizontal"}).append(model.createDiv($.extend(obj,{labelCols:1,cols:13}),"form").removeClass("mb15").addClass("editOnTableForm")).appendTo($td)
						var $input = model.createInput($tableForm.find(".editOnTableForm"),obj);
						if($input){$tableForm.find(".editOnTableForm").append($input)};
						$tableForm.find(".mb15").removeClass("mb15");
						model.events($tableForm);
						model.verEvents($tableForm.parent());
					}else{
						$("<td>",{"class":"text-center"}).appendTo($tr).append($("<button>",{"class":"btn btn-danger btn-xs remove-tr"}).html(" 删除本行").prepend($("<span>",{"class":"glyphicon glyphicon-remove"})).on("click",function(){
							$tr.remove();
						}));
					}
				});
			})
			
		},
		//指定容器（模态框）中生成模型表单
		modelForm : function($this,list){
			var model = this;
			if($.isArray(list)){
				model.colsWrap(function($container,obj){
					if(obj.isOnlyread == "2"){
						model.createView($container,obj);
					}else{
						model.createForm($container,obj);
					}
				},function(obj){
					if(!parseInt(obj.isHidden)){return true;}else{return false;}
				},list,$this);
				model.events($this);
				model.verEvents($this);
			}
		},
		//指定容器（模态框）中生成模型显示框架
		modelView:function($this,list){
			var model = this;
			if($.isArray(list)){
				model.colsWrap(function($container,obj){
					model.createView($container,obj);
				},function(obj){
					if(!parseInt(obj.isHidden)){return true;}else{return false;}
				},list,$this);
			}
		},
		//指定容器中生成搜索模型表单
		modelSearch : function($this,list){
			var model = this;
			var $search = $("<div>",{"class":"search-form row form-horizontal"});
			$search.prependTo($this);
			if($.isArray(list)){
				model.colsWrap(function($c,obj){
					model.createForm($c,obj,"search");
				},function(obj){
					if(!parseInt(obj.isHidden) && parseInt(obj.search) == 2){return true;}else{return false;}
				},list,$search);
			}
			return $search;
		},
		//指定容器中生成高级搜索模型表单
		modelAdvSearch : function($this,list){
			var model = this;
			var $advSearch = $("<div>",{"class":"advSearch-form"});
			$advSearch.appendTo($this);
			if($.isArray(list)){
				model.colsWrap(function($container,obj){
					model.createForm($container,obj,"search");
				},function(obj){
					if(!parseInt(obj.isHidden) && parseInt(obj.search) == 1){return true;}else{return false;}
				},list,$advSearch);
			}
			$advSearch.append($("<div>",{"class":"clearfix"}));
			return $advSearch;
		},
		//生成数据列表
		modelTableList : function($this,obj){
			var options = obj.options,model = this;
			var $table = $("<table>",{"class":"table"});
			this.customSetting.table = $table;
			$table.appendTo($this);
			//处理显示列
			var column = model.createColumns(obj.modelData,options.formatter);
			if(options.checkbox){column.unshift({checkbox: true})};
			if(options.oprate){
				//构造删改查按钮
				var oprateHtml = function(value,row,index){
					html = ""
					if(options.viewApi && options.viewBtn && options.viewBtn(row,index)){
						html += '<button type="button" class="btn btn-success btn-xs view"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> '+options.viewBtnText+'</button>';
					}
					if(options.examineBtn && options.examineBtn(row,index)){
						html += ' <button type="button" class="btn btn-warning btn-xs examine"><span class="glyphicon glyphicon-sort" aria-hidden="true"></span> '+options.examineBtnText+'</button>';
					}
					if(options.editApi && options.editBtn && options.editBtn(row,index)){
						html += ' <button type="button" class="btn btn-info btn-xs edit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> '+options.editBtnText+'</button>';
					}
					if(options.deleteApi && options.deleteBtn && options.deleteBtn(row,index)){
						html += ' <button type="button" class="btn btn-danger btn-xs delete"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> '+options.deleteBtnText+'</button>';
					}
					html += options.oprateAdd(row,index) || "";
					return html;
				}
				//绑定默认删/改/查事件
				var oprateEvents = {};
				if(options.viewApi && options.viewBtn){
					oprateEvents['click .view'] = function(e,value,row,index){
						obj.viewModal.modal("show");
						obj.viewModal.find(".value-control").empty();
						obj.viewModal.find(".modal-footer button.save").remove();
						obj.viewModal.find(".examine-form").remove();
						obj.viewModal.find("h4.modal-title").text(options.viewModalTitle);
						var queryData = {};
						queryData[options["idKey"]] = row[options.idKey];
						model.getFormData({
							"url":options.viewApi,
							"data":queryData,
							"dataField":options.viewDataField,
							"container":obj.viewModal,
							"model":obj.viewModelData || obj.modelData,
							"special":options.contentType === "application/json" && options.method === "post",
							"callback":options.onViewDataLoaded
						})
						
					}
				}
				if(options.examineBtn){
					oprateEvents['click .examine'] = function(e,value,row,index){
						obj.viewModal.modal("show");
						obj.viewModal.find(".value-control").empty();
						obj.viewModal.find(".modal-footer button.save").remove();
						obj.viewModal.find("h4.modal-title").text(options.examineModalTitle);
						var queryData = {};
						queryData[options["idKey"]] = row[options.idKey];
						model.getFormData({
							"url":options.viewApi,
							"data":queryData,
							"dataField":options.viewDataField,
							"container":obj.viewModal.find(".modal-body"),
							"model":obj.viewModelData || obj.modelData,
							"special":options.contentType === "application/json" && options.method === "post",
							"callback":options.onExamineDataLoaded
						})
						obj.viewModal.find(".examine-form").remove();
						var $examineForm = $("<div>",{"class":"examine-form"}).insertAfter(obj.viewModal.find(".modal-body"));
						var $saveBtn = $("<button>",{"class":"btn btn-success save"}).html("确定").appendTo(obj.viewModal.find(".modal-footer"));
						if(options.examineModelApi){
							model.modelSimple("add",$examineForm,{modelApi:options.examineModelApi});
							$saveBtn.on("click",function(){
								$.ajax({
									data:$.extend($examineForm.sendForm(),queryData),
									url:typeof(options.examineApi) == "function" ? options.examineApi(row,$examineForm.sendForm()) : options.examineApi || null,
									suc:function(data){
										$.alert(data.errmsg,"success",function(){
											obj.viewModal.modal("hide");
											$table.bootstrapTable("refresh");
										})
									},
									hasAlert:true,
									loadingContainer:$("body")
								})
							})
						}
						$examineForm.prepend($("<div>",{"class":"clearfix"}));
						
					}
				}
				if(options.editApi && options.editBtn){
					oprateEvents['click .edit'] = function(e,value,row,index){
						obj.formModal.modal("show");
						obj.formModal.resetForm();
						obj.formModal.find(".modal-footer button.save").show();
						obj.formModal.find("h4.modal-title").text(options.editModalTitle);
						_formdata = {};
						_formdata[options["idKey"]] = row[options.idKey];
						model.getFormData({
							url:options.editModelViewApi || options.viewApi,
							data:_formdata,
							dataField:options.viewDataField,
							container:obj.formModal,
							model:obj.editModelData || obj.modelData,
							special:options.contentType === "application/json" && options.method === "post",
							callback:options.onEditDataLoaded
						})
						
						obj.formModal.find("button.save").off("click").on("click",function(){
							model.sendForm({
								url:options.editApi,
								method:options.method,
								contentType:options.contentType,
								container:obj.formModal,
								loadingContainer:$("body"),
								beforeSend:function(data){
									data = $.extend(data,_formdata);
									return data;
								},
								callback:function(data){
									$table.bootstrapTable("refresh");
								}
							})
							
						})
					}
				}
				if(options.deleteApi && options.deleteBtn){
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
										$table.bootstrapTable("refresh");
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
				extendData:options.extendData,
				data:options.listData,
				dataField:options.listDataField,
				contentType:options.contentType,
				simpleSort:options.simpleSort,
				pageList:options.pageList,
				columns:column,
				showRefresh: options.showRefresh,
				showColumns: options.showColumns,
				pageSize:options.pageSize,
				searchFormList:obj.searchForm.find(".form-control:not(.prevent)"),
				pagination:obj.pagination,
				cardView:false,
				onLoadSuccess:function(data){
					obj.searchForm.trigger("listDataLoaded");
					model.exitEditMode($table,$this.find(".fixed-table-toolbar"))
					if(options.onListLoaded && $.isFunction(options.onListLoaded)){
						options.onListLoaded($.extend(obj,{"data":data}));
					}
				},
				onPostBody:function(){
					$table.find("input.switch").each(function(){
						$(this).bootstrapSwitch({size:"mini",onColor:"success",onText:$(this).attr("data-onText"),offText:$(this).attr("data-offText")}).on("switchChange.bootstrapSwitch",function(event,state){
							var field = $(this).attr("data-field");
							var formData = {};
							formData[options.idKey] = $(this).attr("data-id");
							formData[$(this).attr("data-field")] = state == true ? $(this).attr("data-onvalue") : $(this).attr("data-offvalue");
							$.ajax({
								url:options.editApi,
								data:formData,
								suc:function(data){
									$table.bootstrapTable("refresh");
								},
								hasAlert:true,
								loadingContainer:$("body")
							});
						})
					})
				}
			})
			return $table;
		},
		modelSimple:function(type,$container,options){
			options = $.extend({
				method:"post",
				data:{},
				modelApi:"",
				saveApi:"",
				viewApi:"",
				viewDataField:"",
				saveBtn:null,
				callback:function(data){
					
				}
			},options);
			var modelData,model = this;
			
			$container.addClass("form-horizontal").empty();
			if(options.modelApi){
				$container.on(type+"modelDataLoaded",function(){
					if(type == "view"){
						model.modelView($container,modelData)
						model.getFormData({
							url:options.viewApi,
							dataField:options.viewDataField,//数据字段
							container:$container,
							model:modelData,
							special:options.contentType === "application/json" && options.method === "post",
						})
					}else if(type== "edit" || type == "add"){
						model.modelForm($container,modelData)
						if(type == "edit"){
							model.getFormData({
								url:options.viewApi,
								dataField:options.viewDataField,
								container:$container,
								model:modelData,
								special:options.contentType === "application/json" && options.method === "post",
							})
						}else if(type == "add"){
							var viewData = {};
							$.each(modelData,function(index,obj){
								viewData[obj.field] = obj.defaultValue || "";
							})
							model.getFormData({
								url:viewData,
								container:$container,
								model:modelData,
								special:options.contentType === "application/json" && options.method === "post",
							})
						}
					}
				})
			}
			//绑定保存事件
			if(options.saveBtn){
				options.saveBtn.off("click").on("click",function(){
					model.sendForm({
						url:options.saveApi,
						data:options.data,
						contentType:options.contentType,
						method:options.method,
						container:$container,
						loadingContainer:$("body"),
						callback:function(data){
							if(options.callback && $.isFunction(options.callback)){
								options.callback(data);
							}
						}
					})
				})
			}
			
			model.getModel(options.modelApi,null,function(data){
				modelData = data;
				$container.trigger(type+"modelDataLoaded");
			})
			$container.on("modelManagerDestroy",function(){
				if(options.saveBtn){
					options.saveBtn.off("click");
				}
				$container.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
				$container.empty();
				$container.off();
			})
		}
	}
	$.fn.modelManager = function(options,object){
		var $container = $(this);
		var random = parseInt(Math.random() * 1000);
		switch(options){
			//销毁
			case "destroy":
				$container.find("input.datetimePicker,input.timePicker,input.datePicker").datetimepicker("remove");
				$container.find("input[formtype=editor]").each(function(index){this.um.destroy();})
				$container.trigger("modelManagerDestroy");
				$container.empty();
				$container.off();
				return $container;
			break;
			//获得所选
			case "getSelections":
				return $container.find("table.table").bootstrapTable('getSelections');
			break;
			//刷新表格
			case "refreshList":
				$container.find("table.table").bootstrapTable("refresh")
			break;
			case "add":
				model.modelSimple("add",$container,$.extend({},object,{"random":random}))
			break;
			case "edit":
				model.modelSimple("edit",$container,$.extend({},object,{"random":random}))
			break;
			case "view":
				model.modelSimple("view",$container,$.extend({},object,{"random":random}))
			break;
			//调用主方法
			default:
				if(options && typeof(options) == "object"){
					$container.each(function(){
						random = parseInt(Math.random() * 1000);
						model.modelManager($(this),$.extend({},options,{"random":random}));
					})
				}
		}
		return $container;
	};
	$.publicModel = function(bool){
		if(bool){
			$.model = model;
		}else{
			delete $.model;
		}
	}
	$.fn.uploadInit = function(options){
		$(this).each(function(){
			uploadClass.uploadInit($(this),options);
		})
	}
})(jQuery);
