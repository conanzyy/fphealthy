<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>规则管理</title>
<link rel="stylesheet" href="./css/jquery.ui.all.css" />
<script src="./js/jquery-1.10.2.js"></script>
	<script src="./js/jquery.ui.core.js"></script>
	<script src="./js/jquery.ui.widget.js"></script>
	<script src="./js/jquery.ui.tabs.js"></script>
	<script src="./js/jquery.ui.button.js"></script>
	<script src="./js/jquery.ui.accordion.js"></script>
	<script src="./js/jquery.ui.mouse.js"></script>
	<script src="./js/jquery.ui.draggable.js"></script>
	<script src="./js/jquery.ui.position.js"></script>
	<script src="./js/jquery.ui.resizable.js"></script>
	<script src="./js/jquery.ui.dialog.js"></script>
	<script src="./js/jquery.ui.effect.js"></script>
	<link rel="stylesheet" href="./css/demos.css" />
	<style>
		body { font-size: 62.5%; }
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
		#replyType li{list-style-type:none;float:left;width:60px;cursor:pointer;}
		.font{color:blue;}
	</style>
	<script>
	$(function() {
		$( "#tabs" ).tabs({active:2});
		$( "input[type=button]" )
		.button()
		.click(function( event ) {
			event.preventDefault();
		});
		$( "#accordion" ).accordion({
			collapsible: true
		});
		
		
		var ruleName = $( "#ruleName" ),
		keyWord = $( "#keyWord" ),
		allFields = $( [] ).add( ruleName ).add( keyWord ),
		tips = $( ".validateTips" );

	function updateTips( t ) {
		tips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			tips.removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}

	function checkLength( o, n, min, max ) {
		if ( o.val().length >= 60 || o.val().length ==0) {
			o.addClass( "ui-state-error" );
			updateTips( n + " 的字符不能超过60个," + "且必填." );
			return false;
		} else {
			return true;
		}
	}

	function checkRegexp( o, regexp, n ) {
		if ( !( regexp.test( o.val() ) ) ) {
			o.addClass( "ui-state-error" );
			updateTips( n );
			return false;
		} else {
			return true;
		}
	}

	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 500,
		width: 550,
		modal: true,
		buttons: {
			"保存": function() {
				var bValid = true;
				allFields.removeClass( "ui-state-error" );

				bValid = bValid && checkLength( ruleName, "规则名", 3, 16 );
				bValid = bValid && checkLength( keyWord, "关键字", 6, 80 );

				if ( bValid ) {
					var fileName =  $("#file").val();
					if(fileName)
					{
						var seat=fileName.lastIndexOf(".");
						var extension=fileName.substring(seat+1).toLowerCase();
						if((extension != "jpg" && extension != "jpeg" && extension != "png" && extension != "gif")){
							alert("请选择图片文件上传!");
							return false;
						}
					}
					$("#actionForm").submit();
					$( this ).dialog( "close" );
				}
				else
				{
					return false;
				}
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});

	$( "#create-rule" )
		.button()
		.click(function() {
			$( "#dialog-form" ).dialog( "open" );
		});
	});
	function selectChange(value)
	{
		if($("input[name='reply']:checked").size()<5)
		{
			$("#checkAll").prop('checked',false);
		}
		else
		{
			$("#checkAll").prop('checked',true);
		}
		if(value == 0)
		{
			if($("input[name='reply']:eq(0)").prop("checked"))
			{
				$("#word").show();
			}
			else
			{
				$("#word").hide();
			}
		}
		else if(value == 1)
		{
			if($("input[name='reply']:eq(1)").prop("checked"))
			{
				$("#picture").show();
			}
			else
			{
				$("#picture").hide();
			}
		}
		else if(value == 2)
		{
			if($("input[name='reply']:eq(2)").prop("checked"))
			{
				$("#voice").show();
			}
			else
			{
				$("#voice").hide();
			}
		}
		else if(value == 3)
		{
			if($("input[name='reply']:eq(3)").prop("checked"))
			{
				$("#video").show();
			}
			else
			{
				$("#video").hide();
			}
		}
		else if(value == 4)
		{
			if($("input[name='reply']:eq(4)").prop("checked"))
			{
				$("#pictureWord").show();
			}
			else
			{
				$("#pictureWord").hide();
			}
		}
	}
	function handleAll()
	{
		if($("#checkAll").prop('checked'))
		{
			$("input[name='reply']").prop("checked",true);
		}else{
			$("input[name='reply']").prop("checked",false);
		}
	}
	</script>
