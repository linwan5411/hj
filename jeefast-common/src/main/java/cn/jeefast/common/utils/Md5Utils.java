package cn.jeefast.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;

/**
 * md5加密工具
 */
public class Md5Utils {


	/**
	 * 对字符串进行加密 base64
	 * @param value 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encodeBas64(String value) {
		return new BASE64Encoder().encode(value.getBytes());
	}

	/**
	 * 对加密后的字符串进行解密 base64
	 * @param value 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String decodeBase64(String value) {
		String result = null;
		try {
			result = new String(new BASE64Decoder().decodeBuffer(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/***
	 * Md5加密采用的是32位进行加密
	 * @param inStr
	 * @return
	 */
	public static String encodeMD5(String inStr) {
		StringBuffer buf = new StringBuffer("");
		try {
			//生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			//使用指定的字节数组更新摘要。
			md.update(inStr.getBytes());
			//通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			//生成具体的md5密码到buf数组
			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString ().toLowerCase();
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @param path
	 * @return
	 */
	public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}


	/**
	 * 多平台对称解密机制
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String message, String key) throws Exception {
		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	/**
	 * 多平台 Java  .net 对称加密机制
	 * @param key
	 * @param iv
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(byte[] key, byte[] iv, byte[] data) throws Exception {
		Key deskey = null;
		DESKeySpec spec = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		deskey = keyFactory.generateSecret(spec);
		IvParameterSpec ips = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		return toHexString(cipher.doFinal(data, 0, data.length));
	}

	public static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}
	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}

}
