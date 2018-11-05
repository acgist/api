package com.api.test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import com.api.utils.CAUtils;

@SuppressWarnings("deprecation")
public class CAUtilsTest {

	public static void main(String[] args) {
		String cerPath = "e://acgist.cer";
		String pfxPath = "e://acgist.pfx";
		String password = "acgist";
		String alias = "acgist";
		
		CAUtils.create(
			CAUtils.organ("www.acgsit.com", "acgist", "acgist", "GZ", "GD", "CN"),
			CAUtils.organ("acgist.com", "acgist", "acgist", "GZ", "GD", "CN"),
			alias, 10L * 365 * 24 * 60 * 60 * 1000, password, cerPath, pfxPath, "e://acgist.cer", null, null
		);
		
		PublicKey publicKey = CAUtils.loadPublicKey(cerPath);
		PrivateKey privateKey = CAUtils.loadPrivateKey(pfxPath, password);
		String content = "abcdefghijklmnopqrstuvwxyz";
		
//		long begin = System.currentTimeMillis();
//		for (int index = 0; index < 10000; index++) {
//			CAUtils.encrypt(publicKey, content.getBytes());
//		}
//		long end = System.currentTimeMillis();
//		System.out.println(end - begin);
		
		System.out.println("=================================公钥加密私钥解密=================================");
		String encryptContent = CAUtils.encrypt(publicKey, content);
		System.out.println("加密内容：" + content);
		System.out.println("加密后内容：" + encryptContent);
		System.out.println("解密后内容：" + CAUtils.decrypt(privateKey, encryptContent));
		
		// 一般不使用
		System.out.println("=================================私钥加密公钥解密=================================");
		byte[] data = CAUtils.encrypt(privateKey, content.getBytes());
		System.out.println("加密内容：" + content);
		System.out.println("加密后内容：" + Base64.getEncoder().encodeToString(data));
		System.out.println("解密后内容：" + new String(CAUtils.decrypt(publicKey, data)));
		
		System.out.println("=================================私钥签名公钥验签=================================");
		String sign = CAUtils.sign(content, privateKey);
		System.out.println("签名字符串：" + content);
		System.out.println("签名后字符串：" + sign);
		System.out.println("是否通过验签：" + CAUtils.verify(content, sign, publicKey));
		
		System.out.println("=================================字符串和公私钥转换=================================");
		String publicString, privateString;
		System.out.println(publicString = CAUtils.keyToString(publicKey));
		System.out.println(privateString = CAUtils.keyToString(privateKey));
		publicKey = CAUtils.stringToPublicKey(publicString);
		privateKey = CAUtils.stringToPrivateKey(privateString);
		String signValid = CAUtils.sign(content, privateKey);
		System.out.println("签名字符串：" + content);
		System.out.println("签名后字符串：" + sign);
		System.out.println("是否通过验签：" + CAUtils.verify(content, sign, publicKey));
		System.out.println("两次签名结果是否一致：" + signValid.equals(sign));
	}
	
}
