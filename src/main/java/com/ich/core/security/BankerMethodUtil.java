package com.ich.core.security;

import java.math.BigDecimal;

/**
 * 银行家算法单元
 * @author 霍俊
 *
 */
public class BankerMethodUtil {
	
	
	public static BigDecimal method(BigDecimal val){
		return method(val,2);
	}
	
	//55221.12548|5|11
	public static BigDecimal method(BigDecimal val,int precision){
		String result = val.toPlainString();
		int point_position = result.indexOf(".");
		if(point_position == -1) return val;//没有小数位，无需做精确处理
		if((point_position + precision + 1) >= result.length())return val;//位数不够，无需做精确处理
		if(precision < 2) precision = 2;//精度
		String arrs[] = result.split("\\.");
		char carrs[] = arrs[1].toCharArray();
		int methodVal = carrs[precision]-48;
		int attaVal = carrs[precision-1]-48;
		int addVal = bankerMethod(methodVal,attaVal);
		BigDecimal decimal = new BigDecimal(addVal);
		for(int i=1;i<=precision;i++){
			decimal = decimal.multiply(new BigDecimal("0.1"));
		}
		return new BigDecimal(result.substring(0,point_position+precision+1)).add(decimal);
	}
	
	private static int bankerMethod(int methodVal, int attaVal) {
		if(methodVal>=6) return 1;
		if(methodVal<=4) return 0;
		if(methodVal==5){
			if(attaVal%2==0){
				 return 0;
			}else{
				 return 1;
			}
		}
		return 0;
	}



	public static void main(String[] args) {
		for(int i = 0;i<100;i++){
			System.out.print("2.9"+i+"-->");
			System.out.println(method(new BigDecimal("2.9"+i+"")));
		}
		//156   1514.76	9.7100	170601012=胜@1.450元,170601009=平@2.850元,170601010=胜@2.350元
		//20	100.70	5.0300	170603010(主-1)=负@1.420元,170603016=胜@1.450元,170603017=胜@1.620元,170603013=负@1.510元
//		method(new BigDecimal("2.335"));
//		method(new BigDecimal("2.345"));
//		method(new BigDecimal("2.364"));
//		method(new BigDecimal("2.366"));
//		method(new BigDecimal("2.367"));
//		method(new BigDecimal("2.3655"));
	}

}
