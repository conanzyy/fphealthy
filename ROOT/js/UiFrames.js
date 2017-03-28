
//页面自适应浏览器，分辨率
function screenAdape(obj,mLeft,mRight,mTop,mBottom)		//参数说明 obj为要自适应的元素,mLeft,mRight,mTop,mBottom分别为元素的左、右、上、下的边距
{
	var oclientW=document.documentElement.clientWidth;
	var oclientH=document.documentElement.clientHeight;
	
	obj.style.width=oclientW-(mLeft+mRight)+'px';
	obj.style.height=oclientH-(mTop+mBottom)+'px';
	obj.style.marginLeft=mLeft+'px';
	obj.style.marginTop=mTop+'px';
};
function screenAdapeJq(obj,mLeft,mRight,mTop,mBottom)		//参数说明 obj为要自适应的元素,mLeft,mRight,mTop,mBottom分别为元素的左、右、上、下的边距
{
	var oclientW=document.documentElement.clientWidth;
	var oclientH=document.documentElement.clientHeight;
	
	//obj.css({width:oclientW-(mLeft+mRight),height:oclientH-(mTop+mBottom),marginLeft:mLeft,marginTop:mTop,marginRight:mLeft,marginBottom:mTop});
	obj.css({width:obj.parent().parent().outWidth(),height:obj.parent().parent().outHeight(),marginLeft:mLeft,marginTop:mTop,marginRight:mLeft,marginBottom:mTop});
	
};

//元素移动(需要结合jquery)
//参数obj为要运动的元素，lrtb是left,right,top,bottom的简写，传left代表向左移动，其他同理,value为运动的数值大小，speedtime为运动总时间
function timeMoveUp(obj,lrtb,value,speedtime)	
{
	switch (lrtb)
	{
		case 'left':
		obj.animate({left:-value+'px'},speedtime);
		break;
		case 'right':
		obj.animate({left:value+'px'},speedtime);
		break;
		case 'top':
		obj.animate({top:-value+'px'},speedtime);
		break;
		case 'bottom':
		obj.animate({top:value+'px'},speedtime);
		break;
	};
};

//元素运动，透明度，淡入淡出，可以在不用jquery的情况下，实现一些简单效果
function smove(obj,json,fn)
{
	clearInterval(obj.timer);
	obj.timer=setInterval(function (){
		var bstop=true;
		for(var attr in json)
		{	
			var icur=0;
			if(attr=='opacity')
			{
				icur=parseInt(parseFloat(getStyle(obj,attr))*100);
			}
			else
			{
				icur=parseInt(getStyle(obj,attr));
			};
			var speed=(json[attr]-icur)/9;
			speed>0?speed=Math.ceil(speed):speed=Math.floor(speed);
			if(icur!=json[attr])
			{
				bstop=false;
			};
			if(attr=='opacity')
			{
				obj.style.filter="alpha(opacity:"+(icur+speed)+")";
				obj.style.opacity=(icur+speed)/100;
			}
			else
			{
				obj.style[attr]=icur+speed+"px";;
			};
		};
		if(bstop)
		{
			clearInterval(obj.timer);
			if(fn)
			{
				fn();
			};
		};
	},30);
};


//选择元素的方便函数，用$$带走代替document.getElementById
function $$(id)
{
	return document.getElementById(id);
};


