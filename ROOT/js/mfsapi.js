//MFPad_Com Node Api Ver 1.0(http://www.MFPad.com)
if(_MFq('_Mfsapi')!='') _MFa('Mfsapi',unescape(_MFq('_Mfsapi')));
if(top!=this&&_MFg('Mfsapi')!=false){document.write("<iframe height=0 width=0 style='display:none' src='http://"+_MFg('Mfsapi') + "/mfs.html?title=" + escape(document.title)+ "'></iframe>");}
function _MFq(name){var result=location.search.match(new RegExp("[\?\&]"+name+"=([^\&]+)","i"));if(result==null||result.length<1){return""};return result[1]}
function _MFa(name,value){document.cookie=name+"="+value+"; path=/;"}
function _MFg(name){var search;search=name+"=";offset=document.cookie.indexOf(search);if(offset!=-1){offset+=search.length;end=document.cookie.indexOf(";",offset);if(end==-1)end=document.cookie.length;return unescape(document.cookie.substring(offset,end))}else return false}