</head>
<body>
<div id="tabs" style="width:1000px;">
	<ul>
		<li><a href="#tabs-1">被添加自动回复</a></li>
		<li><a href="#tabs-2">消息自动回复</a></li>
		<li><a href="#tabs-3">关键词自动回复</a></li>
	</ul>
	<div style="position:absolute;top:10px;right:100px;">公众平台如何设置消息自动回复 ?</div>
	<div id="tabs-1">
		<p>to do1.</p>
	</div>
	<div id="tabs-2">
		<p>to do2.</p>
	</div>
	<div id="tabs-3">
		<input type="button" value="+ 添加规则" id="create-rule"/>
		

	<c:choose>
		<c:when test="${empty rules}"><div style="width:600px;height:200px;margin-top:20px;">无规则数据</div></c:when>
		<c:otherwise>
<div id="accordion">
	<c:forEach  var="rule" items="${rules}" varStatus="status" step="1">
		<h3>${rule.ruleName}&nbsp;&nbsp;${status.index+1}</h3>
		<div>
			<div style="float:left;">
				<div style="float:left;">关键词</div><div style="float:left;">&nbsp;&nbsp;${rule.keyWord}</div>
			</div>
			<div style="clear:both"></div>
			<div style="float:left;">
				<div style="float:left;">回复</div><div style="float:left;">&nbsp;&nbsp;${rule.isWordReply+rule.isPictureReply+rule.isVoiceReply+rule.isVideoReply+rule.isPictureWordReply}(${rule.isWordReply}条文字,${rule.isPictureReply}条图片,${rule.isVoiceReply}条语音,${rule.isVideoReply}条视频,${rule.isPictureWordReply}条图文)</div>
			</div>
			<div style="clear:both"></div>
		</div>
	</c:forEach>	
</div>
</c:otherwise></c:choose>
	</div>
</div>

<div id="dialog-form" title="创建规则">
	<p class="validateTips"></p>

	<form action="<%=request.getContextPath()%>/addRule.do" method="post" enctype="multipart/form-data" id="actionForm">
	<fieldset>
		<label for="name">规则名</label>
		<input type="text" name="ruleName" id="ruleName" class="text ui-widget-content ui-corner-all" />
		<label for="keyWord">关键字</label>
		<input type="text" name="keyWord" id="keyWord" value="" class="text ui-widget-content ui-corner-all" />
		<label for="reply" style="display:inline;">回复</label>
		<input type="checkbox" name="checkAll" id="checkAll" style="display:inline;" onclick="handleAll()"/>回复全部
		<ul id="replyType">
			<li><input type="checkbox" name="reply" style="display:inline;" onclick="selectChange('0')" value="0"/>文字</li>
			<li><input type="checkbox" name="reply" style="display:inline;" onclick="selectChange('1')" value="1"/>图片</li>
			<li><input type="checkbox" name="reply" style="display:inline;" onclick="selectChange('2')" value="2"/>声音</li>
			<li><input type="checkbox" name="reply" style="display:inline;" onclick="selectChange('3')" value="3"/>视频</li>
			<li><input type="checkbox" name="reply" style="display:inline;" onclick="selectChange('4')" value="4"/>图文</li>
		</ul>
		<div style="clear:both;"></div>
		<div id="word" style="display:none;">
		文字:<input type="text" name="wordContent"/>
		</div>
		<div id="picture" style="display:none;">
		图片:<input type="file" name="file" id="file"/>
		</div>
		<div id="voice" style="display:none;">
		声音:<input type="file"  name="file" id="file1"/>
		</div>
		<div id="video" style="display:none;">
		视频:<input type="file"  name="file" id="file2"/>
		</div>
		<div id="pictureWord" style="display:none;">
		图文:<div><input type="text" name="wordContent1"/></div>
			<div><input type="file" name="file" id="file3"/></div>
		</div>
	</fieldset>
	</form>
</div>
</body>
</html>