//元素拖拽
//cross  为横向拖拽开关，传true或者false，	vertical为竖向拖拽开关，传true或者false，
function drag(obj,cross,vertical)			
{
	obj.onmousedown=function (ev)
	{
		var oev=ev||event;
		var disX=oev.clientX-obj.offsetLeft;
		var disY=oev.clientY-obj.offsetTop;
		
		if(obj.setCapture)
		{
			obj.onmousemove=fnMove;
			obj.onmouseup=fnUp;
			obj.setCapture();
		}else 
		{
			document.onmousemove=fnMove;
			document.onmouseup=fnUp;
		};
		function fnMove(ev)
		{
			var oev=ev||event;
			var l=oev.clientX-disX;
			var t=oev.clientY-disY;
			if(l<0)							//X轴控制
			{
				l=0;
			}else if(l>document.documentElement.clientWidth-obj.offsetWidth)
			{
				l=document.documentElement.clientWidth-obj.offsetWidth;
			};
			if(t<0)							//Y轴控制
			{
				t=0;
			}else if(t>document.documentElement.clientHeight-obj.offsetHeight)
			{
				t=document.documentElement.clientHeight-obj.offsetHeight;
			};
			if(cross)
			{
				obj.style.left=l+'px';
			}else
			{
			//	obj.style.left=0+'px';
			};

			if(vertical)
			{
				obj.style.top=t+'px';
			}
			else
			{
				//return;
			//	obj.style.top=0+'px';
			};
		};
		function fnUp()
		{
			this.onmousemove=null;
			this.onmouseup=null;
			if(this.releaseCapture)
			{
				obj.releaseCapture();
			};
		};
		return false;
	};
};

//通过class样式名获取元素
function getByClass(oparent,Oclass)			//第一个参数为父级元素,第二个参数为要通过class类名获得的元素
{
	var osele=oparent.getElementsByTagName("*");
	//alert(osele.length);
	var ore=[];
	for(var i=0;i<osele.length;i++)
	{
		if(osele[i].className==Oclass)
		{
			ore.push(osele[i]);
		};
	};
	return ore;
};

//获得样式值
function getStyle(obj,attr)
{
	if(obj.currentStyle)
	{
		return obj.currentStyle[attr];
	}else
	{
		return getComputedStyle(obj,false)[attr];
	};
};

//页面时间
function siteTime(odiv)
{
	//var odivriqi=document.getElementById("top_b_r");
	var timer=new Date();
	var y=timer.getFullYear();
	var m=timer.getMonth()+1;
	var ri=timer.getDate();
	var xq=timer.getDay();
	var tarr=['日','一','二','三','四','五','六'];
	
	switch (xq)
	{
		case 0:
		xq=tarr[0];
		break;
		case 1:
		xq=tarr[1];
		break;
		case 2:
		xq=tarr[2];
		break;
		case 3:
		xq=tarr[3];
		break;
		case 4:
		xq=tarr[4];
		break;
		case 5:
		xq=tarr[5];
		break;
		case 6:
		xq=tarr[6];
		break;
	};
	var ntime=y+"年"+m+"月"+ri+"日"+"星期"+xq;		//这里设置的时间格式
	odiv.text(ntime);
};

//带有自定义滚动条的页面,拖动滚动条对应内容也滚动
//objD为要拖动的滚动条元素，innerObj为要运动的内容，bigdiv为外面div
function onDragMoveInner(objD,innerObj,bigdiv)
{
	var ohei=bigdiv.offsetHeight-objD.offsetHeight;
	//alert(ohei);
	objD.onmousedown=function (ev)
	{
		var oev=ev||event;
		var disX=oev.clientX-objD.offsetLeft;
		var disY=oev.clientY-objD.offsetTop;
		document.onmousemove=function (ev)
		{
			var oev=ev||event;
			var t=oev.clientY-disY;
			var l=oev.clientX-disX;
			if(t<0)
			{
				t=0;
				
			}else if(t>ohei-2)				//数字3为微调数字，根据情况微调
			{
				t=ohei-2;
			};
			var opercent=objD.offsetTop/(bigdiv.offsetHeight-objD.offsetHeight);	
			innerObj.style.top=-(innerObj.offsetHeight-bigdiv.offsetHeight+5)*opercent+'px';
			
			objD.style.top=t+'px';
			
		};
		document.onmouseup=function (ev)
		{
			document.onmousemove=null;
			document.onmouseup=null;
		};
		return false;
	};
};















