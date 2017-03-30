package com.friday.pro.core.web.publics;

public class CapitalToChinese {
	public static void main(String[] args) {
		long d=convert("一百三十四亿一千零二十三万六千六百零九");
		System.out.println(d);
		
		System.out.println(translate("100100010"));
	}
	
	/** 
	 * 阿拉伯数字转换为汉字
	 * @param 100100010
	 * @return 最终应该转为这样：一亿零一十万零一十
	 * @author lxie
	 **/
	public static String translate(String input) {
        String[] num=new String[] {"零","一","二","三","四","五","六","七","八","九"};
        String[] unit=new String[]{"","十","百","千","万","亿"};
        String[] mid=new String[input.length()];
        String output="";
        
        //转换数字
        for (int i=0; i<input.length(); i++) {
                mid[i]=num[Integer.parseInt(""+input.charAt(i))];
                output+=mid[i];
        }
        
        //加入单位
        String str="";
        String result="";
        for (int i=0; i<output.length(); i++) {
                if (output.length()-i-1==0) {
                        str=""+output.charAt(i);
                }
                else if ((output.length()-i-1+4)%8==0) {
                        str=output.charAt(i)+unit[4];
                }
                else if ((output.length()-i-1)%8==0) {
                        str=output.charAt(i)+unit[5];
                }
                else {
                        str=output.charAt(i)+unit[(output.length()-i-1)%4];
                }
                result+=str;
        }

        //格式化成中文习惯表达
        result=result.replaceAll("零[千百十]", "零");
        result=result.replaceAll("亿零+万", "亿零");
        result=result.replaceAll("万零+亿", "万亿");
        result=result.replaceAll("零+", "零");
        result=result.replaceAll("零万", "万");
        result=result.replaceAll("零亿", "亿");
        result=result.replaceAll("^一十", "十");
        result=result.replaceAll("零$", "");
        return result;
	}

	/** 
	 * 汉字转换为阿拉伯数字
	 * @param 一百三十四亿一千零二十三万六千六百零九
	 * @return 最终应该转为这样：13410236609
	 * @author lxie
	 **/
	public static long convert(String s){
		String [] s1=s.split("亿");
		long num=restore(s1[0])  *100000000L;
		
		
		String [] s2=s1[1].split("万");
		num=restore(s2[0])  *10000L +num;
		
		num=num+restore(s2[1]);
		
		return num;
	}
	

	public static int  convertNumber(char c){
		int num=0;
		switch(c){
		case '零': num= 0;  break;
		case '一': num= 1;  break; 
		case '二': num= 2;  break; 
		case '三': num= 3;  break;
		case '四': num= 4;  break;
		case '五': num= 5;  break;
		case '六': num= 6;  break;
		case '七': num= 7;  break;
		case '八': num= 8;  break;
		case '九': num= 9;  break;
		}
		return num;
	}
	
	//  比如  一千三百五十1  还原为1351
	public static int restore(String s){
		char [] array=s.toCharArray();
		int num=0;
		
		for (int i=0;  i<array.length-1;  i++){
			if (array[i] != '零'  &&  array[i+1]=='千'  ){
				num=convertNumber( array[i] )*1000;
			}else 
			if (array[i] != '零'  &&  array[i+1]=='百'  ){
				num=num + convertNumber( array[i] )*100;
			}else 
			if (array[i] != '零'  &&  array[i+1]=='十'  ){
				num=num + convertNumber( array[i] )*10;
			}
		}
		return num+convertNumber(array[array.length-1]);
		
	}
	
	public static String convertWord(int num){
		String word = "Z";
		switch(num){
			case 1: word = "A";  break; 
			case 2: word = "B";  break; 
			case 3: word = "C";  break;
			case 4: word = "D";  break;
			case 5: word = "E";  break;
			case 6: word = "F";  break;
			case 7: word = "G";  break;
			case 8: word = "H";  break;
			case 9: word = "I";  break;
		}
		return word;
	}
	
}