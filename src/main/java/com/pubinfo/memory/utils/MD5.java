package com.pubinfo.memory.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String encoding(String string) {
		
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("md5");
			byte[]md5 = mDigest.digest(string.getBytes());
			BigInteger bigInteger = new BigInteger(1,md5);
			String mdString = bigInteger.toString(16);
			return mdString;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	 public static void main(String[] args) {
		MD5 md5 = new MD5();
	 System.out.println(md5.encoding("123"));
	}
}
