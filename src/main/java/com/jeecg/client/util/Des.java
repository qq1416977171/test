package com.jeecg.client.util;

import com.alibaba.druid.filter.config.ConfigTools;

public class Des {
    //加密  
	 //加密  
    public static String Encrypt(String str){  
        String encryptPwd = "";
        try {
            encryptPwd = ConfigTools.encrypt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  encryptPwd;  
    }  
      
    //解密  
    public static String Decrypt(String str){
        String decryptPwd = "";
        try {
            decryptPwd = ConfigTools.decrypt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptPwd);  
    }  
    
    public static int getNumber(){
    	int i = (int)(Math.random()*900)+100;
    	return i;
    }
    
    
    public static String jiami(String password){
    	String no1 = String.valueOf(getNumber());
    	String no2 = String.valueOf(getNumber());
    	password = no1+password;
    	password = Des.Encrypt(password);
    	return no2+password;
    }
    public static String jiemi(String password){
    	password = password.substring(3);
    	
    	password = Des.Decrypt(password);
    	
    	return  password.substring(3);
    }
    
    public static void main(String args[]){
    	
    	String a = jiami("123456");
    	System.out.println(a);
    	String b = jiemi("613PSLBA5XUywyde+sAq1QzMecz/vZ5MbhcsQ3yPnqzFoqewh6bZJ6nlYdhE4z/UBfc+XjxsFoINvq3ntm79HWDLw==");
    	System.out.println(b);
//    	String no1 = String.valueOf(getNumber());
//    	String no2 = String.valueOf(getNumber());
//    	String pass = Des.Encrypt(no1+"cyhlwzx_123456");
//    	String endpass = no2+pass;
//    	System.out.println("enPass:"+endpass);
//    	System.out.println("enPass:"+endpass);
//    	String subString = endpass.substring(3);
//    	System.out.println(subString);
//    	
//    	System.out.println(Des.Decrypt(subString));
//    	System.out.println("abcdefg".substring(3));
    	//String password = "QxxjEIKRNazd3yNEoKEHnJT/VpLh/i3nECSPiA/wi+N2vOXYph8nEObA8N5fywASYjBrsRdtwEGOuNAicQVmSw==";
    	//System.out.println(Des.Decrypt(password));
    }
}
