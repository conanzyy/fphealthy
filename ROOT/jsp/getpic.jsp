<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/photobooth_min.js"></script>
		<script type="text/javascript" src="js/jquery-ui.js"></script>
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<title>拍照</title>
<body>
<style type="text/css">
article,aside,dialog,footer,header,section,footer,nav,figure,menu{display:block}
html,body{ 
margin:0px; 
height:100%; 
}
.title {
	position: relative;
	background-color: #F4F4F4;
	height: 72px;
	border-bottom:1px solid #CECDCD;
}
.fonttitle {
	font-family: 黑体;
	text-align: center;
	margin-top: -48px;
	margin-left: 16px;
	font-size: 24px;
}
.piccontent{
	background-color: #F4F4F4;
	height: 100%;
}
.picafter{
	position: relative;
	background-color: #F9F8F8;
	height: 160px;
}
.bottom{
	position: relative;
	background-color:#E4E4E4;
	height: 46px;
}
.pic {
	margin-top: 10px;
	cursor: pointer;
}
.gallery {
	height:60%; 
}
</style>
<script>
function opencamera(obj){
	obj.disabled="disabled";
	$("#chooseF").attr("disabled","disabled");
	if(obj.innerHTML=='拍照'){
	$('.piccontent').photobooth().on("image",function(event,dataUrl){
	$('.piccontent').hide();
	$("#pic").append('<img src="' + dataUrl + '" >');
	$( '.piccontent' ).data( "photobooth" ).pause();
	$("#quedin").removeAttr("disabled");
	$("#chooseF").removeAttr("disabled");
	obj.disabled=false;
	obj.innerHTML='<font color="red">重拍</font>';
});
}else if(obj.innerHTML=='<font color="red">重拍</font>'){
	$("#pic").empty();
	$("#quedin").attr("disabled","disabled");
	$("#chooseF").attr("disabled","disabled");
	$('.piccontent').show();
	$( '.piccontent' ).data( "photobooth" ).resume();
}
}
function chooseFile(){
	$("#fileElem").click();
	$('.piccontent').hide();
}
</script>
	<div class="title">
	<img src="img/zuojiantou.jpg" class="pic" style="margin-top:0px;"/>
	<div class="fonttitle">
				工作详情
			</div>
		<img src="img/fangzi.jpg" class="pic" style="top:-10px;position: absolute; right: 10px;"/>
	</div>
	<div class="gallery">
	<div class="piccontent"  style="text-align:center;">
	</div>
	<div id="pic" style="text-align:center;">
	</div>
	<script>
		window.URL = window.URL || window.webkitURL;
	var fileElem = document.getElementById("fileElem");
	 var pic = document.getElementById("pic");
	function handleFiles(obj) {
		var files = obj.files,
			img = new Image();
		if(window.URL){
			//File API
			 // alert(files[0].name + "," + files[0].size + " bytes");
		      img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
		      img.width = 400;
		      img.onload = function(e) {
		         window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
		      }
		      $("#pic").empty();
		      pic.appendChild(img);
		}else if(window.FileReader){
			//opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onload = function(e){
				//alert(files[0].name + "," +e.total + " bytes");
				img.src = this.result;
				img.width = 400;
				$("#pic").empty();
				pic.appendChild(img);
			}
		}else{
			//ie
			obj.select();
			obj.blur();
			var nfile = document.selection.createRange().text;
			document.selection.empty();
			img.src = nfile;
			img.width = 400;
			img.onload=function(){
		      //alert(nfile+","+img.fileSize + " bytes");
		    }
		    $("#pic").empty();
			pic.appendChild(img);
			//pic.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src='"+nfile+"')";
		}
	}
</script>
	</div>
	<div class="picafter">
	 <div class="container-fluid">
	<div class="row-fluid" style="margin-top:28px;">
		<div class="span12" style="text-align:center;">
			 <button class="btn" type="button" style="width:80%;" onclick="opencamera(this);">拍照</button><br/>
			 <input style="display:none;" type="file" id="fileElem" multiple accept="image/*"  onchange="handleFiles(this)">
			 <button class="btn" id="chooseF" type="button" style="width:80%;margin-top:14px;" onclick="chooseFile();">从相册中选择</button><br/>
			 <button class="btn" type="button" disabled="disabled" id="quedin" style="width:80%;margin-top:14px;" onclick="">确定</button>
		</div>
	</div>
</div>
	</div>
	<div class="bottom">
	<img src="img/huijiandou.jpg" class="pic" style="margin-top:0px;margin-left:0px;"/>
	<img src="img/bofang.jpg" class="pic" style="position: absolute;left:50%;margin-left:-25px;margin-top:0px;"/>
	<img src="img/lajixiang.jpg" class="pic" style="position: absolute;right:0px;margin-top:0px;"/>
	</div>
	</body>
</html>