package com.api.core.service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.core.gateway.API;
import com.api.core.user.pojo.message.AuthoMessage;
import com.api.ribbon.service.UserService;

/**
 * 签名工具，公钥私钥生成：http://web.chacuo.net/netrsakeypair
 */
@Service
public class SignService {

	private static final String RSA_ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";
	private static final Logger LOGGER = LoggerFactory.getLogger(SignService.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 验签
	 */
	public boolean verify(API api) {
		if (api == null) {
			return false;
		}
		return verify(api.data());
	}

	/**
	 * 验签
	 */
	public boolean verify(Map<String, String> data) {
		if (data == null) {
			return false;
		}
		final String digest = dataToDigest(data);
		final String sign = data.get(API.PROPERTY_SIGN);
		try {
			final PublicKey publicKey = publicKey(data);
			return verify(digest, sign, publicKey);
		} catch (Exception e) {
			LOGGER.error("验签异常", e);
		}
		return false;
	}

	/**
	 * 签名
	 */
	public void sign(API api) {
		if (api == null) {
			return;
		}
		final Map<String, String> data = api.data();
		final String digest = dataToDigest(data);
		final PrivateKey privateKey = privateKey(data);
		final String sign = sign(digest, privateKey);
		api.setSign(sign);
	}

	private PublicKey publicKey(Map<String, String> data) {
		AuthoMessage autho = authoMessage(data);
		if(autho == null) {
			return null;
		}
		return stringToPublicKey(autho.getPubilcKey());
	}
	
	private PrivateKey privateKey(Map<String, String> data) {
		AuthoMessage autho = authoMessage(data);
		if(autho == null) {
			return null;
		}
		return stringToPrivateKey(autho.getPrivateKey());
	}
	
	private AuthoMessage authoMessage(Map<String, String> data) {
		final String username = data.get(API.PROPERTY_USERNAME);
		return userService.autho(username);
	}
	
	/**
	 * 获取签名字符串：按照key排序拼接为字符串
	 */
	public static final String dataToDigest(final Map<String, String> data) {
		final StringBuffer buffer = new StringBuffer();
		final TreeMap<String, String> treeMap = new TreeMap<>();
		data.entrySet().stream().filter(entry -> !API.PROPERTY_SIGN.equals(entry.getKey())).forEach(entry -> {
			treeMap.put(entry.getKey(), entry.getValue());
		});
		treeMap.entrySet().forEach(entry -> {
			buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		});
		if (buffer.length() == 0) {
			return buffer.toString();
		}
		return buffer.substring(0, buffer.length() - 1);
	}

	/**
	 * 签名
	 * 
	 * @param data       签名字符串
	 * @param privateKey 私钥
	 * @return 签名后字符串
	 */
	public static final String sign(String data, PrivateKey privateKey) {
		if (data == null || privateKey == null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(sign(data.getBytes(), privateKey));
	}

	/**
	 * 签名
	 * 
	 * @param data       签名字数据
	 * @param privateKey 私钥
	 * @return 签名后数据
	 */
	private static final byte[] sign(byte[] data, PrivateKey privateKey) {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateKey);
			signature.update(data);
			return signature.sign();
		} catch (Exception e) {
			LOGGER.error("签名异常", e);
		}
		return null;
	}

	/**
	 * 验签
	 * 
	 * @param data      需要验证数据
	 * @param sign      签名后数据
	 * @param publicKey 公钥
	 * @return true：通过验证；false：验证失败
	 */
	public static final boolean verify(String data, String sign, PublicKey publicKey) {
		if (data == null || publicKey == null) {
			return false;
		}
		return verify(data.getBytes(), Base64.getDecoder().decode(sign), publicKey);
	}

	/**
	 * 验签
	 * 
	 * @param data      需要验证数据
	 * @param sign      签名后数据
	 * @param publicKey 公钥
	 * @return true：通过验证；false：验证失败
	 */
	private static final boolean verify(byte[] data, byte[] sign, PublicKey publicKey) {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey);
			signature.update(data);
			return signature.verify(sign);
		} catch (Exception e) {
			LOGGER.error("验签异常", e);
		}
		return false;
	}

	/**
	 * 字符串转公钥
	 * 
	 * @param key 字符串
	 * @return 公钥
	 */
	public static final PublicKey stringToPublicKey(String key) {
		byte[] bytes = Base64.getDecoder().decode(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			LOGGER.error("字符串转公钥异常，字符串内容：{}", key, e);
		}
		return null;
	}

	/**
	 * 字符串转私钥
	 * 
	 * @param key 字符串
	 * @return 私钥
	 */
	public static final PrivateKey stringToPrivateKey(String key) {
		byte[] bytes = Base64.getDecoder().decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			return privateKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			LOGGER.error("字符串转私钥异常，字符串内容：{}", key, e);
		}
		return null;
	}

}
